package iot.ah.demo.controller;

import com.alibaba.cloudapi.sdk.model.ApiResponse;
import iot.ah.demo.core.mysql.request.IotApiClientBuilderParams;
import iot.ah.demo.core.mysql.request.IotApiRequest;
import iot.ah.demo.core.mysql.request.SyncApiPostClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @date 2018/8/27
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/iot/ah",consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
public class SpaceController {
    String appKey = System.getenv("iot.hosting.appKey");
    String appSecret=System.getenv("iot.hosting.appSecret");
    static final String host = System.getenv("iot.hosting.api.domain");
    @Autowired
    private Environment env;

    @RequestMapping(value = "/getAllSpace")
    public Map<String, String> getEnvProp() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        IotApiClientBuilderParams builderParams = new IotApiClientBuilderParams();
        builderParams.setAppKey(appKey);
        builderParams.setAppSecret(appSecret);
        SyncApiPostClient syncClient = new SyncApiPostClient(builderParams);
        IotApiRequest request = new IotApiRequest();
        //设置api的版本
        request.setApiVer("1.0.0");
        request.setId("42423423");
        //请求参数域名、path、request
        String path = "/api/space/listRootSpace";
//        String path = "/api/space/listRelationsInSpace";
        ApiResponse response = null;
        try {
            response = syncClient.postBody(host, path, request);

        } catch (UnsupportedEncodingException e) {
            stringStringHashMap.put("aaa", e.getMessage());
        }
        try {
            String s = new String(response.getBody(), "utf-8");
            stringStringHashMap.put("ccc", s);
        } catch (UnsupportedEncodingException e) {
            stringStringHashMap.put("bbb", e.getMessage());
        }
        Map<String, String> envProp1 = getEnvProp1();
        stringStringHashMap.putAll(envProp1);

        return stringStringHashMap;
    }
    public Map<String, String> getEnvProp1() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        IotApiClientBuilderParams builderParams = new IotApiClientBuilderParams();
        builderParams.setAppKey(appKey);
        builderParams.setAppSecret(appSecret);
        SyncApiPostClient syncClient = new SyncApiPostClient(builderParams);
        IotApiRequest request = new IotApiRequest();
        //设置api的版本
        //请求参数域名、path、request
        String path = "/api/space/listRootSpace";
//        String path = "/api/space/listRelationsInSpace";
        ApiResponse response = null;
        try {
            response = syncClient.postBody(host, path, request);

        } catch (UnsupportedEncodingException e) {
            stringStringHashMap.put("ddd", e.getMessage());
        }
        try {
            String s = new String(response.getBody(), "utf-8");
            stringStringHashMap.put("eee", s);
        } catch (UnsupportedEncodingException e) {
            stringStringHashMap.put("fff", e.getMessage());
        }


        return System.getenv();
    }
}
