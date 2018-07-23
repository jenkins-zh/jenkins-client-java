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
import java.util.HashMap;
import java.util.Map;

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

    public void restart(String jobName, int buildNum, String stage) throws IOException {
        String url = "/job/" + EncodingUtils.encode(jobName) + "/" + buildNum + "/restart/restart/";

        Map<String, Object> data = new HashMap<>();
        data.put("stageName", stage);

        //curl 'http://localhost:8080/job/pipeline/8/restart/restart' -H 'Connection: keep-alive' -H 'Pragma: no-cache' -H 'Cache-Control: no-cache' -H 'Origin: http://localhost:8080' -H 'Upgrade-Insecure-Requests: 1' -H 'Content-Type: application/x-www-form-urlencoded' -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8' -H 'Referer: http://localhost:8080/job/pipeline/8/restart/' -H 'Accept-Encoding: gzip, deflate, br' -H 'Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7' -H 'Cookie: lang=zh-CN; JSESSIONID.503dec74=node0ebhr3ydc10a44mv7kzvfqk8t0.node0; JSESSIONID.26fd2d46=node01vrh1uwkj471x19i4g7x3u52zs7.node0; JSESSIONID.38ec8cc2=node0nmj38aq6ofbg7c66w35jl9781.node0; JSESSIONID.5e1a90bd=node0gpra5jlmwpzqqhyuunybuap80.node0; JSESSIONID=7436EF5B468E2F6FC952AC92D46423AC; screenResolution=1280x800; hudson_auto_refresh=false'
        //
        //
        // --data 'stageName=stage1&Jenkins-Crumb=6c401fbc321622b1f79d4ef8fbd6fdbf&json=%7B%22stageName%22%3A+%22stage1%22%2C+%22Jenkins-Crumb%22%3A+%226c401fbc321622b1f79d4ef8fbd6fdbf%22%7D&Submit=Run' --compressed
        getClient().postFormJson(url, data, this.isCrumb());
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