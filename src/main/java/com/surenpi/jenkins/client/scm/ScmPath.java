package com.surenpi.jenkins.client.scm;

import com.surenpi.jenkins.client.BaseModel;

/**
 * @author suren
 */
public class ScmPath extends BaseModel
{
    private String editType;
    private String file;

    public String getEditType()
    {
        return editType;
    }

    public void setEditType(String editType)
    {
        this.editType = editType;
    }

    public String getFile()
    {
        return file;
    }

    public void setFile(String file)
    {
        this.file = file;
    }
}