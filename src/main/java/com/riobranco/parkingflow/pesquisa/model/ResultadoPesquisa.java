package com.riobranco.parkingflow.pesquisa.model;

public class ResultadoPesquisa {

    private boolean temDados;
    private int totalAvaliacoes;
    private double mediaGeral;
    private double percentualPositivas;
    private double percentualNegativas;
    private String tendencia;
    private boolean alerta;

    public boolean isTemDados() {
        return temDados;
    }

    public void setTemDados(boolean temDados) {
        this.temDados = temDados;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void setTotalAvaliacoes(int totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;
    }

    public double getMediaGeral() {
        return mediaGeral;
    }

    public void setMediaGeral(double mediaGeral) {
        this.mediaGeral = mediaGeral;
    }

    public double getPercentualPositivas() {
        return percentualPositivas;
    }

    public void setPercentualPositivas(double percentualPositivas) {
        this.percentualPositivas = percentualPositivas;
    }

    public double getPercentualNegativas() {
        return percentualNegativas;
    }

    public void setPercentualNegativas(double percentualNegativas) {
        this.percentualNegativas = percentualNegativas;
    }

    public String getTendencia() {
        return tendencia;
    }

    public void setTendencia(String tendencia) {
        this.tendencia = tendencia;
    }

    public boolean isAlerta() {
        return alerta;
    }

    public void setAlerta(boolean alerta) {
        this.alerta = alerta;
    }
}