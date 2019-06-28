package com.notewriter.user.notewriter;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ToolsObject {

    public ToolsObject(Activity ab) {
        this.ab = ab;
    }

    Activity ab;




    public void dialogShow(String head, String msg){

        final Dialog dia=new Dialog(ab);
        dia.setContentView(R.layout.dialog_box);

        TextView header=dia.findViewById(R.id.header);
        TextView message=dia.findViewById(R.id.message);
        Button dia_btn=dia.findViewById(R.id.dia_btn);

        header.setText(head);
        message.setText(msg);

        dia_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dia.dismiss();

            }
        });

        dia.show();
        dia.setCancelable(false);

    }






}
