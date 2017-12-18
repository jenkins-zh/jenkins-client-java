package com.surenpi.jenkins.client.workflow;

import com.surenpi.jenkins.client.ConstantsForTest;
import com.surenpi.jenkins.client.Jenkins;
import com.surenpi.jenkins.client.core.JenkinsInfo;
import com.surenpi.jenkins.client.folder.FolderJob;
import com.surenpi.jenkins.client.job.BuildDetail;
import com.surenpi.jenkins.client.job.JobDetails;
import com.surenpi.jenkins.client.job.Jobs;
import org.apache.http.client.HttpResponseException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

/**
 * @author suren
 */
public class WorkflowTest
{
    private static Workflows workflows;
    private final String jobName = "hello";

    @BeforeClass
    public static void init() throws URISyntaxException
    {
        workflows = new Jenkins(
                new URI(ConstantsForTest.JENKINS_URL),
                ConstantsForTest.JENKINS_USER,
                ConstantsForTest.JENKINS_PASSWD).getWorkflows();
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
        WfWithDetails desc = workflows.last("common-1");

        System.out.println(desc);
        System.out.println(desc.isBuilding());
    }
}