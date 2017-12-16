package com.surenpi.jenkins.client.job;

import com.surenpi.jenkins.client.BaseModel;
import com.surenpi.jenkins.client.core.Artifact;
import com.surenpi.jenkins.client.core.BuildResult;
import com.surenpi.jenkins.client.scm.ScmChangeInfo;

import java.util.List;

/**
 * 一次构建的详细信息
 * @author suren
 */
public class BuildDetail extends BaseModel
{
    private List<Artifact> artifacts;
    private boolean building;
    private String description;
    private String displayName;
    private long duration;
    private long estimatedDuration;
    private String fullDisplayName;
    private String id;
    private boolean keepLog;
    private int number;
    private int queueId;
    private BuildResult result;
    private long timestamp;
    private String url;
    private List<ScmChangeInfo> changeSets;
    private BuildInfo nextBuild;
    private BuildInfo previousBuild;

    public List<Artifact> getArtifacts()
    {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts)
    {
        this.artifacts = artifacts;
    }

    public boolean isBuilding()
    {
        return building;
    }

    public void setBuilding(boolean building)
    {
        this.building = building;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public long getDuration()
    {
        return duration;
    }

    public void setDuration(long duration)
    {
        this.duration = duration;
    }

    public long getEstimatedDuration()
    {
        return estimatedDuration;
    }

    public void setEstimatedDuration(long estimatedDuration)
    {
        this.estimatedDuration = estimatedDuration;
    }

    public String getFullDisplayName()
    {
        return fullDisplayName;
    }

    public void setFullDisplayName(String fullDisplayName)
    {
        this.fullDisplayName = fullDisplayName;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public boolean isKeepLog()
    {
        return keepLog;
    }

    public void setKeepLog(boolean keepLog)
    {
        this.keepLog = keepLog;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getQueueId()
    {
        return queueId;
    }

    public void setQueueId(int queueId)
    {
        this.queueId = queueId;
    }

    public BuildResult getResult()
    {
        return result;
    }

    public void setResult(BuildResult result)
    {
        this.result = result;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public List<ScmChangeInfo> getChangeSets()
    {
        return changeSets;
    }

    public void setChangeSets(List<ScmChangeInfo> changeSets)
    {
        this.changeSets = changeSets;
    }

    public BuildInfo getNextBuild()
    {
        return nextBuild;
    }

    public void setNextBuild(BuildInfo nextBuild)
    {
        this.nextBuild = nextBuild;
    }

    public BuildInfo getPreviousBuild()
    {
        return previousBuild;
    }

    public void setPreviousBuild(BuildInfo previousBuild)
    {
        this.previousBuild = previousBuild;
    }
}