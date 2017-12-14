package com.surenpi.jenkins.client.folder;

import com.google.common.collect.ImmutableMap;
import com.surenpi.jenkins.client.BaseManager;
import com.surenpi.jenkins.client.util.EncodingUtils;
import com.surenpi.jenkins.client.util.UrlUtils;

import java.io.IOException;

/**
 * 管理Jenkins的文件夹（folder）
 * @author suren
 */
public class Folders extends BaseManager
{
    private String folderCls = "com.cloudbees.hudson.plugins.folder.Folder";

    /**
     * 创建文件夹
     * @param folderJob
     * @param folderName
     * @param crumbFlag
     * @throws IOException
     */
    public void create(FolderJob folderJob, String folderName, Boolean crumbFlag) throws IOException
    {
        ImmutableMap<String, String> params = ImmutableMap.of("mode", folderCls,
                "name", EncodingUtils.encodeParam(folderName),
                "from", "", "Submit", "OK");
        getClient().postForm(UrlUtils.toBaseUrl(folderJob) + "createItem?", params, crumbFlag);
    }

    /**
     * @see #create(FolderJob, String, Boolean)
     * @param folderName
     * @param crumbFlag
     * @throws IOException
     */
    public void create(String folderName, Boolean crumbFlag) throws IOException
    {
        create(null, folderName, crumbFlag);
    }

    /**
     * @see #create(String, Boolean)
     * @param folderName
     * @throws IOException
     */
    public void create(String folderName) throws IOException
    {
        create(folderName, isCrumb());
    }

    public String getFolderCls()
    {
        return folderCls;
    }

    public void setFolderCls(String folderCls)
    {
        this.folderCls = folderCls;
    }

    @Override
    protected String[] getDependencyArray()
    {
        return new String[0];
    }
}