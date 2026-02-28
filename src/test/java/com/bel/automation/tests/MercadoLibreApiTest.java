package com.bel.automation.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class MercadoLibreApiTest {

    @Test
    public void validarDepartamentosMercadoLibre() {
        String url = "https://www.mercadolibre.com.ar/menu/departments";

        RestAssured.given()
                .get(url)
                .then()
                .statusCode(200)
                .body("departments", notNullValue());

        System.out.println("API de Mercado Libre validada con éxito.");
    }
}