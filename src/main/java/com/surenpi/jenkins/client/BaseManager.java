package com.surenpi.jenkins.client;

/**
 * @author suren
 */
public abstract class BaseManager {
    private JenkinsClient client;
    /** 如果打开了CSRF保护应该设置为true */
    private boolean crumb = false;

    protected JenkinsClient getClient() {
        return client;
    }

    public void setClient(JenkinsClient client) {
        this.client = client;
    }

    public boolean isCrumb()
    {
        return crumb;
    }

    public void setCrumb(boolean crumb)
    {
        this.crumb = crumb;
    }
}