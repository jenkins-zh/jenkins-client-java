package com.surenpi.jenkins.client.core;

import com.surenpi.jenkins.client.BaseModel;

import java.util.List;

/**
 * @author suren
 */
public class Action extends BaseModel
{
    private List<Cause> causes;

    public List<Cause> getCauses()
    {
        return causes;
    }

    public void setCauses(List<Cause> causes)
    {
        this.causes = causes;
    }
}