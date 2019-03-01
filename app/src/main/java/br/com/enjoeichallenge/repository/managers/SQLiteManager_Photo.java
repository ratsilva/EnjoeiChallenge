package br.com.enjoeichallenge.repository.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

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

        ContentValues values = new ContentValues();

        values.put(PhotoContract.PUBLIC_ID		, 	photo.getPublic_id()	);
        values.put(PhotoContract.CROP		    , 	photo.getCrop()	        );
        values.put(PhotoContract.GRAVITY		, 	photo.getGravity()	    );

        long id_photo = sqlite.insert(PhotoContract.TABLE_NAME, null, values);

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
    public Object select(long id) {

        Photo photo = null;

        accessDB(OPEN_MODE);

        Cursor c = sqlite.rawQuery(

                "SELECT "
                        + "		  " + PhotoContract.ID_PHOTO        + "					,"
                        + "		  " + PhotoContract.PUBLIC_ID       + "					,"
                        + "		  " + PhotoContract.CROP            + "					,"
                        + "		  " + PhotoContract.GRAVITY         + "					"
                        + " FROM " + PhotoContract.TABLE_NAME
                        + " WHERE " + PhotoContract.ID_PHOTO + " = " + id, null);


        if(c.moveToNext()) {

            photo = new Photo();

            photo.setId(c.getInt(0));
            photo.setPublic_id(c.getString(1));
            photo.setCrop(c.getString(2));
            photo.setGravity(c.getString(3));

        }

        c.close();

        accessDB(CLOSE_MODE);

        return photo;

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
