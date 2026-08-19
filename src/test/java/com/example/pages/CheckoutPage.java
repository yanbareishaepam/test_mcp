package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage {
    private final Page page;
    private final Locator firstNameField;
    private final Locator lastNameField;
    private final Locator postalCodeField;
    private final Locator continueButton;
    private final Locator finishButton;
    private final Locator confirmationHeader;

    public CheckoutPage(Page page) {
        this.page = page;
        this.firstNameField = page.locator("[data-test='firstName']");
        this.lastNameField = page.locator("[data-test='lastName']");
        this.postalCodeField = page.locator("[data-test='postalCode']");
        this.continueButton = page.locator("[data-test='continue']");
        this.finishButton = page.locator("[data-test='finish']");
        this.confirmationHeader = page.locator(".complete-header");
    }

    public void fillCustomerInfo(String firstName, String lastName, String postalCode) {
        firstNameField.fill(firstName);
        lastNameField.fill(lastName);
        postalCodeField.fill(postalCode);
        continueButton.click();
    }

    public void finishOrder() {
        finishButton.click();
    }

    public String getConfirmationMessage() {
        return confirmationHeader.textContent();
    }
}
