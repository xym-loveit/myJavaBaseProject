package com.xym.eatuul.filter.route;

import com.xym.eatuul.filter.EatuulFilter;
import com.xym.eatuul.http.RequestContext;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * routeFilter转发请求
 *
 * @author xym
 * @create 2018-09-05 17:48
 */
public class RoutingFilter implements EatuulFilter {
    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public void run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        RequestEntity requestEntity = ctx.getRequestEntity();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.exchange(requestEntity, byte[].class);
        ctx.setResponseEntity(responseEntity);
    }
}
