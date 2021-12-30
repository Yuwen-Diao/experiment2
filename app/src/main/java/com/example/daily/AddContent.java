package com.example.daily;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddContent extends AppCompatActivity {
    private EditText mEt;
    private EditText mTitle;
    private NoteDb mDb;
    private SQLiteDatabase mSqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        mEt = (EditText) this.findViewById(R.id.text);
        mTitle = (EditText) this.findViewById(R.id.title);
        mDb = new NoteDb(this);
        mSqldb = mDb.getWritableDatabase();
    }//添加输入框

    public void save(View v) {
        ContentValues cv = new ContentValues();
        cv.put(NoteDb.CONTENT, mEt.getText().toString());
        cv.put(NoteDb.TIME, getTime());
        cv.put(NoteDb.TITLE, mTitle.getText().toString());
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        String names = prefs.getString("name","无名");
        cv.put(NoteDb.AUTHOR, names);
        mSqldb.insert(NoteDb.TABLE_NAME, null, cv);
        finish();
    }//把标题的输入框和内容的输入框内的内容提取出来保存到数据库里

    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }//返回当前时间

}