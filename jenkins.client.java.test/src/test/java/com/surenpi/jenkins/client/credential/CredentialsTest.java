package com.surenpi.jenkins.client.credential;

import com.surenpi.jenkins.client.Jenkins;
import hudson.model.User;
import hudson.security.SecurityRealm;
import jenkins.security.ApiTokenProperty;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author suren
 */
public class CredentialsTest {
    private static Credentials credentials;
    private final String userName = "hello";

    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Before
    public void init() throws URISyntaxException {
        User user = User.getById("admin", true);

        assertNotNull(user);

        String token = ((ApiTokenProperty) user.getProperty(ApiTokenProperty.class)).getApiToken();

        assertNotNull(j.jenkins.getRootUrl());

        j.jenkins.setSecurityRealm(SecurityRealm.NO_AUTHENTICATION);
        credentials = new Jenkins(new URI(j.jenkins.getRootUrl()), user.getId(), token).getCredentials();
    }

    @Test
    public void list() throws IOException, URISyntaxException {
        Map<String, Credential> map = credentials.list();

        assertNotNull(map);
        assertEquals(0, map.size());
    }

    @Test
    public void create() throws IOException {
        Credential credential = new UserPasswdCredential(userName, "hello");
        credential.setDescription("for unit test");
        credential.setDisplayName("for unit test display");
        credential.setFullName("for unit test fullname");
        credentials.create(credential);

        credential = credentials.createAndFetch(credential);
        System.out.println(credential);
    }

    @Test
    public void del() throws IOException {
        Map<String, Credential> list = credentials.list();
        for(String id : list.keySet()) {
            Credential credential = list.get(id);

            if(credential instanceof UserPasswdCredential &&
                    ((UserPasswdCredential) credential).getUsername().equals(userName)) {
                credentials.delete(id);
            }
        }
    }
}