package com.adrian971029.estruturadados.model;

public class RedBlackTree {

    public static int contNo = 0;
    private final int BLACK = 1;
    private final int RED = 0;
    private final No nulo = new No(null);
    private No raiz = nulo;

    public class No {

        public Processo processo = null;
        int cor = BLACK;
        No esquerda = nulo, direita = nulo, pai = nulo;

        No(Processo p) {
            this.processo = p;
            contNo++;
        }
    }


    public void Inserir(Processo key) {

        No no = new No(key);
        No aux = raiz;
        if (raiz == nulo) {
            raiz = no;
            no.cor = BLACK;
            no.pai = nulo;
        }
        else {
            no.cor = RED;
            while (true) {
                if (no.processo.getInjustica() < aux.processo.getInjustica()) {
                    if (aux.esquerda == nulo) {
                        aux.esquerda = no;
                        no.pai = aux;
                        break;
                    } else {
                        aux = aux.esquerda;
                    }
                } else {
                    if (aux.direita == nulo) {
                        aux.direita = no;
                        no.pai = aux;
                        break;
                    } else {
                        aux = aux.direita;
                    }
                }
            }
            equilibrar(no);
        }

    }


    private No procuraNo(No procuraNo, No no) {

        if (raiz == nulo) {
            return null;
        }

        if (procuraNo.processo.getInjustica() < no.processo.getInjustica()) {
            if (no.esquerda != nulo) {
                return procuraNo(procuraNo, no.esquerda);
            }
        } else if (procuraNo.processo.getInjustica() > no.processo.getInjustica()) {
            if (no.direita != nulo) {
                return procuraNo(procuraNo, no.direita);
            }
        } else if (procuraNo.processo.getInjustica() == no.processo.getInjustica()) {
            return no;
        }
        return null;
    }


    public void imprimeArvore(No no) {
        if (no == nulo) {
            return;
        }
        imprimeArvore(no.esquerda);
        System.out.print("P"+no.processo.getProcessoId() +" "+no.processo.getInjustica() +((no.cor ==RED)?"R":"B ")+"  ");
        imprimeArvore(no.direita);
    }


    private void equilibrar(No no) {
        while (no.pai.cor == RED) {
            No uncle = null;
            if (no.pai == no.pai.esquerda) {
                uncle = no.pai.pai.direita;
                if (uncle != null && uncle.cor == RED) {
                    no.pai.cor = BLACK;
                    uncle.cor = BLACK;
                    no.pai.pai.cor = RED;
                    no = no.pai.pai;
                    continue;
                }
                if (no == no.pai.direita) {
                    no = no.pai;
                    girarEsquerda(no);
                }
                no.pai.cor = BLACK;
                no.pai.pai.cor = RED;
                girarDireita(no.pai.pai);
            } else {
                uncle = no.pai.pai.esquerda;
                if (uncle != null && uncle.cor == RED) {
                    no.pai.cor = BLACK;
                    uncle.cor = BLACK;
                    no.pai.pai.cor = RED;
                    no = no.pai.pai;
                    continue;
                }
                if (no == no.pai.esquerda) {
                    no = no.pai;
                    girarDireita(no);
                }
                no.pai.cor = BLACK;
                no.pai.pai.cor = RED;
                girarEsquerda(no.pai.pai);
            }
        }raiz.cor = BLACK;
    }

    void girarDireita(No no) {
        if (no.pai != null) {
            if (no == no.pai.esquerda) {
                no.pai.esquerda = no.esquerda;
            } else {
                no.pai.direita = no.esquerda;
            }
            no.esquerda.pai = no.pai;
            no.pai = no.esquerda;
            if (no.esquerda.direita != null) {
                no.esquerda.direita.pai = no;
            }
            no.esquerda = no.esquerda.direita;
            no.pai.direita = no;
        } else {
            No esquerda = raiz.esquerda;
            raiz.esquerda = raiz.esquerda.direita;
            esquerda.direita.pai = raiz;
            raiz.pai = esquerda;
            esquerda.direita = raiz;
            esquerda.pai = null;
            raiz = esquerda;
        }
    }

    void girarEsquerda(No no) {
        if (no.pai != null) {
            if (no == no.pai.esquerda) {
                no.pai.esquerda = no.direita;
            } else {
                no.pai.direita = no.direita;
            }
            no.direita.pai = no.pai;
            no.pai = no.direita;
            if (no.direita.esquerda != null) {
                no.direita.esquerda.pai = no;
            }
            no.direita = no.direita.esquerda;
            no.pai.esquerda = no;
        } else {
            No direita = raiz.direita;
            raiz.direita = direita.esquerda;
            direita.esquerda.pai = raiz;
            raiz.pai = direita;
            direita.esquerda = raiz;
            direita.pai = null;
            raiz = direita;
        }
    }

    void apagarArvore() {
        raiz = nulo;
    }

    private No procuraMaisEsquerda() {
        if(raiz != nulo) {
            No no = raiz;
            while(no.esquerda != nulo)
                no = no.esquerda;
            return no;
        } else {
            System.out.println("Vacio");
            return null;
        }
    }

    public No delete(){

        No noZ = procuraMaisEsquerda();

        if(noZ==null){System.out.println("IT IS NULL");}
        if((noZ = procuraNo(noZ, raiz))==null)
            return null;
        No noX;
        No noY = noZ;
        int nyCorOriginal = noY.cor;

        if(noZ.esquerda == nulo){
            noX = noZ.direita;
            transplant(noZ, noZ.direita);
        }else if(noZ.direita == nulo){
            noX = noZ.esquerda;
            transplant(noZ, noZ.esquerda);
        }else{
            noY = treeMinimum(noZ.direita);
            nyCorOriginal = noY.cor;
            noX = noY.direita;
            if(noY.pai == noZ)
                noX.pai = noY;
            else{
                transplant(noY, noY.direita);
                noY.direita = noZ.direita;
                noY.direita.pai = noY;
            }
            transplant(noZ, noY);
            noY.esquerda = noZ.esquerda;
            noY.esquerda.pai = noY;
            noY.cor = noZ.cor;
        }
        if(nyCorOriginal==BLACK)
            deleteFixup(noX);
        contNo--;
        return noZ;

    }

    void deleteFixup(No noA){
        while(noA != raiz && noA.cor == BLACK){
            if(noA == noA.pai.esquerda){
                No noB = noA.pai.direita;
                if(noB.cor == RED){
                    noB.cor = BLACK;
                    noA.pai.cor = RED;
                    girarEsquerda(noA.pai);
                    noB = noA.pai.direita;
                }
                if(noB.esquerda.cor == BLACK && noB.direita.cor == BLACK){
                    noB.cor = RED;
                    noA = noA.pai;
                    continue;
                }
                else if(noB.direita.cor == BLACK){
                    noB.esquerda.cor = BLACK;
                    noB.cor = RED;
                    girarDireita(noB);
                    noB = noA.pai.direita;
                }
                if(noB.direita.cor == RED){
                    noB.cor = noA.pai.cor;
                    noA.pai.cor = BLACK;
                    noB.direita.cor = BLACK;
                    girarEsquerda(noA.pai);
                    noA = raiz;
                }
            }else{
                No noC = noA.pai.esquerda;
                if(noC.cor == RED){
                    noC.cor = BLACK;
                    noA.pai.cor = RED;
                    girarDireita(noA.pai);
                    noC = noA.pai.esquerda;
                }
                if(noC.direita.cor == BLACK && noC.esquerda.cor == BLACK){
                    noC.cor = RED;
                    noA = noA.pai;
                    continue;
                }
                else if(noC.esquerda.cor == BLACK){
                    noC.direita.cor = BLACK;
                    noC.cor = RED;
                    girarEsquerda(noC);
                    noC = noA.pai.esquerda;
                }
                if(noC.esquerda.cor == RED){
                    noC.cor = noA.pai.cor;
                    noA.pai.cor = BLACK;
                    noC.esquerda.cor = BLACK;
                    girarDireita(noA.pai);
                    noA = raiz;
                }
            }
        }
        noA.cor = BLACK;
    }

    No treeMinimum(No raizSubArvore){
        while(raizSubArvore.esquerda!= nulo){
            raizSubArvore = raizSubArvore.esquerda;
        }
        return raizSubArvore;
    }

    void transplant(No objetivo, No noUtilizado){
        if(objetivo.pai == nulo){
            raiz = noUtilizado;
        }else if(objetivo == objetivo.pai.esquerda){
            objetivo.pai.esquerda = noUtilizado;
        }else
            objetivo.pai.direita = noUtilizado;
        noUtilizado.pai = objetivo.pai;
    }

}
