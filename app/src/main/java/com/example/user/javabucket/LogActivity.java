

package com.example.user.javabucket;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogActivity extends AppCompatActivity {

DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Button b1= (Button) findViewById(R.id.login_b);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick(view);
            }
        });

    }

    public void onButtonClick(View view)
    {
        if(view.getId()== R.id.login_b)
        {
            EditText a= (EditText) findViewById(R.id.username_b);
            String str=a.getText().toString();
            EditText p= (EditText) findViewById(R.id.password_b);
            String pass=p.getText().toString();
            db= new DatabaseHelper(this);
            String password=db.searchPass(str);
            if(pass.equals(password))
            {
                Intent i=new Intent(LogActivity.this,StartingScreenActivity.class);
                i.putExtra("Username",str);
                startActivity(i);
            }
            else
            {
                Toast temp=Toast.makeText(LogActivity.this,"Password doesn't match",Toast.LENGTH_LONG);
                temp.show();
            }


        }
    }

}

