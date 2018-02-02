package com.surenpi.jenkins.client.computer;

import com.surenpi.jenkins.client.ConstantsForTest;
import com.surenpi.jenkins.client.Jenkins;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * @author suren
 */
public class ComputersTest
{

    private static Computers computers;

    @BeforeClass
    public static void init() throws URISyntaxException
    {
        computers = new Jenkins(
                new URI(ConstantsForTest.JENKINS_URL),
                ConstantsForTest.JENKINS_USER,
                ConstantsForTest.JENKINS_PASSWD).getComputers();
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