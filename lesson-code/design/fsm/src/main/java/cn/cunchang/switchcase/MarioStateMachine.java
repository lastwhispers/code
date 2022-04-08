package cn.cunchang.switchcase;

import java.util.Objects;

public class MarioStateMachine {
    private int score;
    private State currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMushRoom() {
        if (Objects.equals(this.currentState, State.SMALL)) {
            this.score += 100;
            this.currentState = State.SUPER;
        }
    }

    public void obtainCape() {
        if (Objects.equals(this.currentState, State.SMALL) ||
                Objects.equals(this.currentState, State.SUPER)) {
            this.score += 200;
            this.currentState = State.CAPE;
        }
    }

    public void obtainFireFlower() {
        if (Objects.equals(this.currentState, State.SMALL) ||
                Objects.equals(this.currentState, State.SUPER)) {
            this.score += 300;
            this.currentState = State.FIRE;
        }
    }

    public void meetMonster() {
        if (Objects.equals(this.currentState, State.SUPER)) {
            this.score -= 100;
            this.currentState = State.SMALL;
        }
        if (Objects.equals(this.currentState, State.CAPE)) {
            this.score -= 200;
            this.currentState = State.SMALL;
        }
        if (Objects.equals(this.currentState, State.FIRE)) {
            this.score -= 300;
            this.currentState = State.SMALL;
        }
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void print(){
        int score = this.score;
        State state = this.currentState;
        System.out.println("mario score: " + score + "; state: " + state);
    }

}