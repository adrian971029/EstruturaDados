package com.adrian971029.estruturadados.model;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "processos_table")
public class Processo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nomeProcesso;
    private long tempoExecucao;
    private int processoId;
    @Ignore
    private RedBlackTree redBlackTree;
    private long tempoCPU;
    private long tempoEspera;
    private long tempoChegada;
    @Ignore
    private ImplementacaoHeap heap;
    private long tempoResposta;
    private long injustica;

    public Processo(String nomeProcesso, long tempoExecucao, int processoId, long tempoCPU, long tempoEspera, long tempoChegada, long tempoResposta, long injustica) {
        this.nomeProcesso = nomeProcesso;
        this.tempoExecucao = tempoExecucao;
        this.processoId = processoId;
        this.tempoCPU = tempoCPU;
        this.tempoEspera = tempoEspera;
        this.tempoChegada = tempoChegada;
        this.tempoResposta = tempoResposta;
        this.injustica = injustica;
    }

    @Ignore
    public Processo(RedBlackTree redBlackTree, int novoId, long novoTempoChegada, long novoTempoExecucao) {

        tempoCPU = 0;
        processoId = novoId;
        tempoExecucao = novoTempoExecucao;
        tempoChegada = novoTempoChegada;
        tempoEspera = tempoChegada;
        injustica = tempoChegada;
        this.redBlackTree = redBlackTree;
        this.redBlackTree.Inserir(this);

    }

    @Ignore
    public Processo(ImplementacaoHeap heap, int novoId, long novoTempoChegada, long novoTempoExecucao) {

        tempoCPU = 0;
        tempoResposta = 0;

        processoId = novoId;
        tempoExecucao = novoTempoExecucao;
        tempoChegada = novoTempoChegada;
        tempoEspera = tempoChegada;
        injustica = tempoChegada;

        this.heap = heap;
        this.heap.insert(this);

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

    public long getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(long tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public int getProcessoId() {
        return processoId;
    }

    public void setProcessoId(int processoId) {
        this.processoId = processoId;
    }

    public RedBlackTree getRedBlackTree() {
        return redBlackTree;
    }

    public void setRedBlackTree(RedBlackTree redBlackTree) {
        this.redBlackTree = redBlackTree;
    }

    public long getTempoCPU() {
        return tempoCPU;
    }

    public void setTempoCPU(long tempoCPU) {
        this.tempoCPU = tempoCPU;
    }

    public long getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(long tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public long getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(long tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public ImplementacaoHeap getHeap() {
        return heap;
    }

    public void setHeap(ImplementacaoHeap heap) {
        this.heap = heap;
    }

    public long getTempoResposta() {
        return tempoResposta;
    }

    public void setTempoResposta(long tempoResposta) {
        this.tempoResposta = tempoResposta;
    }

    public long getInjustica() {
        return injustica;
    }

    public void setInjustica(long injustica) {
        this.injustica = injustica;
    }
}

