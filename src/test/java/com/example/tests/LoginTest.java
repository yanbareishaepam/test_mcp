package com.example.tests;

import com.example.pages.CartPage;
import com.example.pages.CheckoutPage;
import com.example.pages.InventoryPage;
import com.example.pages.LoginPage;
import com.microsoft.playwright.Page;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic("SauceDemo")
@Feature("Shopping workflow")
@Story("Successful product purchase")
@Severity(SeverityLevel.CRITICAL)
public class LoginTest extends BaseTest {

    @Test
    @Tag("smoke")
    @DisplayName("User can log in and buy two products")
    void successfulLoginTest(Page page) {
        LoginPage loginPage = new LoginPage(page);
        InventoryPage inventoryPage = new InventoryPage(page);
        CartPage cartPage = new CartPage(page);
        CheckoutPage checkoutPage = new CheckoutPage(page);

        String baseUrl = config.baseUrl().replaceAll("/+$", "");

        Allure.parameter("baseUrl", baseUrl);
        Allure.parameter("username", config.username());

        Allure.step("Open login page", () -> {
            page.navigate(baseUrl);
            assertThat(page).hasURL(baseUrl + "/");
        });

        Allure.step("Log in with valid credentials", () -> {
            loginPage.login(config.username(), config.password());
            assertThat(page).hasURL(baseUrl + "/inventory.html");
        });

        Allure.step("Sort products from low to high price", () -> {
            inventoryPage.sortByPriceLowToHigh();
            assertThat(page.locator("[data-test='product-sort-container']")).hasValue("lohi");
        });

        Allure.step("Add products to cart", () -> {
            inventoryPage.addProductToCart("Sauce Labs Backpack");
            inventoryPage.addProductToCart("Sauce Labs Bike Light");
            assertThat(page.locator("a.shopping_cart_link")).hasText("2");
        });

        Allure.step("Open shopping cart", () -> {
            inventoryPage.openCart();
            assertThat(page).hasURL(baseUrl + "/cart.html");
        });

        Allure.step("Proceed to checkout", () -> {
            cartPage.proceedToCheckout();
            assertThat(page).hasURL(baseUrl + "/checkout-step-one.html");
        });

        Allure.step("Fill customer details", () -> {
            checkoutPage.fillCustomerInfo("Ivan", "Petrov", "12345");
            assertThat(page).hasURL(baseUrl + "/checkout-step-two.html");
        });

        Allure.step("Finish the purchase", () -> {
            checkoutPage.finishOrder();
            assertThat(page).hasURL(baseUrl + "/checkout-complete.html");
        });

        String confirmationMessage = checkoutPage.getConfirmationMessage();
        Allure.step("Verify order confirmation", () -> {
            assertThat(page.locator(".complete-header")).hasText("Thank you for your order!");
        });

        Allure.addAttachment("Order confirmation screenshot", "image/png",
                new ByteArrayInputStream(page.screenshot()), ".png");
        Allure.addAttachment("Confirmation message", "text/plain", confirmationMessage, ".txt");
    }
}
