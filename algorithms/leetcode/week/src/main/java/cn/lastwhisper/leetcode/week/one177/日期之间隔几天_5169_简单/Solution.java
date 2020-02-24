package cn.lastwhisper.leetcode.week.one177.日期之间隔几天_5169_简单;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class Solution {
    public int daysBetweenDates(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int count = 0;
        try {
            long to = sdf.parse(date1).getTime();
            long from = sdf.parse(date2).getTime();
            count = (int) ((to - from) / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Math.abs(count);
    }

    public static void main(String[] args) {
        String date1 = "2019-06-29", date2 = "2019-06-30";
        System.out.println(new Solution().daysBetweenDates(date1, date2));
    }
}