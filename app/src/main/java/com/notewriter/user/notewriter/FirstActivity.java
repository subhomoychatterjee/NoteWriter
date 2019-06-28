package com.notewriter.user.notewriter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    ListView lst;
    ImageView add;
    ArrayList<String> arrayList=new ArrayList<>();
    CustomListview customListview;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        lst=findViewById(R.id.listview);
        add=findViewById(R.id.add);


        addToList();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstActivity.this,TakeNoteActivity.class);
                startActivity(intent);
            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title=arrayList.get(i);
                Intent intent=new Intent(FirstActivity.this,SavedNoteActivity.class);
                intent.putExtra("title",title);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        addToList();
    }

    public void addToList(){
        dataBaseHelper=new DataBaseHelper(this);

        arrayList=dataBaseHelper.getAllTitle();

        customListview = new CustomListview(this,arrayList);
        lst.setAdapter(customListview);
    }

    public void dataChanged(){
        arrayList.clear();
        arrayList=dataBaseHelper.getAllTitle();
        customListview.notifyDataSetChanged();
        addToList();
    }

}
