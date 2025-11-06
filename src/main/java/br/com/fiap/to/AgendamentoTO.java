package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AgendamentoTO
{
    private Long id;
    @NotNull
    private PacienteTO paciente;
    @NotNull
    private String tipo;
    @NotNull
    private LocalDate data;
    @NotNull
    private String status;
    @NotNull
    private String canal;

    public AgendamentoTO() {
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }
}
