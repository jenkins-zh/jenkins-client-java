package com.surenpi.jenkins.client.computer;

import com.surenpi.jenkins.client.Jenkins;
import hudson.model.User;
import hudson.security.SecurityRealm;
import jenkins.security.ApiTokenProperty;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * @author suren
 */
public class ComputersTest
{
    private static Computers computers;
    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Before
    public void init() throws URISyntaxException
    {
        User user = User.getById("admin", true);

        assertNotNull(user);

        String token = ((ApiTokenProperty) user.getProperty(ApiTokenProperty.class)).getApiToken();

        assertNotNull(j.jenkins.getRootUrl());

        j.jenkins.setSecurityRealm(SecurityRealm.NO_AUTHENTICATION);
        computers = new Jenkins(new URI(j.jenkins.getRootUrl()), user.getId(), token).getComputers();
    }

    @Test
    public void getComputers() throws IOException
    {
        Map<String, Computer> computerList = computers.getComputers();
        System.out.println(computerList);

        List<ComputerWithDetails> computerDetailList = computers.getComputerSet().getComputers();
        for(ComputerWithDetails detail : computerDetailList)
        {
            System.out.println(detail.getAssignedLabels());
            System.out.println(detail.getAbsoluteRemotePath());

            LoadStatistics load = detail.getLoadStatistics();
            System.out.println(load);
        }
    }
}