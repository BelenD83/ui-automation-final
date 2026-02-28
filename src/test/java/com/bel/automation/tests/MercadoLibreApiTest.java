package com.bel.automation.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import utils.BaseTest; // 1. Importamos tu configuracion base
import static org.hamcrest.Matchers.*;

public class MercadoLibreApiTest extends BaseTest { // 2. "extends BaseTest" activa el Chrome

    @Test
    public void validarDepartamentosMercadoLibre() {
        String url = "https://www.mercadolibre.com.ar/menu/departments";

        // 3. Abrimos la pagina en el navegador.
        // Esto es lo que permite que el robot saque una foto si algo sale mal.
        driver.get(url);

        // 4. Hacemos la validacion de la API (el servicio web)
        RestAssured.given()
                .get(url)
                .then()
                .statusCode(200) // Verifica que la conexion sea exitosa
                .body("departments", notNullValue()); // Verifica que existan departamentos

        System.out.println("API de Mercado Libre validada con exito.");
    }
}