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
public class Jobs extends BaseManager {
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
        create(jobName, jobXml, false);
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
}