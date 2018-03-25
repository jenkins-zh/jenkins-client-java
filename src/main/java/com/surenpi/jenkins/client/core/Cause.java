package com.surenpi.jenkins.client.core;

import com.surenpi.jenkins.client.BaseModel;

/**
 * @author suren
 */
public class Cause extends BaseModel
{
    private String userId;
    private String userName;
    private String shortDescription;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getShortDescription()
    {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
    }
}