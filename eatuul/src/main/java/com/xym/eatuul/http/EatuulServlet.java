package com.xym.eatuul.http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-05 17:52
 */
@WebServlet(name = "eatuul", urlPatterns = "/*")
public class EatuulServlet extends HttpServlet {

    private EatRunner eatRunner = new EatRunner();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将request，和response放入上下文对象中
        eatRunner.init(request, response);
        try {
            //执行前置过滤
            eatRunner.preRoute();
            //执行过滤
            eatRunner.route();
            //执行后置过滤
            eatRunner.postRoute();
        } catch (Throwable e) {
            RequestContext.getCurrentContext().getResponse()
                    .sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } finally {
            //清除变量
            RequestContext.getCurrentContext().unset();
        }
    }
}
