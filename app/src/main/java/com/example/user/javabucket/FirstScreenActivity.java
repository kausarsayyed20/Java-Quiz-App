package com.example.user.javabucket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstScreenActivity extends AppCompatActivity {
    Button but_sign;
    Button but_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        addlog();
        addsign();
    }

    private void addsign()
    {
        but_sign= (Button) findViewById(R.id.button_sign);
        but_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                signup();

            }

            private void signup() {
                Intent intent = new Intent(FirstScreenActivity.this,SignupActivity.class);
                startActivity(intent);

            }
        });
    }

    private void addlog()
    {
        but_login= (Button) findViewById(R.id.button_log);
        but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }

            private void login()
            {
                Intent i = new Intent(FirstScreenActivity.this,LogActivity.class);
                startActivity(i);
            }
        });
    }
}
