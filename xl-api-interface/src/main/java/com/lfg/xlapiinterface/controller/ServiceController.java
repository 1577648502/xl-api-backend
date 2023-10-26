package com.lfg.xlapiinterface.controller;

import cn.hutool.json.JSONUtil;
import com.lfg.xlapiinterface.utils.RequestUtils;
import com.lfg.xlapiinterface.utils.ResponseUtils;
import com.lfg.xlapisdk.exception.ApiException;
import com.lfg.xlapisdk.model.params.*;
import com.lfg.xlapisdk.model.response.NameResponse;
import com.lfg.xlapisdk.model.response.RandomWallpaperResponse;
import com.lfg.xlapisdk.model.response.ResultResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.lfg.xlapiinterface.utils.RequestUtils.get;


/**
 * @author lfg
 * @Date: 2023年08月16日 11:29
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/")
public class ServiceController {
    @GetMapping("/name")
    public NameResponse getName(NameParams nameParams) {
        return JSONUtil.toBean(JSONUtil.toJsonStr(nameParams), NameResponse.class);
    }

    @GetMapping("/loveTalk")
    public String randomLoveTalk() {
        return RequestUtils.get("https://api.vvhan.com/api/love");
    }

    @GetMapping("/poisonousChickenSoup")
    public String getPoisonousChickenSoup() {
        return RequestUtils.get("https://api.btstu.cn/yan/api.php?charset=utf-8&encode=json");
    }

    @GetMapping("/randomWallpaper")
    public RandomWallpaperResponse randomWallpaper(RandomWallpaperParams randomWallpaperParams) throws ApiException {
        String baseUrl = "https://api.btstu.cn/sjbz/api.php";
        String url = RequestUtils.buildUrl(baseUrl, randomWallpaperParams);
        if (StringUtils.isAllBlank(randomWallpaperParams.getLx(), randomWallpaperParams.getMethod())) {
            url = url + "?format=json";
        } else {
            url = url + "&format=json";
        }
        return JSONUtil.toBean(RequestUtils.get(url), RandomWallpaperResponse.class);
    }

    @GetMapping("/horoscope")
    public ResultResponse getHoroscope(HoroscopeParams horoscopeParams) throws ApiException {
        String response = RequestUtils.get("https://api.vvhan.com/api/horoscope", horoscopeParams);
        Map<String, Object> fromResponse = ResponseUtils.responseToMap(response);
        boolean success = (boolean) fromResponse.get("success");
        if (!success) {
            ResultResponse baseResponse = new ResultResponse();
            baseResponse.setData(fromResponse);
            return baseResponse;
        }
        return JSONUtil.toBean(response, ResultResponse.class);
    }

    @GetMapping("/ipInfo")
    public ResultResponse getIpInfo(IpInfoParams ipInfoParams) {
        return ResponseUtils.baseResponse("https://api.vvhan.com/api/getIpInfo", ipInfoParams);
    }

    @GetMapping("/weather")
    public ResultResponse getWeatherInfo(WeatherParams weatherParams) {
        return ResponseUtils.baseResponse("https://api.vvhan.com/api/weather", weatherParams);
    }
    @GetMapping("/delivery")
    public ResultResponse getDelivery(DeliveryParams deliveryParams) {
        return ResponseUtils.baseResponse("https://api.oioweb.cn/api/common/delivery", deliveryParams);
    }
    @GetMapping("/qZoneVisitor")
    public ResultResponse getQZoneVisitor(QzoneVisitorParams qzoneVisitorParams) {
        return ResponseUtils.baseResponse("https://api.oioweb.cn/api/qq/QzoneVisitor", qzoneVisitorParams);
    }
    @GetMapping("/oneDayEnglish")
    public String getOneDayEnglish()  {
        return RequestUtils.get("https://api.oioweb.cn/api/common/OneDayEnglish");
    }
    @GetMapping("/hotList")
    public String getHotList()  {
        return RequestUtils.get("https://api.oioweb.cn/api/common/HotList");
    }
    @GetMapping("/gasolinePriceQuery")
    public String getGasolinePriceQuery()  {
        return RequestUtils.get("https://api.oioweb.cn/api/common/GasolinePriceQuery");
    }
    @GetMapping("/history")
    public String getHistory()  {
        return RequestUtils.get("https://api.oioweb.cn/api/common/history");
    }
}
