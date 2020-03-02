package iot.ah.demo.controller;

import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.iotx.api.client.IoTApiClientBuilderParams;
import com.aliyun.iotx.api.client.IoTApiRequest;
import com.aliyun.iotx.api.client.SyncApiClient;
import iot.ah.demo.core.mysql.BaseDownResponse;
import iot.ah.demo.core.mysql.BillingRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @date 2018/8/27
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/CommunityParkingService")
public class CommunityParkingController {
    @Autowired
    private Environment env;

    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 系统环境变量中获取的
     */
    public static final String appKey = System.getenv("iot.hosting.appKey");
    public static final String AppSecret = System.getenv("iot.hosting.appSecret");
    //服务模型请求的路由
    private static final String SERVICE_EDGE__PATH = System.getenv("iot.hosting.mesh.domain");

    @RequestMapping("/test")
    public String servicewk(@RequestBody Map<String, Object> params) throws UnsupportedEncodingException {
        //服务调用的接口---一般格式为：  /服务名称/接口名称
        String path = String.valueOf(params.get("service_path"));
        String content = handleservice(params, path);
        JSONObject jsonObject = JSON.parseObject(content);
        Integer code = (Integer) jsonObject.get("code");
        if (200 != code) {
            return String.valueOf(jsonObject);
        }
        return null;
    }

    private String handleservice(Map<String, Object> params, String path) throws UnsupportedEncodingException {
        IoTApiClientBuilderParams ioTApiClientBuilderParams =
                new IoTApiClientBuilderParams();
        ioTApiClientBuilderParams.setAppKey(appKey);
        ioTApiClientBuilderParams.setAppSecret(AppSecret);
        SyncApiClient syncClient = new SyncApiClient(ioTApiClientBuilderParams);
        //均为可调节的参数，需要自行填入
        IoTApiRequest request = new IoTApiRequest();
        String ApiVer = String.valueOf(params.get("ApiVer"));
        String Version = String.valueOf(params.get("Version"));
        request.putParam("lineName","2313213");
        request.setApiVer(ApiVer);
        request.setVersion(Version);
        logger.warn("request: {}" + JSON.toJSONString(request));
        ApiResponse response = syncClient.postBody(SERVICE_EDGE__PATH, path, request, true);
        String content = new String(response.getBody());
        return content;
    }

    @PostMapping(value = "/BillingRules")
    @ResponseBody
    public BaseDownResponse getEnvProp(HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
            String sign = map.get("x-ca-signature");
            logger.info(sign);
        }
        String json = "";
        String signHeaders = request.getHeader("HEADER_SM_ROUTER_DESTINATION");
        logger.info(signHeaders);

        json = new String(readInputStream(request.getInputStream()), "UTF-8");
        logger.info("调用成功"+"请求参数"+json);

        BaseDownResponse baseDownResponse = new BaseDownResponse();
        baseDownResponse.setMessage(json);
        return baseDownResponse;
    }
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        boolean var3 = false;

        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }

        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }
    @RequestMapping(value = "/getAllEnvProp")
    public Map<String, String> getEnvProp() {
        return System.getenv();
    }
}
