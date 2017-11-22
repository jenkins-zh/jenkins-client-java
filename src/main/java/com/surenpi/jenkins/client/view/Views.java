package com.surenpi.jenkins.client.view;

import com.surenpi.jenkins.client.BaseManager;
import com.surenpi.jenkins.client.folder.FolderJob;
import com.surenpi.jenkins.client.util.EncodingUtils;
import com.surenpi.jenkins.client.util.UrlUtils;

import java.io.IOException;

/**
 * @author suren
 */
public class Views extends BaseManager {
    /**
     * @param folderJob
     * @param viewName
     * @param viewXml
     * @param crumbFlag
     * @throws IOException
     */
    public void create(FolderJob folderJob, String viewName, String viewXml, Boolean crumbFlag) throws IOException {
        String path = UrlUtils.toBaseUrl(folderJob) + "createView?name=" + EncodingUtils.encodeParam(viewName);

        getClient().post_xml(path, viewXml, crumbFlag);
    }

    /**
     * @see #create(FolderJob, String, String, Boolean)
     * @param viewName
     * @param viewXml
     * @param crumbFlag
     * @throws IOException
     */
    public void create(String viewName, String viewXml, Boolean crumbFlag) throws IOException {
        create(null, viewName, viewXml, crumbFlag);
    }

    /**
     * @see #create(String, String, Boolean)
     * @param viewName
     * @param viewXml
     * @throws IOException
     */
    public void create(String viewName, String viewXml) throws IOException {
        create(viewName, viewXml, false);
    }
}