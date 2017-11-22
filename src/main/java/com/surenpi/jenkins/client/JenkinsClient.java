package com.surenpi.jenkins.client;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * 操作Jenkins的基本方法接口
 * @author suren
 */
public interface JenkinsClient {
    public <T extends BaseModel> T get(String path, Class<T> cls) throws IOException;

    public String get(String path) throws IOException;

    public <T extends BaseModel> T getQuietly(String path, Class<T> cls);

    public InputStream getFile(URI path) throws IOException;

    public <R extends BaseModel, D> R post(String path, D data, Class<R> cls) throws IOException;

    public <R extends BaseModel, D> R post(String path, D data, Class<R> cls, boolean crumbFlag) throws IOException;

    public void post_form(String path, Map<String, String> data, boolean crumbFlag) throws IOException;

    public HttpResponse post_form_with_result(String path, List<NameValuePair> data, boolean crumbFlag) throws IOException;

    public String post_xml(String path, String xml_data) throws IOException;

    public String post_xml(String path, String xml_data, boolean crumbFlag) throws IOException;

    public String post_text(String path, String textData, boolean crumbFlag) throws IOException;

    public String post_text(String path, String textData, ContentType contentType, boolean crumbFlag)
            throws IOException;

    public void post(String path) throws IOException;

    public void post(String path, boolean crumbFlag) throws IOException;

    public void post_form_json(String path, Map<String, Object> data, boolean crumbFlag) throws IOException;
}
