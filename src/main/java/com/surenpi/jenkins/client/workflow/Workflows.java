package com.surenpi.jenkins.client.workflow;

import com.surenpi.jenkins.client.BaseManager;

/**
 * 流水新管理
 * @author suren
 */
public class Workflows extends BaseManager
{
    @Override
    protected String[] getDependencyArray()
    {
        return new String[]{"pipeline-rest-api"};
    }
}