package com.insacvl.sefkim_flickr.db;
/**
* @Author : ZKIM Youssef
*/
import android.provider.BaseColumns;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                        FavouriteContract                                        *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * To prevent someone from accidentally instantiating the contract class, make the constructor     *
 * private.                                                                                        *
 *                                                                                                 *
 *=================================================================================================*

 */

public final class FavouriteContract {
    private FavouriteContract() {}

    public static class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_IMAGE_ID = "imageId";
        public static final String COLUMN_IMAGE_URL = "imageUrl";
        public static final String COLUMN_IMAGE_TITLE = "imageTitle";
    }
}
