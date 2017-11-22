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

import java.net.URI;

/**
 * Jenkins Client main entry
 * @author suren
 */
public class Jenkins {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final JenkinsHttpClient client;

    public Jenkins(URI uri) {
        this(new JenkinsHttpClient(uri));
    }

    public Jenkins(JenkinsHttpClient client) {
        this.client = client;
    }

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
        Credentials credentials = new Credentials();
        credentials.setClient(this.client);

        return credentials;
    }

    public Plugins getPlugins() {
        return null;
    }

    public boolean isRunning() {
        return false;
    }

    public void shutdown() {
    }

    public String getVersion() {
        return "";
    }
}