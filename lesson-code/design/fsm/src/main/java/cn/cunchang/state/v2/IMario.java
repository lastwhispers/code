package cn.cunchang.state.v2;

public interface IMario { //所有状态类的接口
    State getName();

    //以下是定义的事件
    void obtainMushRoom(MarioStateMachine stateMachine);

    void obtainCape(MarioStateMachine stateMachine);

    void obtainFireFlower(MarioStateMachine stateMachine);

    void meetMonster(MarioStateMachine stateMachine);
}