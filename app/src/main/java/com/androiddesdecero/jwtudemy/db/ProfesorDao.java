package com.androiddesdecero.jwtudemy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.androiddesdecero.jwtudemy.model.Profesor;
import com.androiddesdecero.jwtudemy.model.ProfesorEntity;

import java.util.List;

@Dao
public interface ProfesorDao {

    @Insert
    void insert(Profesor profesor);

    @Update
    void update(Profesor profesor);

    @Delete
    void delete(Profesor profesor);

    //Borrar todo
    @Query("DELETE FROM tabla_profesor")
    void deleteTodo();

    @Query("SELECT * FROM tabla_profesor")
    List<Profesor> getAll();
}
