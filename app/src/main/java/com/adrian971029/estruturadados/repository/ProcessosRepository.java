package com.adrian971029.estruturadados.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

import com.adrian971029.estruturadados.db.EstruturaDadosDatabase;
import com.adrian971029.estruturadados.db.dao.ProcessoDao;
import com.adrian971029.estruturadados.db.dto.DtoProcesso;

public class ProcessosRepository {

    private ProcessoDao processoDao;
    private LiveData<List<DtoProcesso>> allNotes;

    public ProcessosRepository(Application application) {
        EstruturaDadosDatabase database = EstruturaDadosDatabase.getInstance(application);
        processoDao = database.processoDao();
        allNotes = processoDao.getAllProcessos();
    }

    public void insert(DtoProcesso processo) {
        new InsertProcessoAsyncTask(processoDao).execute(processo);
    }

    public void update(DtoProcesso processo) {
        new UpdateProcessoAsyncTask(processoDao).execute(processo);
    }

    public void delete(DtoProcesso processo) {
        new DeleteProcessoAsyncTask(processoDao).execute(processo);
    }

    public void deleteAllProcessos() {
        new DeleteAllProcessosAsyncTask(processoDao).execute();
    }

    public LiveData<List<DtoProcesso>> getAllProcessos() {
        return allNotes;
    }

    private static class InsertProcessoAsyncTask extends AsyncTask<DtoProcesso, Void, Void> {
        private ProcessoDao processoDao1;

        private InsertProcessoAsyncTask(ProcessoDao processoDao) {
            this.processoDao1 = processoDao;
        }

        @Override
        protected Void doInBackground(DtoProcesso... processos) {
            processoDao1.insert(processos[0]);
            return null;
        }
    }

    private static class UpdateProcessoAsyncTask extends AsyncTask<DtoProcesso, Void, Void> {
        private ProcessoDao processoDao;

        private UpdateProcessoAsyncTask(ProcessoDao noteDao) {
            this.processoDao = noteDao;
        }

        @Override
        protected Void doInBackground(DtoProcesso... processos) {
            processoDao.update(processos[0]);
            return null;
        }
    }

    private static class DeleteProcessoAsyncTask extends AsyncTask<DtoProcesso, Void, Void> {
        private ProcessoDao processoDao1;

        private DeleteProcessoAsyncTask(ProcessoDao processoDao) {
            this.processoDao1 = processoDao;
        }

        @Override
        protected Void doInBackground(DtoProcesso... processos) {
            processoDao1.delete(processos[0]);
            return null;
        }
    }

    private static class DeleteAllProcessosAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProcessoDao processoDao;

        private DeleteAllProcessosAsyncTask(ProcessoDao processoDao) {
            this.processoDao = processoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            processoDao.deleteAllProcessos();
            return null;
        }
    }

}
