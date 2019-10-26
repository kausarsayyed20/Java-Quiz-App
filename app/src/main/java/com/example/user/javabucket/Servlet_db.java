package com.example.user.javabucket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.user.javabucket.QuizContract2.*;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.version;

/**
 * Created by User on 02/09/2018.
 */

public class Servlet_db extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="servlet.db";
    private  static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public Servlet_db(Context context) {
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
        Question q1 = new Question("why servlet is used", "web application", "android app", "programs", 1);
        addQuestion(q1);
        Question q2 = new Question("servlet technology is", "robust", "scalable", "All of the Above", 3);
        addQuestion(q2);
        Question q3 = new Question("HttpServlet class extends", "Response", "Request", "GenericServlet class ", 3);
        addQuestion(q3);
        Question q4 = new Question("Which of the following code is used to get an attribute in a HTTP session object in servlet?", "session.alterAttribute(String name)", "session.getAttribute(String name)", "session.setAttribute(String name)", 2);
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