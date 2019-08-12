package com.desgin.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lastwhisper on 2019/1/22
 */
public class Boss {
    public void commandCheckNumber(TeamLeader teamLeader) {
        teamLeader.checkNumberOfCourse();
    }
}
