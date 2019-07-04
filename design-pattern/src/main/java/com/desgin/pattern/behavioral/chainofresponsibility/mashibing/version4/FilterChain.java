package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lastwhisper
 */
public class FilterChain implements Filter {

    List<Filter> filters = new ArrayList<Filter>();

    int index = 0;

    public FilterChain addFilter(Filter f) {
        filters.add(f);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        if (index == filters.size()) return;
        Filter filter = filters.get(index);
        index++;
        filter.doFilter(request, response, chain);
    }
}
