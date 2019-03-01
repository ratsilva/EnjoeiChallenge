package br.com.enjoeichallenge.repository.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.enjoeichallenge.objects.Photo;
import br.com.enjoeichallenge.objects.User;
import br.com.enjoeichallenge.objects.contracts.UserContract;

public class SQLiteManager_User extends SQLiteManager implements SQLiteManager_CRUD {

    private SQLiteManager_Photo sqlPhoto;

    public SQLiteManager_User(Context ctx_) {
        super(ctx_);
        sqlPhoto = new SQLiteManager_Photo(ctx_);
    }

    @Override
    public long insert(Object obj) {

        User user = (User) obj;

        user.setId_photo(sqlPhoto.save(user.getAvatar()));

        accessDB(OPEN_MODE);

        ContentValues values = new ContentValues();

        values.put(UserContract.ID_USER		, 	user.getId()        	);
        values.put(UserContract.NAME		    , 	user.getName()	        );
        values.put(UserContract.ID_PHOTO		, 	user.getId_photo()	    );

        long id_user = sqlite.insert(UserContract.TABLE_NAME, null, values);

        accessDB(CLOSE_MODE);

        return id_user;

    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }

    @Override
    public long update(Object obj) {

        User user = (User) obj;

        return user.getId();
    }

    @Override
    public Object select(Object obj) {

        User user = (User) obj;

        accessDB(OPEN_MODE);

        Cursor c = sqlite.rawQuery(

                "SELECT "
                + "		  " + UserContract.ID_USER  + "					,"
                + "		  " + UserContract.NAME     + "					,"
                + "		  " + UserContract.ID_PHOTO + "					"
                + " FROM " + UserContract.TABLE_NAME
                + " WHERE " + UserContract.ID_USER + " = " + user.getId(), null);

        user = null;

        if(c.moveToNext()) {

            user = new User();

            user.setId(         c.getInt(0));
            user.setName(       c.getString(1));
            user.setId_photo(   c.getInt(2));

        }

        c.close();

        accessDB(CLOSE_MODE);

        if(user != null){
            Photo p = new Photo();
            p.setId(user.getId_photo());
            p = (Photo) sqlPhoto.select(p);
            user.setAvatar(p);
        }

        return user;
    }

    @Override
    public ArrayList<Object> selectAll(String where) {

        accessDB(OPEN_MODE);

        Cursor c = sqlite.rawQuery(

                "SELECT "
                        + "		  " + UserContract.ID_USER  + "					,"
                        + "		  " + UserContract.NAME     + "					,"
                        + "		  " + UserContract.ID_PHOTO + "					"
                        + " FROM " + UserContract.TABLE_NAME
                        + " " + where, null);

        User user;

        ArrayList<Object> listUsers = new ArrayList<>();
        while(c.moveToNext()) {

            user = new User();

            user.setId(         c.getInt(0));
            user.setName(       c.getString(1));
            user.setId_photo(   c.getInt(2));

            listUsers.add(user);

        }

        c.close();

        accessDB(CLOSE_MODE);

        if(!listUsers.isEmpty()){

            for(int i = 0; i < listUsers.size(); i++){

                User u = (User) listUsers.get(i);

                Photo p = new Photo();
                p.setId(u.getId_photo());
                p = (Photo) sqlPhoto.select(p);
                u.setAvatar(p);

                listUsers.set(i, u);

            }

            return listUsers;
        }

        return null;

    }

    @Override
    public long save(Object obj) {

        long id_user;

        if(select(obj) != null){
            id_user = update(obj);
        }
        else{
            id_user = insert(obj);
        }

        return id_user;
    }
}
