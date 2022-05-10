package cn.cunchang.v2;

/**
 * 权重分类
 * Created by qiumu on 2020/10/10.
 */
public class WeightCategory {

    //类别
    private String category;
    //权重值
    private int weight;

    public WeightCategory(String category, int weight) {
        this.category = category;
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;

    }

}
