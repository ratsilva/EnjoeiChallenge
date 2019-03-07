package br.com.enjoeichallenge.models;

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
    public void deleteAll() {

        accessDB(OPEN_MODE);

        sqlite.delete(PhotoContract.TABLE_NAME, null, null);
        sqlite.delete("SQLITE_SEQUENCE", "name = '" + PhotoContract.TABLE_NAME + "'", null);

        accessDB(CLOSE_MODE);

    }

    @Override
    public long update(Object obj) {


        return 0;
    }

    @Override
    public Object select(Object obj) {

        Photo photo = (Photo) obj;

        accessDB(OPEN_MODE);

        Cursor c = sqlite.rawQuery(

                "SELECT "
                        + "		  " + PhotoContract.ID_PHOTO        + "					,"
                        + "		  " + PhotoContract.PUBLIC_ID       + "					,"
                        + "		  " + PhotoContract.CROP            + "					,"
                        + "		  " + PhotoContract.GRAVITY         + "					"
                        + " FROM " + PhotoContract.TABLE_NAME
                        + " WHERE " + PhotoContract.ID_PHOTO + " = " + photo.getId(), null);

        photo = null;

        if(c.moveToNext()) {

            photo = new Photo();

            photo.setId(            c.getInt(0));
            photo.setPublic_id(     c.getString(1));
            photo.setCrop(          c.getString(2));
            photo.setGravity(       c.getString(3));

        }

        c.close();

        accessDB(CLOSE_MODE);

        return photo;

    }

    @Override
    public ArrayList<Object> selectAll(String where) {

        accessDB(OPEN_MODE);

        Cursor c = sqlite.rawQuery(

                "SELECT "
                        + "		  " + PhotoContract.ID_PHOTO        + "					,"
                        + "		  " + PhotoContract.PUBLIC_ID       + "					,"
                        + "		  " + PhotoContract.CROP            + "					,"
                        + "		  " + PhotoContract.GRAVITY         + "					"
                        + " FROM " + PhotoContract.TABLE_NAME
                        + " " + where, null);

        Photo photo;

        ArrayList<Object> listPhotos = new ArrayList<>();
        while(c.moveToNext()) {

            photo = new Photo();

            photo.setId(            c.getInt(0));
            photo.setPublic_id(     c.getString(1));
            photo.setCrop(          c.getString(2));
            photo.setGravity(       c.getString(3));

            listPhotos.add(photo);

        }

        c.close();

        accessDB(CLOSE_MODE);

        if(listPhotos.isEmpty()) 	return null;
        else					    return listPhotos;

    }

    @Override
    public long save(Object obj) {

        long id_photo;

        if(select(obj) != null){
            id_photo = update(obj);
        }
        else{
            id_photo = insert(obj);
        }

        return id_photo;
    }
}
