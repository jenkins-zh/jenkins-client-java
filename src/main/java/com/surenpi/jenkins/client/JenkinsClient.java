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
    <T extends BaseModel> T get(String path, Class<T> cls) throws IOException;

    <T extends BaseModel> List<T> getList(String path, Class<T> itemCls) throws IOException;

    String get(String path) throws IOException;

    <T extends BaseModel> T getQuietly(String path, Class<T> cls);

    InputStream getFile(URI path) throws IOException;

    <R extends BaseModel, D> R post(String path, D data, Class<R> cls) throws IOException;

    <R extends BaseModel, D> R post(String path, D data, Class<R> cls, boolean crumbFlag) throws IOException;

    void postForm(String path, Map<String, String> data, boolean crumbFlag) throws IOException;

    HttpResponse postFormWithResult(String path, List<NameValuePair> data, boolean crumbFlag) throws IOException;

    String postXml(String path, String xml_data) throws IOException;

    String postXml(String path, String xml_data, boolean crumbFlag) throws IOException;

    String postText(String path, String textData, boolean crumbFlag) throws IOException;

    String postText(String path, String textData, ContentType contentType, boolean crumbFlag)
            throws IOException;

    void post(String path) throws IOException;

    void post(String path, boolean crumbFlag) throws IOException;

    void postFormJson(String path, Map<String, Object> data, boolean crumbFlag) throws IOException;
}
