package com.surenpi.jenkins.client.queue;

import com.surenpi.jenkins.client.BaseModel;

/**
 * @author suren
 */
public class QueueItem extends BaseModel
{
    private int id;
    private boolean blocked;
    private boolean buildable;
    private long inQueueSince;
    private String params;
    private boolean stuck;
    private String url;
    private String why;
    private long buildableStartMilliseconds;
    private boolean pending;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean isBlocked()
    {
        return blocked;
    }

    public void setBlocked(boolean blocked)
    {
        this.blocked = blocked;
    }

    public boolean isBuildable()
    {
        return buildable;
    }

    public void setBuildable(boolean buildable)
    {
        this.buildable = buildable;
    }

    public long getInQueueSince()
    {
        return inQueueSince;
    }

    public void setInQueueSince(long inQueueSince)
    {
        this.inQueueSince = inQueueSince;
    }

    public String getParams()
    {
        return params;
    }

    public void setParams(String params)
    {
        this.params = params;
    }

    public boolean isStuck()
    {
        return stuck;
    }

    public void setStuck(boolean stuck)
    {
        this.stuck = stuck;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getWhy()
    {
        return why;
    }

    public void setWhy(String why)
    {
        this.why = why;
    }

    public long getBuildableStartMilliseconds()
    {
        return buildableStartMilliseconds;
    }

    public void setBuildableStartMilliseconds(long buildableStartMilliseconds)
    {
        this.buildableStartMilliseconds = buildableStartMilliseconds;
    }

    public boolean isPending()
    {
        return pending;
    }

    public void setPending(boolean pending)
    {
        this.pending = pending;
    }
}