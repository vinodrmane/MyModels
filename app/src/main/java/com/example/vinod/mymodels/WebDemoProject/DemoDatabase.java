package com.example.vinod.mymodels.WebDemoProject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.vinod.mymodels.Adapter.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinod on 7/11/2018.
 */

public class DemoDatabase {
    private DataHelper mHelper;
    private SQLiteDatabase mDatabase;

    public DemoDatabase(Context context) {
        mHelper = new DataHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    public void insertProducts(List<DemoGitHubRepo> userList) {
        String sql = "INSERT INTO " + (DataHelper.TABLE_USERS) + " VALUES (null,?);";
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        for (int i = 0; i < userList.size(); i++) {
            DemoGitHubRepo demoGitHubRepo = userList.get(i);
            statement.clearBindings();
            //for a given column index, simply bind the data to be put inside that index
            statement.bindString(1, demoGitHubRepo.getName());
            Log.v("DemoDatabase",demoGitHubRepo.getName());
            statement.execute();
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }



    public ArrayList<DemoGitHubRepo> readProducts() {
        ArrayList<DemoGitHubRepo> listUsers = new ArrayList<>();


        String[] columns = {DataHelper.COLUMN_UID,DataHelper.COLUMN_NAME};
        Cursor cursor = mDatabase.query(DataHelper.TABLE_USERS, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                DemoGitHubRepo demoGitHubRepo = new DemoGitHubRepo();
                demoGitHubRepo.setName(cursor.getString(cursor.getColumnIndex(DataHelper.COLUMN_NAME)));
                listUsers.add(demoGitHubRepo);
                Log.v("DemoDatabase",demoGitHubRepo.getName());


            }
            while (cursor.moveToNext());
        }
        return listUsers;
    }


/*
    // Updating single student
    public int updateStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_NAME, student.getStudentName());
        values.put(KEY_STUDENT_EMAIL, student.getStudentEmail());

        // updating student row
        return db.update(TABLE_STUDENTS,
                values,
                KEY_STUDENT_ID + " = ?",
                new String[] { String.valueOf(student.getStudentID())});

    }
     // Add New Student
    public void addStudent(StudentModel student) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Content values use KEY-VALUE pair concept
        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, student.getStudentID());
        values.put(KEY_STUDENT_NAME, student.getStudentName());
        values.put(KEY_STUDENT_EMAIL, student.getStudentEmail());

        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }


  public class MyApplication extends Application {

 // how to use database in activity  example

 private static MyApplication sInstance;
 private static Database mDatabase;

public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    public synchronized static Database getWritableDatabase() {
        if (mDatabase == null) {
            mDatabase = new Database(getAppContext());
        }
        return mDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mDatabase = new Database(this);
    }
}

    mListMovies = MyApplication.getWritableDatabase().readMovies(Database.BOX_OFFICE);

*/


    private static class DataHelper extends SQLiteOpenHelper {
        public static final String TABLE_USERS = "users";

        public static final String COLUMN_UID = "_id";
        public static final String COLUMN_NAME = "name";

        private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +COLUMN_NAME + " TEXT" +");";

        private static final String DB_NAME = "User_db";
        private static final int DB_VERSION = 1;
        private Context mContext;

        public DataHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_USERS);
            } catch (SQLiteException exception) {
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
                onCreate(db);
            } catch (SQLiteException exception) {
            }
        }
    }
}
