package com.report1.kdh.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 동현 on 2017-10-09.
 */
public class SaveRecord extends AppCompatActivity {

    Button button;
    LinearLayout linearLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_layout);

        linearLayout=(LinearLayout)findViewById(R.id.linear);
        Intent intent=getIntent();
        String[] str=intent.getStringArrayExtra("record");
        for(int i=0; i<str.length; i++)
        {
            TextView textView;
            textView=new TextView(this);
            textView.setText(str[i]);
            linearLayout.addView(textView);
        }

        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
