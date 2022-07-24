package org.homedepot.data;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "poinfo")
public interface Poinfo {
    String host();

    int port();

    Log log();

    interface Log {
        boolean enabled();

        String suffix();

        boolean rotate();
    }
}
