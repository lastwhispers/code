package com.desgin.pattern.behavioral.memento;

import java.util.Stack;

/**
 * Create by lastwhisper on 2019/2/11
 */
public class ArticleMementoManager {
    private final Stack<ArticleMemento> ARTICLE_MEMENTO_STACK = new Stack<>();

    public ArticleMemento getMemento() {
        return ARTICLE_MEMENTO_STACK.pop();
    }

    public void addMemento(ArticleMemento articleMemento) {
        ARTICLE_MEMENTO_STACK.push(articleMemento);
    }
}
