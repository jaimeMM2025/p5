package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    AppUserRepository repoAppUser;
    @Autowired
    TokenRepository repoToken;
    public Token login(String email, String password) {

        AppUser appUser = repoAppUser.findByEmail(email);
        if (appUser == null){
            return null;
        }

        Token token = repoToken.findByAppUser(appUser);
        if (token == null){
            return null;
        }
        return token;
    }

    public AppUser authentication(String tokenId) {

        Optional<Token> token = repoToken.findById(tokenId);

        if (token.isPresent()) {
            AppUser appUser = token.get().getAppUser();
            return appUser;
        } else {
            return null; // o manejarlo de otra forma según tu lógica de negocio
        }
    }

    public ProfileResponse profile(AppUser appUser) {

        String name = appUser.getName();
        String email = appUser.getEmail();
        Role role = appUser.getRole();
        ProfileResponse perfil = new ProfileResponse(name,email,role);

        return perfil;
    }
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {

        appUser.setName(profile.name());
        appUser.setRole(profile.role());
        appUser.setPassword(profile.password());
        ProfileResponse perfil = new ProfileResponse(appUser.getName(),appUser.getEmail(),appUser.getRole());


        return perfil;


    }
    public ProfileResponse profile(RegisterRequest register) {

        ProfileResponse perfil = new ProfileResponse(register.name(),register.email(),register.role());
        return perfil;
    }


    public void logout(String tokenId) {

        Optional<Token> token = repoToken.findById(tokenId);


    }

    public void delete(AppUser appUser) {

        repoToken.deleteByAppUser(appUser);

    }

}
