package com.notewriter.user.notewriter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.security.PrivilegedAction;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;

    private static final String DATABASE_NAME="note.db";

    private static final String TABLE_NOTE="NOTE";

    private static final String COLOUMN_TITLE="title";

    Context context;

    private String CREATE_TABLE="CREATE TABLE "+TABLE_NOTE+"("+COLOUMN_TITLE+" TEXT)";

    private String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NOTE;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(DROP_TABLE);

    }

    public void addUser(String title){
        SQLiteDatabase db=getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put(COLOUMN_TITLE, title);

        long res=db.insert(TABLE_NOTE,null,values);
        db.close();
    }

    public ArrayList<String> getAllTitle(){

        ArrayList<String > list=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();

        String quary="SELECT * FROM "+TABLE_NOTE;

        Cursor cursor=db.rawQuery(quary,null);

        if(cursor!=null){
            while (cursor.moveToNext()){
                list.add(cursor.getString(0));
            }
        }
        cursor.close();
        db.close();

        return list;
    }

    public void dataDelete(String title){

        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NOTE,"title = ?",new String[] {title});
        db.close();

    }

}
