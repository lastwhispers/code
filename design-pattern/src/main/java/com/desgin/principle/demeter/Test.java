package com.desgin.principle.demeter;

/**
 * Create by lastwhisper on 2019/1/22
 */
public class Test {
    public static void main(String[] args){
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        boss.commandCheckNumber(teamLeader);
    }
}
