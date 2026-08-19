package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {
    private final Page page;
    private final Locator checkoutButton;

    public CartPage(Page page) {
        this.page = page;
        this.checkoutButton = page.locator("[data-test='checkout']");
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }
}
