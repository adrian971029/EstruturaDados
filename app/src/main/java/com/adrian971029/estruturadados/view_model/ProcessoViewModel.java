package com.adrian971029.estruturadados.view_model;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.adrian971029.estruturadados.db.dto.DtoProcesso;
import com.adrian971029.estruturadados.repository.ProcessosRepository;

public class ProcessoViewModel extends AndroidViewModel {

    private ProcessosRepository repository;
    private LiveData<List<DtoProcesso>> allProcessos;

    public ProcessoViewModel(@NonNull Application application) {
        super(application);
        repository = new ProcessosRepository(application);
        allProcessos = repository.getAllProcessos();
    }

    public void insert(DtoProcesso processo) {
        repository.insert(processo);
    }

    public void update(DtoProcesso processo) {
        repository.update(processo);
    }

    public void delete(DtoProcesso processo) {
        repository.delete(processo);
    }

    public void deleteAllProcessos() {
        repository.deleteAllProcessos();
    }

    public LiveData<List<DtoProcesso>> getAllNotes() {
        return allProcessos;
    }

}
