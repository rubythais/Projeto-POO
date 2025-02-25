package model;

import interfaces.Validavel;
import java.io.Serializable;

public class Usuario implements Serializable {
    private String id;
    private String nome;
    private String email;
    private String cpf;

    public Usuario(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.id = java.util.UUID.randomUUID().toString();
    }

    @Override
    public boolean validar() {
        return utils.Validador.validarEmail(email) && 
               utils.Validador.validarCPF(cpf);
    }

    
    public String getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}

