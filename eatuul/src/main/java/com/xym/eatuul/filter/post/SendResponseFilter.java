package com.xym.eatuul.filter.post;

import com.xym.eatuul.filter.EatuulFilter;
import com.xym.eatuul.http.RequestContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * postFilters,将请求输出为response
 *
 * @author xym
 * @create 2018-09-05 17:49
 */
public class SendResponseFilter implements EatuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public void run() {
        addResponseHeaders();
        try {
            writeResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeResponse() throws Exception {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse servletResponse = ctx.getResponse();
        if (servletResponse.getCharacterEncoding() == null) { // only set if not set
            servletResponse.setCharacterEncoding("UTF-8");
        }
        ResponseEntity responseEntity = ctx.getResponseEntity();
        if (responseEntity.hasBody()) {
            byte[] body = (byte[]) responseEntity.getBody();
            ServletOutputStream outputStream = servletResponse.getOutputStream();
            outputStream.write(body);
            outputStream.flush();
        }
    }

    private void addResponseHeaders() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse servletResponse = ctx.getResponse();
        ResponseEntity responseEntity = ctx.getResponseEntity();
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String headerName = entry.getKey();
            List<String> headerValues = entry.getValue();
            for (String headerValue : headerValues) {
                servletResponse.addHeader(headerName, headerValue);
            }
        }
    }
}
