package br.com.enjoeichallenge.repository.managers;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import br.com.enjoeichallenge.models.Photo;

public class SQLiteManager_Photo extends SQLiteManager implements SQLiteManager_CRUD {

    public SQLiteManager_Photo(Context ctx_) {
        super(ctx_);
    }

    @Override
    public long insert(Object obj) {

        Photo photo = (Photo) obj;

        accessDB(OPEN_MODE);

        ContentValues valores = new ContentValues();

        //valores.put("id_user"	    , 	id_photo	);
        valores.put("public_id"		, 	photo.getPublic_id()	);
        valores.put("crop"		    , 	photo.getCrop()	        );
        valores.put("gravity"		, 	photo.getGravity()	    );
        valores.put("id_product"	, 	photo.getId_product()	);

        long id_photo = sqlite.insert("Photo", null, valores);

        accessDB(CLOSE_MODE);

        return id_photo;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public Object select(int id) {
        return null;
    }

    @Override
    public ArrayList<Object> selectAll() {
        return null;
    }
}
