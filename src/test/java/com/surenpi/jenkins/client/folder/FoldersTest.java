package com.surenpi.jenkins.client.folder;

import com.surenpi.jenkins.client.ConstantsForTest;
import com.surenpi.jenkins.client.Jenkins;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FoldersTest
{
    private static Folders folders;
    private final String folderName = "hello";

    @BeforeClass
    public static void init() throws URISyntaxException
    {
        folders = new Jenkins(
                new URI(ConstantsForTest.JENKINS_URL),
                ConstantsForTest.JENKINS_USER,
                ConstantsForTest.JENKINS_PASSWD).getFolders();
    }

    @Test
    public void create() throws IOException
    {
        folders.create(folderName);
    }

    @Test
    public void getXml() throws IOException
    {
        String xml = folders.getXml(folderName);
        System.out.println(xml);
    }
}