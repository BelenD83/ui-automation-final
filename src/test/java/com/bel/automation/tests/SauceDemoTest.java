package com.bel.automation.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Listeners; // <--- IMPORTANTE: Para que no se ponga rojo
import java.io.File;
import java.nio.file.Files;

// AQUÍ CONECTAMOS CON TU CLASE DE REPORTES
@Listeners(com.bel.automation.utils.Listeners.class)
public class SauceDemoTest {
    WebDriver driver;

    @BeforeMethod
    public void prepararRobot() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    // --- FUNCIONALIDAD 1: LOGIN ---

    @Test
    public void CP01_LoginExitoso() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed());
    }

    @Test
    public void CP02_UsuarioBloqueado() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertTrue(error.contains("locked out"));
    }

    @Test
    public void CP03_ContrasenaIncorrecta() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("clave_error");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertTrue(error.contains("Username and password do not match"));
    }

    @Test
    public void CP04_UsuarioInexistente() {
        driver.findElement(By.id("user-name")).sendKeys("usuario_no_existe");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertTrue(error.contains("Username and password do not match"));
    }

    @Test
    public void CP05_CamposVacios() {
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        // FALLO A PROPÓSITO PARA EL REPORTE:
        Assert.assertTrue(error.contains("Cualquier cosa"), "Generando captura de pantalla...");
    }

    // --- FUNCIONALIDAD 2: CARRITO ---

    @Test
    public void CP06_AgregarProducto() {
        hacerLogin();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), "1");
    }

    @Test
    public void CP07_VerificarProductoEnCarrito() {
        hacerLogin();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        String nombre = driver.findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(nombre, "Sauce Labs Backpack");
    }

    @Test
    public void CP08_QuitarDesdeCatalogo() {
        hacerLogin();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        int size = driver.findElements(By.className("shopping_cart_badge")).size();
        Assert.assertEquals(size, 0);
    }

    @Test
    public void CP09_QuitarDesdeCarrito() {
        hacerLogin();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        int size = driver.findElements(By.className("inventory_item_name")).