package com.bel.automation.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

@org.testng.annotations.Listeners({
        io.qameta.allure.testng.AllureTestNg.class,
        com.bel.automation.utils.Listeners.class
})
public class MercadoLibreApiTest {

    @Test
    public void validarDepartamentosMercadoLibre() {
        String url = "https://www.mercadolibre.com.ar/menu/departments";

        RestAssured.given()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0")
                .get(url)
                .then()
                .statusCode(200)
                .body("departments", notNullValue());

        System.out.println("API de Mercado Libre validada con éxito.");

        // Para fallar a propósito y ver el Listener (captura/reporte):
        // org.testng.Assert.fail("Fallo forzado para probar el reporte con captura");
    }
}