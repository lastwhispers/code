package cn.cunchang.state.v1;

public class ApplicationDemo {
    public static void main(String[] args) {
        MarioStateMachine mario = new MarioStateMachine();
        // 吃了蘑菇
        mario.obtainMushRoom();
        mario.print();
        // 获得斗篷
        mario.obtainCape();
        mario.print();
        // 遇到怪物
        mario.meetMonster();
        mario.print();
        // 获得火焰
        mario.obtainFireFlower();
        mario.print();
    }

}