package com.surenpi.jenkins.client.view;

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
    public static void init() throws URISyntaxException {
        views = new Jenkins(
                new URI("http://localhost:8080/jenkins")).getViews();
    }

    @Test
    public void create() throws IOException {
        views.create(viewName, VIEW_XML);
    }

    public static final String VIEW_XML = "";
}