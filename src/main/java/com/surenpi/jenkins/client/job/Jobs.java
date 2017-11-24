package com.surenpi.jenkins.client.job;

import com.surenpi.jenkins.client.BaseManager;
import com.surenpi.jenkins.client.folder.FolderJob;
import com.surenpi.jenkins.client.util.EncodingUtils;
import com.surenpi.jenkins.client.util.UrlUtils;

import java.io.IOException;

/**
 * You can create, update, del a job througth this manager.
 * @author suren
 */
public class Jobs extends BaseManager
{
    /**
     * create a job inside the folder
     * @param folder
     * @param jobName
     * @param jobXml
     * @param crumFlag
     * @throws IOException
     */
    public void create(FolderJob folder, String jobName, String jobXml, Boolean crumFlag) throws IOException {
        String path = UrlUtils.toBaseUrl(folder) + "createItem?name=" + EncodingUtils.encodeParam(jobName);

        getClient().post_xml(path, jobXml, crumFlag);
    }

    /**
     * @see #create(FolderJob, String, String, Boolean)
     * @param jobName
     * @param jobXml
     * @param crumFlag
     * @throws IOException
     */
    public void create(String jobName, String jobXml, Boolean crumFlag) throws IOException {
        create(null, jobName, jobXml, crumFlag);
    }

    /**
     * @see #create(String, String, Boolean)
     * @param jobName
     * @param jobXml
     * @throws IOException
     */
    public void create(String jobName, String jobXml) throws IOException {
        create(jobName, jobXml, isCrumb());
    }

    /**
     * copy a job from a origin to new one
     * @param originName
     * @param newName
     * @throws IOException
     */
    public void copy(String originName, String newName) throws IOException {
        getClient().post(String.format("/createItem?mode=copy&from=%s&name=%s",
                EncodingUtils.encodeParam(originName),
                EncodingUtils.encodeParam(newName)));
    }

    /**
     * delete a job
     * @param folderJob
     * @param jobName
     * @param crumbFlag
     */
    public void delete(FolderJob folderJob, String jobName, Boolean crumbFlag) throws IOException {
        String path = UrlUtils.toJobBaseUrl(folderJob, jobName) + "/doDelete";
        getClient().post(path, crumbFlag);
    }

    /**
     * @see #delete(FolderJob, String, Boolean)
     * @param jobName
     * @param crumbFlag
     * @throws IOException
     */
    public void delete(String jobName, Boolean crumbFlag) throws IOException {
        delete(null, jobName, crumbFlag);
    }

    /**
     * @see #delete(String, Boolean)
     * @param jobName
     * @throws IOException
     */
    public void delete(String jobName) throws IOException
    {
        delete(jobName, isCrumb());
    }

    /**
     * disable a job by jobName<br/>
     * 禁用
     * @param jobName
     * @param crumbFlag
     * @throws IOException
     */
    public void disable(String jobName, Boolean crumbFlag) throws IOException
    {
        getClient().post("/job/" + EncodingUtils.encode(jobName) + "/disable", crumbFlag);
    }

    /**
     * @see #disable(String, Boolean)
     * @param jobName
     * @throws IOException
     */
    public void disable(String jobName) throws IOException
    {
        disable(jobName, isCrumb());
    }

    /**
     * 启用
     * @param jobName
     * @param crumbFlag
     * @throws IOException
     */
    public void enable(String jobName, Boolean crumbFlag) throws IOException
    {
        getClient().post("/job/" + EncodingUtils.encode(jobName) + "/enable", crumbFlag);
    }

    /**
     * @see #enable(String, Boolean)
     * @param jobName
     * @throws IOException
     */
    public void enable(String jobName) throws IOException
    {
        enable(jobName, isCrumb());
    }
}