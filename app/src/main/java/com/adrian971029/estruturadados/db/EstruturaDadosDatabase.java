package com.adrian971029.estruturadados.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.adrian971029.estruturadados.db.dao.ProcessoDao;
import com.adrian971029.estruturadados.model.Processo;

@Database(entities = {Processo.class}, version = 1)
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
            //new PopulateDbAsyncTask(instance).execute();
        }
    };

    //Metodo para popular elementos ao banco primeira vez que o aplicativo es iniciado
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProcessoDao processoDao;

        public PopulateDbAsyncTask(EstruturaDadosDatabase db) {
            processoDao = db.processoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Adicionar elementos para que inicien siempre en el banco de dados
            return null;
        }
    }

}
