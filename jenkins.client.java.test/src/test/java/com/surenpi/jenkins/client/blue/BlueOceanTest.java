package com.surenpi.jenkins.client.blue;

import com.surenpi.jenkins.client.Jenkins;
import com.surenpi.jenkins.client.core.Step;
import hudson.model.User;
import hudson.security.SecurityRealm;
import jenkins.security.ApiTokenProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class BlueOceanTest {
    private static BlueOcean blue;
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
        blue = new Jenkins(new URI(j.jenkins.getRootUrl()), user.getId(), token).getBlueOcean();
    }

    @Test
    public void steps() throws IOException {
//        /blue/rest/organizations/jenkins/pipelines/param/pipelines/param-p1-j1/runs/4/steps/
        String folder = "param";
        String jobName = "param-p1-j1";
        String buildId = "4";

        List<Step> steps = blue.steps(folder, jobName, buildId);

        Assert.assertNotNull(steps);
    }
}
