package com.hdfs.visa.api.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LogRequestAndResponseFilter extends OncePerRequestFilter {
  private Logger log = LogManager.getLogger(getClass());
  // put filter at the end of all other filters to make sure we are processing after all others

  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    request = new ContentCachingRequestWrapper(request);
    response = new ContentCachingResponseWrapper(response);

    // pass through filter chain to do the actual request handling
    filterChain.doFilter(request, response);
    // body can only be read after the actual request handling was done!

    log.info(createMessage(request, response));
    updateResponse(response);
  }

  private String getRequestBody(HttpServletRequest request) {
    // wrap request to make sure we can read the body of the request (otherwise it will be consumed by the actual
    // request handler)
    ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
    if (wrapper != null) {
      byte[] buf = wrapper.getContentAsByteArray();
      if (buf.length > 0) {
        String payload;
        try {
          payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
        }
        catch (UnsupportedEncodingException ex) {
          payload = "[unknown]";
        }

        return payload;
      }
    }
    return "[none]";
  }

 protected String createMessage(HttpServletRequest request, HttpServletResponse response) {
    StringBuilder msg = new StringBuilder();
    msg.append("REQUEST: ");
    msg.append(request.getMethod());
    msg.append(" uri=").append(request.getRequestURI());

    String payload;
    payload = request.getQueryString();
    if (payload != null) {
      msg.append('?').append(payload);
    }

    payload = request.getRemoteAddr();
    if (StringUtils.hasLength(payload)) {
      msg.append("; client=").append(payload);
    }

    msg.append("; headers=").append((new ServletServerHttpRequest(request)).getHeaders());

    payload = getRequestBody(request);
    if (payload != null) {
      msg.append("; payload=").append(payload);
    }
    msg.append("\n RESPONSE: ");
    msg.append(response.getStatus());
    msg.append(" ");
    msg.append(getResponsePayload(response));
    return msg.toString();
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
