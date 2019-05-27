package com.desgin.pattern.behavioral.memento;

/**
 * Create by lastwhisper on 2019/2/11
 */
public class Test {
    public static void main(String[] args) {
        ArticleMementoManager articleMementoManager = new ArticleMementoManager();
        Article article = new Article("设计模式笔记A", "笔记内容A", "图片A");
        System.out.println("----------创建笔记A成功------------");
        ArticleMemento articleMemento = article.saveToMemento();
        articleMementoManager.addMemento(articleMemento);
        System.out.println("----------笔记A保存成功-------------");
        System.out.print("笔记A内容");
        System.out.println(articleMemento);
        //修改笔记
        System.out.println("----------修改笔记A ing-------------");
        article.setTitle("设计模式笔记B");
        article.setContent("笔记内容B");
        article.setImgs("图片B");
        System.out.println("----------修改笔记B end-------------");
        articleMemento = article.saveToMemento();
        articleMementoManager.addMemento(articleMemento);
        System.out.println("----------A->B保存成功--------------");
        System.out.print("笔记B内容");
        System.out.println(articleMemento);

        System.out.println("----------修改笔记B ing-------------");
        article.setTitle("设计模式笔记C");
        article.setContent("笔记内容C");
        article.setImgs("图片C");
        System.out.println("----------修改笔记C end-------------");
        System.out.println("----------B->C未保存--------------");
        System.out.println("----------撤回一次--------------");
        articleMemento = articleMementoManager.getMemento();
        article.undoFromMemento(articleMemento);
        System.out.println("----------撤回两次--------------");
        articleMemento = articleMementoManager.getMemento();
        article.undoFromMemento(articleMemento);
        System.out.println("----------此时笔记-------------");
        System.out.println(articleMemento);
    }
}
