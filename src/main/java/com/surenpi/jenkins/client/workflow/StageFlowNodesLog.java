/**
 *
 */
package com.surenpi.jenkins.client.workflow;

import com.surenpi.jenkins.client.BaseModel;
import com.surenpi.jenkins.client.JenkinsClient;

/**
 * @author johuang
 *
 */
public class StageFlowNodesLog extends BaseModel
{
    String nodeId;
    String nodeStatus;
    long length;
    boolean hasMore;
    String text;
    String consoleUrl;
    private JenkinsClient client;

    public String getNodeId() {
        return nodeId;
    }
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
    public String getNodeStatus() {
        return nodeStatus;
    }
    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }
    public long getLength() {
        return length;
    }
    public void setLength(long length) {
        this.length = length;
    }
    public boolean isHasMore() {
        return hasMore;
    }
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getConsoleUrl() {
        return consoleUrl;
    }
    public void setConsoleUrl(String consoleUrl) {
        this.consoleUrl = consoleUrl;
    }
    @Override
    public String toString() {
        return "StageFlowNodesLog [nodeId=" + nodeId + ", nodeStatus=" + nodeStatus + ", length=" + length
                + ", hasMore=" + hasMore + ", text=" + text + ", consoleUrl=" + consoleUrl + "]";
    }

    public void setClient(JenkinsClient client)
    {
        this.client = client;
    }
}