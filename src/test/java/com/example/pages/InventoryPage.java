package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InventoryPage {
    private final Page page;
    private final Locator sortDropdown;
    private final Locator cartButton;

    public InventoryPage(Page page) {
        this.page = page;
        this.sortDropdown = page.locator("[data-test='product-sort-container']");
        this.cartButton = page.locator("a.shopping_cart_link");
    }

    public void sortByPriceLowToHigh() {
        sortDropdown.selectOption("lohi");
    }

    public void addProductToCart(String productName) {
        page.locator("div.inventory_item")
                .filter(new Locator.FilterOptions().setHasText(productName))
                .locator("button")
                .click();
    }

    public void openCart() {
        cartButton.click();
    }
}
