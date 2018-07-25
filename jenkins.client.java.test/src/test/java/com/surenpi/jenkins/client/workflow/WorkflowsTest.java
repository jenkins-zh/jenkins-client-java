package com.surenpi.jenkins.client.workflow;

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

/**
 * @author suren
 */
public class WorkflowsTest
{
    private static Workflows workflows;
    private final String jobName = "pipeline";
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
        workflows = new Jenkins(new URI(j.jenkins.getRootUrl()), user.getId(), token).getWorkflows();
    }

    @Test
    public void create() throws IOException
    {
        WfWithDetails wfDesc = workflows.getWfDescribe("common-1", 1);
        System.out.println(wfDesc);

        Stage a = workflows.getWfNodeDescribe("common-1", 1, 6);
        System.out.println(a);
    }

    @Test
    public void last() throws IOException
    {
        WfWithDetails desc = workflows.last("common");

        System.out.println(desc.getStages().get(0).getStatus());
        System.out.println(desc.isBuilding());
    }

    @Test
    public void getWfDescribe() throws IOException
    {
        WfWithDetails desc = workflows.getWfDescribe(jobName, 6);
        System.out.println(desc);
    }

    @Test
    public void restart() throws IOException {
        workflows.restart(jobName, 6, "stage1");
    }
}