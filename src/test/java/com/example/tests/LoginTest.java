package com.example.tests;

import com.example.pages.LoginPage;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    void successfulLoginTest(Page page) {
        LoginPage loginPage = new LoginPage(page);

        page.navigate(config.baseUrl());
        loginPage.login(config.username(), config.password());

        assertThat(page).hasURL(config.baseUrl() + "/inventory.html");
    }
}
