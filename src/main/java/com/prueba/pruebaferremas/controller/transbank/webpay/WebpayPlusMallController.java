package com.prueba.pruebaferremas.controller.transbank.webpay;

import cl.transbank.common.IntegrationApiKeys;
import cl.transbank.common.IntegrationCommerceCodes;
import cl.transbank.common.IntegrationType;
import cl.transbank.model.MallTransactionCreateDetails;
import cl.transbank.webpay.common.WebpayOptions;
import cl.transbank.webpay.webpayplus.responses.*;
import cl.transbank.webpay.webpayplus.WebpayPlus;


import com.prueba.pruebaferremas.controller.transbank.BaseController;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
public class WebpayPlusMallController extends BaseController {
    private WebpayPlus.MallTransaction tx;
    public WebpayPlusMallController(){
        tx = new WebpayPlus.MallTransaction(new WebpayOptions(IntegrationCommerceCodes.WEBPAY_PLUS_MALL, IntegrationApiKeys.WEBPAY, IntegrationType.TEST));
    }

    @RequestMapping(value = "/webpay_plus_mall/create", method = RequestMethod.GET)
    public ModelAndView create(HttpServletRequest request) {

        log.info("Webpay Plus Mall MallTransaction.create");
        String buyOrder = "buyOrder_" + getRandomNumber();
        String buyOrderMallOne = "childBuyOrder_" + getRandomNumber();
        String buyOrderMallTwo = "childBuyOrder_" + getRandomNumber();
        String sessionId = "sessionId_" + getRandomNumber();
        double amountMallOne = 1000;
        double amountMallTwo = 1000;
        String returnUrl = request.getRequestURL().toString().replace("create","commit");

        String mallOneCommerceCode = IntegrationCommerceCodes.WEBPAY_PLUS_MALL_CHILD1;
        String mallTwoCommerceCode = IntegrationCommerceCodes.WEBPAY_PLUS_MALL_CHILD2;
        final MallTransactionCreateDetails mallDetails = MallTransactionCreateDetails.build()
                .add(amountMallOne, mallOneCommerceCode, buyOrderMallOne)
                .add(amountMallTwo, mallTwoCommerceCode, buyOrderMallTwo);

        Map<String, Object> details = new HashMap<>();

        try {
            final WebpayPlusMallTransactionCreateResponse response = tx.create(
                    buyOrder, sessionId, returnUrl, mallDetails);

            details.put("url", response.getUrl());
            details.put("token", response.getToken());
            details.put("resp", toJson(response));

            details.put("childCommerceCode1", mallOneCommerceCode);
            details.put("childBuyOrder1", buyOrderMallOne);
            details.put("amount1", amountMallOne);

            details.put("childCommerceCode2", mallTwoCommerceCode);
            details.put("childBuyOrder2", buyOrderMallTwo);
            details.put("amount2", amountMallTwo);
        }
        catch (Exception e) {
            log.error("ERROR", e);
            details.put("resp", e.getMessage());
        }

        return new ModelAndView("webpay_plus_mall/create", "details", details);
    }

    @RequestMapping(value = "/webpay_plus_mall/commit", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView commit(@RequestParam("token_ws") String tokenWs, HttpServletRequest request) {
        log.info(String.format("token_ws : %s", tokenWs));

        Map<String, Object> details = new HashMap<>();
        details.put("token_ws", tokenWs);

        try {
            final WebpayPlusMallTransactionCommitResponse response = tx.commit(tokenWs);

            details.put("response", response);
            WebpayPlusMallTransactionStatusResponse.Detail detail = response.getDetails().get(0);
            details.put("token_ws", tokenWs);
            details.put("amount", detail.getAmount());
            details.put("authorization_code", detail.getAuthorizationCode());
            details.put("child_buy_order", detail.getBuyOrder());
            details.put("child_commerce_code", detail.getCommerceCode());

            details.put("refund-endpoint", "/webpay_plus_mall/refund");
            details.put("status-endpoint", "/webpay_plus_mall/status");
            details.put("resp", toJson(response));
        }
        catch (Exception e) {
            log.error("ERROR", e);
            details.put("resp", e.getMessage());
        }

        return new ModelAndView("webpay_plus_mall/commit", "details", details);
    }

    @RequestMapping(value = "/webpay_plus_mall/refund", method = RequestMethod.POST)
    public ModelAndView refund(@RequestParam("token_ws") String token,
                               @RequestParam("child_commerce_code") String childCommerceCode,
                               @RequestParam("child_buy_order") String childBuyOrder,
                               @RequestParam("amount") double amount) {

        Map<String, Object> details = new HashMap<>();
        try {
            final WebpayPlusMallTransactionRefundResponse response = tx.refund(token, childBuyOrder, childCommerceCode, amount);
            details.put("response", response);
            details.put("resp", toJson(response));
        }
        catch (Exception e) {
            log.error("ERROR", e);
            details.put("resp", e.getMessage());
        }

        return new ModelAndView("webpay_plus_mall/refund", "details", details);
    }
    @RequestMapping(value = "/webpay_plus_mall/status", method = RequestMethod.POST)
    public ModelAndView status(@RequestParam("token_ws") String token){
        Map<String, Object> details = new HashMap<>();
        details.put("token_ws", token);

        try {
            final WebpayPlusMallTransactionStatusResponse response = tx.status(token);
            details.put("resp", toJson(response));
        }
        catch (Exception e) {
            log.error("ERROR", e);
            details.put("resp", e.getMessage());
        }
        return new ModelAndView("webpay_plus_mall/status", "details", details);
    }
}