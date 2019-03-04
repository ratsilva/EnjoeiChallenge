package br.com.enjoeichallenge.models.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.enjoeichallenge.objects.contracts.PhotoContract;
import br.com.enjoeichallenge.objects.contracts.ProductContract;
import br.com.enjoeichallenge.objects.contracts.ProductPhotoContract;
import br.com.enjoeichallenge.objects.contracts.UserContract;

public class SQLiteRepository{

    private static final String[] SCRIPT_DATABASE_CREATE = new String[] {

            "DROP TABLE IF EXISTS " + ProductContract.TABLE_NAME            + ";" 	,
            "DROP TABLE IF EXISTS " + PhotoContract.TABLE_NAME              + ";" 	,
            "DROP TABLE IF EXISTS " + UserContract.TABLE_NAME               + ";" 	,
            "DROP TABLE IF EXISTS " + ProductPhotoContract.TABLE_NAME       + ";" 	,

            "	CREATE TABLE '" + ProductContract.TABLE_NAME + "' (	"	+
            "	    '" + ProductContract.ID_PRODUCT                     + "'  integer 	,	"	+
            "	    '" + ProductContract.DISCOUNT_PERCENTAGE            + "'  real 	    ,	"	+
            "	    '" + ProductContract.TITLE                          + "'  text 	    ,	"	+
            "	    '" + ProductContract.PRICE                          + "'  real 	    ,	"	+
            "	    '" + ProductContract.ORIGINAL_PRICE                 + "'  real 	    ,	"	+
            "	    '" + ProductContract.SIZE                           + "'  text 	    ,	"	+
            "	    '" + ProductContract.LIKES_COUNT                    + "'  integer 	,	"	+
            "	    '" + ProductContract.MAXIMUM_INSTALLMENT            + "'  integer 	,	"	+
            "	    '" + ProductContract.PUBLISHED_COMMENTS_COUNT       + "'  integer 	,	"	+
            "	    '" + ProductContract.CONTENT                        + "'  text 	    ,	"	+
            "	    '" + ProductContract.ID_USER                        + "'  integer 	,	"	+
            "	    PRIMARY KEY (" + ProductContract.ID_PRODUCT + ", " + ProductContract.ID_USER + ")"	+
            "	);	",

            "	CREATE TABLE '" + UserContract.TABLE_NAME + "' (	"	+
                    "	    '" + UserContract.ID_USER         + "'  integer 	,	"	+
                    "	    '" + UserContract.NAME            + "'  text 	    ,	"	+
                    "	    '" + UserContract.ID_PHOTO        + "'  integer 	,	"	+
                    "	    PRIMARY KEY (" + UserContract.ID_USER + ")"	+
                    "	);	",

            "	CREATE TABLE '" + PhotoContract.TABLE_NAME + "' (	"	+
                    "	    '" + PhotoContract.ID_PHOTO         + "'  integer PRIMARY KEY AUTOINCREMENT	,	"	+
                    "	    '" + PhotoContract.PUBLIC_ID        + "'  text 	                            ,	"	+
                    "	    '" + PhotoContract.CROP             + "'  text 	                            ,	"	+
                    "	    '" + PhotoContract.GRAVITY          + "'  text 	                            	"	+
                    "	);	",

            "	CREATE TABLE '" + ProductPhotoContract.TABLE_NAME + "' (	"	+
                    "	    '" + ProductPhotoContract.IDPRODUCT         + "'  integer	,	"	+
                    "	    '" + ProductPhotoContract.IDPHOTO           + "'  integer 	,   "	+
                    "	    '" + ProductPhotoContract.IDUSER            + "'  integer 	,   "	+
                    "	    PRIMARY KEY (" + ProductPhotoContract.IDPRODUCT + ", " + ProductPhotoContract.IDUSER + ", " + ProductPhotoContract.IDPHOTO + ")"	+
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
