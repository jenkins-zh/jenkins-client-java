package com.surenpi.jenkins.client.credential;

import com.surenpi.jenkins.client.Jenkins;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author suren
 */
public class CredentialsTest {
    private static Credentials credentials;
    private final String userName = "hello";

    @BeforeClass
    public static void init() throws URISyntaxException {
        credentials = new Jenkins(
                new URI("http://localhost:8080/jenkins")).getCredentials();
    }

    @Test
    public void list() throws IOException, URISyntaxException {
        Map<String, Credential> list = credentials.list();

        System.out.println(list);
    }

    @Test
    public void create() throws IOException {
        Credential credential = new UserPasswdCredential(userName, "hello");
        credentials.create(credential);
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