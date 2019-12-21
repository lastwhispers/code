package cn.lastwhisper.xfq.lambda;

import lombok.Data;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  按DeptId 和 Type进行分组，并将同组内的num求和
 *  //计算 集合中 DeptId 和 Type 相同的数据的num总和
 * @author lastwhisper
 * @date 2019/12/18
 */
public class Test1 {
    private static List<DataBean> totalStocks = new ArrayList<>();

    static {
        DataBean stock1 = new DataBean();
        stock1.setDeptId(2);
        stock1.setType(2);
        stock1.setNum(2);
        totalStocks.add(stock1);

        DataBean stock2 = new DataBean();
        stock2.setDeptId(2);
        stock2.setType(2);
        stock2.setNum(3);
        totalStocks.add(stock2);

        DataBean stock3 = new DataBean();
        stock3.setDeptId(3);
        stock3.setType(3);
        stock3.setNum(5);
        totalStocks.add(stock3);

        DataBean stock4 = new DataBean();
        stock4.setDeptId(3);
        stock4.setType(3);
        stock4.setNum(4);
        totalStocks.add(stock4);

        DataBean stock5 = new DataBean();
        stock5.setDeptId(4);
        stock5.setType(4);
        stock5.setNum(10);
        totalStocks.add(stock5);
    }

    @Data
    private static class DataBean {
        private int type;
        private int deptId;
        private int num;
    }

    public static void main(String[] args) {
        // 先根据DeptId，再根据Type进行分组，最终进行统计
        Map<Integer, Map<Integer, IntSummaryStatistics>> deptIdMapTypeStatisticsMap = totalStocks.stream()
                .collect(Collectors.groupingBy(DataBean::getDeptId,
                        Collectors.groupingBy(DataBean::getType, Collectors.summarizingInt(DataBean::getNum))));
        for (Map.Entry<Integer, Map<Integer, IntSummaryStatistics>> deptIdEntry : deptIdMapTypeStatisticsMap.entrySet()) {
            System.out.println("deptId:"+deptIdEntry.getKey());
            Map<Integer, IntSummaryStatistics> statisticsMap = deptIdEntry.getValue();
            for (Map.Entry<Integer, IntSummaryStatistics> entry : statisticsMap.entrySet()) {
                System.out.println("type:"+deptIdEntry.getKey()+" sum"+entry.getValue().getSum());
            }
        }

    }
}

