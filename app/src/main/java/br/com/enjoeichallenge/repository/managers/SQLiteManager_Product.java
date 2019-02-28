package br.com.enjoeichallenge.repository.managers;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import br.com.enjoeichallenge.objects.Product;
import br.com.enjoeichallenge.objects.contracts.ProductContract;

public class SQLiteManager_Product extends SQLiteManager implements SQLiteManager_CRUD {

    public SQLiteManager_Product(Context ctx_) {
        super(ctx_);
    }


    @Override
    public long insert(Object obj) {

        Product product = (Product) obj;

        accessDB(OPEN_MODE);

        ContentValues valores = new ContentValues();

        valores.put(ProductContract.ID_PRODUCT		            , 	product.getId()        	                );
        valores.put(ProductContract.DISCOUNT_PERCENTAGE	        , 	product.getDiscount_percentage()	    );
        valores.put(ProductContract.TITLE		                , 	product.getTitle()	                    );
        valores.put(ProductContract.PRICE	                    , 	product.getPrice()	                    );
        valores.put(ProductContract.ORIGINAL_PRICE		        , 	product.getOriginal_price()	            );
        valores.put(ProductContract.SIZE		                , 	product.getSize()	                    );
        valores.put(ProductContract.LIKES_COUNT		            , 	product.getLikes_count()	            );
        valores.put(ProductContract.MAXIMUM_INSTALLMENT		    , 	product.getMaximum_installment()	    );
        valores.put(ProductContract.PUBLISHED_COMMENTS_COUNT    , 	product.getPublished_comments_count()   );
        valores.put(ProductContract.CONTENT	    	            , 	product.getContent()	                );
        valores.put(ProductContract.ID_USER 		            , 	product.getUser().getId()	            );

        long id_product = sqlite.insert(ProductContract.TABLE_NAME, null, valores);

        accessDB(CLOSE_MODE);

        return id_product;

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

        Product product = (Product) obj;
        long id_product;

        if(select(product.getId()) != null){
            id_product = update(obj);
            Log.v("saving", "atualizando produto: " + product.getId());
        }
        else{
            id_product = insert(obj);
            Log.v("saving", "inserindo produto: " + product.getId());
        }

        return id_product;
    }
}
