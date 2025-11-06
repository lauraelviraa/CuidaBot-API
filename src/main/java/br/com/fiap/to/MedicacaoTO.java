package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MedicacaoTO {
    private Long id;
    @NotNull
    private PacienteTO paciente;
    @NotNull
    private String nome;
    @NotNull
    private String dosagem;
    private int frequenciaHoras;
    @NotNull
    private LocalDate data;
    @NotNull
    private char ativo;

    public MedicacaoTO() {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public int getFrequenciaHoras() {
        return frequenciaHoras;
    }

    public void setFrequenciaHoras(int frequenciaHoras) {
        this.frequenciaHoras = frequenciaHoras;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }
}
