package br.com.enjoeichallenge.repository.managers;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import br.com.enjoeichallenge.models.Product;
import br.com.enjoeichallenge.models.User;

public class SQLiteManager_Product extends SQLiteManager implements SQLiteManager_CRUD {

    public SQLiteManager_Product(Context ctx_) {
        super(ctx_);
    }


    @Override
    public long insert(Object obj) {

        Product product = (Product) obj;

        accessDB(OPEN_MODE);

        ContentValues valores = new ContentValues();

        valores.put("id_product"		        , 	product.getId()        	                );
        valores.put("discount_percentage"	    , 	product.getDiscount_percentage()	    );
        valores.put("title"		                , 	product.getTitle()	                    );
        valores.put("price"		                , 	product.getPrice()	                    );
        valores.put("original_price"		    , 	product.getOriginal_price()	            );
        valores.put("size"		                , 	product.getSize()	                    );
        valores.put("likes_count"		        , 	product.getLikes_count()	            );
        valores.put("maximum_installment"		, 	product.getMaximum_installment()	    );
        valores.put("published_comments_count"  , 	product.getPublished_comments_count()   );
        valores.put("content"		            , 	product.getContent()	                );
        valores.put("id_user"		            , 	product.getUser().getId()	            );

        long id_product = sqlite.insert("Product", null, valores);

        accessDB(CLOSE_MODE);

        return id_product;

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
