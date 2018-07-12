package com.example.vinod.mymodels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


import com.example.vinod.mymodels.Adapter.Product;

import java.util.ArrayList;

/**
 * Created by Vinod on 7/11/2018.
 */

public class Database {
    public static final int BOX_OFFICE = 0;
    public static final int UPCOMING = 1;
    private DataHelper mHelper;
    private SQLiteDatabase mDatabase;

    public Database(Context context) {
        mHelper = new DataHelper(context);
        mDatabase = mHelper.getWritableDatabase();
    }

    public void insertProducts(int table, ArrayList<Product> listProducts, boolean clearPrevious) {
        if (clearPrevious) {
            deleteProducts(table);
        }
        //create a sql prepared statement
        String sql = "INSERT INTO " + (table == BOX_OFFICE ? DataHelper.TABLE_BOX_OFFICE : DataHelper.TABLE_UPCOMING) + " VALUES (?,?,?,?,?,?,?,?,?,?);";
        //compile the statement and start a transaction
        SQLiteStatement statement = mDatabase.compileStatement(sql);
        mDatabase.beginTransaction();
        for (int i = 0; i < listProducts.size(); i++) {
            Product currentProduct = listProducts.get(i);
            statement.clearBindings();
            //for a given column index, simply bind the data to be put inside that index
            statement.bindString(0, currentProduct.getP_name());
            statement.execute();
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    public ArrayList<Product> readProducts(int table) {

        /*  String selectQuery = "SELECT  * FROM " + TABLE_STUDENTS;
          SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);*/
        ArrayList<Product> listProducts = new ArrayList<>();

        //get a list of columns to be retrieved, we need all of them
        String[] columns = {DataHelper.COLUMN_UID,
                DataHelper.COLUMN_TITLE,
        };
        Cursor cursor = mDatabase.query((table == BOX_OFFICE ? DataHelper.TABLE_BOX_OFFICE : DataHelper.TABLE_UPCOMING), columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                Product Product = new Product();
                Product.setP_name(cursor.getString(cursor.getColumnIndex(DataHelper.COLUMN_TITLE)));
                listProducts.add(Product);
            }
            while (cursor.moveToNext());
        }
        return listProducts;
    }

    // Getting single product details through ID
    public Product getProduct(int productId) {

       // SQLiteDatabase db = this.getReadableDatabase();


        //You can browse to the query method to know more about the arguments.
        Cursor cursor = mDatabase.query(DataHelper.TABLE_BOX_OFFICE,
                new String[] { DataHelper.COLUMN_UID, DataHelper.COLUMN_TITLE },
                DataHelper.COLUMN_UID + "=?",
                new String[] { String.valueOf(productId) },
                null,
                null,
                null,
                null);
        if (cursor != null)
            cursor.moveToFirst();
        Product product = new Product(cursor.getString(1));
        return product;
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

    public void deleteProducts(int table) {
        mDatabase.delete((table == BOX_OFFICE ? DataHelper.TABLE_BOX_OFFICE : DataHelper.TABLE_UPCOMING), null, null);
    }

    private static class DataHelper extends SQLiteOpenHelper {
        public static final String TABLE_UPCOMING = " Products_upcoming";
        public static final String TABLE_BOX_OFFICE = "Products_box_office";
        public static final String COLUMN_UID = "_id";
        public static final String COLUMN_TITLE = "title";
     //   public static final String COLUMN_RELEASE_DATE = "release_date";

        private static final String CREATE_TABLE_BOX_OFFICE = "CREATE TABLE " + TABLE_BOX_OFFICE + " (" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT," +
         //       COLUMN_RELEASE_DATE + " INTEGER," +
                ");";
        private static final String CREATE_TABLE_UPCOMING = "CREATE TABLE " + TABLE_UPCOMING + " (" +
                COLUMN_UID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT," +
              //  COLUMN_RELEASE_DATE + " INTEGER," +
                ");";
        private static final String DB_NAME = "Products_db";
        private static final int DB_VERSION = 1;
        private Context mContext;

        public DataHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_BOX_OFFICE);
                db.execSQL(CREATE_TABLE_UPCOMING);
              //  L.m("create table box office executed");
            } catch (SQLiteException exception) {
              //  L.t(mContext, exception + "");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
              //  L.m("upgrade table box office executed");
                db.execSQL(" DROP TABLE IF EXISTS " + TABLE_BOX_OFFICE );
                db.execSQL(" DROP TABLE IF EXISTS "  + TABLE_UPCOMING );
                onCreate(db);
            } catch (SQLiteException exception) {
               // L.t(mContext, exception + "");
            }
        }
    }
}
