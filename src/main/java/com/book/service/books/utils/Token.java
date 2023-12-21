package com.book.service.books.utils;

import lombok.Getter;

public class Token {
    private String sub;
    private Long nbf;
    private String role;
    private String iss;
    private Long exp;
    private Long iat;
    private String nonce;
    private String jti;

    public Token(){

    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Long getNbf() {
        return nbf;
    }

    public void setNbf(Long nbf) {
        this.nbf = nbf;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public Token(String sub, Long nbf, String role, String iss, Long exp, Long iat, String nonce, String jti) {
        this.sub = sub;
        this.nbf = nbf;
        this.role = role;
        this.iss = iss;
        this.exp = exp;
        this.iat = iat;
        this.nonce = nonce;
        this.jti = jti;
    }
}
