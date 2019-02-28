package br.com.enjoeichallenge.repository.managers;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import br.com.enjoeichallenge.models.Photo;
import br.com.enjoeichallenge.models.Product;
import br.com.enjoeichallenge.models.User;

public class SQLiteManager_User extends SQLiteManager implements SQLiteManager_CRUD {

    public SQLiteManager_User(Context ctx_) {
        super(ctx_);
    }

    @Override
    public long insert(Object obj) {

        User user = (User) obj;

        accessDB(OPEN_MODE);

        ContentValues valores = new ContentValues();

        valores.put("id_user"		, 	user.getId()        	);
        valores.put("name"		    , 	user.getName()	        );
        valores.put("id_photo"		, 	user.getId_photo()	    );

        long id_user = sqlite.insert("User", null, valores);

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
    public Object select(int id) {
        return null;
    }

    @Override
    public ArrayList<Object> selectAll() {
        return null;
    }

    @Override
    public long save(Object obj) {

        User user = (User) obj;
        long id_user;

        if(select(user.getId()) != null) 	id_user = update(obj);
        else						            id_user = insert(obj);

        return id_user;
    }
}
