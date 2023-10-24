package com.lfg.xlapibackend.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfg.xlapibackend.mapper.PaymentInfoMapper;
import com.lfg.xlapibackend.model.entity.PaymentInfo;
import com.lfg.xlapibackend.model.vo.PaymentInfoVo;
import com.lfg.xlapibackend.service.PaymentInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author lfg
 * @Date: 2023/08/23 08:16:24
 * @Version: 1.0
 * @Description: 支付信息服务impl
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

    @Override
    public boolean createPaymentInfo(PaymentInfoVo paymentInfoVo) {
        String transactionId = paymentInfoVo.getTransactionId();
        String tradeType = paymentInfoVo.getTradeType();
        String tradeState = paymentInfoVo.getTradeState();
        String tradeStateDesc = paymentInfoVo.getTradeStateDesc();
        String successTime = paymentInfoVo.getSuccessTime();

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderNo(paymentInfoVo.getOutTradeNo());
        paymentInfo.setTransactionId(transactionId);
        paymentInfo.setTradeType(tradeType);
        paymentInfo.setTradeState(tradeState);
        if (StringUtils.isNotBlank(successTime)) {
            paymentInfo.setSuccessTime(successTime);
        }
        paymentInfo.setTradeStateDesc(tradeStateDesc);
        paymentInfo.setContent(JSONUtil.toJsonStr(paymentInfoVo));
        return this.save(paymentInfo);
    }
}




