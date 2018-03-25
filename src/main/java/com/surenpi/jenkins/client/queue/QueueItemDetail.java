package com.surenpi.jenkins.client.queue;

import com.surenpi.jenkins.client.core.Action;

import java.util.List;

/**
 * @author suren
 */
public class QueueItemDetail extends QueueItem
{
    private QueueTask task;
    private List<Action> actions;

    public QueueTask getTask()
    {
        return task;
    }

    public void setTask(QueueTask task)
    {
        this.task = task;
    }

    public List<Action> getActions()
    {
        return actions;
    }

    public void setActions(List<Action> actions)
    {
        this.actions = actions;
    }
}