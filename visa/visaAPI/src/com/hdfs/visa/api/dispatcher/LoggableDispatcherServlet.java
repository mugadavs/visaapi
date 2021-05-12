package com.hdfs.visa.api.dispatcher;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LoggableDispatcherServlet extends DispatcherServlet {
  private static final Logger log = LogManager.getLogger(LoggableDispatcherServlet.class);

  @Override
  protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (!(request instanceof ContentCachingRequestWrapper)) {
      request = new ContentCachingRequestWrapper(request);
    }
    if (!(response instanceof ContentCachingResponseWrapper)) {
      response = new ContentCachingResponseWrapper(response);
    }
    HandlerExecutionChain handler = getHandler(request);

    try {
      super.doDispatch(request, response);
    } finally {
      log(request, response, handler);
      updateResponse(response);
    }
  }

  private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache, HandlerExecutionChain handler) {
    if (logger.isDebugEnabled()){
      log.log(Level.DEBUG, requestToCache.getMethod() + " " + requestToCache.getRequestURI() + " RESPONSE BODY: " + getResponsePayload(responseToCache));
    } else {
      log.log(Level.INFO, requestToCache.getMethod() + " " + requestToCache.getRequestURI());
    }

  }

  private String getResponsePayload(HttpServletResponse response) {
    ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
    if (wrapper != null) {

      byte[] buf = wrapper.getContentAsByteArray();
      if (buf.length > 0) {
        int length = Math.min(buf.length, 5120);
        try {
          return new String(buf, 0, length, wrapper.getCharacterEncoding());
        }
        catch (UnsupportedEncodingException ex) {
          log.log(Level.ERROR, ex);
        }
      }
    }
    return "[none]";
  }

  private void updateResponse(HttpServletResponse response) throws IOException {
    ContentCachingResponseWrapper responseWrapper =
        WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
    responseWrapper.copyBodyToResponse();
  }

}
