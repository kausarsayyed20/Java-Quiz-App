package com.example.user.javabucket;

import android.provider.BaseColumns;

/**
 * Created by User on 07/09/2018.
 */

public class QuizContract2
{
    private  QuizContract2() {}

    public static class QuestionTable implements BaseColumns
    {
        public static final String TABLE_NAME="servlet_questions";
        public static final String COLUMN_QUESTION="question";
        public static final String COLUMN_OPTION1="option1";
        public static final String COLUMN_OPTION2="option2";
        public static final String COLUMN_OPTION3="option3";
        public static final String COLUMN_ANSWER_NR="answer_nr";
    }
}
