package com.surenpi.jenkins.client.workflow;

import com.surenpi.jenkins.client.BaseManager;
import com.surenpi.jenkins.client.JenkinsClient;
import com.surenpi.jenkins.client.job.BuildDetail;
import com.surenpi.jenkins.client.job.Jobs;
import com.surenpi.jenkins.client.util.EncodingUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;

import java.io.IOException;

/**
 * Pipeline module api.
 * @author suren
 */
public class Workflows extends BaseManager
{

    /**
     * wfapi describe
     * @param jobName
     * @param buildNo
     */
    public WfWithDetails getWfDescribe(String jobName, int buildNo) throws IOException
    {
        String path = "/";
        try {
            JenkinsClient client = getClient();

            WfWithDetails wfWithDetails = getClient().get(path + "job/" + EncodingUtils.encode(jobName) + "/" + buildNo + "/wfapi/describe", WfWithDetails.class);
            wfWithDetails.setClient(client);
            setBuildingInfo(wfWithDetails, jobName);

            return wfWithDetails;
        } catch (HttpResponseException e) {
//            LOGGER.debug("getWfDescribe(jobName={}) status={}", jobName, e.getStatusCode());
            if (e.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                return null;
            }
            throw e;
        }
    }

    public Stage getWfNodeDescribe(String jobName, int buildNo, int stageId) throws IOException {
        String path = "/";
        try {
            JenkinsClient client = getClient();

            Stage stage = client.get(path + "job/" + EncodingUtils.encode(jobName) + "/" + buildNo + "/execution/node/" + stageId + "/wfapi/describe", Stage.class);
            if (null != stage && CollectionUtils.isNotEmpty(stage.getStageFlowNodes())) {
                stage.setClient(client);
                for (StageFlowNodes stageFlowNode : stage.getStageFlowNodes()) {
                    int nodeId = stageFlowNode.getId();
                    StageFlowNodesLog log = client.get(path + "job/" + EncodingUtils.encode(jobName) + "/" + buildNo + "/execution/node/" + nodeId + "/wfapi/log", StageFlowNodesLog.class);
                    log.setClient(client);
                    stageFlowNode.setLog(log);
                }
            }

            return stage;
        } catch (HttpResponseException e) {
//            LOGGER.debug("getWfDescribe(jobName={}) status={}", jobName, e.getStatusCode());
            if (e.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                return null;
            }
            throw e;
        }
    }

    /**
     * Fetch last running status of Pipeline
     * @param jobName
     * @return
     * @throws IOException
     */
    public WfWithDetails last(String jobName) throws IOException
    {
        String url = "/job/" + EncodingUtils.encode(jobName) + "/lastBuild/wfapi/describe";
        WfWithDetails details = getClient().get(url, WfWithDetails.class);
        setBuildingInfo(details, jobName);

        return details;
    }

    private void setBuildingInfo(WfWithDetails details, String jobName) throws IOException
    {
        String buildId = details.getId();
        Jobs jobs = new Jobs();
        jobs.setClient(getClient());

        BuildDetail buildDetail = jobs.getBuildDetails(jobName, Integer.parseInt(buildId));
        boolean building = buildDetail.isBuilding();
        details.setBuilding(building);
    }

    @Override
    protected String[] getDependencyArray()
    {
        return new String[]{"pipeline-rest-api"};
    }
}