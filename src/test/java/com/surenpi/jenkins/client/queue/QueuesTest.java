package com.surenpi.jenkins.client.queue;

import com.surenpi.jenkins.client.ConstantsForTest;
import com.surenpi.jenkins.client.Jenkins;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author suren
 */
public class QueuesTest
{
    private static Queues queues;

    @BeforeClass
    public static void init() throws URISyntaxException
    {
        queues = new Jenkins(
                new URI(ConstantsForTest.JENKINS_URL),
                ConstantsForTest.JENKINS_USER,
                ConstantsForTest.JENKINS_PASSWD).getQueues();
    }

    @Test
    public void getItems() throws IOException
    {
        Queue queue = queues.getItems();
        System.out.println(queue);
    }

    @Test
    public void cancelItem() throws IOException
    {
        Queue queue = queues.getItems();
        if(queue != null)
        {
            List<QueueItem> items = queue.getItems();
            if(items == null)
            {
                return;
            }

            for(QueueItem item : items)
            {
                queues.cancelItem(item.getId());
            }
        }
    }
}