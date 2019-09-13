package com.desgin.principle.dependencyInversion.v1;

/**
 * @author lastwhisper
 */
public class Bottom {
    private Tire tire;

    public Bottom() {
        this.tire = new Tire();
    }
}
