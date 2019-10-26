package com.example.user.javabucket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.version;

/**
 * Created by User on 04/09/2018.
 */

public class Inheritance_db extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inheritance.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public Inheritance_db(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db=db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract3.QuestionTable.TABLE_NAME + " ( " +
                QuizContract3.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract3.QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract3.QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract3.QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract3.QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract3.QuestionTable.COLUMN_ANSWER_NR + " INTEGER " +
                " ) ";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();

    }


    private void fillQuestionTable() {
        Question q1 = new Question("There are----- types of Inheritance ", "Runtime", "Compiletime", "All of the above", 1);
        addQuestion(q1);
        Question q2 = new Question("Type of Exception are?", "Checked", "Unchecked", "All of the Above", 3);
        addQuestion(q2);
        Question q3 = new Question("How Many keywords does Java Exception has", "five", "four", "three ", 1);
        addQuestion(q3);
        Question q4 = new Question("'finally' keyword is executed : ", "always in program", "not at all executed", "executed when no exception occurs", 1);
        addQuestion(q4);

    }
    private  void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract3.QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuizContract3.QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuizContract3.QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuizContract3.QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuizContract3.QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        db.insert(QuizContract3.QuestionTable.TABLE_NAME,null,cv);
    }

    public ArrayList<Question> getAllQuestions()
    {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract3.QuestionTable.TABLE_NAME, null);
        if(c.moveToFirst())
        {
            do {

                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract3.QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract3.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract3.QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract3.QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract3.QuestionTable.COLUMN_ANSWER_NR)));
                questionList.add(question);

            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS" + QuizContract3.QuestionTable.TABLE_NAME);
        onCreate(db);
    }
}


