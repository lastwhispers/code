package cn.cunchang.state.v2;

public enum State {
    SMALL(0),
    SUPER(1),
    CAPE(2),
    FIRE(3),
    ;
    private int value;

    private State(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}