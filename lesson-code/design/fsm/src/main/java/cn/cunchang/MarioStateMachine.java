package cn.cunchang;

public class MarioStateMachine {
    private int score;
    private State currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMushRoom() {
        //TODO
    }

    public void obtainCape() {
        //TODO
    }

    public void obtainFireFlower() {
        //TODO
    }

    public void meetMonster() {
        //TODO
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void print() {
        int score = this.score;
        State state = this.currentState;
        System.out.println("mario score: " + score + "; state: " + state);
    }

}