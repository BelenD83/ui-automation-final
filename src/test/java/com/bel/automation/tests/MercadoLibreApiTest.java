package com.bel.automation.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class MercadoLibreApiTest {
    @Test
    public void validarDepartamentosMeli() {
        RestAssured.given()
                .header("User-Agent", "PostmanRuntime/7.28.4") // Para evitar el error 403
                .get("https://www.mercadolibre.com.ar/menu/departments")
                .then()
                .statusCode(200)
                .body("departments", notNullValue()); // Verifica que contenga departamentos
    }
}