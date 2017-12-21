package com.surenpi.jenkins.client.job;

import com.surenpi.jenkins.client.Jenkins;
import com.surenpi.jenkins.client.core.JenkinsInfo;
import com.surenpi.jenkins.client.folder.FolderJob;
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
public class JobsTest {
    private static Jobs jobs;
    private final String jobName = "hello";

    @BeforeClass
    public static void init() throws URISyntaxException
    {
        jobs = new Jenkins(
                new URI("http://localhost:8080/jenkins"), "admin", "admin").getJobs();
    }

    @Test
    public void create() throws IOException
    {
        jobs.create(jobName + "-" + System.currentTimeMillis(), JOB_XML);

        jobs.create(new FolderJob("folder"), jobName + "-" + System.currentTimeMillis(), JOB_XML);

        jobs.create(new FolderJob("folder-" + System.currentTimeMillis()),
                jobName + "-" + System.currentTimeMillis(), JOB_XML, true, true);
    }

    @Test
    public void build() throws IOException
    {
        jobs.build("common");
    }

    @Test
    public void buildWithParams() throws IOException
    {
        Map<String, String> params = Collections.singletonMap("a", "a");
        jobs.buildWithParams("free", params);
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
        BuildDetail buildDetails = jobs.getLastBuildDetails("common-devops-server");
        System.out.println(buildDetails);
    }

    @Test
    public void getXml() throws IOException
    {
        String xml = jobs.getXml("common");
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