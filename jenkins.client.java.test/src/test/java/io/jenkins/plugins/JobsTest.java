package io.jenkins.plugins;

import com.surenpi.jenkins.client.Jenkins;
import com.surenpi.jenkins.client.core.JenkinsInfo;
import com.surenpi.jenkins.client.folder.FolderJob;
import com.surenpi.jenkins.client.job.BuildDetail;
import com.surenpi.jenkins.client.job.Job;
import com.surenpi.jenkins.client.job.JobDetails;
import com.surenpi.jenkins.client.job.Jobs;
import hudson.matrix.MatrixProject;
import hudson.model.*;
import hudson.security.SecurityRealm;
import hudson.tasks.Shell;
import jenkins.security.ApiTokenProperty;
import org.apache.http.client.HttpResponseException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.recipes.WithPlugin;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * @author suren
 */
public class JobsTest {
    private static Jobs jobs;
    private final String jobName = "hello";
    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Before
    public void setup() throws Exception {
        User user = User.getById("admin", true);

        assertNotNull(user);

        String token = ((ApiTokenProperty) user.getProperty(ApiTokenProperty.class)).getApiToken();

        assertNotNull(j.jenkins.getRootUrl());

        j.jenkins.setSecurityRealm(SecurityRealm.NO_AUTHENTICATION);
        jobs = new Jenkins(new URI(j.jenkins.getRootUrl()), user.getId(), token).getJobs();
    }

    @Test
    public void list() throws IOException {
        List<Job> list = jobs.getAllJobs();
        assertNotNull(list);
        assertEquals(0, list.size());

        j.createFreeStyleProject();
        list = jobs.getAllJobs();
        assertEquals(1, list.size());
    }

    @Test
//    @WithPlugin("cloudbees-folder")
    public void create() throws IOException {
        jobs.create(jobName, JOB_XML);
        assertNotNull(j.jenkins.getItem(jobName));

//        final String folder = "folder";
//        jobs.create(new FolderJob(folder), jobName, JOB_XML, true, true);
//        assertNotNull(j.jenkins.getItem(folder));
    }

    @Test
    @Ignore
    public void build() throws Exception
    {
        FreeStyleProject project = j.createFreeStyleProject();

        jobs.build(project.getName());
        j.waitUntilNoActivity();
        assertEquals(1, project.getBuilders().size());
    }

    @Test
    public void getLogText() throws IOException, ExecutionException, InterruptedException {
        FreeStyleProject project = j.createFreeStyleProject();
        FreeStyleBuild build = project.scheduleBuild2(0).waitForStart();
        j.waitForCompletion(build);

        String log = jobs.getLogText(project.getName(), 1);
        assertNotNull(log);
        assertThat(log, containsString("Finished:"));

        project.getBuildersList().add(new Shell("echo hello"));
        build = project.scheduleBuild2(0).waitForStart();
        j.waitForCompletion(build);
        log = jobs.getLogText(project.getName(), 2);
        assertThat(log, containsString("hello"));
    }

    @Test
    public void stop() throws Exception {
        FreeStyleProject project = j.createFreeStyleProject();

        project.getBuildersList().add(new Shell("sleep 160"));
        project.scheduleBuild2(0).waitForStart();

        assertTrue(project.isBuilding());
        jobs.stop(project.getName(), 1);
        j.waitUntilNoActivityUpTo(6000);
        assertFalse(project.isBuilding());
    }

    @Test
    public void buildWithParams() throws Exception {
        MatrixProject project = j.createProject(MatrixProject.class);

        final String name = "NAME";
        project.addProperty(new ParametersDefinitionProperty(new StringParameterDefinition(name, "")));

        project.getBuildersList().add(new Shell("echo env." + name));

        final Map<String, String> params = Collections.singletonMap(name, System.currentTimeMillis() + "-hello");
        jobs.buildWithParams(project.getName(), params);
        j.waitUntilNoActivity();
        assertEquals(1, project.getBuilds().size());
        assertThat(project.getBuild("1").getLog(), containsString(params.get(name)));
    }

    @Test
    public void batchDel() throws IOException
    {
        jobs.batchDel(jobName);
    }

    @Test
    public void jenkinsInfo() throws IOException
    {
        JenkinsInfo info = jobs.getAll();
        System.out.println(info);
    }

    @Test
    public void getDetails() throws IOException
    {
        JobDetails details = jobs.getDetails("common-devops-server");
        System.out.println(details);
    }

    @Test(expected = HttpResponseException.class)
    public void getNotExistsJob() throws IOException
    {
        jobs.getDetails(String.valueOf(System.currentTimeMillis()));
    }

    @Test
    public void getBuildDetails() throws IOException
    {
        BuildDetail buildDetails = jobs.getBuildDetails("common-devops-server", 14);
        System.out.println(buildDetails);
    }

    @Test
    public void getLastBuildDetails() throws IOException
    {
        BuildDetail buildDetails = jobs.getLastBuildDetails("common");
        System.out.println(buildDetails);
    }

    @Test
    public void getXml() throws IOException
    {
        String xml = jobs.getXml("pipeline-test");
        System.out.println(xml);
    }

    public static final String JOB_XML = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<project>\n" +
            "  <description></description>\n" +
            "  <keepDependencies>false</keepDependencies>\n" +
            "  <properties/>\n" +
            "  <scm class=\"hudson.scm.NullSCM\"/>\n" +
            "  <canRoam>true</canRoam>\n" +
            "  <disabled>false</disabled>\n" +
            "  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>\n" +
            "  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>\n" +
            "  <triggers/>\n" +
            "  <concurrentBuild>false</concurrentBuild>\n" +
            "  <builders/>\n" +
            "  <publishers/>\n" +
            "  <buildWrappers/>\n" +
            "</project>\n";
}