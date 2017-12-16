package com.surenpi.jenkins.client.job;

import com.surenpi.jenkins.client.core.Health;

import java.util.List;

/**
 * @author suren
 */
public class JobDetails extends Job {
    private String description;
    private String displayName;
    private String displayNameOrNull;
    private String fullDisplayName;
    private String fullName;
    private boolean buildable;
    private List<BuildInfo> builds;
    private String color;
    private BuildInfo firstBuild;
    private List<Health> healthReport;
    private boolean inQueue;
    private boolean keepDependencies;
    private BuildInfo lastBuild;
    private BuildInfo lastCompletedBuild;
    private BuildInfo lastFailedBuild;
    private BuildInfo lastStableBuild;
    private BuildInfo lastSuccessfulBuild;
    private BuildInfo lastUnstableBuild;
    private BuildInfo lastUnsuccessfulBuild;
    private int nextBuildNumber;
    private boolean concurrentBuild;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDisplayNameOrNull()
    {
        return displayNameOrNull;
    }

    public void setDisplayNameOrNull(String displayNameOrNull)
    {
        this.displayNameOrNull = displayNameOrNull;
    }

    public String getFullDisplayName()
    {
        return fullDisplayName;
    }

    public void setFullDisplayName(String fullDisplayName)
    {
        this.fullDisplayName = fullDisplayName;
    }

    @Override
    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public boolean isBuildable()
    {
        return buildable;
    }

    public void setBuildable(boolean buildable)
    {
        this.buildable = buildable;
    }

    public List<BuildInfo> getBuilds()
    {
        return builds;
    }

    public void setBuilds(List<BuildInfo> builds)
    {
        this.builds = builds;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public BuildInfo getFirstBuild()
    {
        return firstBuild;
    }

    public void setFirstBuild(BuildInfo firstBuild)
    {
        this.firstBuild = firstBuild;
    }

    public List<Health> getHealthReport()
    {
        return healthReport;
    }

    public void setHealthReport(List<Health> healthReport)
    {
        this.healthReport = healthReport;
    }

    public boolean isInQueue()
    {
        return inQueue;
    }

    public void setInQueue(boolean inQueue)
    {
        this.inQueue = inQueue;
    }

    public boolean isKeepDependencies()
    {
        return keepDependencies;
    }

    public void setKeepDependencies(boolean keepDependencies)
    {
        this.keepDependencies = keepDependencies;
    }

    public BuildInfo getLastBuild()
    {
        return lastBuild;
    }

    public void setLastBuild(BuildInfo lastBuild)
    {
        this.lastBuild = lastBuild;
    }

    public BuildInfo getLastCompletedBuild()
    {
        return lastCompletedBuild;
    }

    public void setLastCompletedBuild(BuildInfo lastCompletedBuild)
    {
        this.lastCompletedBuild = lastCompletedBuild;
    }

    public BuildInfo getLastFailedBuild()
    {
        return lastFailedBuild;
    }

    public void setLastFailedBuild(BuildInfo lastFailedBuild)
    {
        this.lastFailedBuild = lastFailedBuild;
    }

    public BuildInfo getLastStableBuild()
    {
        return lastStableBuild;
    }

    public void setLastStableBuild(BuildInfo lastStableBuild)
    {
        this.lastStableBuild = lastStableBuild;
    }

    public BuildInfo getLastSuccessfulBuild()
    {
        return lastSuccessfulBuild;
    }

    public void setLastSuccessfulBuild(BuildInfo lastSuccessfulBuild)
    {
        this.lastSuccessfulBuild = lastSuccessfulBuild;
    }

    public BuildInfo getLastUnstableBuild()
    {
        return lastUnstableBuild;
    }

    public void setLastUnstableBuild(BuildInfo lastUnstableBuild)
    {
        this.lastUnstableBuild = lastUnstableBuild;
    }

    public BuildInfo getLastUnsuccessfulBuild()
    {
        return lastUnsuccessfulBuild;
    }

    public void setLastUnsuccessfulBuild(BuildInfo lastUnsuccessfulBuild)
    {
        this.lastUnsuccessfulBuild = lastUnsuccessfulBuild;
    }

    public int getNextBuildNumber()
    {
        return nextBuildNumber;
    }

    public void setNextBuildNumber(int nextBuildNumber)
    {
        this.nextBuildNumber = nextBuildNumber;
    }

    public boolean isConcurrentBuild()
    {
        return concurrentBuild;
    }

    public void setConcurrentBuild(boolean concurrentBuild)
    {
        this.concurrentBuild = concurrentBuild;
    }
}