package com.notewriter.user.notewriter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TakeNoteActivity extends AppCompatActivity {

    Button btn_save;
    EditText edt_note,edt_title;
    DataBaseHelper dataBaseHelper;
    boolean title_enable=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_note);

        btn_save=findViewById(R.id.btn_save);
        edt_note=findViewById(R.id.edt_note);
        edt_title=findViewById(R.id.edt_title);
        dataBaseHelper=new DataBaseHelper(this);

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            title_enable=bundle.getBoolean("set_edt");
            edt_title.setText(bundle.getString("title"));
            edt_note.setText(bundle.getString("note"));
            edt_title.setEnabled(title_enable);
        }

    }

//    public void read(View view){
//
//        try {
//            FileInputStream fileInputStream=openFileInput(edt_title.getText().toString().trim());
//            InputStreamReader inputStreamReader= new InputStreamReader(fileInputStream);
//            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
//            StringBuffer stringBuffer=new StringBuffer();
//            String note;
//            while((note=bufferedReader.readLine())!=null){
//                stringBuffer.append(note+"\n");
//            }
//            edt_note.setText(stringBuffer.toString());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    public  void write(View view){
        String title,note;
        ToolsObject toolsObject=new ToolsObject(this);
        title=edt_title.getText().toString().trim();
        note=edt_note.getText().toString().trim();
        ArrayList<String> arrayList=dataBaseHelper.getAllTitle();
        for(int i=0;i<arrayList.size();i++)
            if(title.equalsIgnoreCase(arrayList.get(i)) && title_enable){
            toolsObject.dialogShow("Alert","Title matched with another Note\nPlease give a Unique title");
            return;
            }
        if(title.isEmpty()){
            toolsObject.dialogShow("Error","Give a Title for this note");
            return;
        }
        if(note.isEmpty()){
            toolsObject.dialogShow("Error","Nothing is Writen in the note\nFill up the Note");
            return;
        }
        saveData(title,note);
//        Intent intent=new Intent(this,SavedNoteActivity.class);
//        intent.putExtra("title",title);
//        startActivity(intent);
        finish();

    }


    public void saveData(String title,String note){

        try {
            FileOutputStream fileOutputStream=openFileOutput(title,MODE_PRIVATE);
            fileOutputStream.write(note.getBytes());
            if(title_enable)
                dataBaseHelper.addUser(edt_title.getText().toString().trim());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back(View view){
        finish();
    }
}
