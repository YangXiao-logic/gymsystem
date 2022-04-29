package com.gym.controller;

import com.gym.common.api.CommonPage;
import com.gym.common.api.CommonResult;
import com.gym.mbg.model.PmsBrand;
import com.gym.mbg.model.Product;
import com.gym.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "ProductController", description = "商品管理")
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @ApiOperation("获取所有商品列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<List<Product>> getBrandList() {
        return CommonResult.success(productService.listAllProduct());
    }

    @ApiOperation("添加商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:create')")
    public CommonResult createBrand(@RequestBody Product product) {
        CommonResult commonResult;
        int count = productService.createProduct(product);
        if (count == 1) {
            commonResult = CommonResult.success(product);
            LOGGER.debug("createProduct success:{}", product);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createProduct failed:{}", product);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id商品信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:update')")
    public CommonResult updateProduct(@PathVariable("id") Long id, @RequestBody Product productDto, BindingResult result) {
        CommonResult commonResult;
        int count = productService.updateProduct(id, productDto);
        if (count == 1) {
            commonResult = CommonResult.success(productDto);
            LOGGER.debug("updateProduct success:{}", productDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateProduct failed:{}", productDto);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    public CommonResult deleteProduct(@PathVariable("id") Long id) {
        int count = productService.deleteProduct(id);
        if (count == 1) {
            LOGGER.debug("deleteProduct success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteProduct failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("分页查询商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<CommonPage<Product>> listProduct(@RequestParam(value = "pageNum", defaultValue = "1")
                                                        @ApiParam("页码") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3")
                                                        @ApiParam("每页数量") Integer pageSize) {
        List<Product> brandList = productService.listProduct(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation("获取指定id的商品详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public CommonResult<Product> brand(@PathVariable("id") Long id) {
        return CommonResult.success(productService.getProduct(id));
    }

}
