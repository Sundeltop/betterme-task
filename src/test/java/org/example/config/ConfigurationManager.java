package org.example.config;

import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigCache;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ConfigurationManager {

    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
