package br.com.enjoeichallenge.repository.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteRepository{

    private static final String[] SCRIPT_DATABASE_CREATE = new String[] {

            "DROP TABLE IF EXISTS Product;" 	,
            "DROP TABLE IF EXISTS User;" 		,
            "DROP TABLE IF EXISTS Photo;" 		,

            "	CREATE TABLE 'Product' (	"	+
                    "	  'id_product'                  integer 	,	"	+
                    "	  'discount_percentage' 	    real 		,	"	+
                    "	  'title' 		                text 		,	"	+
                    "	  'price' 			            real 		,	"	+
                    "	  'original_price' 		        real 		,	"	+
                    "	  'size' 			            text		,	"	+
                    "	  'likes_count' 			    integer		,	"	+
                    "	  'maximum_installment' 	    integer		,	"	+
                    "	  'published_comments_count'    integer		,	"	+
                    "	  'content' 	                text		,	"	+
                    "	  'id_user'                  	integer		,	"	+
                    "	  PRIMARY KEY (id_product,id_user)			"	+
                    "	);	"
            ,

            "	CREATE TABLE 'User' (	"	+
                    "	  'id_user' 		            integer 	,	"	+
                    "	  'name' 	                    text 		,	"	+
                    "	  'id_photo' 		            integer 	,	"   +
                    "	  PRIMARY KEY (id_user)			            "	+
                    "	);	"
            ,

            "	CREATE TABLE 'Photo' (	"	+
                    "	  'id_photo' 		            integer PRIMARY KEY AUTOINCREMENT 	,	"	+
                    "	  'public_id' 	                text 		            ,	"	+
                    "	  'crop' 		                text 	                ,	"   +
                    "	  'gravity' 		            text 	                ,	"   +
                    "	  'id_product' 		            integer                 "   +
                    "	);	"
    };

    private static String NOME_BANCO = "enjoeichallenge_database";

    private static final int VERSAO_BANCO = 1;

    protected static SQLiteDatabase db;

    private static SQLiteHelper dbHelper;

    public SQLiteDatabase open(Context ctx) {

        if(dbHelper==null){

            dbHelper = new SQLiteHelper(ctx,
                    SQLiteRepository.NOME_BANCO,
                    SQLiteRepository.VERSAO_BANCO,
                    SQLiteRepository.SCRIPT_DATABASE_CREATE);

            db = dbHelper.getWritableDatabase();

        }

        return db;

    }

    public void close() {

        if(dbHelper != null) {
            dbHelper.close();
            dbHelper = null;
        }

    }

}
