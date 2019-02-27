package br.com.enjoeichallenge.repository.managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.enjoeichallenge.repository.util.SQLiteLockThread;
import br.com.enjoeichallenge.repository.util.SQLiteRepository;

public class SQLiteManager {

    // Variáveis auxiliares
    private static Context ctx;

    // Variáveis de controle do banco de dados
    protected  	static SQLiteDatabase       sqlite;
    protected 	static SQLiteRepository     rec 		            = null;
    private	static int                      USUARIOS_ATIVOS_SQLITE  = 0;
    private static SQLiteLockThread         lock;

    public SQLiteManager(Context ctx_){
        SQLiteManager.ctx = ctx_;
    }

    public void accessDB(int modo){

        if(lock==null) lock = new SQLiteLockThread();

        lock.lock();

        switch(modo){

            case 1:
                connectDB();
                break;
            case 2:
                disconnectDB();
                break;
        }

        lock.unlock();

    }

    public void connectDB(){

        USUARIOS_ATIVOS_SQLITE++;

        if(sqlite==null) {
            rec = new SQLiteRepository();
            sqlite = rec.open(ctx);
        }
    }

    public void disconnectDB(){

        USUARIOS_ATIVOS_SQLITE--;

        if(USUARIOS_ATIVOS_SQLITE==0){

            rec.close();
            sqlite.close();
            sqlite = null;

        }

    }

}
