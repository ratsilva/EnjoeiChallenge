package br.com.enjoeichallenge.repository.managers;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import br.com.enjoeichallenge.objects.Photo;
import br.com.enjoeichallenge.objects.contracts.PhotoContract;

public class SQLiteManager_Photo extends SQLiteManager implements SQLiteManager_CRUD {

    public SQLiteManager_Photo(Context ctx_) {
        super(ctx_);
    }

    @Override
    public long insert(Object obj) {

        Photo photo = (Photo) obj;

        accessDB(OPEN_MODE);

        ContentValues valores = new ContentValues();

        valores.put(PhotoContract.PUBLIC_ID		, 	photo.getPublic_id()	);
        valores.put(PhotoContract.CROP		    , 	photo.getCrop()	        );
        valores.put(PhotoContract.GRAVITY		, 	photo.getGravity()	    );
        valores.put(PhotoContract.ID_PRODUCT	, 	photo.getId_product()	);

        long id_photo = sqlite.insert(PhotoContract.TABLE_NAME, null, valores);

        accessDB(CLOSE_MODE);

        return id_photo;
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
    public Object select(int id) {
        return null;
    }

    @Override
    public ArrayList<Object> selectAll() {
        return null;
    }

    @Override
    public long save(Object obj) {

        Photo photo = (Photo) obj;
        long id_photo;

        if(select(photo.getId()) != null) 	id_photo = update(obj);
        else						        id_photo = insert(obj);

        return id_photo;
    }
}
