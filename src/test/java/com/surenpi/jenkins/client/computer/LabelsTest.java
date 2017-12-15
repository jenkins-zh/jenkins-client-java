package com.surenpi.jenkins.client.computer;

import com.surenpi.jenkins.client.ConstantsForTest;
import com.surenpi.jenkins.client.Jenkins;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LabelsTest
{

    private static Labels labels;

    @BeforeClass
    public static void init() throws URISyntaxException
    {
        labels = new Jenkins(
                new URI(ConstantsForTest.JENKINS_URL),
                ConstantsForTest.JENKINS_USER,
                ConstantsForTest.JENKINS_PASSWD).getLabels();
    }

    @Test
    public void test() throws IOException
    {
        LabelDetail labelsArray = labels.getLabels();
        System.out.println(labelsArray);
    }
}