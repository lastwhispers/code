package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lastwhisper
 */
public class FilterChain implements Filter {

    private List<Filter> filters = new ArrayList<Filter>();

    private int index = 0;

    public FilterChain addFilter(Filter f) {
        filters.add(f);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        if (index == filters.size()) return;
        filters.get(index++).doFilter(request, response, chain);
    }
}
