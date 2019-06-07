package com.adrian971029.estruturadados.core;

import com.adrian971029.estruturadados.model.ImplementacaoHeap;
import com.adrian971029.estruturadados.model.Processo;
import com.adrian971029.estruturadados.model.RedBlackTree;

import java.util.ArrayList;

public class Agendador {

    Processo processo;
    public static long CLOCK1 = 0;
    public static long CLOCK2 = 0;

    public ArrayList<Double> agendadorRedBlackTree(RedBlackTree redBlackTree, int tempoQuantico, int num) {

        long guardiaoTempo = 0;
        long tempoTotalEspera = 0;
        long tempoTotalResposta = 0;

        while(RedBlackTree.contNo > 1) {
            long iniciar = System.nanoTime();
            processo = redBlackTree.delete().processo;

            if(processo.getTempoExecucao() > tempoQuantico) {
                processo.setInjustica(processo.getInjustica() + tempoQuantico);
                processo.setTempoCPU(processo.getTempoCPU() + tempoQuantico);
                processo.setTempoExecucao(processo.getTempoExecucao() - tempoQuantico);
                CLOCK2 = CLOCK2 + tempoQuantico;
                processo.setTempoEspera(CLOCK2 - processo.getTempoChegada() - processo.getTempoCPU());
                if(processo.getTempoExecucao() > 0) {
                    redBlackTree.Inserir(processo);
                } else {
                    tempoTotalEspera += processo.getTempoEspera();
                    processo.setTempoResposta(CLOCK2 - processo.getTempoChegada());
                    tempoTotalResposta += processo.getTempoResposta();
                }
            } else {
                processo.setTempoCPU(processo.getTempoCPU() + processo.getTempoExecucao());
                CLOCK2 = CLOCK2 + processo.getTempoExecucao();
                processo.setTempoEspera(CLOCK2 - processo.getTempoChegada() - processo.getTempoCPU());
                processo.setTempoExecucao(0);
                tempoTotalEspera += processo.getTempoEspera();
                processo.setTempoResposta(CLOCK2 - processo.getTempoChegada());
                tempoTotalResposta += processo.getTempoResposta();
            }
            long end = System.nanoTime();
            guardiaoTempo+=(end-iniciar);
        }

        long unidadeTempo = guardiaoTempo / CLOCK1;

        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add((double)num);
        arrayList.add((double)guardiaoTempo);
        arrayList.add((double)guardiaoTempo/num);
        arrayList.add((double)tempoTotalEspera * unidadeTempo);
        arrayList.add((double)tempoTotalResposta*unidadeTempo);

        return arrayList;

    }

    public ArrayList<Double> agendadorHeap(ImplementacaoHeap heap, int tempoQuantico, int num) {

        long guardiaoTempo = 0;
        long tempoTotalEspera = 0;
        long tempoTotalResposta = 0;

        while(RedBlackTree.contNo > 1) {
            long iniciar = System.nanoTime();
            processo = heap.remove();

            if(processo.getTempoExecucao() > tempoQuantico) {
                processo.setInjustica(processo.getInjustica() + tempoQuantico);
                processo.setTempoCPU(processo.getTempoCPU() + tempoQuantico);
                processo.setTempoExecucao(processo.getTempoExecucao() - tempoQuantico);
                CLOCK2 = CLOCK2 + tempoQuantico;
                processo.setTempoEspera(CLOCK2 - processo.getTempoChegada() - processo.getTempoCPU());
                if(processo.getTempoExecucao() > 0) {
                    heap.insert(processo);
                } else {
                    tempoTotalEspera += processo.getTempoEspera();
                    processo.setTempoResposta(CLOCK2 - processo.getTempoChegada());
                    tempoTotalResposta += processo.getTempoResposta();
                }
            } else {
                processo.setTempoCPU(processo.getTempoCPU() + processo.getTempoExecucao());
                CLOCK2 = CLOCK2 + processo.getTempoExecucao();
                processo.setTempoEspera(CLOCK2 - processo.getTempoChegada() - processo.getTempoCPU());
                processo.setTempoExecucao(0);
                tempoTotalEspera += processo.getTempoEspera();
                processo.setTempoResposta(CLOCK2 - processo.getTempoChegada());
                tempoTotalResposta += processo.getTempoResposta();
            }
            long end = System.nanoTime();
            guardiaoTempo+=(end-iniciar);
        }

        long unidadeTempo = guardiaoTempo / CLOCK1;

        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add((double)num);
        arrayList.add((double)guardiaoTempo);
        arrayList.add((double)guardiaoTempo/num);
        arrayList.add((double)tempoTotalEspera * unidadeTempo);
        arrayList.add((double)tempoTotalResposta*unidadeTempo);

        return arrayList;

    }

}
