package com.surenpi.jenkins.client.plugin;

import com.surenpi.jenkins.client.BaseManager;

import java.io.IOException;
import java.util.List;

/**
 * 插件管理
 * @author suren
 */
public class Plugins extends BaseManager
{
    /**
     * This will give you back the {@link PluginManager}.
     *
     * @return {@link PluginManager}
     * @throws IOException in case of a failure.
     */
    public PluginManager getPluginManager() throws IOException
    {
        return getClient().get("pluginManager/?depth=2", PluginManager.class);
    }

    public boolean pluginsExists(String[] pluginArray) throws IOException
    {
        if(pluginArray == null)
        {
            return true;
        }

        PluginManager pluginMgr = getPluginManager();
        List<Plugin> pluginList = pluginMgr.getPlugins();
        if(pluginList == null)
        {
            return false;
        }

        for(String pluginName : pluginArray)
        {
            boolean exists = false;
            for(Plugin plugin : pluginList)
            {
                if(plugin.getShortName().equals(pluginName))
                {
                    exists = true;
                    break;
                }
            }

            if(!exists)
            {
                return false;
            }
        }

        return true;
    }

    public void upload()
    {
//        http://localhost:8080/jenkins/pluginManager/uploadPlugin
    }

    @Override
    protected String[] getDependencyArray()
    {
        return null;
    }
}