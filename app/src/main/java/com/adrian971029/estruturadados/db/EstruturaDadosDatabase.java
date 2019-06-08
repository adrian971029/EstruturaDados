package com.adrian971029.estruturadados.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.adrian971029.estruturadados.core.Agendador;
import com.adrian971029.estruturadados.db.dao.ProcessoDao;
import com.adrian971029.estruturadados.db.dto.DtoProcesso;
import com.adrian971029.estruturadados.model.ImplementacaoHeap;
import com.adrian971029.estruturadados.model.Processo;
import com.adrian971029.estruturadados.model.RedBlackTree;

import java.util.ArrayList;

@Database(entities = {DtoProcesso.class}, version = 1)
public abstract class EstruturaDadosDatabase extends RoomDatabase {

    private static EstruturaDadosDatabase instance;

    public abstract ProcessoDao processoDao();

    public static synchronized EstruturaDadosDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EstruturaDadosDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //Metodo para popular elementos ao banco primeira vez que o aplicativo es iniciado
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProcessoDao processoDao;
        private RedBlackTree redBlackTree;
        private ImplementacaoHeap heap;
        private ArrayList<Processo> processArrayList = new ArrayList<Processo>();
        private Agendador agendador;
        private double[] dadosASalvar = new double[5];
        private String nomeProcesso;
        private double tempoTotalExecucao;
        private double tempoExecucaoProcesso;
        private double tempoTotalEspera;
        private double tempoTotalResposta;
        private boolean isRedBlackTree;

        public PopulateDbAsyncTask(EstruturaDadosDatabase db) {
            processoDao = db.processoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Adicionar elementos para que inicien siempre en el banco de dados
            redBlackTree = new RedBlackTree();
            heap = new ImplementacaoHeap();
            agendador = new Agendador();

            for (int i = 0; i < 25; i++) {
                processArrayList.add(new Processo(redBlackTree, 1, 150, 300));
                processArrayList.add(new Processo(heap, 2, 150, 300));
            }

            dadosASalvar = agendador.agendadorRedBlackTree(redBlackTree, 3500, 25);

            nomeProcesso = "Red Black Tree Comparacao";
            tempoTotalExecucao = dadosASalvar[1];
            tempoExecucaoProcesso = dadosASalvar[2];
            tempoTotalEspera = dadosASalvar[3];
            tempoTotalResposta = dadosASalvar[4];

            processoDao.insert(new DtoProcesso(nomeProcesso, 25,
                    tempoTotalExecucao, tempoExecucaoProcesso, tempoTotalEspera,
                    tempoTotalResposta, true));

            dadosASalvar = agendador.agendadorHeap(heap, 3500, 25);

            nomeProcesso = "Heap Comparacao";
            tempoTotalExecucao = dadosASalvar[1];
            tempoExecucaoProcesso = dadosASalvar[2];
            tempoTotalEspera = dadosASalvar[3];
            tempoTotalResposta = dadosASalvar[4];

            processoDao.insert(new DtoProcesso(nomeProcesso, 25,
                    tempoTotalExecucao, tempoExecucaoProcesso, tempoTotalEspera,
                    tempoTotalResposta, false));

            return null;
        }
    }

}
