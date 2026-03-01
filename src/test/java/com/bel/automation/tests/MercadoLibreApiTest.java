package com.bel.automation.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Listeners; // Agregamos este import para que reconozca la etiqueta
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

// 1. La etiqueta mágica va aquí una sola vez
@Listeners(com.bel.automation.utils.Listeners.class)
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

        // 2. PARA TU TAREA: Descomenta la línea de abajo para que falle y ver el reporte
        // org.testng.Assert.fail("Fallo forzado para probar el reporte con captura");
    }
}