package com.adrian971029.estruturadados.view_model;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.adrian971029.estruturadados.model.Processo;
import com.adrian971029.estruturadados.repository.ProcessosRepository;

public class ProcessoViewModel extends AndroidViewModel {

    private ProcessosRepository repository;
    private LiveData<List<Processo>> allProcessos;

    public ProcessoViewModel(@NonNull Application application) {
        super(application);
        repository = new ProcessosRepository(application);
        allProcessos = repository.getAllProcessos();
    }

    public void insert(Processo processo) {
        repository.insert(processo);
    }

    public void update(Processo processo) {
        repository.update(processo);
    }

    public void delete(Processo processo) {
        repository.delete(processo);
    }

    public void deleteAllProcessos() {
        repository.deleteAllProcessos();
    }

    public LiveData<List<Processo>> getAllNotes() {
        return allProcessos;
    }

}
