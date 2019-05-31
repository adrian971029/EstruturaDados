package com.adrian971029.estruturadados.model;

import java.util.ArrayList;

public class ImplementacaoHeap {

    private static final int FRENTE = 1;
    private static ArrayList<Processo> Heap;

    public ImplementacaoHeap(){

        Heap = new ArrayList<Processo>();
        Heap.add(null);

    }

    public void insert(Processo p)
    {
        Heap.add(p);
        int current = Heap.size()-1;

        while (current!=1&&(Heap.get(current).getInjustica() < Heap.get(pai(current)).getInjustica()))
        {

            troca(current, pai(current));
            current = pai(current);

        }
    }

    private void minHeapify(int pos)
    {
        if (!isFolha(pos))
        {
            if ( (Heap.get(pos)).getInjustica() > Heap.get(filhoEsquerdo(pos)).getInjustica() || (Heap.get(pos)).getInjustica() > Heap.get(filhoDireito(pos)).getInjustica())
            {
                if (Heap.get(filhoEsquerdo(pos)).getInjustica() < Heap.get(filhoDireito(pos)).getInjustica())
                {
                    troca(pos, filhoEsquerdo(pos));
                    minHeapify(filhoEsquerdo(pos));
                }
                else
                {
                    troca(pos, filhoDireito(pos));
                    minHeapify(filhoDireito(pos));
                }
            }
        }
    }

    public Processo remove()
    {
        Processo pr=null;
        if(Heap.size()>1)
        {
            pr = Heap.get(FRENTE);
            Heap.set(FRENTE,Heap.get(Heap.size()-1));
            Heap.remove(Heap.size()-1);
            minHeapify(FRENTE);
        }
        return pr;
    }

    public int getSize()
    {
        return Heap.size()-1;
    }

    private int pai(int pos)
    {
        return pos / 2;
    }

    private int filhoEsquerdo(int pos)
    {
        return (2 * pos);
    }

    private int filhoDireito(int pos)
    {
        return (2 * pos) + 1;
    }

    private boolean isFolha(int pos)
    {
        if (pos >=  (Heap.size() / 2)  &&  pos <= Heap.size())
        {
            return true;
        }
        return false;
    }

    private void troca(int fpos, int spos)
    {
        Processo tmp;
        tmp = Heap.get(fpos);
        Heap.set (fpos, Heap.get(spos));
        Heap.set(spos, tmp);
    }

}

