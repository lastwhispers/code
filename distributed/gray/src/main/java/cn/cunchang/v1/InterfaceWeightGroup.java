package cn.cunchang.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 */
@Slf4j(topic = "gray")
public class InterfaceWeightGroup {


    /**
     * 权重配置ID
     */
    private Integer id;


    /**
     * 灰度权重对象
     */
    private WeightCategory grayWeightCategory = null;

    /**
     * 正常请求权重对象
     */
    private WeightCategory normalWeightCategory = null;

    /**
     * 灰度请求进来次数
     */
    private int grayRequestAccessNumber = 0;


    /**
     * 权重请求列表
     */
    private List<WeightCategory> categoryList=new ArrayList<>();

    /**
     * 初始化
     */
    private void init(){

        categoryList.add(grayWeightCategory);
        categoryList.add(normalWeightCategory);

    }



    public InterfaceWeightGroup(Integer id,WeightCategory grayWeightCategory,WeightCategory normalWeightCategory){
        this.id = id;
        this.grayWeightCategory = grayWeightCategory;
        this.normalWeightCategory = normalWeightCategory;
        //添加权重到list
        init();
    }

    /**
     * 此次是否是灰度
     * @return
     */
    public boolean accessGray(){

        String categoryName =  getWeight(categoryList);

        if(Constant.GRAY.equals(categoryName)){
            grayRequestAccessNumber ++;
            log.info("id={},grayRequestAccessNumber={}",id,grayRequestAccessNumber);
            return true;
        }

        return false;

    }

    /**
     * 权重获取方法
     * @param categorys
     * @return
     */
    public String getWeight(List<WeightCategory> categorys) {

        if(CollectionUtils.isEmpty(categorys)){
            return Constant.NORMAL;
        }

        Integer weightSum = 0;
        String result=null;
        for (WeightCategory wc : categorys) {
            weightSum += wc.getWeight();
        }

        if (weightSum <= 0) {
            log.error("Error: weightSum=" + weightSum.toString());
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WeightCategory getGrayWeightCategory() {
        return grayWeightCategory;
    }

    public void setGrayWeightCategory(WeightCategory grayWeightCategory) {
        this.grayWeightCategory = grayWeightCategory;
    }

    public WeightCategory getNormalWeightCategory() {
        return normalWeightCategory;
    }

    public void setNormalWeightCategory(WeightCategory normalWeightCategory) {
        this.normalWeightCategory = normalWeightCategory;
    }

    public int getGrayRequestAccessNumber() {
        return grayRequestAccessNumber;
    }

    public void setGrayRequestAccessNumber(int grayRequestAccessNumber) {
        this.grayRequestAccessNumber = grayRequestAccessNumber;
    }




}
