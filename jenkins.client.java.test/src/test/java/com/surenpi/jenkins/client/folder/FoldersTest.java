package com.surenpi.jenkins.client.folder;

import com.surenpi.jenkins.client.Jenkins;
import hudson.model.User;
import hudson.security.SecurityRealm;
import jenkins.security.ApiTokenProperty;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;

public class FoldersTest
{
    private static Folders folders;
    private final String folderName = "hello";
    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Before
    public void init() throws URISyntaxException
    {
        User user = User.getById("admin", true);

        assertNotNull(user);

        String token = ((ApiTokenProperty) user.getProperty(ApiTokenProperty.class)).getApiToken();

        assertNotNull(j.jenkins.getRootUrl());

        j.jenkins.setSecurityRealm(SecurityRealm.NO_AUTHENTICATION);
        folders = new Jenkins(new URI(j.jenkins.getRootUrl()), user.getId(), token).getFolders();
    }

    @Test
    public void create() throws IOException
    {
        folders.create(folderName);

        assertNotNull(j.jenkins.getItem(folderName));
    }

    @Test
    public void getXml() throws IOException
    {
        folders.create(folderName);
        String xml = folders.getXml(folderName);
        assertNotNull(xml);
    }
}