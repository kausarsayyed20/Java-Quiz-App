package com.example.user.javabucket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends AppCompatActivity {
    Button inherit_bt,multithread_bt,server_bt,exception_bt,jdbc_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        addinherit();
        addmulti();
        addserver();
        addexcept();
        addjd();
    }

    private void addjd()
    {
       jdbc_bt = (Button) findViewById(R.id.bt_jdbc);
        jdbc_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }

            private void next() {
                Intent intent = new Intent(OptionActivity.this,JDBC1Activity.class);
                startActivity(intent);

            }
        });
    }

    private void addexcept()
    {
        exception_bt = (Button) findViewById(R.id.bt_exception);
        exception_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               next();
            }
            private void next() {
                Intent intent = new Intent(OptionActivity.this,ExceptionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addserver()
    {
        server_bt = (Button) findViewById(R.id.bt_serverlet);
        server_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }

            private void next() {
                Intent intent = new Intent(OptionActivity.this,ServletActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addmulti()

    {
         multithread_bt = (Button) findViewById(R.id.bt_multithread);
        multithread_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }

            private void next() {

                Intent intent = new Intent(OptionActivity.this,MultithreadingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addinherit()
    {
        inherit_bt = (Button) findViewById(R.id.bt_inherit);
        inherit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }

            private void next()
            {
                Intent intent = new Intent(OptionActivity.this,QuizActivity.class);
                startActivity(intent);
            }
        });

    }
}
