package edu.comillas.icai.gitt.pat.spring.p5;

import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class P5ApplicationE2ETest {

    private static final String NAME = "Name";
    private static final String EMAIL = "name@email.com";
    private static final String PASS = "aaaaaaA1";

    @Autowired
    TestRestTemplate client;

    @Test public void registerTest() {
        // Given ...
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String registro = "{" +
                "\"name\":\"" + NAME + "\"," +
                "\"email\":\"" + EMAIL + "\"," +
                "\"role\":\"" + Role.USER + "\"," +
                "\"password\":\"" + PASS + "\"}";

        // When ...
        ResponseEntity<String> response = client.exchange(
                "http://localhost:8080/api/users",
                HttpMethod.POST, new HttpEntity<>(registro, headers), String.class);

        // Then ...
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("{" +
                "\"name\":\"" + NAME + "\"," +
                "\"email\":\"" + EMAIL + "\"," +
                "\"role\":\"" + Role.USER + "\"}",
                response.getBody());
    }

    /**
     * TODO#11
     * Completa el siguiente test E2E para que verifique la
     * respuesta de login cuando se proporcionan credenciales correctas
     */
    @Test
    public void loginOkTest() {
        // Given ...
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Hola Mario hice lo que me pusiste en el correo de Crearme un usuario
        // y aun asi no me pasa el test no entiendo muy bien por qué la verdad
        //perdona por no poder hacer que pase el test
        String registro = "{" +
                "\"name\":\"" + NAME + "\"," +
                "\"email\":\"" + EMAIL + "\"," +
                "\"role\":\"USER\"," +
                "\"password\":\"" + PASS + "\"}";

        client.exchange(
                "http://localhost:8080/api/users",
                HttpMethod.POST,
                new HttpEntity<>(registro, headers),
                String.class);

        // LOGIN
        String login = "{" +
                "\"email\":\"" + EMAIL + "\"," +
                "\"password\":\"" + PASS + "\"}";

        ResponseEntity<Void> response = client.exchange(
                "http://localhost:8080/api/users/me/session",
                HttpMethod.POST,
                new HttpEntity<>(login, headers),
                Void.class);

        // Then ...
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode(), "El login debe ser exitoso con 201");
        Assertions.assertTrue(response.getHeaders().containsKey("Set-Cookie"), "Debe haber Set-Cookie en headers");
        Assertions.assertTrue(Objects.requireNonNull(response.getHeaders().getFirst("Set-Cookie")).contains("session="),
                "La cookie debe contener 'session='");
    }

}
