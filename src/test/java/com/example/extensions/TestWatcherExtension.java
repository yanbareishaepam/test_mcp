package com.example.extensions;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

public class TestWatcherExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();
        try {
            Class<?> currentClass = testInstance.getClass();
            Field pageField = null;

            while (currentClass != null) {
                for (Field field : currentClass.getDeclaredFields()) {
                    if (field.getType().equals(Page.class)) {
                        pageField = field;
                        break;
                    }
                }
                if (pageField != null) {
                    break;
                }
                currentClass = currentClass.getSuperclass();
            }

            if (pageField == null) {
                return;
            }

            pageField.setAccessible(true);
            Page page = (Page) pageField.get(testInstance);

            if (page != null) {
                byte[] screenshot = page.screenshot();
                Allure.addAttachment("Failed Screen", "image/png", new ByteArrayInputStream(screenshot), ".png");
            }
        } catch (Exception e) {
            System.out.println("Unable to capture screenshot after failure: " + e.getMessage());
        }
    }
}
