package org.example.config;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static org.aeonbits.owner.ConfigCache.getOrCreate;

@NoArgsConstructor(access = PRIVATE)
public final class ConfigurationManager {

    public static Configuration configuration() {
        return getOrCreate(Configuration.class);
    }
}
