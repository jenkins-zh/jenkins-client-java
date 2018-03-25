package com.surenpi.jenkins.client.queue;

import com.surenpi.jenkins.client.BaseModel;

/**
 * @author suren
 */
public class QueueTask extends BaseModel
{
    private String name;
    private String url;
    private String color;

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

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }
}