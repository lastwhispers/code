package cn.lastwhisper.product.controller;

import cn.lastwhisper.product.domain.ProductCategory;
import cn.lastwhisper.product.domain.ProductInfo;
import cn.lastwhisper.product.dto.CartDTO;
import cn.lastwhisper.product.service.CategoryService;
import cn.lastwhisper.product.service.ProductService;
import cn.lastwhisper.product.utils.ResultVOUtil;
import cn.lastwhisper.product.vo.ProductInfoVO;
import cn.lastwhisper.product.vo.ProductVO;
import cn.lastwhisper.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author lastwhisper
 * @date 2019/10/24
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1. 查询所有在架的商品列表
     * 2. 获取类目type列表
     * 3. 根据type列表查询类目列表
     * 4. 根据接口json数据格式构造数据
     */
    @RequestMapping("/list")
    public ResultVO list() {
        //1. 查询所有在架的商品列表
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2. 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //3. 根据type列表查询类目列表
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //4. 根据接口json数据格式构造数据
        // 4.1 第一层：category list
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            // 4.2 第二层：product list
            List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
            for (ProductInfo productInfo : productInfoList) {
                // 只有当类目相同时才将类目下的商品挂载到该类目下
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        // 4.3 最外层
        //ResultVO<List<ProductVO>> resultVO = new ResultVO<>();
        //resultVO.setCode(0);
        //resultVO.setMsg("成功");
        //resultVO.setData(productVOList);

        return ResultVOUtil.success(productVOList);
    }

    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
        //try {
        //    Thread.sleep(2000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        return productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList) {
        productService.decreaseStock(cartDTOList);
    }
}
