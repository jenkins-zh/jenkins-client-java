package com.surenpi.jenkins.client.queue;

import com.surenpi.jenkins.client.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.model.User;
import hudson.security.SecurityRealm;
import jenkins.security.ApiTokenProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author suren
 */
public class QueuesTest
{
    private static Queues queues;
    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Before
    public void init() throws URISyntaxException
    {
        User user = User.getById("admin", true);

        assertNotNull(user);

        String token = ((ApiTokenProperty) user.getProperty(ApiTokenProperty.class)).getApiToken();

        assertNotNull(j.jenkins.getRootUrl());

        j.jenkins.setSecurityRealm(SecurityRealm.NO_AUTHENTICATION);
        queues = new Jenkins(new URI(j.jenkins.getRootUrl()), user.getId(), token).getQueues();
    }

    @Test
    public void getItems() throws IOException
    {
        Queue queue = queues.getItems();
        assertNotNull(queue.getItems());
        assertEquals(0, queue.getItems().size());

        j.jenkins.setNumExecutors(0);
        FreeStyleProject project = j.createFreeStyleProject();
        project.scheduleBuild2(0);

        queue = queues.getItems();
        List<QueueItem> items = queue.getItems();
        assertEquals(1, items.size());

        QueueItem firstItem = items.get(0);
        int firstItemId = firstItem.getId();

        assertNotNull(queues.getItem(firstItemId));

        queues.cancelItem(firstItemId);
        assertEquals(0, queues.getItems().getItems().size());
    }
}