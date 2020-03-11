package com.example.projetofinalfilmes.data;

import android.provider.BaseColumns;

public class UserContract {

    private UserContract(){}

    public static class UserEntry implements BaseColumns {

        public static final String TABLE_NAME = "users";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_PHOTO = "userPhoto";

    }

}
