package com.surenpi.jenkins.client.core;

import com.surenpi.jenkins.client.BaseModel;
import com.surenpi.jenkins.client.computer.Label;
import com.surenpi.jenkins.client.job.Job;
import com.surenpi.jenkins.client.view.View;

import java.util.List;

public class JenkinsInfo extends BaseModel
{
    private List<Label> assignedLabels;
    private String mode;
    private String nodeDescription;
    private int numExecutors;
    private String description;
    private List<Job> jobs;
    private boolean quietingDown;
    private int slaveAgentPort;
    private boolean useCrumbs;
    private boolean useSecurity;
    private List<View> views;

    public List<Label> getAssignedLabels()
    {
        return assignedLabels;
    }

    public void setAssignedLabels(List<Label> assignedLabels)
    {
        this.assignedLabels = assignedLabels;
    }

    public String getMode()
    {
        return mode;
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }

    public String getNodeDescription()
    {
        return nodeDescription;
    }

    public void setNodeDescription(String nodeDescription)
    {
        this.nodeDescription = nodeDescription;
    }

    public int getNumExecutors()
    {
        return numExecutors;
    }

    public void setNumExecutors(int numExecutors)
    {
        this.numExecutors = numExecutors;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<Job> getJobs()
    {
        return jobs;
    }

    public void setJobs(List<Job> jobs)
    {
        this.jobs = jobs;
    }

    public boolean isQuietingDown()
    {
        return quietingDown;
    }

    public void setQuietingDown(boolean quietingDown)
    {
        this.quietingDown = quietingDown;
    }

    public int getSlaveAgentPort()
    {
        return slaveAgentPort;
    }

    public void setSlaveAgentPort(int slaveAgentPort)
    {
        this.slaveAgentPort = slaveAgentPort;
    }

    public boolean isUseCrumbs()
    {
        return useCrumbs;
    }

    public void setUseCrumbs(boolean useCrumbs)
    {
        this.useCrumbs = useCrumbs;
    }

    public boolean isUseSecurity()
    {
        return useSecurity;
    }

    public void setUseSecurity(boolean useSecurity)
    {
        this.useSecurity = useSecurity;
    }

    public List<View> getViews()
    {
        return views;
    }

    public void setViews(List<View> views)
    {
        this.views = views;
    }
}