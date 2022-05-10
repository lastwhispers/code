package cn.cunchang.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 */
public class WeightTest {

    public static void main(String[] args) throws Exception{

        //测试数据
        List<WeightCategory> categoryList=new ArrayList<>();
        WeightCategory weightCategory1=new WeightCategory("一等奖",100);
        WeightCategory weightCategory2=new WeightCategory("二等奖",900);
        categoryList.add(weightCategory1);
        categoryList.add(weightCategory2);

        String result= "";
        int a1=0,a2=0;
        for (int i=0;i<1000;i++){
            if(i%50 ==0){
                System.out.println("sleep ");
                Thread.currentThread().sleep(5000L);
            }
            result = getWeight(categoryList);
            System.out.println(i+"  开奖结果： "+result);
            if(result.equals("一等奖")){
                a1++;
            }
            else if(result.equals("二等奖")){
                a2++;
            }
        }

        System.out.println("一等奖共出现 "+a1);
        System.out.println("二等奖共出现 "+a2);


    }

    /**
     * 权重获取方法
     * @param categorys
     * @return
     */
    public static String getWeight(List<WeightCategory> categorys) {
        Integer weightSum = 0;
        String result=null;
        for (WeightCategory wc : categorys) {
            weightSum += wc.getWeight();
        }

        if (weightSum <= 0) {
            System.err.println("Error: weightSum=" + weightSum.toString());
            return result;
        }
        Random random = new Random();
        Integer n = random.nextInt(weightSum); // n in [0, weightSum)
        Integer m = 0;
        for (WeightCategory wc : categorys) {
            if (m <= n && n < m + wc.getWeight()) {
                result=wc.getCategory();
                break;
            }
            m += wc.getWeight();
        }
        return result;
    }


}
