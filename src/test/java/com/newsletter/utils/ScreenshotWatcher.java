package com.newsletter.utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

public class ScreenshotWatcher implements TestWatcher, AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            Object testInstance = context.getRequiredTestInstance();
            if (testInstance instanceof TestBase) {
                WebDriver driver = ((TestBase) testInstance).getDriver();
                if (driver != null) {
                    AllureUtils.takeScreenshot(driver);
                }
            }
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        // Logic moved to afterTestExecution to ensure driver is still alive
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
    }
}
