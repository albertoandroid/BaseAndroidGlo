package com.androiddesdecero.jwtudemy.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.androiddesdecero.jwtudemy.model.Profesor;
import com.androiddesdecero.jwtudemy.model.ProfesorEntity;

@Database(entities = {Profesor.class}, version = 1)
public abstract class ProfesorDatabase extends RoomDatabase {

    private static ProfesorDatabase instance;

    public abstract ProfesorDao profesorDao();

    public static synchronized ProfesorDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProfesorDatabase.class, "profesor_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
