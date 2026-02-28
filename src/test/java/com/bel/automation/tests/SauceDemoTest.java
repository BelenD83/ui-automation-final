package com.bel.automation.tests;

import com.bel.automation.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest {
    @Test
    public void flujoCompraCompleto() {
        driver.get("https://www.saucedemo.com/");
        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        // Agregar al carrito
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        // Checkout
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Giang");
        driver.findElement(By.id("last-name")).sendKeys("Test");
        driver.findElement(By.id("postal-code")).sendKeys("1234");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        // Validación final
        String mensaje = driver.findElement(By.className("complete-header")).getText();
        Assert.assertTrue(mensaje.equalsIgnoreCase("Thank you for your order!"), "La compra no se completó");
    }
}