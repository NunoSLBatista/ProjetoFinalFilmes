package com.example.projetofinalfilmes.data;

import android.provider.BaseColumns;

public class GenreContract {

    private GenreContract(){}

    public static class GenreEntry implements BaseColumns {

        public static final String TABLE_NAME = "genres";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";

    }

}
