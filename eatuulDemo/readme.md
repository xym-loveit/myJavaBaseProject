#仿zuul自定义服务网关实现
原理为：定义一个Servlet接收请求。然后经过preFilter(封装请求参数),routeFilter(转发请求)，postFilter(输出内容)。三个过滤器之间，共享request、response以及其他的一些全局变量

调用顺序不变，按照PreFilters->RoutingFilters->PostFilters的顺序调用