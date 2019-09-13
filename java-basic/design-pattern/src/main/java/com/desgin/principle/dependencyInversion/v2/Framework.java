package com.desgin.principle.dependencyInversion.v2;

/**
 * @author lastwhisper
 */
public class Framework {
    private Bottom bottom;

    public Framework(Bottom bottom) {
        this.bottom = bottom;
    }
}
