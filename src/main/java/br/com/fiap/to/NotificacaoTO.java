package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class NotificacaoTO
{
    private Long id;
    @NotNull
    private PacienteTO paciente;
    @NotNull
    private String tipo;
    private String mensagem;
    private String titulo;
    @NotNull
    private LocalDate dataEnvio;
    @NotNull
    private char lida;

    public NotificacaoTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteTO paciente) {
        this.paciente = paciente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public char getLida() {
        return lida;
    }

    public void setLida(char lida) {
        this.lida = lida;
    }
}
