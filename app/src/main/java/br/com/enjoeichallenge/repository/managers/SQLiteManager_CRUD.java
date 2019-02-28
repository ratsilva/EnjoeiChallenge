package br.com.enjoeichallenge.repository.managers;

import java.util.ArrayList;

public interface SQLiteManager_CRUD {

    // Insert
    long insert(Object obj);

    // Delete
    boolean delete(Object obj);

    // Update
    long update(Object obj);

    // Select
    Object select(int id);
    ArrayList<Object> selectAll();

    // Auxiliares
    long save(Object obj);

}
