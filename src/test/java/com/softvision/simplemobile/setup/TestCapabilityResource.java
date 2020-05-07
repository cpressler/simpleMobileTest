package com.softvision.simplemobile.setup;

import com.softvision.simplemobile.util.JsonResourceObjectMapper;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

/***
 * This will load up the capabilities from the json file provided and then it will create a DesiredCapabilities Object
 */
public class TestCapabilityResource {

    public static DesiredCapabilities load(String capabilityFileName) throws IOException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        JsonResourceObjectMapper mapper = new JsonResourceObjectMapper(TestCapability.class);

        Map<String, Object> jsonMap = mapper.loadJsonMap(capabilityFileName);
        jsonMap.forEach((k, v) -> desiredCapabilities.setCapability(k,v));

        return desiredCapabilities;
    }
}
