package com.surenpi.jenkins.client;

import com.surenpi.jenkins.client.computer.Computers;
import com.surenpi.jenkins.client.core.OverLoad;
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

    public Jenkins(URI uri, String userName, String password)
    {
        this(new JenkinsHttpClient(uri, userName, password));
    }

    public Jenkins(JenkinsHttpClient client) {
        this.client = client;
    }

    public Folders getFolders() {
        return null;
    }

    public Jobs getJobs() {
        Jobs jobs = new Jobs();
        jobs.setClient(this.client);
        return jobs;
    }

    public Views getViews() {
        Views views = new Views();
        views.setClient(this.client);
        return views;
    }

    public Workflows getWorkflows() {
        return null;
    }

    public Computers getComputers() {
        Computers computers = new Computers();
        computers.setClient(this.client);
        return computers;
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
        Plugins plugins = new Plugins();
        plugins.setClient(this.client);
        return plugins;
    }

    public boolean isRunning() {
        return false;
    }

    public OverLoad getOverLoad() {
//        http://localhost:8080/jenkins/overallLoad/api/json
        return null;
    }

    public void shutdown() {
    }

    public String getVersion() {
        return "";
    }
}