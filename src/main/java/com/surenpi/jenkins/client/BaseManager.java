package com.surenpi.jenkins.client;

import com.surenpi.jenkins.client.plugin.Plugins;

import java.io.IOException;

/**
 * @author suren
 */
public abstract class BaseManager
{
    private JenkinsClient client;
    /** 如果打开了CSRF保护应该设置为true */
    private boolean crumb = true;

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

    protected abstract String[] getDependencyArray();

    /**
     * 插件依赖是否满足的检查
     * @return 依赖满足返回true
     */
    public boolean dependencyCheck() throws IOException
    {
        Plugins plugins = new Plugins();
        plugins.setClient(getClient());

        return plugins.pluginsExists(getDependencyArray());
    }
}