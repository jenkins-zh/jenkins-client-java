package com.surenpi.jenkins.client.scm;

import com.surenpi.jenkins.client.BaseModel;
import com.surenpi.jenkins.client.core.Author;

import java.util.List;

/**
 * SCM变更记录
 * @author suren
 */
public class ScmChange extends BaseModel
{
    private List<String> affectedPaths;
    private String commitId;
    private long timestamp;
    private Author author;
    private String authorEmail;
    private String comment;
    private String date;
    private String id;
    private String msg;
    private List<ScmPath> paths;

    public List<String> getAffectedPaths()
    {
        return affectedPaths;
    }

    public void setAffectedPaths(List<String> affectedPaths)
    {
        this.affectedPaths = affectedPaths;
    }

    public String getCommitId()
    {
        return commitId;
    }

    public void setCommitId(String commitId)
    {
        this.commitId = commitId;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public String getAuthorEmail()
    {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail)
    {
        this.authorEmail = authorEmail;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public List<ScmPath> getPaths()
    {
        return paths;
    }

    public void setPaths(List<ScmPath> paths)
    {
        this.paths = paths;
    }
}