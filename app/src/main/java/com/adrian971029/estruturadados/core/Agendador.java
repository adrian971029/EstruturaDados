package com.adrian971029.estruturadados.core;

import com.adrian971029.estruturadados.model.ImplementacaoHeap;
import com.adrian971029.estruturadados.model.Processo;
import com.adrian971029.estruturadados.model.RedBlackTree;

public class Agendador {

    Processo processo;
    private static long CLOCK1 = 0;
    private static long CLOCK2 = 0;

    public double[] agendadorRedBlackTree(RedBlackTree redBlackTree, int tempoQuantico, int numEntrada) {

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

        long unidadeTempo = guardiaoTempo / CLOCK2;

        double [] dados = new double[5];
        dados[0] = (double)numEntrada;
        dados[1] = (double)guardiaoTempo;
        dados[2] = (double)guardiaoTempo/numEntrada;
        dados[3] = (double)tempoTotalEspera * unidadeTempo;
        dados[4] = (double)tempoTotalResposta*unidadeTempo;

        return dados;

    }

    public double[] agendadorHeap(ImplementacaoHeap heap, int tempoQuantico, int numEntrada) {

        long guardiaoTempo = 0;
        long tempoTotalEspera = 0;
        long tempoTotalResposta = 0;

        while(heap.getSize() >= 1) {
            long iniciar = System.nanoTime();
            processo = heap.remove();

            if(processo.getTempoExecucao() > tempoQuantico) {
                processo.setInjustica(processo.getInjustica() + tempoQuantico);
                processo.setTempoCPU(processo.getTempoCPU() + tempoQuantico);
                processo.setTempoExecucao(processo.getTempoExecucao() - tempoQuantico);
                CLOCK1 = CLOCK1 + tempoQuantico;
                processo.setTempoEspera(CLOCK1 - processo.getTempoChegada() - processo.getTempoCPU());
                if(processo.getTempoExecucao() > 0) {
                    heap.insert(processo);
                } else {
                    tempoTotalEspera += processo.getTempoEspera();
                    processo.setTempoResposta(CLOCK1 - processo.getTempoChegada());
                    tempoTotalResposta += processo.getTempoResposta();
                }
            } else {
                processo.setTempoCPU(processo.getTempoCPU() + processo.getTempoExecucao());
                CLOCK1 = CLOCK1 + processo.getTempoExecucao();
                processo.setTempoEspera(CLOCK1 - processo.getTempoChegada() - processo.getTempoCPU());
                processo.setTempoExecucao(0);
                tempoTotalEspera += processo.getTempoEspera();
                processo.setTempoResposta(CLOCK1 - processo.getTempoChegada());
                tempoTotalResposta += processo.getTempoResposta();
            }
            long end = System.nanoTime();
            guardiaoTempo+=(end-iniciar);
        }

        long unidadeTempo = guardiaoTempo / CLOCK1;

        double [] dados = new double[5];
        dados[0] = (double)numEntrada;
        dados[1] = (double)guardiaoTempo;
        dados[2] = (double)guardiaoTempo/numEntrada;
        dados[3] = (double)tempoTotalEspera * unidadeTempo;
        dados[4] = (double)tempoTotalResposta*unidadeTempo;

        return dados;

    }

}
