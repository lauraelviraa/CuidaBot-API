package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ExameTO
{
    private Long id;
    @NotNull
    private PacienteTO paciente;
    @NotNull
    private String nomeExame;
    @NotNull
    private String status;
    @NotNull
    private LocalDate dataExame;
    private String resultado;

    public ExameTO() {
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

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataExame() {
        return dataExame;
    }

    public void setDataExame(LocalDate dataExame) {
        this.dataExame = dataExame;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
