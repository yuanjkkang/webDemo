package iot.ah.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.aliyun.iotx.api.client.IoTApiClientBuilderParams;
import com.aliyun.iotx.api.client.IoTApiRequest;
import com.aliyun.iotx.api.client.SyncApiClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

     static final String appKey = System.getenv("iot.hosting.appKey");
     static final String appSecret = System.getenv("iot.hosting.appSecret");
     static final String host = System.getenv("iot.hosting.api.domain");

    String modelId = "FaceSet";

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert() throws UnsupportedEncodingException {
        IoTApiClientBuilderParams ioTApiClientBuilderParams = new IoTApiClientBuilderParams();
        ioTApiClientBuilderParams.setAppKey(appKey);
        ioTApiClientBuilderParams.setAppSecret(appSecret);
        SyncApiClient syncClient = new SyncApiClient(ioTApiClientBuilderParams);

        IoTApiRequest request = new IoTApiRequest();
        //设置api的版本
        request.setApiVer("0.0.3");
        request.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        //如果需要登陆，设置当前的会话的token
        //request.setIotToken("xxxxxxxxx");

        request.putParam("modelId", modelId);

        long now = System.currentTimeMillis();
        String modeljson = "{'FaceSetID' : 1 ,'Name' : 'testname' ,'Image' : 'testimages'}";
        request.putParam("properties", JSON.parse(modeljson));
        request.setVersion("1.0");// 网关协议版本
        System.out.println(request);
        ApiResponse response = syncClient.postBody(host, "/data/model/data/insert", request, false);
        System.out.println("response code = " + response.getCode() + " response = " + new String(response.getBody(), "UTF-8"));
        return new String(response.getBody(), "UTF-8");
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete() throws UnsupportedEncodingException {
        IoTApiClientBuilderParams ioTApiClientBuilderParams = new IoTApiClientBuilderParams();
        ioTApiClientBuilderParams.setAppKey(appKey);
        ioTApiClientBuilderParams.setAppSecret(appSecret);
        SyncApiClient syncClient = new SyncApiClient(ioTApiClientBuilderParams);

        IoTApiRequest request = new IoTApiRequest();
        //设置api的版本
        request.setApiVer("0.0.2");
        request.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        //如果需要登陆，设置当前的会话的token
        //request.setIotToken("xxxxxxxxx");

        request.putParam("modelId", modelId);
        String condition = ("[{'fieldName' : 'id' , 'value' : 5 , 'operate' : 'eq'}]");
        request.putParam("conditions", JSON.parseArray(condition));
        request.setVersion("1.0");// 网关协议版本
        System.out.println(request);
        ApiResponse response = syncClient.postBody(host,
                "/data/model/data/delete", request, false);
        System.out.println("response code = " + response.getCode() + " response = " + new String(response.getBody(), "UTF-8"));
        return new String(response.getBody(), "UTF-8");
    }

    @ResponseBody
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String query() throws UnsupportedEncodingException {
        IoTApiClientBuilderParams ioTApiClientBuilderParams = new IoTApiClientBuilderParams();
        ioTApiClientBuilderParams.setAppKey(appKey);
        ioTApiClientBuilderParams.setAppSecret(appSecret);
        SyncApiClient syncClient = new SyncApiClient(ioTApiClientBuilderParams);

        IoTApiRequest request = new IoTApiRequest();
        //设置api的版本
        request.setApiVer("0.0.3");
        request.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        //如果需要登陆，设置当前的会话的token
        //request.setIotToken("xxxxxxxxx");

        request.putParam("modelId", modelId);
        //request.putParam("scopeId", scopeId);
        //String condition = ("[{'fieldName' : 'id' , 'value' : 4 , 'operate' : 'eq'}]");
        request.putParam("conditions", JSON.parseArray("[]"));
        List<String> returnFields = Lists.newArrayList("*");
        request.putParam("returnFields", returnFields);
        request.setVersion("1.0");// 网关协议版本
        System.out.println(request);
        ApiResponse response = syncClient.postBody(host,
                "/data/model/data/query", request, false);
        System.out.println("response code = " + response.getCode() + " response = " + new String(response.getBody(), "UTF-8"));
        return new String(response.getBody(), "UTF-8");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update() throws UnsupportedEncodingException {
        IoTApiClientBuilderParams ioTApiClientBuilderParams = new IoTApiClientBuilderParams();
        ioTApiClientBuilderParams.setAppKey(appKey);
        ioTApiClientBuilderParams.setAppSecret(appSecret);
        SyncApiClient syncClient = new SyncApiClient(ioTApiClientBuilderParams);

        IoTApiRequest request = new IoTApiRequest();
        //设置api的版本
        request.setApiVer("0.0.2");
        request.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        //如果需要登陆，设置当前的会话的token
        //request.setIotToken("xxxxxxxxx");

        request.putParam("modelId", modelId);
        String condition = ("[{'fieldName' : 'id' , 'value' : 4 , 'operate' : 'eq'}]");
        request.putParam("updateDetails", JSON.parse("{'serialNumber' : '22' , 'photoID' : '22'}"));
        request.setVersion("1.0");// 网关协议版本
        System.out.println(request);
        ApiResponse response = syncClient.postBody(host,
                "/data/model/data/update", request, false);
        System.out.println("response code = " + response.getCode() + " response = " + new String(response.getBody(), "UTF-8"));
        return new String(response.getBody(), "UTF-8");
    }
}
