package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * TODO#5
 * Crea el repositorio para la entidad Token de modo que,
 * además de las operaciones CRUD, se pueda consultar el Token asociado
 * a un AppUser dado
 */

public interface TokenRepository extends CrudRepository<Token, String> {

    Token findByAppUser(AppUser appUser);

    @Modifying
    @Transactional
    @Query("DELETE FROM Token token WHERE token.appUser = :appUser")
    void deleteByAppUser(AppUser appUser);


}