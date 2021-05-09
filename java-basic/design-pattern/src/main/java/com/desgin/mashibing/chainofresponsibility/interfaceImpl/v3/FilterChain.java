package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lastwhisper
 */
public class FilterChain implements Filter{

    List<Filter> filters = new ArrayList<Filter>();

    public FilterChain addFilter(Filter f) {
        filters.add(f);
        return this;
    }

    public String doFilter(String str) {
        String r = str;
        for (Filter filter : filters) {
            r = filter.doFilter(r);
        }
        return r;
    }
}
