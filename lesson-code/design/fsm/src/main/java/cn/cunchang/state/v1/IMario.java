package cn.cunchang.state.v1;

public interface IMario { //所有状态类的接口
    State getName();

    //以下是定义的事件
    void obtainMushRoom();

    void obtainCape();

    void obtainFireFlower();

    void meetMonster();
}