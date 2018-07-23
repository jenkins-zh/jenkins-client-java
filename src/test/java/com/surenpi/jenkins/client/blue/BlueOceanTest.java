package com.surenpi.jenkins.client.blue;

import com.surenpi.jenkins.client.Jenkins;
import com.surenpi.jenkins.client.core.Step;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class BlueOceanTest {
    private static BlueOcean blue;

    @BeforeClass
    public static void init() throws URISyntaxException
    {
        blue = new Jenkins(
                new URI("http://localhost:30251/"),
                "admin",
                "c6fc6a59033d1144fec5a565cb2b6796").getBlueOcean();
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
