package test;

import org.apache.commons.io.FileUtils;
import org.jvnet.hudson.test.HudsonTestCase;
import org.jvnet.hudson.test.JenkinsRecipe;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.Recipe;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.net.URL;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Recipe(WithPlugins.RunnerImpl.class)
@JenkinsRecipe(WithPlugins.RuleRunnerImpl.class)
@Target(METHOD)
@Retention(RUNTIME)
public @interface WithPlugins {
    /**
     * Name of the plugins.
     *
     * For now, this has to be one or more of the plugins statically available in resources
     * "/plugins/NAME". TODO: support retrieval through Maven repository.
     * TODO: load the HPI file from $M2_REPO or $USER_HOME/.m2 by naming e.g. org.jvnet.hudson.plugins:monitoring:hpi:1.34.0
     * (used in conjunction with the depepdency in POM to ensure it's available)
     */
    String[] value();

    class RunnerImpl extends Recipe.Runner<WithPlugins> {
        private WithPlugins a;

        @Override
        public void setup(HudsonTestCase testCase, WithPlugins recipe) throws Exception {
            a = recipe;
            testCase.useLocalPluginManager = true;
        }

        @Override
        public void decorateHome(HudsonTestCase testCase, File home) throws Exception {
            for (String plugin : a.value()) {
                String[] pom = plugin.split(":");
                if(pom.length >= 2) {
                    loadFromMaven(pom, home);
                } else {
                    URL res = getClass().getClassLoader().getResource("plugins/" + plugin);
                    FileUtils.copyURLToFile(res, new File(home, "plugins/" + plugin));
                }
            }
        }

        private void loadFromMaven(String[] pom, File home) {
            final String groupId = pom[0];
            final String artifactId = pom[1];
            final String version = pom[2];

            final String plugin = artifactId + "-" + version + ".hpi";

            String mvnHome = System.getenv("HOME");
            File pluginFile = new File(mvnHome, ".m2/repository/"
                    + groupId + "/" + artifactId + "/" + version
                    + "/" + plugin);
            if(pluginFile.isFile()) {
                try {
                    FileUtils.copyURLToFile(pluginFile.toURL(), new File(home, "plugins/" + plugin));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class RuleRunnerImpl extends JenkinsRecipe.Runner<WithPlugins> {
        private WithPlugins a;

        @Override
        public void setup(JenkinsRule jenkinsRule, WithPlugins recipe) throws Exception {
            a = recipe;
            jenkinsRule.useLocalPluginManager = true;
        }

        @Override
        public void decorateHome(JenkinsRule jenkinsRule, File home) throws Exception {
            for (String plugin : a.value()) {
                String[] pom = plugin.split(":");
                if(pom.length >= 2) {
                    loadFromMaven(pom, home);
                } else {
                    URL res = getClass().getClassLoader().getResource("plugins/" + plugin);
                    FileUtils.copyURLToFile(res, new File(home, "plugins/" + plugin));
                }
            }
        }

        private void loadFromMaven(String[] pom, File home) {
            final String groupId = pom[0];
            final String artifactId = pom[1];
            final String version = pom[2];

            final String plugin = artifactId + "-" + version + ".hpi";

            String mvnHome = System.getenv("HOME");
            File pluginFile = new File(mvnHome, ".m2/repository/"
                    + groupId + "/" + artifactId + "/" + version
                    + "/" + plugin);
            if(pluginFile.isFile()) {
                try {
                    FileUtils.copyURLToFile(pluginFile.toURL(), new File(home, "plugins/" + plugin));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
