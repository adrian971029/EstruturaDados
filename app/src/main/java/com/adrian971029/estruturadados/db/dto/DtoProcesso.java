package com.adrian971029.estruturadados.db.dto;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "processos_table")
public class DtoProcesso {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nomeProcesso;
    private int numTotalEntradas;
    private double tempoTotalExecucao;
    private double tempoExecucaoProcesso;
    private double tempoTotalEspera;
    private double tempoTotalResposta;
    private boolean isRedBlackTree;

    public DtoProcesso(String nomeProcesso, int numTotalEntradas, double tempoTotalExecucao, double tempoExecucaoProcesso, double tempoTotalEspera, double tempoTotalResposta, boolean isRedBlackTree) {
        this.nomeProcesso = nomeProcesso;
        this.numTotalEntradas = numTotalEntradas;
        this.tempoTotalExecucao = tempoTotalExecucao;
        this.tempoExecucaoProcesso = tempoExecucaoProcesso;
        this.tempoTotalEspera = tempoTotalEspera;
        this.tempoTotalResposta = tempoTotalResposta;
        this.isRedBlackTree = isRedBlackTree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProcesso() {
        return nomeProcesso;
    }

    public void setNomeProcesso(String nomeProcesso) {
        this.nomeProcesso = nomeProcesso;
    }

    public int getNumTotalEntradas() {
        return numTotalEntradas;
    }

    public void setNumTotalEntradas(int numTotalEntradas) {
        this.numTotalEntradas = numTotalEntradas;
    }

    public double getTempoTotalExecucao() {
        return tempoTotalExecucao;
    }

    public void setTempoTotalExecucao(double tempoTotalExecucao) {
        this.tempoTotalExecucao = tempoTotalExecucao;
    }

    public double getTempoExecucaoProcesso() {
        return tempoExecucaoProcesso;
    }

    public void setTempoExecucaoProcesso(double tempoExecucaoProcesso) {
        this.tempoExecucaoProcesso = tempoExecucaoProcesso;
    }

    public double getTempoTotalEspera() {
        return tempoTotalEspera;
    }

    public void setTempoTotalEspera(double tempoTotalEspera) {
        this.tempoTotalEspera = tempoTotalEspera;
    }

    public double getTempoTotalResposta() {
        return tempoTotalResposta;
    }

    public void setTempoTotalResposta(double tempoTotalResposta) {
        this.tempoTotalResposta = tempoTotalResposta;
    }

    public boolean isRedBlackTree() {
        return isRedBlackTree;
    }

    public void setRedBlackTree(boolean redBlackTree) {
        isRedBlackTree = redBlackTree;
    }
}
