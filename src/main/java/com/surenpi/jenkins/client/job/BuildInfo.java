package com.surenpi.jenkins.client.job;

import com.surenpi.jenkins.client.BaseModel;

/**
 * @author suren
 */
public class BuildInfo extends BaseModel
{
    private int number;
    private String url;

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
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