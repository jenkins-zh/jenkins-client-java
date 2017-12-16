package com.surenpi.jenkins.client.folder;

import com.surenpi.jenkins.client.job.Job;

import java.util.List;

/**
 * @author suren
 */
public class FolderJob extends Job
{

    private String displayName;
    private List<Job> jobs;

    public FolderJob()
    {
    }

    public FolderJob(String name)
    {
        super(name, "/job/" + name + "/");
    }

    public FolderJob(String name, String url)
    {
        super(name, url);
    }
    
    public FolderJob(String name, String url, String fullName) {
        super(name, url, fullName);
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Determine if this FolderJob object is a valid folder or not.
     * 
     * (internally: if jobs list exists)
     * 
     * @return true if this job is a folder.
     */
    public boolean isFolder()
    {
        if (jobs != null) {
            return true;
        }
        return false;
    }
}
