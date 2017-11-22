package com.surenpi.jenkins.client.credential;

import com.surenpi.jenkins.client.Jenkins;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author suren
 */
public class CredentialsTest {
    @Test
    public void list() throws IOException, URISyntaxException {
        Credentials credentials = new Jenkins(
                new URI("http://localhost:8080/jenkins")).getCredentials();
        Map<String, Credential> list = credentials.list();

        System.out.println(list);
    }
}