package com.adrian971029.estruturadados.db.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.adrian971029.estruturadados.db.dto.DtoProcesso;

@Dao
public interface ProcessoDao {

    @Insert
    void insert(DtoProcesso processo);

    @Update
    void update(DtoProcesso processo);

    @Delete
    void delete(DtoProcesso processo);

    @Query("DELETE FROM processos_table")
    void deleteAllProcessos();

    @Query("SELECT * FROM processos_table ORDER BY id DESC")
    LiveData<List<DtoProcesso>> getAllProcessos();

}
