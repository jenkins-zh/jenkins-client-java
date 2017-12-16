package com.surenpi.jenkins.client.view;

import com.surenpi.jenkins.client.ConstantsForTest;
import com.surenpi.jenkins.client.Jenkins;
import com.surenpi.jenkins.client.job.Jobs;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author suren
 */
public class ViewsTest {
    private static Views views;
    private final String viewName = "hello";

    @BeforeClass
    public static void init() throws URISyntaxException
    {
        views = new Jenkins(
                new URI(ConstantsForTest.JENKINS_URL),
                ConstantsForTest.JENKINS_USER,
                ConstantsForTest.JENKINS_PASSWD).getViews();
    }

    @Test
    public void create() throws IOException
    {
        views.create(viewName, VIEW_XML);
    }

    @Test
    public void info() throws IOException
    {
        View view = views.info("aaa");
        System.out.println(view);
    }

    public static final String VIEW_XML = "";
}