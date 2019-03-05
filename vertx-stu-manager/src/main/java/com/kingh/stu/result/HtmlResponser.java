package com.kingh.stu.result;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/3/5 13:57
 */
public class HtmlResponser implements ResponseHandler {

    @Override
    public void handle(Result result, RoutingContext context) {
        String page = result.getView();
        if(StringUtils.isBlank(page)) {
            page = "/404.html";
            context.reroute(page);
            return;
        }

        String path = "/page" + page;
        context.put("data", result.getData());
        context.put("name","小红");
        context.reroute(path);
    }
}
