package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="AndroidJSonDataBase";

    public static final String TABLE_NAME="AndroidJSonTable";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_Age="age";

    public static final String Table_Column_3_Description="description";


    public static final String TABLE_USERNAME="UserTable";

    public static final String Table_Column_User_ID="id";

    public static final String Table_Column_1_User_Name="name";

    public static final String Table_Column_2_User_Email="email";

    public static final String Table_Column_3_User_Password="password";


    public static  final String TABLE_ADMIN = "AdminTable";

    public static final String Table_Column_Admin_ID = "id";

    public static final String Table_Column_1_Admin_Name = "name";

    public static final String Table_Column_2_Admin_Description = "description";

    public static final String Table_Column_3_Admin_Treatment ="treatment";


    public static  final String TABLE_ = "AdminTable";




    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR,"+Table_Column_2_Age+" INTEGER , "+Table_Column_3_Description+" VARCHAR)";
        database.execSQL(CREATE_TABLE);


        String CREATE_USER_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_USERNAME+" ("+Table_Column_User_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_User_Name+" VARCHAR, "+Table_Column_2_User_Email+" VARCHAR, "+Table_Column_3_User_Password+" VARCHAR)";
        database.execSQL(CREATE_USER_TABLE);


        String CREATE_ADMIN_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_ADMIN+" ("+Table_Column_Admin_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Admin_Name+" VARCHAR, "+Table_Column_2_Admin_Description+" VARCHAR, "+Table_Column_3_Admin_Treatment+" VARCHAR)";
        database.execSQL(CREATE_ADMIN_TABLE);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERNAME);
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE AndroidJSonTable ADD COLUMN age INTEGER DEFAULT 0");
        }

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ADMIN);

        onCreate(db);

    }


}
