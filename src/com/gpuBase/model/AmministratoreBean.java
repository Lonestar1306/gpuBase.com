package com.gpuBase.model;

public class AmministratoreBean {

    public AmministratoreBean(String nomeAmministratore, String password) {
        super();
        this.nomeAmministratore = nomeAmministratore;
        this.password = password;
    }

    public AmministratoreBean() {
        super();
    }

    public String getNomeAmministratore() {
        return nomeAmministratore;
    }

    public void setNomeAmministratore(String nomeAmministratore) {
        this.nomeAmministratore = nomeAmministratore;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AmministatoreBean [nomeAmministratore=" + nomeAmministratore + ", password=" + password + "]";
    }

    private String nomeAmministratore;
    private String password;

}