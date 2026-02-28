package com.bel.automation.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class MercadoLibreApiTest { // Quitamos el "extends BaseTest" para evitar líos

    @Test
    public void validarDepartamentosMercadoLibre() {
        String url = "https://www.mercadolibre.com.ar/menu/departments";

        // Usamos RestAssured para hablar con el servicio web
        RestAssured.given()
                .get(url)
                .then()
                .statusCode(200) // Verifica que la página responda OK
                .body("departments", notNullValue()) // Verifica que contenga la lista de departamentos
                .body("departments.name", hasItem("Tecnología")); // Verificación extra de contenido

        System.out.println("Punto 5 completado: Servicio de Mercado Libre validado correctamente.");
    }
}