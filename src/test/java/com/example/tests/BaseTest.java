package com.example.tests;

import com.example.config.ProjectConfig;
import com.example.extensions.TestWatcherExtension;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@UsePlaywright
@ExtendWith(TestWatcherExtension.class)
public abstract class BaseTest {
    protected ProjectConfig config;
    protected Page page;

    @BeforeEach
    void setupConfig(Page page) {
        this.page = page;
        config = ConfigFactory.create(ProjectConfig.class);
        page.setDefaultTimeout((long) config.timeout());
    }
}
