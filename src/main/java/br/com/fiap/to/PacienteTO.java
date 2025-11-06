package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PacienteTO {
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private String cpf;
    @PastOrPresent
    private LocalDate dataNascimento;
    private char sexo;
    private char baixaVisao;
    private char leitorTela;

    public PacienteTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public char getBaixaVisao() {
        return baixaVisao;
    }

    public void setBaixaVisao(char baixaVisao) {
        this.baixaVisao = baixaVisao;
    }

    public char getLeitorTela() {
        return leitorTela;
    }

    public void setLeitorTela(char leitorTela) {
        this.leitorTela = leitorTela;
    }
}
