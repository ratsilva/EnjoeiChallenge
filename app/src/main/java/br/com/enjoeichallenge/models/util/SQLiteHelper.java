package br.com.enjoeichallenge.models.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private String[] scriptSQLCreate;

    public SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate) {
        super(context, nomeBanco, null, versaoBanco);
        this.scriptSQLCreate 	= scriptSQLCreate;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        for(String sql : scriptSQLCreate){
            db.execSQL(sql);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Lidar com atualização de banco de dados

    }
}
