package com.desgin.principle.dependencyInversion.v1;

/**
 * @author lastwhisper
 */
public class Framework {
    private Bottom bottom;

    public Framework() {
        this.bottom = new Bottom();
    }
}
