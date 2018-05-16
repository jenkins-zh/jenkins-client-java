[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.surenpi/jenkins.client.java/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.surenpi/jenkins.client.java)

# jenkins-client-java

Java binding for the Jenkins client.

[sonar](https://sonarcloud.io/dashboard?id=com.surenpi.ci%3Ajenkins.client.java)

# How to use it

Add the following dependency to the pom.xml file of your project:

```xml
<dependency>
    <groupId>com.surenpi.ci</groupId>
    <artifactId>jenkins.client.java</artifactId>
    <version>1.0.0-20171217</version>
</dependency>
```
# Example of get all jobs from jenkins

```java
import com.surenpi.jenkins.client.Jenkins;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author suren
 */
public class Demo
{
    public static void main(String[] args) throws URISyntaxException, IOException
    {
        URI serverURI = new URI("http://localhost:8080/jenkins");
        Jenkins jenkins = new Jenkins(serverURI, "admin", "admin");

        Jobs jobMgr = jenkins.getJobs();
        List<Job> allJobs = jobMgr.getAllJobs();

        for(Job job : allJobs)
        {
            System.out.println(job.getName());
        }
    }
}
```

# Example of get all installed plugins from jenkins

```java
import com.surenpi.jenkins.client.Jenkins;
import com.surenpi.jenkins.client.plugin.Plugin;
import com.surenpi.jenkins.client.plugin.Plugins;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author suren
 */
public class Demo
{
    public static void main(String[] args) throws URISyntaxException, IOException
    {
        URI serverURI = new URI("http://localhost:8080/jenkins");
        Jenkins jenkins = new Jenkins(serverURI, "admin", "admin");

        Plugins pluginMgr = jenkins.getPlugins();
        List<Plugin> allInstalledPlugins = pluginMgr.getPluginManager().getPlugins();
        for(Plugin plugin : allInstalledPlugins)
        {
            System.out.println(plugin.getShortName());
        }
    }
}
```

# Example of get all credentials from jenkins

```java
import com.surenpi.jenkins.client.Jenkins;
import com.surenpi.jenkins.client.credential.Credential;
import com.surenpi.jenkins.client.credential.Credentials;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author suren
 */
public class Demo
{
    public static void main(String[] args) throws URISyntaxException, IOException
    {
        URI serverURI = new URI("http://localhost:8080/jenkins");
        Jenkins jenkins = new Jenkins(serverURI, "admin", "admin");

        Credentials credentialMgr = jenkins.getCredentials();
        Map<String, Credential> credentialMap = credentialMgr.list();
        for(String key : credentialMap.keySet())
        {
            System.out.println(credentialMap.get(key).getDescription());
        }
    }
}
```

# Compile & Package

If you want to compile project, you can via `mvn clean compile`

If you want to package project and skip the junit test, you can via `mvn clean package -DskipTest`
