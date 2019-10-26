package com.example.user.javabucket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingScreenActivity extends AppCompatActivity {
        private static final int REQUEST_CODE_QUIZ=1;
        public static final String SHARED_PREFS="shredPrefs";
    public static final String KEY_HIGHSCORE="keyHighScore";
    private TextView textViewHighScore;
    private int highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

      //  textViewHighScore= (TextView) findViewById(R.id.textView_highscore);
        Button btStartQuiz = (Button) findViewById(R.id.button_start);
        btStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();

            }


        });
    }
        private void startQuiz()
        {
            Intent intent = new Intent(StartingScreenActivity.this,OptionActivity.class);
            startActivityForResult(intent,REQUEST_CODE_QUIZ);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==REQUEST_CODE_QUIZ){
            if(resultCode ==RESULT_OK){
                int score=data.getIntExtra(ServletActivity.EXTRA_SCORE, 0);
                if(score>highscore)
                {
                    updateHighScore(score);
                }

            }
        }
    }
    private void loadHighScore()
    {
        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore=prefs.getInt(KEY_HIGHSCORE,0);


    }

    private void updateHighScore(int highscorenew){
        highscore=highscorenew;
        textViewHighScore.setText("Highscore"+highscore);
        SharedPreferences prefs=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();

    }
}
