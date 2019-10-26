package com.example.user.javabucket;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Display extends LogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
    Bundle bundle=getIntent().getExtras();
       // String s1=bundle.getString("Name");

        //int s1=bundle.getInt("Score");

       int s1=getIntent().getIntExtra("Score",0);
        TextView tv= (TextView) findViewById(R.id.score);
        tv.setText(String.valueOf(s1));

        //int s=bundle.getInt("Score");
          //  String s=bundle.getInt("Score");
        //TextView tv1= (TextView) findViewById(R.id.score1);
        //tv1.setText(s);



//    String username=getIntent().getStringExtra("Score");
       //int s=getIntent().getIntExtra("Score",0);
        //EditText e= (EditText) findViewById(R.id.score);
       // e.setText(username);
        //String s=getIntent().getDataString("Score");
        //TextView tv=(TextView)findViewById(R.id.name);
        //tv.setText(username);

    }
}
