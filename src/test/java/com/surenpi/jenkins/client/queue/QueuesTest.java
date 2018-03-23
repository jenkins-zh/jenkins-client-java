package com.surenpi.jenkins.client.queue;

import com.surenpi.jenkins.client.ConstantsForTest;
import com.surenpi.jenkins.client.Jenkins;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
    public void getQueue() throws IOException
    {
        Queue queue = queues.getQueue();
        System.out.println(queue);
    }
}