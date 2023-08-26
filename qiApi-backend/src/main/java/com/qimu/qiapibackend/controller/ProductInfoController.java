package com.qimu.qiapibackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qimu.qiapibackend.annotation.AuthCheck;
import com.qimu.qiapibackend.common.BaseResponse;
import com.qimu.qiapibackend.common.DeleteRequest;
import com.qimu.qiapibackend.common.ErrorCode;
import com.qimu.qiapibackend.common.ResultUtils;
import com.qimu.qiapibackend.constant.CommonConstant;
import com.qimu.qiapibackend.exception.BusinessException;
import com.qimu.qiapibackend.model.dto.productInfo.ProductInfoAddRequest;
import com.qimu.qiapibackend.model.dto.productInfo.ProductInfoQueryRequest;
import com.qimu.qiapibackend.model.dto.productInfo.ProductInfoSearchTextRequest;
import com.qimu.qiapibackend.model.dto.productInfo.ProductInfoUpdateRequest;
import com.qimu.qiapibackend.model.entity.ProductInfo;
import com.qimu.qiapibackend.model.entity.User;
import com.qimu.qiapibackend.service.ProductInfoService;
import com.qimu.qiapibackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.qimu.qiapibackend.constant.UserConstant.ADMIN_ROLE;

/**
 * 帖子接口
 *
 * @author qimu
 */
@RestController
@RequestMapping("/productInfo")
@Slf4j
public class ProductInfoController {

    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private UserService userService;


    // region 增删改查

    /**
     * 添加接口信息
     * 创建
     *
     * @param productInfoAddRequest 接口信息添加请求
     * @param request               请求
     * @return {@link BaseResponse}<{@link Long}>
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = ADMIN_ROLE)
    public BaseResponse<Long> addProductInfo(@RequestBody ProductInfoAddRequest productInfoAddRequest, HttpServletRequest request) {
        if (productInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productInfoAddRequest, productInfo);
        // 校验
        productInfoService.validProductInfo(productInfo, true);
        User loginUser = userService.getLoginUser(request);
        productInfo.setUserId(loginUser.getId());
        boolean result = productInfoService.save(productInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newProductInfoId = productInfo.getId();
        return ResultUtils.success(newProductInfoId);
    }

    /**
     * 删除接口信息
     *
     * @param deleteRequest 删除请求
     * @param request       请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = ADMIN_ROLE)
    public BaseResponse<Boolean> deleteProductInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (ObjectUtils.anyNull(deleteRequest, deleteRequest.getId()) || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        ProductInfo oldProductInfo = productInfoService.getById(id);
        if (oldProductInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!oldProductInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = productInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新接口信息
     * 更新
     *
     * @param productInfoUpdateRequest 接口信息更新请求
     * @param request                  请求
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = ADMIN_ROLE)
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<Boolean> updateProductInfo(@RequestBody ProductInfoUpdateRequest productInfoUpdateRequest,
                                                   HttpServletRequest request) {
        if (ObjectUtils.anyNull(productInfoUpdateRequest, productInfoUpdateRequest.getId()) || productInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(productInfoUpdateRequest, productInfo);
        // 参数校验
        productInfoService.validProductInfo(productInfo, false);
        User user = userService.getLoginUser(request);
        long id = productInfoUpdateRequest.getId();
        // 判断是否存在
        ProductInfo oldProductInfo = productInfoService.getById(id);
        if (oldProductInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!userService.isAdmin(request) && !oldProductInfo.getUserId().equals(user.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = productInfoService.updateById(productInfo);
        return ResultUtils.success(result);
    }

    /**
     * 通过id获取接口信息
     *
     * @param id id
     * @return {@link BaseResponse}<{@link ProductInfo}>
     */
    @GetMapping("/get")
    public BaseResponse<ProductInfo> getProductInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductInfo productInfo = productInfoService.getById(id);
        return ResultUtils.success(productInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param productInfoQueryRequest 接口信息查询请求
     * @return {@link BaseResponse}<{@link List}<{@link ProductInfo}>>
     */
    @AuthCheck(mustRole = ADMIN_ROLE)
    @GetMapping("/list")
    public BaseResponse<List<ProductInfo>> listProductInfo(ProductInfoQueryRequest productInfoQueryRequest) {
        if (productInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductInfo productInfoQuery = new ProductInfo();
        BeanUtils.copyProperties(productInfoQueryRequest, productInfoQuery);

        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>(productInfoQuery);
        List<ProductInfo> productInfoList = productInfoService.list(queryWrapper);
        return ResultUtils.success(productInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param productInfoQueryRequest 接口信息查询请求
     * @param request                 请求
     * @return {@link BaseResponse}<{@link Page}<{@link ProductInfo}>>
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<ProductInfo>> listProductInfoByPage(ProductInfoQueryRequest productInfoQueryRequest, HttpServletRequest request) {
        if (productInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        ProductInfo productInfoQuery = new ProductInfo();
        BeanUtils.copyProperties(productInfoQueryRequest, productInfoQuery);
        long size = productInfoQueryRequest.getPageSize();
        String sortField = productInfoQueryRequest.getSortField();
        String sortOrder = productInfoQueryRequest.getSortOrder();

        String name = productInfoQueryRequest.getName();
        long current = productInfoQueryRequest.getCurrent();
        String description = productInfoQueryRequest.getDescription();
        String productType = productInfoQueryRequest.getProductType();

        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name)
                .or()
                .like(StringUtils.isNotBlank(description), "description", description)
                .eq(ObjectUtils.isNotEmpty(productType), "productType", productType);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<ProductInfo> productInfoPage = productInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(productInfoPage);
    }

    /**
     * 分页获取列表
     *
     * @param productInfoQueryRequest 接口信息查询请求
     * @param request                 请求
     * @return {@link BaseResponse}<{@link Page}<{@link ProductInfo}>>
     */
    @GetMapping("/get/searchText")
    public BaseResponse<Page<ProductInfo>> listProductInfoBySearchTextPage(ProductInfoSearchTextRequest productInfoQueryRequest, HttpServletRequest request) {
        if (productInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductInfo productInfoQuery = new ProductInfo();
        BeanUtils.copyProperties(productInfoQueryRequest, productInfoQuery);

        String searchText = productInfoQueryRequest.getSearchText();
        long size = productInfoQueryRequest.getPageSize();
        long current = productInfoQueryRequest.getCurrent();
        String sortField = productInfoQueryRequest.getSortField();
        String sortOrder = productInfoQueryRequest.getSortOrder();

        QueryWrapper<ProductInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(searchText), "name", searchText)
                .or()
                .like(StringUtils.isNotBlank(searchText), "description", searchText);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<ProductInfo> productInfoPage = productInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(productInfoPage);
    }
    // endregion
}
