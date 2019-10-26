package com.example.user.javabucket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignupActivity extends AppCompatActivity {
    EditText txtname,txtusername,txtpassword,txtmail;
    Button button_submit;
    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtname = (EditText) findViewById(R.id.name1);
        txtusername = (EditText) findViewById(R.id.us1);
        txtpassword = (EditText) findViewById(R.id.ps);
        txtmail = (EditText) findViewById(R.id.email1);
        mydb= new DatabaseHelper(this);
        addlog();
    }

    private void addlog()
    {
        button_submit= (Button) findViewById(R.id.btsignup);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean isInserted= mydb.insertlog(txtname.getText().toString(),txtusername.getText().toString(),txtpassword.getText().toString(),txtmail.getText().toString());
                if(isInserted)
                {
                    Toast.makeText(SignupActivity.this,"Data is inserted....",Toast.LENGTH_LONG).show();
                    startScreen();
                }

                else
                {
                    Toast.makeText(SignupActivity.this,"Data is not inserted",Toast.LENGTH_LONG).show();
                }

            }

            private void startScreen()
            {
                Intent intent = new Intent(SignupActivity.this,FirstScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
