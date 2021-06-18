package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");



//            sqLiteDatabase.execSQL("INSERT INTO users(name, age) VALUES ('Sandeep', 21)");
//            sqLiteDatabase.execSQL("INSERT INTO users(name, age) VALUES ('Unni', 14)");
//            sqLiteDatabase.execSQL("INSERT INTO users(name, age) VALUES ('Mummi', 55)");
//            sqLiteDatabase.execSQL("INSERT INTO users(name, age) VALUES ('Papa', 60)");

            //sqLiteDatabase.execSQL("DELETE FROM users WHERE rowid IN (SELECT rowid FROM users WHERE name = 'Sandeep' LIMIT 1)");

            //sqLiteDatabase.execSQL("UPDATE users SET age = 13 WHERE name = 'Sandeep'");

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);

            //Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE age < 25", null);

           // Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name ='Sandeep'", null);

           // Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%i%' LIMIT 1", null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");

            cursor.moveToFirst();
            while(cursor != null){
                Log.i("name", cursor.getString(nameIndex));
                Log.i("age", String.valueOf(cursor.getInt(ageIndex)));
                cursor.moveToNext();
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}