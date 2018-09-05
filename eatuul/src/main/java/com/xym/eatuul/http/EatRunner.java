package com.xym.eatuul.http;

import com.xym.eatuul.filter.EatuulFilter;
import com.xym.eatuul.filter.post.SendResponseFilter;
import com.xym.eatuul.filter.pre.RequestWrapperFilter;
import com.xym.eatuul.filter.route.RoutingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 具体执行器
 * <p>
 * 需要说明一下，在Zuul中，ZuulRunner在获取具体有哪些过滤器的时候，
 * 有一个FileLoader可以动态读取配置加载。
 * 博主在实现我们自己的EatuulRunner时候，略去动态读取的过程，直接静态写死
 *
 * @author xym
 * @create 2018-09-05 17:55
 */
public class EatRunner {

    private ConcurrentHashMap<String, List<EatuulFilter>> hashFiltersByType = new ConcurrentHashMap<String, List<EatuulFilter>>() {{
        put("pre", new ArrayList<EatuulFilter>() {{
            add(new RequestWrapperFilter());
        }});
        put("route", new ArrayList<EatuulFilter>() {{
            add(new RoutingFilter());
        }});
        put("post", new ArrayList<EatuulFilter>() {{
            add(new SendResponseFilter());
        }});
    }};

    /**
     * 初始化请求和响应
     *
     * @param req
     * @param resp
     */
    public void init(HttpServletRequest req, HttpServletResponse resp) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setRequest(req);
        ctx.setResponse(resp);
    }

    public void preRoute() throws Throwable {
        runFilters("pre");
    }

    public void route() throws Throwable {
        runFilters("route");
    }

    public void postRoute() throws Throwable {
        runFilters("post");
    }

    private void runFilters(String sType) {
        List<EatuulFilter> list = this.hashFiltersByType.get(sType);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                EatuulFilter zuulFilter = list.get(i);
                zuulFilter.run();
            }
        }
    }

}
