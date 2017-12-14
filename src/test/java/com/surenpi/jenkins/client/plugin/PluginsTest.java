package com.surenpi.jenkins.client.plugin;

import com.surenpi.jenkins.client.Jenkins;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author suren
 */
public class PluginsTest
{
    private static Plugins plugins;
    private final String jobName = "hello";

    @BeforeClass
    public static void init() throws URISyntaxException
    {
        plugins = new Jenkins(
                new URI("http://localhost:8080/jenkins"), "admin", "admin").getPlugins();
    }

    @Test
    public void getPluginManager() throws IOException
    {
        PluginManager mgr = plugins.getPluginManager();
        Assert.assertNotNull(mgr);

        Assert.assertNotNull(mgr.getPlugins());
        Assert.assertTrue(mgr.getPlugins().size() > 0);

        for(Plugin plugin : mgr.getPlugins())
        {
            System.out.println(plugin.getShortName() + "==" + plugin.getUrl());
        }
    }
}