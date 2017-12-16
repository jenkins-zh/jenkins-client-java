package com.surenpi.jenkins.client.view;

import com.surenpi.jenkins.client.BaseModel;
import com.surenpi.jenkins.client.job.Job;

import java.util.List;

/**
 * @author suren
 */
public class View extends BaseModel
{
    private String name;
    private String url;
    private String description;
    private List<Job> jobs;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
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
}