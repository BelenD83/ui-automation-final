package com.bel.automation.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

@Listeners(com.bel.automation.utils.Listeners.class)
public class MercadoLibreApiTest {

public class MercadoLibreApiTest {

    @Test
    public void validarDepartamentosMercadoLibre() {
        String url = "https://www.mercadolibre.com.ar/menu/departments";

        RestAssured.given()
                // Agregamos este "disfraz" para que ML no nos bloquee
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0")
                .get(url)
                .then()
                .statusCode(200) // Ahora sí debería devolver 200
                .body("departments", notNullValue());

        System.out.println("API de Mercado Libre validada con éxito.");
    }
}
}