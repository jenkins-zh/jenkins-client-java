package com.surenpi.jenkins.client.plugin;

import com.surenpi.jenkins.client.BaseModel;

/**
 * @author suren
 */
public class Plugin extends BaseModel
{
    private boolean active;
    private String backupVersion;
    private boolean bundled;
    private boolean downgradable;
    private boolean enabled;
    private boolean hasUpdate;
    private String longName;
    private boolean pinned;
    private String shortName;
    private String supportsDynamicLoad; // YesNoMayBe
    private String url;
    private String version;

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public String getBackupVersion()
    {
        return backupVersion;
    }

    public void setBackupVersion(String backupVersion)
    {
        this.backupVersion = backupVersion;
    }

    public boolean isBundled()
    {
        return bundled;
    }

    public void setBundled(boolean bundled)
    {
        this.bundled = bundled;
    }

    public boolean isDowngradable()
    {
        return downgradable;
    }

    public void setDowngradable(boolean downgradable)
    {
        this.downgradable = downgradable;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public boolean isHasUpdate()
    {
        return hasUpdate;
    }

    public void setHasUpdate(boolean hasUpdate)
    {
        this.hasUpdate = hasUpdate;
    }

    public String getLongName()
    {
        return longName;
    }

    public void setLongName(String longName)
    {
        this.longName = longName;
    }

    public boolean isPinned()
    {
        return pinned;
    }

    public void setPinned(boolean pinned)
    {
        this.pinned = pinned;
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public String getSupportsDynamicLoad()
    {
        return supportsDynamicLoad;
    }

    public void setSupportsDynamicLoad(String supportsDynamicLoad)
    {
        this.supportsDynamicLoad = supportsDynamicLoad;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }
}