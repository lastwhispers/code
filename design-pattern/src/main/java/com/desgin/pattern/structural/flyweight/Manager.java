package com.desgin.pattern.structural.flyweight;

public class Manager implements Employee {
    /**
     * 内部状态
     */
    private String title = "部门经理";
    /**
     * 外部状态
     */
    private String department;
    private String reportContent;

    public Manager(String department) {
        this.department = department;
    }

    /**
     * 我们可以在外部来重置年终报告
     */
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    @Override
    public void report() {
        System.out.println(reportContent);
    }
}
