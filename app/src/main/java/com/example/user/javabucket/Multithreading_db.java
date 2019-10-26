package com.example.user.javabucket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.user.javabucket.QuizContract1.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.version;

/**
 * Created by User on 02/09/2018.
 */

public class Multithreading_db extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="multithreading.db";
    private  static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public Multithreading_db(Context context) {
        super(context,DATABASE_NAME ,null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        this.db=db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER " +
                " ) ";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();
    }

    private void fillQuestionTable() {
        Question q1 = new Question("what are two ways of multitasking", "process-based and thread-base", "progress-based and thread based", "program-based and thread-based", 1);
        addQuestion(q1);
        Question q2 = new Question("what is thread in java", "lightweight sub process", "smallest unit of processing.", "All of the Above", 3);
        addQuestion(q2);
        Question q3 = new Question("Thread priority in java is?", "Integer", "Float", "Long", 1);
        addQuestion(q3);
        Question q4 = new Question("In java multi-threading, a thread can be created by", "Extending Thread class", "Implementing Runnable Interface", "Using both", 3);
        addQuestion(q4);

    }
    private  void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        db.insert(QuestionTable.TABLE_NAME,null,cv);
    }

    public ArrayList<Question> getAllQuestions()
    {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);
        if(c.moveToFirst())
        {
            do {

                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questionList.add(question);

            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS" + QuestionTable.TABLE_NAME);
        onCreate(db);
    }
}
