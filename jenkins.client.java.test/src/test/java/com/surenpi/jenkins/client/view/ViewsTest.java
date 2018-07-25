package com.surenpi.jenkins.client.view;

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

import static org.junit.Assert.assertNotNull;

/**
 * @author suren
 */
public class ViewsTest {
    private static Views views;
    private final String viewName = "hello";

    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Before
    public void setup() throws URISyntaxException
    {
        User user = User.getById("admin", true);

        assertNotNull(user);

        String token = ((ApiTokenProperty) user.getProperty(ApiTokenProperty.class)).getApiToken();

        assertNotNull(j.jenkins.getRootUrl());

        j.jenkins.setSecurityRealm(SecurityRealm.NO_AUTHENTICATION);
        views = new Jenkins(new URI(j.jenkins.getRootUrl()), user.getId(), token).getViews();
    }

    @Test
    public void create() throws IOException
    {
        views.create(viewName, VIEW_XML);

        hudson.model.View view = j.jenkins.getView(viewName);
        assertNotNull(view);
    }

    @Test
    public void info() throws IOException
    {
        views.create(viewName, VIEW_XML);
        View view = views.info(viewName);
        assertNotNull(view);
    }

    @Test
    public void getXml() throws IOException
    {
        views.create(viewName, VIEW_XML);
        assertNotNull(j.jenkins.getView(viewName));
        String viewXmlConfig = views.getXml(viewName);
        assertNotNull(viewXmlConfig);
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