package com.surenpi.jenkins.client.computer;

import com.surenpi.jenkins.client.Jenkins;
import hudson.model.User;
import hudson.security.SecurityRealm;
import jenkins.security.ApiTokenProperty;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;

public class LabelsTest
{
    private static Labels labels;
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
        labels = new Jenkins(new URI(j.jenkins.getRootUrl()), user.getId(), token).getLabels();
    }

    @Test
    public void test() throws IOException
    {
        LabelDetail labelsArray = labels.getLabels();
        System.out.println(labelsArray);
    }
}