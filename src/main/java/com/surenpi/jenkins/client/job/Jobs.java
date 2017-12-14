package com.surenpi.jenkins.client.job;

import com.surenpi.jenkins.client.BaseManager;
import com.surenpi.jenkins.client.folder.FolderJob;
import com.surenpi.jenkins.client.util.EncodingUtils;
import com.surenpi.jenkins.client.util.UrlUtils;

import java.io.IOException;

/**
 * You can create, update, del a job througth this manager.<br/>
 * 你可以对Jenkins的任务做创建、更新、删除的操作
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

        getClient().postXml(path, jobXml, crumFlag);
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
     * 更新流水线
     * @param folderJob
     * @param jobName
     * @param jobXml
     * @param crumbFlag
     * @throws IOException
     */
    public void update(FolderJob folderJob, String jobName, String jobXml, Boolean crumbFlag) throws IOException
    {
        String path = UrlUtils.toJobBaseUrl(folderJob, jobName) + "/config.xml";
        getClient().postXml(path, jobXml, crumbFlag);
    }

    /**
     * @see #update(FolderJob, String, String, Boolean)
     * @param jobName
     * @param jobXml
     * @param crumbFlag
     * @throws IOException
     */
    public void update(String jobName, String jobXml, Boolean crumbFlag) throws IOException
    {
        update(null, jobName, jobXml, crumbFlag);
    }

    /**
     * @see #update(String, String, Boolean)
     * @param jobName
     * @param jobXml
     * @throws IOException
     */
    public void update(String jobName, String jobXml) throws IOException
    {
        update(jobName, jobXml, isCrumb());
    }

    /**
     * delete a job
     * @param folderJob
     * @param jobName
     * @param crumbFlag
     */
    public void delete(FolderJob folderJob, String jobName, Boolean crumbFlag) throws IOException
    {
        String path = UrlUtils.toJobBaseUrl(folderJob, jobName) + "/doDelete";
        getClient().post(path, crumbFlag);
    }

    /**
     * @see #delete(FolderJob, String, Boolean)
     * @param jobName
     * @param crumbFlag
     * @throws IOException
     */
    public void delete(String jobName, Boolean crumbFlag) throws IOException
    {
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
     * 重命名job名称
     * @param folderJob
     * @param oldName
     * @param newName
     * @param crumbFlag
     * @throws IOException
     */
    public void rename(FolderJob folderJob, String oldName, String newName, Boolean crumbFlag)
            throws IOException
    {
        String path = UrlUtils.toJobBaseUrl(folderJob, oldName)
                + "/doRename?newName=" + EncodingUtils.encodeParam(newName);
        getClient().post(path, crumbFlag);
    }

    /**
     * @see #rename(FolderJob, String, String, Boolean)
     * @param oldName
     * @param newName
     * @param crumbFlag
     * @throws IOException
     */
    public void rename(String oldName, String newName, Boolean crumbFlag) throws IOException
    {
        rename(null, oldName, newName, crumbFlag);
    }

    /**
     * @see #rename(String, String, Boolean)
     * @param oldName
     * @param newName
     * @throws IOException
     */
    public void rename(String oldName, String newName) throws IOException
    {
        rename(oldName, newName, isCrumb());
    }

    /**
     * 获取job的xml配置内容
     * @param folderJob
     * @param jobName
     * @return
     * @throws IOException
     */
    public String getXml(FolderJob folderJob, String jobName) throws IOException
    {
        return getClient().get(UrlUtils.toJobBaseUrl(folderJob, jobName) + "/config.xml");
    }

    /**
     * @see #getXml(FolderJob, String)
     * @param jobName
     * @return
     * @throws IOException
     */
    public String getXml(String jobName) throws IOException
    {
        return getXml(null, jobName);
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
        getClient().post("/job/" + EncodingUtils.encode(jobName) + "/disable", isCrumb());
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
        getClient().post("/job/" + EncodingUtils.encode(jobName) + "/enable", isCrumb());
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

    /**
     * Build a job by name
     * @param jobName
     * @throws IOException
     */
    public void build(String jobName) throws IOException
    {
        getClient().post("/job/" + EncodingUtils.encode(jobName) + "/build", isCrumb());
    }

    @Override
    protected String[] getDependencyArray()
    {
        return new String[0];
    }
}