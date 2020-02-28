package iot.ah.demo.controller;

import iot.ah.demo.core.mysql.BaseDownResponse;
import iot.ah.demo.core.mysql.BillingRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @date 2018/8/27
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/CommunityParkingService",consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
public class CommunityParkingController {
    @Autowired
    private Environment env;

    @RequestMapping(value = "/BillingRules")
    public BaseDownResponse getEnvProp(@RequestBody BillingRules billingRules) {
        System.out.println(billingRules);
        BaseDownResponse<String> stringBaseDownResponse = new BaseDownResponse<>();
        stringBaseDownResponse.setData("success");
        return stringBaseDownResponse;
    }

    @RequestMapping(value = "/getAllEnvProp")
    public Map<String, String> getEnvProp() {
        return System.getenv();
    }
}
