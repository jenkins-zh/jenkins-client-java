package com.surenpi.jenkins.client.core;

import com.surenpi.jenkins.client.BaseModel;

/**
 * @author suren
 */
public class Author extends BaseModel
{
    private String absoluteUrl;
    private String fullName;

    public String getAbsoluteUrl()
    {
        return absoluteUrl;
    }

    public void setAbsoluteUrl(String absoluteUrl)
    {
        this.absoluteUrl = absoluteUrl;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
}