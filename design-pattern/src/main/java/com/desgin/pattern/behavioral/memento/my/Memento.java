package com.desgin.pattern.behavioral.memento.my;

class Memento {
    private int bloodFlow;
    private int magicPoint;

    public int getBloodFlow() {
        return bloodFlow;
    }

    public int getMagicPoint() {
        return magicPoint;
    }

    public Memento(int bloodFlow,int magicPoint){
        this.bloodFlow = bloodFlow;
        this.magicPoint = magicPoint;
    }
}
