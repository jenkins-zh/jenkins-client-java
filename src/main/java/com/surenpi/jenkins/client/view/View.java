package com.surenpi.jenkins.client.view;

import com.surenpi.jenkins.client.BaseModel;

public class View extends BaseModel
{
    private String name;
    private String url;

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
}