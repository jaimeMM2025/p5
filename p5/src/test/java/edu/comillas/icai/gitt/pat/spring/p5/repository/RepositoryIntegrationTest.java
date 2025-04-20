package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import edu.comillas.icai.gitt.pat.spring.p5.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired TokenRepository tokenRepository;
    @Autowired AppUserRepository appUserRepository;

    /**
     * TODO#9
     * Completa este test de integración para que verifique
     * que los repositorios TokenRepository y AppUserRepository guardan
     * los datos correctamente, y las consultas por AppToken y por email
     * definidas respectivamente en ellos retornan el token y usuario guardados.
     */
    @Test
    void saveTest() {
        // Given
        AppUser user = new AppUser();
        user.setEmail("test@email.com");
        user.setName("Test");
        user.setPassword("aaaaaaA1");
        user.setRole(Role.USER);
        appUserRepository.save(user);

        Token token = new Token();
        token.setAppUser(user);
        tokenRepository.save(token);

        // When
        AppUser BuscarUsuario = appUserRepository.findByEmail("test@email.com");
        Token BuscarToken = tokenRepository.findByAppUser(user);

        // Then
        Assertions.assertNotNull(BuscarUsuario);
        Assertions.assertNotNull(BuscarToken);
        Assertions.assertEquals("test@email.com", BuscarUsuario.getEmail());
        Assertions.assertEquals(user.getId(), BuscarToken.getAppUser().getId());
    }

    /**
     * TODO#10
     * Completa este test de integración para que verifique que
     * cuando se borra un usuario, automáticamente se borran sus tokens asociados.
     */
    @Test void deleteCascadeTest() {
        // Given ...

        AppUser user = new AppUser();
        user.setEmail("test@email.com");
        user.setName("Test");
        user.setPassword("aaaaaaA1");
        user.setRole(Role.USER);
        appUserRepository.save(user);

        Token token = new Token();
        token.setAppUser(user);
        tokenRepository.save(token);

        Assertions.assertEquals(1, appUserRepository.count());
        Assertions.assertEquals(1, tokenRepository.count());

        // When ...
        appUserRepository.delete(user);


        // Then ...
        Assertions.assertEquals(0, appUserRepository.count());
        Assertions.assertEquals(0, tokenRepository.count());

    }
}