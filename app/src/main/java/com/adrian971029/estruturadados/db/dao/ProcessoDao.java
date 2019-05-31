package com.adrian971029.estruturadados.db.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.adrian971029.estruturadados.model.Processo;

@Dao
public interface ProcessoDao {

    @Insert
    void insert(Processo processo);

    @Update
    void update(Processo processo);

    @Delete
    void delete(Processo processo);

    @Query("DELETE FROM processos_table")
    void deleteAllProcessos();

    @Query("SELECT * FROM processos_table ORDER BY tempoExecucao DESC")
    LiveData<List<Processo>> getAllProcessos();

}
