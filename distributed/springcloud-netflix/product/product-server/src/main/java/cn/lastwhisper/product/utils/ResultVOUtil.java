package cn.lastwhisper.product.utils;

import cn.lastwhisper.product.vo.ResultVO;

/**
 * http响应结果集工具类
 * @author lastwhisper
 * @date 2019/10/26
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

}
