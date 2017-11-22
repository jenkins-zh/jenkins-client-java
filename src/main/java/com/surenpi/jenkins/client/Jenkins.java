package com.surenpi.jenkins.client;

import com.surenpi.jenkins.client.computer.Computers;
import com.surenpi.jenkins.client.credential.Credentials;
import com.surenpi.jenkins.client.folder.Folders;
import com.surenpi.jenkins.client.job.Jobs;
import com.surenpi.jenkins.client.plugin.Plugins;
import com.surenpi.jenkins.client.view.Views;
import com.surenpi.jenkins.client.workflow.Workflows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jenkins Client main entry
 * @author suren
 */
public class Jenkins {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public Folders getFolders() {
        return null;
    }

    public Jobs getJobs() {
        return null;
    }

    public Views getViews() {
        return null;
    }

    public Workflows getWorkflows() {
        return null;
    }

    public Computers getComputers() {
        return null;
    }

    /**
     * Get the credential manager of jenkins
     * @return
     */
    public Credentials getCredentials() {
        return null;
    }

    public Plugins getPlugins() {
        return null;
    }
}