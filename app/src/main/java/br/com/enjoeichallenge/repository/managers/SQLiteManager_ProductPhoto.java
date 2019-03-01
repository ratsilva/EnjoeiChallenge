package br.com.enjoeichallenge.repository.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.enjoeichallenge.objects.ProductPhoto;
import br.com.enjoeichallenge.objects.contracts.ProductPhotoContract;


public class SQLiteManager_ProductPhoto extends SQLiteManager implements SQLiteManager_CRUD {

    SQLiteManager_ProductPhoto(Context ctx_) {
        super(ctx_);
    }

    @Override
    public long insert(Object obj) {

        ProductPhoto prodPhoto = (ProductPhoto) obj;

        accessDB(OPEN_MODE);

        ContentValues values = new ContentValues();

        values.put(ProductPhotoContract.IDPRODUCT		, 	prodPhoto.getIdproduct()        );
        values.put(ProductPhotoContract.IDPHOTO		    , 	prodPhoto.getIdphoto()	        );
        values.put(ProductPhotoContract.IDUSER		    , 	prodPhoto.getIduser()	        );

        long id_user = sqlite.insert(ProductPhotoContract.TABLE_NAME, null, values);

        accessDB(CLOSE_MODE);

        return id_user;

    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }

    @Override
    public long update(Object obj) {
        return 0;
    }

    @Override
    public Object select(Object obj) {

        return null;
    }

    @Override
    public ArrayList<Object> selectAll(String where) {

        accessDB(OPEN_MODE);

        Cursor c = sqlite.rawQuery(

                "SELECT "
                        + "		  " + ProductPhotoContract.IDPRODUCT        + "					,"
                        + "		  " + ProductPhotoContract.IDUSER           + "					,"
                        + "		  " + ProductPhotoContract.IDPHOTO         + "					"
                        + " FROM " + ProductPhotoContract.TABLE_NAME
                        + " " + where, null);

        ProductPhoto prodPhoto;

        ArrayList<Object> listProdPhoto = new ArrayList<>();
        while(c.moveToNext()) {

            prodPhoto = new ProductPhoto();

            prodPhoto.setIdproduct(     c.getInt(0));
            prodPhoto.setIduser(        c.getInt(1));
            prodPhoto.setIdphoto(       c.getInt(2));

            listProdPhoto.add(prodPhoto);

        }

        c.close();

        accessDB(CLOSE_MODE);

        if(listProdPhoto.isEmpty()) 	return null;
        else					        return listProdPhoto;

    }

    @Override
    public long save(Object obj) {

        long id_productPhoto;

        if(select(obj) != null){
            id_productPhoto = update(obj);
        }
        else{
            id_productPhoto = insert(obj);
        }

        return id_productPhoto;
    }
}
