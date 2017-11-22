package com.surenpi.jenkins.client.util;

import com.google.common.net.UrlEscapers;

public abstract class EncodingUtils {

    public static String encode(String pathPart) {
        // jenkins doesn't like the + for space, use %20 instead
        String escape = UrlEscapers.urlPathSegmentEscaper().escape(pathPart);
        return escape;
    }

    public static String encodeParam(String pathPart) {
        // jenkins doesn't like the + for space, use %20 instead
        return UrlEscapers.urlFormParameterEscaper().escape(pathPart);
    }

}
