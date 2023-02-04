package com.gpuBase.model;

public class ClienteBean {

    public ClienteBean(String mailCliente, String password, String indirizzo, String telefono) {
        super();
        this.mailCliente = mailCliente;
        this.password = password;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
    }
    public ClienteBean() {
        super();
    }
    public String getMailCliente() {
        return mailCliente;
    }
    public void setMailCliente(String mailCliente) {
        this.mailCliente = mailCliente;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    private String mailCliente;
    private String password;
    private String indirizzo;
    private String telefono;

    }