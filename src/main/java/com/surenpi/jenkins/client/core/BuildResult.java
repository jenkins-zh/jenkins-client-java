package com.surenpi.jenkins.client.core;

/**
 * @author suren
 */
public enum BuildResult
{
    SUCCESS("SUCCESS"), FAILURE("FAILURE");

    private String name;
    BuildResult(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
