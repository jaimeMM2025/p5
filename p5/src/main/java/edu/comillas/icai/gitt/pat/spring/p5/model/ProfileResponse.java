package edu.comillas.icai.gitt.pat.spring.p5.model;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;

public record ProfileResponse(
    String name,
    String email,
    Role role
) {
    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public Role role() {
        return role;
    }
}
