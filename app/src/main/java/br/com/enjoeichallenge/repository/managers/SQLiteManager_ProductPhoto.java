package br.com.enjoeichallenge.repository.managers;

import android.content.ContentValues;
import android.content.Context;

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

        ContentValues valores = new ContentValues();

        valores.put(ProductPhotoContract.IDPRODUCT		, 	prodPhoto.getIdproduct()        );
        valores.put(ProductPhotoContract.IDPHOTO		, 	prodPhoto.getIdphoto()	        );
        valores.put(ProductPhotoContract.IDUSER		    , 	prodPhoto.getIduser()	        );

        long id_user = sqlite.insert(ProductPhotoContract.TABLE_NAME, null, valores);

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
        return null;
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
