package com.lfg.xlapiinterface.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qimuu.easyweb.common.ErrorCode;
import com.qimuu.easyweb.exception.BusinessException;
import com.lfg.xlapisdk.exception.ApiException;
import com.lfg.xlapisdk.model.response.ResultResponse;

import java.util.Map;

import static com.lfg.xlapiinterface.utils.RequestUtils.get;


/**
 * @author lfg
 * @Date: 2023年09月22日 17:18
 * @Version: 1.0
 * @Description:
 */
public class ResponseUtils {
    public static Map<String, Object> responseToMap(String response) {
        return new Gson().fromJson(response, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public static <T> ResultResponse baseResponse(String baseUrl, T params) {
        String response = null;
        try {
            response = get(baseUrl, params);
            Map<String, Object> fromResponse = responseToMap(response);
            Object code = "400";
            if (fromResponse.get("code")!=null){
                code = fromResponse.get("code").toString();
            }

            boolean success = false;
            if (fromResponse.get("success") != null) {
                success = (boolean) fromResponse.get("success");
            }
            ResultResponse baseResponse = new ResultResponse();
            if (!success || !code.equals("200.0")) {
                baseResponse.setData(fromResponse);
                return baseResponse;
            }
            fromResponse.remove("success");
            baseResponse.setData(fromResponse);
            return baseResponse;
        } catch (ApiException e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "构建url异常");
        }
    }
}
