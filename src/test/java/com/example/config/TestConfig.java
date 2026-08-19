package com.example.config;

import org.aeonbits.owner.Config;

@Config.Sources("file:src/test/resources/config.properties")
public interface TestConfig extends Config {
    @Key("base.url")
    String baseUrl();

    @Key("browser")
    @DefaultValue("chromium")
    String browser();

    @Key("headless")
    @DefaultValue("true")
    boolean headless();

    @Key("timeout")
    @DefaultValue("5000")
    double timeout();

    @Key("username")
    @DefaultValue("standard_user")
    String username();

    @Key("password")
    @DefaultValue("secret_sauce")
    String password();
}
