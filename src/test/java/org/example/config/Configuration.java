package org.example.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadPolicy;
import static org.aeonbits.owner.Config.LoadType.MERGE;
import static org.aeonbits.owner.Config.Sources;

@LoadPolicy(MERGE)
@Sources({"system:properties", "classpath:config.properties"})
public interface Configuration extends Config {

    @Key("base.url")
    @DefaultValue("http://localhost/v2/store/inventory")
    String baseUrl();
}
