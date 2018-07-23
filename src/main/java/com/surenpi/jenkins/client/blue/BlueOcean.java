package com.surenpi.jenkins.client.blue;

import com.surenpi.jenkins.client.BaseManager;
import com.surenpi.jenkins.client.core.Step;

import java.io.IOException;
import java.util.List;

public class BlueOcean extends BaseManager {
    public static final String baseUrl = "/blue/rest/organizations/jenkins/pipelines";

    public String log(String folder, String jobName, String buildId, String stepId) {
//        /blue/rest/organizations/jenkins/pipelines/param/pipelines/param-p1-j1/runs/4/steps/5/log

//        /blue/rest/organizations/jenkins/pipelines/param/pipelines/param-p1-j1/runs/4/steps/
        return null;
    }

    public List<Step> steps(String folder, String jobName, String buildId) throws IOException {
        StringBuffer buf = new StringBuffer(baseUrl);

        buf.append("/").append(folder);
        buf.append("/").append("pipelines/").append(jobName);
        buf.append("/").append("runs");
        buf.append("/").append(buildId).append("/steps");

        return getClient().getList(buf.toString(), Step.class);
    }

    @Override
    protected String[] getDependencyArray() {
        return new String[0];
    }
}
