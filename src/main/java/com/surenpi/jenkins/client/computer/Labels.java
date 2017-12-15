package com.surenpi.jenkins.client.computer;

import com.surenpi.jenkins.client.BaseManager;
import com.surenpi.jenkins.client.BaseModel;

import java.io.IOException;
import java.util.List;

public class Labels extends BaseManager
{
    @Override
    protected String[] getDependencyArray()
    {
        return new String[0];
    }

    public LabelDetail getLabels() throws IOException
    {
//        http://localhost:8080/jenkins/computer/agent/surenc/api/json
        return getClient().get("/computer/agent/surenc/api/json?depth=1", LabelDetail.class);
    }
}