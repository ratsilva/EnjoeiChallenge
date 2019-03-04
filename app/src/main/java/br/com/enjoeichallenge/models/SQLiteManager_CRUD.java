package br.com.enjoeichallenge.models;

import java.util.ArrayList;

public interface SQLiteManager_CRUD {

    // Insert
    long insert(Object obj);

    // Delete
    boolean delete(Object obj);

    // Update
    long update(Object obj);

    // Select
    Object select(Object obj);
    ArrayList<Object> selectAll(String where);

    // Auxiliares
    long save(Object obj);

}
