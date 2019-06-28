package com.notewriter.user.notewriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.widget.Toast.*;

public class CustomListview extends ArrayAdapter<String> {
    private ArrayList arrayList;
    private Activity context;

    public CustomListview(Activity context, ArrayList arrayList) {
        super(context,R.layout.listview_layout,arrayList);
        this.arrayList=arrayList;
        this.context=context;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        View r=convertView;
        Viewholder viewholder=null;
        LayoutInflater layoutInflater = context.getLayoutInflater();
         if(r==null){
             r=layoutInflater.inflate(R.layout.listview_layout,null,true);
//             img_del.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View view) {
//                     DataBaseHelper dataBaseHelper=new DataBaseHelper(context);
//                     dataBaseHelper.dataDelete((String) arrayList.get(position));
//                     Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
////                     arrayList=dataBaseHelper.getAllTitle();
////                     notifyDataSetChanged();
//                 }
//             });
             viewholder=new Viewholder(r);
             r.setTag(viewholder);
         }
         else {
             viewholder= (Viewholder) r.getTag();
         }
         viewholder.tvw1.setText(arrayList.get(position).toString());
         viewholder.img_del.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 DataBaseHelper dataBaseHelper=new DataBaseHelper(context);
                     dataBaseHelper.dataDelete((String) arrayList.get(position));
                     Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show();
                     arrayList.remove(position);
                     notifyDataSetChanged();
             }
         });
        return r;
    }

    class Viewholder{

        TextView tvw1;
        ImageView img_del;
        Viewholder( View view){

            tvw1= view.findViewById(R.id.tv1);
            img_del=view.findViewById(R.id.img_del);

        }
    }

    public void change(ArrayList<String> arrayList){

        this.arrayList=arrayList;
        notifyDataSetChanged();

    }
}
