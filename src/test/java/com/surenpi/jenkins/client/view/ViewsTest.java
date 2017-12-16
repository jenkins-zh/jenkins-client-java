package com.surenpi.jenkins.client.view;

import com.surenpi.jenkins.client.ConstantsForTest;
import com.surenpi.jenkins.client.Jenkins;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author suren
 */
public class ViewsTest {
    private static Views views;
    private final String viewName = "hello";

    @BeforeClass
    public static void init() throws URISyntaxException
    {
        views = new Jenkins(
                new URI(ConstantsForTest.JENKINS_URL),
                ConstantsForTest.JENKINS_USER,
                ConstantsForTest.JENKINS_PASSWD).getViews();
    }

    @Test
    public void create() throws IOException
    {
        views.create(viewName, VIEW_XML);
    }

    @Test
    public void info() throws IOException
    {
        View view = views.info(viewName);
        System.out.println(view);
    }

    @Test
    public void getXml() throws IOException
    {
        String viewXmlConfig = views.getXml(viewName);
        System.out.println(viewXmlConfig);
    }

    public static final String VIEW_XML = "<hudson.model.ListView>\n" +
            "    <name>rrr</name>\n" +
            "    <filterExecutors>false</filterExecutors>\n" +
            "    <filterQueue>false</filterQueue>\n" +
            "    <properties class=\"hudson.model.View$PropertyList\"/>\n" +
            "    <jobNames>\n" +
            "        <comparator class=\"hudson.util.CaseInsensitiveComparator\"/>\n" +
            "    </jobNames>\n" +
            "    <jobFilters/>\n" +
            "    <columns>\n" +
            "        <hudson.views.StatusColumn/>\n" +
            "        <hudson.views.WeatherColumn/>\n" +
            "        <hudson.views.JobColumn/>\n" +
            "        <hudson.views.LastSuccessColumn/>\n" +
            "        <hudson.views.LastFailureColumn/>\n" +
            "        <hudson.views.LastDurationColumn/>\n" +
            "        <hudson.views.BuildButtonColumn/>\n" +
            "        <hudson.plugins.favorite.column.FavoriteColumn plugin=\"favorite@2.3.2-SNAPSHOT\"/>\n" +
            "    </columns>\n" +
            "    <recurse>false</recurse>\n" +
            "</hudson.model.ListView>";
}