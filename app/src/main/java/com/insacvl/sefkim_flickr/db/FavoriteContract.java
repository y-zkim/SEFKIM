package com.insacvl.sefkim_flickr.db;

import android.provider.BaseColumns;

public final class FavoriteContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FavoriteContract() {}

    public static class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_IMAGE_ID = "imageId";
        public static final String COLUMN_IMAGE_URL = "imageUrl";
        public static final String COLUMN_IMAGE_TITLE = "imageTitle";
    }
}
