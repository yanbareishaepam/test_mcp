package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;
    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;

    public LoginPage(Page page) {
        this.page = page;
        this.usernameField = page.locator("#user-name");
        this.passwordField = page.locator("#password");
        this.loginButton = page.locator("#login-button");
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void navigateTo(String url) {
        navigate(url);
    }

    public void login(String username, String password) {
        usernameField.fill(username);
        passwordField.fill(password);
        loginButton.click();
    }
}