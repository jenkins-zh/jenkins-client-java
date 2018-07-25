package com.surenpi.jenkins.client.plugin;

import com.surenpi.jenkins.client.Jenkins;
import hudson.PluginWrapper;
import hudson.model.User;
import hudson.security.SecurityRealm;
import jenkins.security.ApiTokenProperty;
import org.junit.*;
import org.jvnet.hudson.test.JenkinsRule;
import test.WithPlugins;

import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertNotNull;

/**
 * @author suren
 */
public class PluginsTest
{
    @Rule
    public JenkinsRule j = new JenkinsRule();
    private static Plugins plugins;

    @Before
    public void setup() throws Exception {
        User user = User.getById("admin", true);

        assertNotNull(user);

        String token = ((ApiTokenProperty) user.getProperty(ApiTokenProperty.class)).getApiToken();

        assertNotNull(j.jenkins.getRootUrl());

        j.jenkins.setSecurityRealm(SecurityRealm.NO_AUTHENTICATION);
        plugins = new Jenkins(new URI(j.jenkins.getRootUrl()), user.getId(), token).getPlugins();
    }

    @Test
    @WithPlugins("io.alauda.jenkins.plugins:alauda-devops-sync:0.2.6-SNAPSHOT")
    public void getPluginManager() throws IOException
    {
        PluginManager mgr = plugins.getPluginManager();
        Assert.assertNotNull(mgr);
        Assert.assertNotNull(mgr.getPlugins());
        Assert.assertEquals(0, mgr.getPlugins().size());
        PluginWrapper plugin = j.getPluginManager().getPlugin("alauda-devops-sync");
        System.out.println(plugin);
    }
}