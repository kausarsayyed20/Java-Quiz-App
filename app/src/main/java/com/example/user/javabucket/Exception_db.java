package com.example.user.javabucket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.user.javabucket.QuizContract3.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.version;

/**
 * Created by User on 02/09/2018.
 */

public class Exception_db extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="exception.db";
    private  static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public Exception_db(Context context) {
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
        Question q1 = new Question("Exception in java is handle at which time", "Runtime", "Compiletime", "All of the above", 1);
        addQuestion(q1);
        Question q2 = new Question("Type of Exception are?", "Checked", "Unchecked", "All of the Above", 3);
        addQuestion(q2);
        Question q3 = new Question("How Many keywords does Java Exception has", "five", "four", "three ", 1);
        addQuestion(q3);
        Question q4 = new Question("'finally' keyword is executed : ", "always in program", "not at all executed", "executed when no exception occurs", 1);
        addQuestion(q4);
        Question q5 = new Question("Which of these is a super class of all errors and exception in the java language? ", "RuntimeException", "Throwable", "Catchable", 2);
        addQuestion(q5);
        Question q6 = new Question("The built-in class in java, which is used to handle all exceptions is..", "Error", "Exception", "Throwable", 3);
        addQuestion(q6);
        Question q7 = new Question("Exception classes are available in the ------ package ", "java.lang", "java.awt", "java.io", 3);
        addQuestion(q7);
        Question q8 = new Question(" The ---------- statement is passed a single parameter, which is refernce to the exception object thrown", "throw", "catch", "try", 2);
        addQuestion(q8);
        Question q9 = new Question("When a ------ blcok is defined, this is guqranted to execute, regardless os whether or not in exception is thrown ", "finally", "throw", "try", 1);
        addQuestion(q9);
        Question q10 = new Question("-----------exception is thrown when an attempt is made to acess an array element beyound the index of the array ", "Throwable", "Restricted", "ArrayIndexOutOfBounds", 1);
        addQuestion(q10);

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
