package com.project.yellowRest.config.google;

import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;
import java.security.Principal;
import java.util.Objects;

@ToString
@Getter
public class GooglePrincipal implements Principal {

    private BigInteger id;
    private String email;
    private String given_name;
    private String family_name;
    private String picture;


    public GooglePrincipal() {
    }

    public GooglePrincipal(BigInteger id, String email, String given_name, String family_name, String picture) {
        this.id = id;
        this.email = email;
        this.given_name = given_name;
        this.family_name = family_name;
        this.picture = picture;
    }

    public BigInteger getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GooglePrincipal that = (GooglePrincipal) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public String getName() {
        return this.email;
    }
}
