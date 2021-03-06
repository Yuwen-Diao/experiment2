package com.example.daily;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

public class ShowContent extends AppCompatActivity {
    private TextView mTextview;
    private TextView time;
    private TextView author;
    private TextView title;
    private NoteDb mDb;
    private SQLiteDatabase mSql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_content);
        mTextview = (TextView) this.findViewById(R.id.showtext);
        time = (TextView) this.findViewById(R.id.showtime);
        title = (TextView) this.findViewById(R.id.showtitle);
        author = (TextView) this.findViewById(R.id.showauthor);
        mDb = new NoteDb(this);
        mSql = mDb.getWritableDatabase();

        mTextview.setText(getIntent().getStringExtra(NoteDb.CONTENT));
        time.setText(getIntent().getStringExtra(NoteDb.TIME));
        author.setText(getIntent().getStringExtra(NoteDb.AUTHOR));
        title.setText(getIntent().getStringExtra(NoteDb.TITLE));
    }

    public void delete(View v) {
        int id = getIntent().getIntExtra(NoteDb.ID, 0);
        mSql.delete(NoteDb.TABLE_NAME, " _id = " + id, null);
        finish();

    }

    public void update(View v) {
        int id = getIntent().getIntExtra(NoteDb.ID, 0);
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(NoteDb.TITLE, title.getText().toString());
        updatedValues.put(NoteDb.CONTENT, mTextview.getText().toString());
        updatedValues.put(NoteDb.AUTHOR, author.getText().toString());
        updatedValues.put(NoteDb.TIME, AddContent.getTime());
        String where = " _id = " + id;
        mSql.update(NoteDb.TABLE_NAME, updatedValues, where, null);

        finish();
    }
}