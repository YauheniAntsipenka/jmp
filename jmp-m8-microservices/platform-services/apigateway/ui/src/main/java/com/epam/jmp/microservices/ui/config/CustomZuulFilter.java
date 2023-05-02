package com.epam.jmp.microservices.ui.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

/**
 * CustomZuulFilter
 * Date: 03/19/2023
 *
 * @author Yauheni Antsipenka
 */
@Component
public class CustomZuulFilter extends ZuulFilter {

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("Test", "TestSample");
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return 1110;
    }

    @Override
    public String filterType() {
        return "pre";
    }

}
