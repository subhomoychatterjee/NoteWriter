package com.notewriter.user.notewriter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SavedNoteActivity extends AppCompatActivity {

    TextView txt_title,txt_note;
    Button btn_edt;
    String title="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_note);

        txt_note=findViewById(R.id.txt_note);
        txt_title=findViewById(R.id.txt_title);
        btn_edt=findViewById(R.id.btn_edt);

        Bundle bundle=getIntent().getExtras();
        title=bundle.getString("title");
        txt_title.setText(title);
        read();

    }

    public void read(){

        try {
            FileInputStream fileInputStream=openFileInput(title);
            InputStreamReader inputStreamReader= new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();
            String note;
            while((note=bufferedReader.readLine())!=null){
                stringBuffer.append(note+"\n");
            }
            txt_note.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void edit(View view){

        Intent intent=new Intent(this,TakeNoteActivity.class);
        intent.putExtra("title",txt_title.getText().toString().trim());
        intent.putExtra("note",txt_note.getText().toString().trim());
        intent.putExtra("set_edit",false);
        startActivity(intent);
        finish();

    }

    public void deleteNote(View view){

        DataBaseHelper dataBaseHelper=new DataBaseHelper(this);
        dataBaseHelper.dataDelete(txt_title.getText().toString().trim());
        finish();

    }

    public void back(View view){
        finish();
    }

}
