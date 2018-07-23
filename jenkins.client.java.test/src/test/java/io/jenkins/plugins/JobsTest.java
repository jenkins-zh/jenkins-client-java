package io.jenkins.plugins;

import com.surenpi.jenkins.client.Jenkins;
import com.surenpi.jenkins.client.core.JenkinsInfo;
import com.surenpi.jenkins.client.folder.FolderJob;
import com.surenpi.jenkins.client.job.BuildDetail;
import com.surenpi.jenkins.client.job.Job;
import com.surenpi.jenkins.client.job.JobDetails;
import com.surenpi.jenkins.client.job.Jobs;
import hudson.model.FreeStyleProject;
import hudson.model.User;
import hudson.security.SecurityRealm;
import jenkins.security.ApiTokenProperty;
import org.apache.http.client.HttpResponseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author suren
 */
public class JobsTest {
    private static Jobs jobs;
    private final String jobName = "hello";
    @Rule
    public JenkinsRule j = new JenkinsRule();

//    @BeforeClass
//    public static void init() throws URISyntaxException
//    {
//
//        jobs = new Jenkins(
//                new URI("http://localhost:8080/"),
//                "admin",
//                "11d732ac24c507d79abad6405b27a10f3a").getJobs();
//    }

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
    public void list() throws IOException
    {
        List<Job> list = jobs.getAllJobs();
        assertNotNull(list);
        assertEquals(0, list.size());

        j.createFreeStyleProject();
        list = jobs.getAllJobs();
        assertEquals(1, list.size());
    }

    @Test
    public void create() throws IOException
    {
        jobs.create(jobName, JOB_XML);

        assertNotNull(j.jenkins.getItem(jobName));

//        jobs.create(new FolderJob("folder"), jobName + "-" + System.currentTimeMillis(), JOB_XML);
//
//        jobs.create(new FolderJob("folder-" + System.currentTimeMillis()),
//                jobName + "-" + System.currentTimeMillis(), JOB_XML, true, true);
    }

    @Test
    public void build() throws Exception
    {
        FreeStyleProject project = j.createFreeStyleProject();

        jobs.build(project.getName());
        j.waitUntilNoActivity();
        assertEquals(1, project.getBuilders().size());
    }

    @Test
    public void getLogText() throws IOException
    {
        jobs.getLogText("common", 1);
//        http://localhost:8080/job/pipeline/11/logText/progressiveText?start
    }

    @Test
    public void stop() throws IOException
    {
        jobs.stop("common", 21);
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