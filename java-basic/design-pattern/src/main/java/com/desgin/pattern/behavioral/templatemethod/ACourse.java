package com.desgin.pattern.behavioral.templatemethod;

public abstract class ACourse {
    /** 声明成final就是不希望子类去覆盖这个方法 */
    protected final void makeCourse() {
        this.makePPT();
        this.makeVideo();
        /** 这里是否需要写手记交由勾子方法来决定 */
        if (needWriteArticle()) {
            this.writeArticle();
        }
        this.packageCourse();
    }

    final void makePPT() {
        System.out.println("制作PPT");
    }

    final void makeVideo() {
        System.out.println("制作视频");
    }

    /** 这个编写手记是一个可选项 */
    final void writeArticle() {
        System.out.println("编写手记");
    }

    /** 我们就要给这个编写手记声明一个勾子方法 */
    /** 这个并不是一个final方法，子类是可以进行覆盖的 */
    protected boolean needWriteArticle() {
        return false;
    }

    abstract void packageCourse();

}
