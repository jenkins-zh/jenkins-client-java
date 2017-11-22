package com.surenpi.jenkins.client;

/**
 * @author suren
 */
public abstract class BaseManager {
    private JenkinsClient client;

    protected JenkinsClient getClient() {
        return client;
    }

    public void setClient(JenkinsClient client) {
        this.client = client;
    }
}