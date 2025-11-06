package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PacienteCuidadorTO {
    private Long id;
    @NotNull
    private PacienteTO paciente;
    @NotNull
    private CuidadorTO cuidador;
    @NotNull
    private String papel;
    @NotNull
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public PacienteCuidadorTO() {
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

    public CuidadorTO getCuidador() {
        return cuidador;
    }

    public void setCuidador(CuidadorTO cuidador) {
        this.cuidador = cuidador;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
