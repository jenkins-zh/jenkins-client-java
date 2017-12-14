package com.surenpi.jenkins.client.view;

import com.surenpi.jenkins.client.BaseManager;
import com.surenpi.jenkins.client.folder.FolderJob;
import com.surenpi.jenkins.client.util.EncodingUtils;
import com.surenpi.jenkins.client.util.UrlUtils;

import java.io.IOException;

/**
 * 视图管理
 * @author suren
 */
public class Views extends BaseManager
{
    /**
     * @param folderJob
     * @param viewName
     * @param viewXml
     * @param crumbFlag
     * @throws IOException
     */
    public void create(FolderJob folderJob, String viewName, String viewXml, Boolean crumbFlag) throws IOException
    {
        String path = UrlUtils.toBaseUrl(folderJob) + "createView?name=" + EncodingUtils.encodeParam(viewName);

        getClient().postXml(path, viewXml, crumbFlag);
    }

    /**
     * @see #create(FolderJob, String, String, Boolean)
     * @param viewName
     * @param viewXml
     * @param crumbFlag
     * @throws IOException
     */
    public void create(String viewName, String viewXml, Boolean crumbFlag) throws IOException
    {
        create(null, viewName, viewXml, crumbFlag);
    }

    /**
     * @see #create(String, String, Boolean)
     * @param viewName
     * @param viewXml
     * @throws IOException
     */
    public void create(String viewName, String viewXml) throws IOException
    {
        create(viewName, viewXml, isCrumb());
    }

    /**
     * 更新视图
     * @param folder
     * @param viewName
     * @param viewXml
     * @param crumbFlag
     * @throws IOException
     */
    public void update(FolderJob folder, String viewName, String viewXml, boolean crumbFlag) throws IOException
    {
        String path = UrlUtils.toBaseUrl(folder) + "view/" + EncodingUtils.encode(viewName) + "/config.xml";
        getClient().postXml(path, viewXml, crumbFlag);
    }

    /**
     * @see #update(FolderJob, String, String, boolean)
     * @param viewName
     * @param viewXml
     * @param crumbFlag
     * @throws IOException
     */
    public void update(String viewName, String viewXml, boolean crumbFlag) throws IOException
    {
        update(null, viewName, viewXml, crumbFlag);
    }

    /**
     * @see #update(String, String, boolean)
     * @param viewName
     * @param viewXml
     * @throws IOException
     */
    public void update(String viewName, String viewXml) throws IOException
    {
        update(viewName, viewXml, isCrumb());
    }

    @Override
    protected String[] getDependencyArray()
    {
        return null;
    }
}