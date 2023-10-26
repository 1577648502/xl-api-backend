package com.lfg.xlapibackend.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ijpay.alipay.AliPayApi;
import com.lfg.xlapibackend.common.ErrorCode;
import com.lfg.xlapibackend.exception.BusinessException;
import com.lfg.xlapibackend.model.entity.User;
import com.lfg.xlapibackend.model.enums.InterfaceStatusEnum;
import com.lfg.xlapicommon.model.entity.InterfaceInfo;
import com.lfg.xlapicommon.model.vo.UserVO;
import com.lfg.xlapicommon.service.inner.InnerUserInterfaceInvokeService;
import com.lfg.xlapisdk.client.XlApiClient;
import com.lfg.xlapisdk.model.request.CurrencyRequest;
import com.lfg.xlapisdk.model.response.ResultResponse;
import com.lfg.xlapisdk.service.ApiService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;


/**
 * 用户服务测试
 *
 * @author lfg
 */
@SpringBootTest
class UserServiceTest {

    /**
     * 商户API私钥路径
     */

    private static final String Secret = "b81f683a36eccba0f8656dc402281d12";
    /**
     * 商户号
     */
    public static String merchantId = "1609724068";
    public static String privateKeyPath = "D:\\Program Files\\WXCertUtil\\cert\\1609724068_20230822_cert\\apiclient_key.pem";
    /**
     * 商户证书序列号
     */
    public static String merchantSerialNumber = "763256B437DFF0C8BEBD27E012BFFD134FDBD9A6";
    /**
     * 商户APIV3密钥
     */
    public static String apiV3Key = "bf389934bf389934bf389934bf389934";

    @Resource
    private UserService userService;
    @Resource
    private InterfaceInfoService interfaceInfoService;
    @Resource
    private InnerUserInterfaceInvokeService innerUserInterfaceInvokeService;

    @Test
    void getCaptcha() {
        String captcha = RandomUtil.randomNumbers(6);
        System.err.println(captcha);
    }

    @Test
    void invoke() {
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(1697274658264342530L);

        User user = userService.getById(1691069533871013889L);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        // userVO.setBalance(0);
        // userVO.setStatus(UserAccountStatusEnum.BAN.getValue());
        InterfaceInfo newInterfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfo, newInterfaceInfo);
        boolean invoke = innerUserInterfaceInvokeService.invoke(newInterfaceInfo.getId(), user.getId(), interfaceInfo.getReduceScore());
        Assertions.assertTrue(invoke);
    }

    @Test
    void date() {

        Date date = DateUtil.date(System.currentTimeMillis());
        System.err.println(date + "=data");
        DateTime offset = DateUtil.offset(date, DateField.MINUTE, 5);
        System.err.println(offset + "=offset+5");

        String format = DateUtil.format(offset, "yyyy-MM-dd'T'HH:mm:ssXXX");
        System.err.println(format);

        // System.err.println(RandomUtil.randomNumbers(20));
    }

    @Test
    void header() {
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(1697274658264342530L);
        interfaceInfo.setRequestHeader(null);
        String requestHeader = interfaceInfo.getRequestHeader();
        Map<String, String> headerMap = new Gson().fromJson(requestHeader, new TypeToken<Map<String, String>>() {
        }.getType());
        if ((ObjectUtils.anyNull(headerMap, headerMap.get("Content-Type"))) || !headerMap.get("Content-Type").equals("application/json")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求头/响应头必须是JSON格式");
        }
    }

    @SneakyThrows

    @Test
    void refund() throws AlipayApiException {
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo("order_44952882910123906867");
        model.setRefundAmount("0.01");
        model.setRefundReason("重复购买");
        AlipayTradeRefundResponse alipayTradeRefundResponse = AliPayApi.tradeRefundToResponse(model);
        System.err.println(alipayTradeRefundResponse);
    }

    @Test
    void testAddUser() {
        User user = new User();
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        boolean result = userService.updateById(user);
        Assertions.assertTrue(result);
    }

    @Test
    void testDeleteUser() {
        boolean result = userService.removeById(1L);
        Assertions.assertTrue(result);
    }

    @Test
    void testGetUser() {
        User user = userService.getById(1L);
        Assertions.assertNotNull(user);
    }
    @Resource
    private ApiService apiService;
    private final Gson gson = new Gson();
    @Test
    void invokeInterface() {
        Long id = 1705239469589733378L;
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if (interfaceInfo.getStatus() != InterfaceStatusEnum.ONLINE.getValue()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口未开启");
        }
        // 构建请求参数
        String requestParams = "{\n" +
                "  \"id\": \"1705239469589733378\"\n" +
                "}";
        Map<String, Object> params = new Gson().fromJson(requestParams, new TypeToken<Map<String, Object>>() {
        }.getType());
//        com.lfg.xlapibackend.model.vo.UserVO loginUser = userService.getLoginUser(request);
        String accessKey = "d6e8355787fb5dc52f7833d0c6d06494";
        String secretKey = "6decf70188bdbf9ea7b14fd7f7b0e0dd";
        try {
            XlApiClient xlApiClient = new XlApiClient(accessKey, secretKey);
            CurrencyRequest currencyRequest = new CurrencyRequest();
            currencyRequest.setMethod(interfaceInfo.getMethod());
            currencyRequest.setPath(interfaceInfo.getUrl());
            currencyRequest.setRequestParams(params);
            ResultResponse response = apiService.request(xlApiClient, currencyRequest);
            System.out.println(response.getData());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }
    }
}