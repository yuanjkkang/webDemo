package iot.ah.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private Environment env;

    @RequestMapping(value = "/getAllSpace")
    public Map<String, String> getEnvProp() {
        return System.getenv();
    }
}
