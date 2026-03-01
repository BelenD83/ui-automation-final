package com.bel.automation.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

public class Listeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriverFromTestInstance(result.getInstance());

        // Si es un test de API (no hay driver), no pasa nada.
        if (driver == null) {
            System.out.println("No hay WebDriver (probablemente test API). No se adjunta screenshot a Allure.");
            return;
        }

        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    "Screenshot - " + result.getName(),
                    new ByteArrayInputStream(screenshot)
            );

        } catch (Exception e) {
            System.out.println("No se pudo sacar screenshot: " + e.getMessage());
        }
    }

    private WebDriver getDriverFromTestInstance(Object testInstance) {
        if (testInstance == null) return null;

        // Busca un campo llamado "driver" en la clase del test (y superclases si hubiera)
        Class<?> clazz = testInstance.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField("driver");
                field.setAccessible(true);
                Object value = field.get(testInstance);
                if (value instanceof WebDriver) return (WebDriver) value;
                return null;
            } catch (NoSuchFieldException ignored) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}