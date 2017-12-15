package com.surenpi.jenkins.client.job;

import com.surenpi.jenkins.client.Jenkins;
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
    public static void init() throws URISyntaxException {
        jobs = new Jenkins(
                new URI("http://localhost:8080/jenkins"), "admin", "admin").getJobs();
    }

    @Test
    public void create() throws IOException {
        jobs.setCrumb(true);
        jobs.create(jobName + "-" + System.currentTimeMillis(), JOB_XML);
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