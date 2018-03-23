package com.surenpi.jenkins.client.queue;

import com.surenpi.jenkins.client.BaseManager;

import java.io.IOException;

/**
 * @author suren
 */
public class Queues extends BaseManager
{
    @Override
    protected String[] getDependencyArray()
    {
        return null;
    }

    public Queue getQueue() throws IOException
    {
        return getClient().get("/queue/api/json", Queue.class);
    }
}