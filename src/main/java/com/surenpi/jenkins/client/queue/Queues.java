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

    /**
     * Get the queue items.
     * @return
     * @throws IOException
     */
    public Queue getItems() throws IOException
    {
        return getClient().get("/queue/api/json", Queue.class);
    }

    public QueueItemDetail getItem(int id) throws IOException
    {
        return getClient().get("/queue/item/" + id + "/api/json", QueueItemDetail.class);
    }

    public void cancelItem(int id) throws IOException
    {
        getClient().post("/queue/cancelItem?id=" + id, this.isCrumb());
    }
}