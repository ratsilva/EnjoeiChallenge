package br.com.enjoeichallenge.repository.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import br.com.enjoeichallenge.objects.Photo;
import br.com.enjoeichallenge.objects.Product;
import br.com.enjoeichallenge.objects.ProductPhoto;
import br.com.enjoeichallenge.objects.User;
import br.com.enjoeichallenge.objects.contracts.ProductContract;
import br.com.enjoeichallenge.objects.contracts.ProductPhotoContract;

public class SQLiteManager_Product extends SQLiteManager implements SQLiteManager_CRUD {

    private SQLiteManager_Photo sqlPhoto;
    private SQLiteManager_ProductPhoto sqlProdPhoto;
    private SQLiteManager_User sqlUser;

    public SQLiteManager_Product(Context ctx_) {
        super(ctx_);
        sqlPhoto = new SQLiteManager_Photo(ctx_);
        sqlProdPhoto = new SQLiteManager_ProductPhoto(ctx_);
        sqlUser = new SQLiteManager_User(ctx_);
    }


    @Override
    public long insert(Object obj) {

        Product product = (Product) obj;

        for(Photo photo : product.getPhotos()){

            long id_photo = sqlPhoto.save(photo);
            ProductPhoto pp = new ProductPhoto();
            pp.setIdphoto(id_photo);
            pp.setIdproduct(product.getId());
            pp.setIduser(product.getId_user());
            sqlProdPhoto.save(pp);

        }

        accessDB(OPEN_MODE);

        ContentValues values = new ContentValues();

        values.put(ProductContract.ID_PRODUCT		            , 	product.getId()        	                );
        values.put(ProductContract.DISCOUNT_PERCENTAGE	        , 	product.getDiscount_percentage()	    );
        values.put(ProductContract.TITLE		                , 	product.getTitle()	                    );
        values.put(ProductContract.PRICE	                    , 	product.getPrice()	                    );
        values.put(ProductContract.ORIGINAL_PRICE		        , 	product.getOriginal_price()	            );
        values.put(ProductContract.SIZE		                    , 	product.getSize()	                    );
        values.put(ProductContract.LIKES_COUNT		            , 	product.getLikes_count()	            );
        values.put(ProductContract.MAXIMUM_INSTALLMENT		    , 	product.getMaximum_installment()	    );
        values.put(ProductContract.PUBLISHED_COMMENTS_COUNT     , 	product.getPublished_comments_count()   );
        values.put(ProductContract.CONTENT	    	            , 	product.getContent()	                );
        values.put(ProductContract.ID_USER 		                , 	product.getUser().getId()	            );

        long id_product = sqlite.insert(ProductContract.TABLE_NAME, null, values);

        accessDB(CLOSE_MODE);

        return id_product;

    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }

    @Override
    public long update(Object obj) {

        Product product = (Product) obj;

        return product.getId();
    }

    @Override
    public Object select(Object obj) {

        Product product = (Product) obj;

        accessDB(OPEN_MODE);

        Cursor c = sqlite.rawQuery(

                "SELECT "
                        + "		  " + ProductContract.ID_PRODUCT                + "					,"
                        + "		  " + ProductContract.DISCOUNT_PERCENTAGE       + "					,"
                        + "		  " + ProductContract.TITLE                     + "					,"
                        + "		  " + ProductContract.PRICE                     + "					,"
                        + "		  " + ProductContract.ORIGINAL_PRICE            + "					,"
                        + "		  " + ProductContract.SIZE                      + "					,"
                        + "		  " + ProductContract.LIKES_COUNT               + "					,"
                        + "		  " + ProductContract.MAXIMUM_INSTALLMENT       + "					,"
                        + "		  " + ProductContract.PUBLISHED_COMMENTS_COUNT  + "					,"
                        + "		  " + ProductContract.CONTENT                   + "					,"
                        + "		  " + ProductContract.ID_USER                   + "					"
                        + " FROM " + ProductContract.TABLE_NAME
                        + " WHERE " + ProductContract.ID_PRODUCT + " = " + product.getId()
                        + " AND " + ProductContract.ID_USER + " = " + product.getId_user(), null);

        product = null;

        if(c.moveToNext()) {

            product = new Product();

            product.setId(                          c.getInt(0));
            product.setDiscount_percentage(         c.getDouble(1));
            product.setTitle(                       c.getString(2));
            product.setPrice(                       c.getDouble(3));
            product.setOriginal_price(              c.getDouble(4));
            product.setSize(                        c.getString(5));
            product.setLikes_count(                 c.getInt(6));
            product.setMaximum_installment(         c.getInt(7));
            product.setPublished_comments_count(    c.getInt(8));
            product.setContent(                     c.getString(9));
            product.setId_user(                     c.getInt(10));

        }

        c.close();

        accessDB(CLOSE_MODE);

        if(product != null){
            // Pegar User
            User u = new User();
            u.setId(product.getId_user());
            u = (User) sqlUser.select(u);
            product.setUser(u);

            // Pegar Photos
            ArrayList<Photo> photos = new ArrayList<>();

            String where =  "WHERE " + ProductPhotoContract.IDPRODUCT + " = " + product.getId() +
                            " AND " + ProductPhotoContract.IDUSER + " = " + product.getId_user();
            ArrayList<Object> ppList = sqlProdPhoto.selectAll(where);

            for(Object pp : ppList){

                ProductPhoto productPhoto = (ProductPhoto) pp;
                Photo p = new Photo();

                p.setId(productPhoto.getIdproduct());
                p = (Photo) sqlPhoto.select(p);
                photos.add(p);
            }

            product.setPhotos(photos);
        }

        return product;

    }

    @Override
    public ArrayList<Object> selectAll(String where) {

        accessDB(OPEN_MODE);

        Cursor c = sqlite.rawQuery(

                "SELECT "
                        + "		  " + ProductContract.ID_PRODUCT                + "					,"
                        + "		  " + ProductContract.DISCOUNT_PERCENTAGE       + "					,"
                        + "		  " + ProductContract.TITLE                     + "					,"
                        + "		  " + ProductContract.PRICE                     + "					,"
                        + "		  " + ProductContract.ORIGINAL_PRICE            + "					,"
                        + "		  " + ProductContract.SIZE                      + "					,"
                        + "		  " + ProductContract.LIKES_COUNT               + "					,"
                        + "		  " + ProductContract.MAXIMUM_INSTALLMENT       + "					,"
                        + "		  " + ProductContract.PUBLISHED_COMMENTS_COUNT  + "					,"
                        + "		  " + ProductContract.CONTENT                   + "					,"
                        + "		  " + ProductContract.ID_USER                   + "					"
                        + " FROM " + ProductContract.TABLE_NAME
                        + " " + where, null);

        Product product;

        ArrayList<Object> listProducts = new ArrayList<>();
        while(c.moveToNext()) {

            product = new Product();

            product.setId(                          c.getInt(0));
            product.setDiscount_percentage(         c.getDouble(1));
            product.setTitle(                       c.getString(2));
            product.setPrice(                       c.getDouble(3));
            product.setOriginal_price(              c.getDouble(4));
            product.setSize(                        c.getString(5));
            product.setLikes_count(                 c.getInt(6));
            product.setMaximum_installment(         c.getInt(7));
            product.setPublished_comments_count(    c.getInt(8));
            product.setContent(                     c.getString(9));
            product.setId_user(                     c.getInt(10));

            listProducts.add(product);

        }

        c.close();

        accessDB(CLOSE_MODE);

        if(!listProducts.isEmpty()){

            for(int i = 0; i < listProducts.size(); i++){

                Product p = (Product) listProducts.get(i);

                // Pegar User
                User u = new User();
                u.setId(p.getId_user());
                u = (User) sqlUser.select(u);
                p.setUser(u);

                // Pegar Photos
                ArrayList<Photo> photos = new ArrayList<>();

                String where2 =  "WHERE " + ProductPhotoContract.IDPRODUCT + " = " + p.getId() +
                        " AND " + ProductPhotoContract.IDUSER + " = " + p.getId_user();
                ArrayList<Object> ppList = sqlProdPhoto.selectAll(where2);

                for(Object pp : ppList){

                    ProductPhoto productPhoto = (ProductPhoto) pp;
                    Photo p2 = new Photo();

                    p2.setId(productPhoto.getIdproduct());
                    p2 = (Photo) sqlPhoto.select(p2);
                    photos.add(p2);
                }

                p.setPhotos(photos);

                listProducts.set(i, p);

            }

            return listProducts;
        }

        return null;

    }

    @Override
    public long save(Object obj) {

        long id_product;

        if(select(obj) != null){
            id_product = update(obj);
        }
        else{
            id_product = insert(obj);
        }

        return id_product;
    }
}
