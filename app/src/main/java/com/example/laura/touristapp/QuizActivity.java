package com.example.laura.touristapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laura.touristapp.Helper.LocaleHelper;

import io.paperdb.Paper;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        String language = Paper.book().read("language");
        super.attachBaseContext(LocaleHelper.onAttach(newBase, language));
    }

    private final String STATE_PLAYER_NAME = "PLAYER_NAME";
    private final String STATE_SCORE = "SCORE";
    private final String STATE_Q1 = "STATE_Q1";
    private final String STATE_Q2 = "STATE_Q2";
    private final String STATE_Q3 = "STATE_Q3";
    private final String STATE_Q4 = "STATE_Q4";
    private final String STATE_Q5 = "STATE_Q5";
    private final String STATE_Q6 = "STATE_Q6";
    private final String STATE_Q7 = "STATE_Q7";
    private final String STATE_Q8 = "STATE_Q8";
    private final String STATE_Q9 = "STATE_Q9";
    private final String STATE_Q10 = "STATE_Q10";
    private RadioGroup q1, q2, q4, q3, q5, q6, q7, q8, q9, q10;
    public RadioButton q1_1,q1_2,q1_3,q2_1,q2_2,q2_3,q3_1, q3_2, q3_3,q4_1,q4_2,q4_3,
            q5_1,q5_2,q5_3,q6_1,q6_2,q6_3, q7_1, q7_2, q7_3,q8_1,q8_2,q8_3,q9_1,q9_2,q9_3,q10_1,q10_2,q10_3;
    private String playerName;
    private String[] correctAnswers;
    private int totalCorrect;
    private int questionNumber;
    public TextView question1,question2,question3,question4,question5,question6,question7,question8,question9,question10;

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //save checked radio buttons
        savedInstanceState.putString(STATE_PLAYER_NAME, playerName);
        savedInstanceState.putInt(STATE_Q1, q1.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q2, q2.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q3, q3.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q4, q4.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q5, q5.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q6, q6.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q7, q7.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q8, q8.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q9, q9.getCheckedRadioButtonId());
        savedInstanceState.putInt(STATE_Q10, q10.getCheckedRadioButtonId());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playerName = savedInstanceState.getString(STATE_PLAYER_NAME);
        //restore checked radio buttons
        try {
            q1.check(savedInstanceState.getInt(STATE_Q1));
            q2.check(savedInstanceState.getInt(STATE_Q2));
            q3.check(savedInstanceState.getInt(STATE_Q3));
            q4.check(savedInstanceState.getInt(STATE_Q4));
            q5.check(savedInstanceState.getInt(STATE_Q5));
            q6.check(savedInstanceState.getInt(STATE_Q6));
            q7.check(savedInstanceState.getInt(STATE_Q6));
            q8.check(savedInstanceState.getInt(STATE_Q8));
            q9.check(savedInstanceState.getInt(STATE_Q9));
            q10.check(savedInstanceState.getInt(STATE_Q10));
        } catch (Exception e) {
            //Log.v(getString(R.string.quiz_activity_name), getString(R.string.LOG_ONRESTORE_ERROR));
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = R.layout.activity_quiz;
        setContentView(layoutId);
        String language= Paper.book().read("language");

        initialStates(getIntent());
    }
    private void initialStates(Intent intent) {
        Bundle extra = getIntent().getExtras();
        final String keyword = extra.getString("key");

        playerName = intent.getStringExtra(STATE_PLAYER_NAME);
        totalCorrect = 0;
        questionNumber = 0;
        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4 = findViewById(R.id.question4);
        question5 = findViewById(R.id.question5);
        question6 = findViewById(R.id.question6);
        question7 = findViewById(R.id.question7);
        question8 = findViewById(R.id.question8);
        question9 = findViewById(R.id.question9);
        question10 = findViewById(R.id.question10);
        //RadioButton
        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        q4 = findViewById(R.id.q4);
        q5 = findViewById(R.id.q5);
        q6 = findViewById(R.id.q6);
        q7 = findViewById(R.id.q7);
        q8 = findViewById(R.id.q8);
        q9 = findViewById(R.id.q9);
        q10 = findViewById(R.id.q10);

        q1_1 = findViewById(R.id.q1_1);
        q1_2 = findViewById(R.id.q1_2);
        q1_3 = findViewById(R.id.q1_3);
        q2_1 = findViewById(R.id.q2_1);
        q2_2 = findViewById(R.id.q2_2);
        q2_3 = findViewById(R.id.q2_3);
        q3_1 = findViewById(R.id.q3_1);
        q3_2 = findViewById(R.id.q3_2);
        q3_3 = findViewById(R.id.q3_3);
        q4_1 = findViewById(R.id.q4_1);
        q4_2 = findViewById(R.id.q4_2);
        q4_3 = findViewById(R.id.q4_3);
        q5_1 = findViewById(R.id.q5_1);
        q5_2 = findViewById(R.id.q5_2);
        q5_3 = findViewById(R.id.q5_3);
        q6_1 = findViewById(R.id.q6_1);
        q6_2 = findViewById(R.id.q6_2);
        q6_3 = findViewById(R.id.q6_3);
        q7_1 = findViewById(R.id.q7_1);
        q7_2 = findViewById(R.id.q7_2);
        q7_3 = findViewById(R.id.q7_3);
        q8_1 = findViewById(R.id.q8_1);
        q8_2 = findViewById(R.id.q8_2);
        q8_3 = findViewById(R.id.q8_3);
        q9_1 = findViewById(R.id.q9_1);
        q9_2 = findViewById(R.id.q9_2);
        q9_3 = findViewById(R.id.q9_3);
        q10_1 = findViewById(R.id.q10_1);
        q10_2 = findViewById(R.id.q10_2);
        q10_3 = findViewById(R.id.q10_3);

        FillCorrectAnswersList();
        if(keyword.equals("Eger"))
        {
            question1.setText(getResources().getString(R.string.eger_q1));
            question2.setText(getResources().getString(R.string.eger_q2));
            question3.setText(getResources().getString(R.string.eger_q3));
            question4.setText(getResources().getString(R.string.eger_q4));
            question5.setText(getResources().getString(R.string.eger_q5));
            question6.setText(getResources().getString(R.string.eger_q6));
            question7.setText(getResources().getString(R.string.eger_q7));
            question8.setText(getResources().getString(R.string.eger_q8));
            question9.setText(getResources().getString(R.string.eger_q9));
            question10.setText(getResources().getString(R.string.eger_q10));

            q1_1.setText(getResources().getString(R.string.eger_answer_q1_1));
            q1_2.setText(getResources().getString(R.string.eger_answer_q1_2));
            q1_3.setText(getResources().getString(R.string.eger_answer_q1_3));

            q2_1.setText(getResources().getString(R.string.eger_answer_q2_1));
            q2_2.setText(getResources().getString(R.string.eger_answer_q2_2));
            q2_3.setText(getResources().getString(R.string.eger_answer_q2_3));

            q3_1.setText(getResources().getString(R.string.eger_answer_q3_1));
            q3_2.setText(getResources().getString(R.string.eger_answer_q3_2));
            q3_3.setText(getResources().getString(R.string.eger_answer_q3_3));

            q4_1.setText(getResources().getString(R.string.eger_answer_q4_1));
            q4_2.setText(getResources().getString(R.string.eger_answer_q4_2));
            q4_3.setText(getResources().getString(R.string.eger_answer_q4_3));

            q5_1.setText(getResources().getString(R.string.eger_answer_q5_1));
            q5_2.setText(getResources().getString(R.string.eger_answer_q5_2));
            q5_3.setText(getResources().getString(R.string.eger_answer_q5_3));

            q6_1.setText(getResources().getString(R.string.eger_answer_q6_1));
            q6_2.setText(getResources().getString(R.string.eger_answer_q6_2));
            q6_3.setText(getResources().getString(R.string.eger_answer_q6_3));

            q7_1.setText(getResources().getString(R.string.eger_answer_q7_1));
            q7_2.setText(getResources().getString(R.string.eger_answer_q7_2));
            q7_3.setText(getResources().getString(R.string.eger_answer_q7_3));

            q8_1.setText(getResources().getString(R.string.eger_answer_q8_1));
            q8_2.setText(getResources().getString(R.string.eger_answer_q8_2));
            q8_3.setText(getResources().getString(R.string.eger_answer_q8_3));

            q9_1.setText(getResources().getString(R.string.eger_answer_q9_1));
            q9_2.setText(getResources().getString(R.string.eger_answer_q9_2));
            q9_3.setText(getResources().getString(R.string.eger_answer_q9_3));

            q10_1.setText(getResources().getString(R.string.eger_answer_q10_1));
            q10_2.setText(getResources().getString(R.string.eger_answer_q10_2));
            q10_3.setText(getResources().getString(R.string.eger_answer_q10_3));

        }
        else if(keyword.equals("Keszthely"))
        {
            question1.setText(getResources().getString(R.string.keszthely_q1));
            question2.setText(getResources().getString(R.string.keszthely_q2));
            question3.setText(getResources().getString(R.string.keszthely_q3));
            question4.setText(getResources().getString(R.string.keszthely_q4));
            question5.setText(getResources().getString(R.string.keszthely_q5));
            question6.setText(getResources().getString(R.string.keszthely_q6));
            question7.setText(getResources().getString(R.string.keszthely_q7));
            question8.setText(getResources().getString(R.string.keszthely_q8));
            question9.setText(getResources().getString(R.string.keszthely_q9));
            question10.setText(getResources().getString(R.string.keszthely_q10));

            q1_1.setText(getResources().getString(R.string.keszthely_answer_q1_1));
            q1_2.setText(getResources().getString(R.string.keszthely_answer_q1_2));
            q1_3.setText(getResources().getString(R.string.keszthely_answer_q1_3));

            q2_1.setText(getResources().getString(R.string.keszthely_answer_q2_1));
            q2_2.setText(getResources().getString(R.string.keszthely_answer_q2_2));
            q2_3.setText(getResources().getString(R.string.keszthely_answer_q2_3));

            q3_1.setText(getResources().getString(R.string.keszthely_answer_q3_1));
            q3_2.setText(getResources().getString(R.string.keszthely_answer_q3_2));
            q3_3.setText(getResources().getString(R.string.keszthely_answer_q3_3));

            q4_1.setText(getResources().getString(R.string.keszthely_answer_q4_1));
            q4_2.setText(getResources().getString(R.string.keszthely_answer_q4_2));
            q4_3.setText(getResources().getString(R.string.keszthely_answer_q4_3));

            q5_1.setText(getResources().getString(R.string.keszthely_answer_q5_1));
            q5_2.setText(getResources().getString(R.string.keszthely_answer_q5_2));
            q5_3.setText(getResources().getString(R.string.keszthely_answer_q5_3));

            q6_1.setText(getResources().getString(R.string.keszthely_answer_q6_1));
            q6_2.setText(getResources().getString(R.string.keszthely_answer_q6_2));
            q6_3.setText(getResources().getString(R.string.keszthely_answer_q6_3));

            q7_1.setText(getResources().getString(R.string.keszthely_answer_q7_1));
            q7_2.setText(getResources().getString(R.string.keszthely_answer_q7_2));
            q7_3.setText(getResources().getString(R.string.keszthely_answer_q7_3));

            q8_1.setText(getResources().getString(R.string.keszthely_answer_q8_1));
            q8_2.setText(getResources().getString(R.string.keszthely_answer_q8_2));
            q8_3.setText(getResources().getString(R.string.keszthely_answer_q8_3));

            q9_1.setText(getResources().getString(R.string.keszthely_answer_q9_1));
            q9_2.setText(getResources().getString(R.string.keszthely_answer_q9_2));
            q9_3.setText(getResources().getString(R.string.keszthely_answer_q9_3));

            q10_1.setText(getResources().getString(R.string.keszthely_answer_q10_1));
            q10_2.setText(getResources().getString(R.string.keszthely_answer_q10_2));
            q10_3.setText(getResources().getString(R.string.keszthely_answer_q10_3));

        }
        else if(keyword.equals("Pécs"))
        {
            question1.setText(getResources().getString(R.string.pecs_q1));
            question2.setText(getResources().getString(R.string.pecs_q2));
            question3.setText(getResources().getString(R.string.pecs_q3));
            question4.setText(getResources().getString(R.string.pecs_q4));
            question5.setText(getResources().getString(R.string.pecs_q5));
            question6.setText(getResources().getString(R.string.pecs_q6));
            question7.setText(getResources().getString(R.string.pecs_q7));
            question8.setText(getResources().getString(R.string.pecs_q8));
            question9.setText(getResources().getString(R.string.pecs_q9));
            question10.setText(getResources().getString(R.string.pecs_q10));

            q1_1.setText(getResources().getString(R.string.pecs_answer_q1_1));
            q1_2.setText(getResources().getString(R.string.pecs_answer_q1_2));
            q1_3.setText(getResources().getString(R.string.pecs_answer_q1_3));

            q2_1.setText(getResources().getString(R.string.pecs_answer_q2_1));
            q2_2.setText(getResources().getString(R.string.pecs_answer_q2_2));
            q2_3.setText(getResources().getString(R.string.pecs_answer_q2_3));

            q3_1.setText(getResources().getString(R.string.pecs_answer_q3_1));
            q3_2.setText(getResources().getString(R.string.pecs_answer_q3_2));
            q3_3.setText(getResources().getString(R.string.pecs_answer_q3_3));

            q4_1.setText(getResources().getString(R.string.pecs_answer_q4_1));
            q4_2.setText(getResources().getString(R.string.pecs_answer_q4_2));
            q4_3.setText(getResources().getString(R.string.pecs_answer_q4_3));

            q5_1.setText(getResources().getString(R.string.pecs_answer_q5_1));
            q5_2.setText(getResources().getString(R.string.pecs_answer_q5_2));
            q5_3.setText(getResources().getString(R.string.pecs_answer_q5_3));

            q6_1.setText(getResources().getString(R.string.pecs_answer_q6_1));
            q6_2.setText(getResources().getString(R.string.pecs_answer_q6_2));
            q6_3.setText(getResources().getString(R.string.pecs_answer_q6_3));

            q7_1.setText(getResources().getString(R.string.pecs_answer_q7_1));
            q7_2.setText(getResources().getString(R.string.pecs_answer_q7_2));
            q7_3.setText(getResources().getString(R.string.pecs_answer_q7_3));

            q8_1.setText(getResources().getString(R.string.pecs_answer_q8_1));
            q8_2.setText(getResources().getString(R.string.pecs_answer_q8_2));
            q8_3.setText(getResources().getString(R.string.pecs_answer_q8_3));

            q9_1.setText(getResources().getString(R.string.pecs_answer_q9_1));
            q9_2.setText(getResources().getString(R.string.pecs_answer_q9_2));
            q9_3.setText(getResources().getString(R.string.pecs_answer_q9_3));

            q10_1.setText(getResources().getString(R.string.pecs_answer_q10_1));
            q10_2.setText(getResources().getString(R.string.pecs_answer_q10_2));
            q10_3.setText(getResources().getString(R.string.pecs_answer_q10_3));

        }
        else if(keyword.equals("Sopron"))
        {
            question1.setText(getResources().getString(R.string.sopron_q1));
            question2.setText(getResources().getString(R.string.sopron_q2));
            question3.setText(getResources().getString(R.string.sopron_q3));
            question4.setText(getResources().getString(R.string.sopron_q4));
            question5.setText(getResources().getString(R.string.sopron_q5));
            question6.setText(getResources().getString(R.string.sopron_q6));
            question7.setText(getResources().getString(R.string.sopron_q7));
            question8.setText(getResources().getString(R.string.sopron_q8));
            question9.setText(getResources().getString(R.string.sopron_q9));
            question10.setText(getResources().getString(R.string.sopron_q10));

            q1_1.setText(getResources().getString(R.string.sopron_answer_q1_1));
            q1_2.setText(getResources().getString(R.string.sopron_answer_q1_2));
            q1_3.setText(getResources().getString(R.string.sopron_answer_q1_3));

            q2_1.setText(getResources().getString(R.string.sopron_answer_q2_1));
            q2_2.setText(getResources().getString(R.string.sopron_answer_q2_2));
            q2_3.setText(getResources().getString(R.string.sopron_answer_q2_3));

            q3_1.setText(getResources().getString(R.string.sopron_answer_q3_1));
            q3_2.setText(getResources().getString(R.string.sopron_answer_q3_2));
            q3_3.setText(getResources().getString(R.string.sopron_answer_q3_3));

            q4_1.setText(getResources().getString(R.string.sopron_answer_q4_1));
            q4_2.setText(getResources().getString(R.string.sopron_answer_q4_2));
            q4_3.setText(getResources().getString(R.string.sopron_answer_q4_3));

            q5_1.setText(getResources().getString(R.string.sopron_answer_q5_1));
            q5_2.setText(getResources().getString(R.string.sopron_answer_q5_2));
            q5_3.setText(getResources().getString(R.string.sopron_answer_q5_3));

            q6_1.setText(getResources().getString(R.string.sopron_answer_q6_1));
            q6_2.setText(getResources().getString(R.string.sopron_answer_q6_2));
            q6_3.setText(getResources().getString(R.string.sopron_answer_q6_3));

            q7_1.setText(getResources().getString(R.string.sopron_answer_q7_1));
            q7_2.setText(getResources().getString(R.string.sopron_answer_q7_2));
            q7_3.setText(getResources().getString(R.string.sopron_answer_q7_3));

            q8_1.setText(getResources().getString(R.string.sopron_answer_q8_1));
            q8_2.setText(getResources().getString(R.string.sopron_answer_q8_2));
            q8_3.setText(getResources().getString(R.string.sopron_answer_q8_3));

            q9_1.setText(getResources().getString(R.string.sopron_answer_q9_1));
            q9_2.setText(getResources().getString(R.string.sopron_answer_q9_2));
            q9_3.setText(getResources().getString(R.string.sopron_answer_q9_3));

            q10_1.setText(getResources().getString(R.string.sopron_answer_q10_1));
            q10_2.setText(getResources().getString(R.string.sopron_answer_q10_2));
            q10_3.setText(getResources().getString(R.string.sopron_answer_q10_3));

        }
        else if(keyword.equals("Szeged"))
        {
            question1.setText(getResources().getString(R.string.szeged_q1));
            question2.setText(getResources().getString(R.string.szeged_q2));
            question3.setText(getResources().getString(R.string.szeged_q3));
            question4.setText(getResources().getString(R.string.szeged_q4));
            question5.setText(getResources().getString(R.string.szeged_q5));
            question6.setText(getResources().getString(R.string.szeged_q6));
            question7.setText(getResources().getString(R.string.szeged_q7));
            question8.setText(getResources().getString(R.string.szeged_q8));
            question9.setText(getResources().getString(R.string.szeged_q9));
            question10.setText(getResources().getString(R.string.szeged_q10));

            q1_1.setText(getResources().getString(R.string.szeged_answer_q1_1));
            q1_2.setText(getResources().getString(R.string.szeged_answer_q1_2));
            q1_3.setText(getResources().getString(R.string.szeged_answer_q1_3));

            q2_1.setText(getResources().getString(R.string.szeged_answer_q2_1));
            q2_2.setText(getResources().getString(R.string.szeged_answer_q2_2));
            q2_3.setText(getResources().getString(R.string.szeged_answer_q2_3));

            q3_1.setText(getResources().getString(R.string.szeged_answer_q3_1));
            q3_2.setText(getResources().getString(R.string.szeged_answer_q3_2));
            q3_3.setText(getResources().getString(R.string.szeged_answer_q3_3));

            q4_1.setText(getResources().getString(R.string.szeged_answer_q4_1));
            q4_2.setText(getResources().getString(R.string.szeged_answer_q4_2));
            q4_3.setText(getResources().getString(R.string.szeged_answer_q4_3));

            q5_1.setText(getResources().getString(R.string.szeged_answer_q5_1));
            q5_2.setText(getResources().getString(R.string.szeged_answer_q5_2));
            q5_3.setText(getResources().getString(R.string.szeged_answer_q5_3));

            q6_1.setText(getResources().getString(R.string.szeged_answer_q6_1));
            q6_2.setText(getResources().getString(R.string.szeged_answer_q6_2));
            q6_3.setText(getResources().getString(R.string.szeged_answer_q6_3));

            q7_1.setText(getResources().getString(R.string.szeged_answer_q7_1));
            q7_2.setText(getResources().getString(R.string.szeged_answer_q7_2));
            q7_3.setText(getResources().getString(R.string.szeged_answer_q7_3));

            q8_1.setText(getResources().getString(R.string.szeged_answer_q8_1));
            q8_2.setText(getResources().getString(R.string.szeged_answer_q8_2));
            q8_3.setText(getResources().getString(R.string.szeged_answer_q8_3));

            q9_1.setText(getResources().getString(R.string.szeged_answer_q9_1));
            q9_2.setText(getResources().getString(R.string.szeged_answer_q9_2));
            q9_3.setText(getResources().getString(R.string.szeged_answer_q9_3));

            q10_1.setText(getResources().getString(R.string.szeged_answer_q10_1));
            q10_2.setText(getResources().getString(R.string.szeged_answer_q10_2));
            q10_3.setText(getResources().getString(R.string.szeged_answer_q10_3));

        }
        else if(keyword.equals("Tihany"))
        {
            question1.setText(getResources().getString(R.string.tihany_q1));
            question2.setText(getResources().getString(R.string.tihany_q2));
            question3.setText(getResources().getString(R.string.tihany_q3));
            question4.setText(getResources().getString(R.string.tihany_q4));
            question5.setText(getResources().getString(R.string.tihany_q5));
            question6.setText(getResources().getString(R.string.tihany_q6));
            question7.setText(getResources().getString(R.string.tihany_q7));
            question8.setText(getResources().getString(R.string.tihany_q8));
            question9.setText(getResources().getString(R.string.tihany_q9));
            question10.setText(getResources().getString(R.string.tihany_q10));

            q1_1.setText(getResources().getString(R.string.tihany_answer_q1_1));
            q1_2.setText(getResources().getString(R.string.tihany_answer_q1_2));
            q1_3.setText(getResources().getString(R.string.tihany_answer_q1_3));

            q2_1.setText(getResources().getString(R.string.tihany_answer_q2_1));
            q2_2.setText(getResources().getString(R.string.tihany_answer_q2_2));
            q2_3.setText(getResources().getString(R.string.tihany_answer_q2_3));

            q3_1.setText(getResources().getString(R.string.tihany_answer_q3_1));
            q3_2.setText(getResources().getString(R.string.tihany_answer_q3_2));
            q3_3.setText(getResources().getString(R.string.tihany_answer_q3_3));

            q4_1.setText(getResources().getString(R.string.tihany_answer_q4_1));
            q4_2.setText(getResources().getString(R.string.tihany_answer_q4_2));
            q4_3.setText(getResources().getString(R.string.tihany_answer_q4_3));

            q5_1.setText(getResources().getString(R.string.tihany_answer_q5_1));
            q5_2.setText(getResources().getString(R.string.tihany_answer_q5_2));
            q5_3.setText(getResources().getString(R.string.tihany_answer_q5_3));

            q6_1.setText(getResources().getString(R.string.tihany_answer_q6_1));
            q6_2.setText(getResources().getString(R.string.tihany_answer_q6_2));
            q6_3.setText(getResources().getString(R.string.tihany_answer_q6_3));

            q7_1.setText(getResources().getString(R.string.tihany_answer_q7_1));
            q7_2.setText(getResources().getString(R.string.tihany_answer_q7_2));
            q7_3.setText(getResources().getString(R.string.tihany_answer_q7_3));

            q8_1.setText(getResources().getString(R.string.tihany_answer_q8_1));
            q8_2.setText(getResources().getString(R.string.tihany_answer_q8_2));
            q8_3.setText(getResources().getString(R.string.tihany_answer_q8_3));

            q9_1.setText(getResources().getString(R.string.tihany_answer_q9_1));
            q9_2.setText(getResources().getString(R.string.tihany_answer_q9_2));
            q9_3.setText(getResources().getString(R.string.tihany_answer_q9_3));

            q10_1.setText(getResources().getString(R.string.tihany_answer_q10_1));
            q10_2.setText(getResources().getString(R.string.tihany_answer_q10_2));
            q10_3.setText(getResources().getString(R.string.tihany_answer_q10_3));

        }
        else if(keyword.equals("Veszprém"))
        {
            question1.setText(getResources().getString(R.string.veszprem_q1));
            question2.setText(getResources().getString(R.string.veszprem_q2));
            question3.setText(getResources().getString(R.string.veszprem_q3));
            question4.setText(getResources().getString(R.string.veszprem_q4));
            question5.setText(getResources().getString(R.string.veszprem_q5));
            question6.setText(getResources().getString(R.string.veszprem_q6));
            question7.setText(getResources().getString(R.string.veszprem_q7));
            question8.setText(getResources().getString(R.string.veszprem_q8));
            question9.setText(getResources().getString(R.string.veszprem_q9));
            question10.setText(getResources().getString(R.string.veszprem_q10));

            q1_1.setText(getResources().getString(R.string.veszprem_answer_q1_1));
            q1_2.setText(getResources().getString(R.string.veszprem_answer_q1_2));
            q1_3.setText(getResources().getString(R.string.veszprem_answer_q1_3));

            q2_1.setText(getResources().getString(R.string.veszprem_answer_q2_1));
            q2_2.setText(getResources().getString(R.string.veszprem_answer_q2_2));
            q2_3.setText(getResources().getString(R.string.veszprem_answer_q2_3));

            q3_1.setText(getResources().getString(R.string.veszprem_answer_q3_1));
            q3_2.setText(getResources().getString(R.string.veszprem_answer_q3_2));
            q3_3.setText(getResources().getString(R.string.veszprem_answer_q3_3));

            q4_1.setText(getResources().getString(R.string.veszprem_answer_q4_1));
            q4_2.setText(getResources().getString(R.string.veszprem_answer_q4_2));
            q4_3.setText(getResources().getString(R.string.veszprem_answer_q4_3));

            q5_1.setText(getResources().getString(R.string.veszprem_answer_q5_1));
            q5_2.setText(getResources().getString(R.string.veszprem_answer_q5_2));
            q5_3.setText(getResources().getString(R.string.veszprem_answer_q5_3));

            q6_1.setText(getResources().getString(R.string.veszprem_answer_q6_1));
            q6_2.setText(getResources().getString(R.string.veszprem_answer_q6_2));
            q6_3.setText(getResources().getString(R.string.veszprem_answer_q6_3));

            q7_1.setText(getResources().getString(R.string.veszprem_answer_q7_1));
            q7_2.setText(getResources().getString(R.string.veszprem_answer_q7_2));
            q7_3.setText(getResources().getString(R.string.veszprem_answer_q7_3));

            q8_1.setText(getResources().getString(R.string.veszprem_answer_q8_1));
            q8_2.setText(getResources().getString(R.string.veszprem_answer_q8_2));
            q8_3.setText(getResources().getString(R.string.veszprem_answer_q8_3));

            q9_1.setText(getResources().getString(R.string.veszprem_answer_q9_1));
            q9_2.setText(getResources().getString(R.string.veszprem_answer_q9_2));
            q9_3.setText(getResources().getString(R.string.veszprem_answer_q9_3));

            q10_1.setText(getResources().getString(R.string.veszprem_answer_q10_1));
            q10_2.setText(getResources().getString(R.string.veszprem_answer_q10_2));
            q10_3.setText(getResources().getString(R.string.veszprem_answer_q10_3));

        }
    }

    private void FillCorrectAnswersList() {
        Bundle extra = getIntent().getExtras();
        String keyword = extra.getString("key");
        if(keyword.equals("Eger")){
        correctAnswers = getResources().getStringArray(R.array.eger_correctAnswersArray);}
        else  if(keyword.equals("Keszthely")){
            correctAnswers = getResources().getStringArray(R.array.keszthely_correctAnswersArray);}
            else  if(keyword.equals("Pécs")){
            correctAnswers = getResources().getStringArray(R.array.pecs_correctAnswersArray);}
            else  if(keyword.equals("Sopron")){
            correctAnswers = getResources().getStringArray(R.array.sopron_correctAnswersArray);}
            else  if(keyword.equals("Szeged")){
            correctAnswers = getResources().getStringArray(R.array.szeged_correctAnswersArray);}
            else  if(keyword.equals("Tihany")){
            correctAnswers = getResources().getStringArray(R.array.tihany_correctAnswersArray);}
            else  if(keyword.equals("Veszprém")){
            correctAnswers = getResources().getStringArray(R.array.veszprem_correctAnswersArray);}

    }


    public void submit(View view) {
        Bundle extra = getIntent().getExtras();
        String keyword = extra.getString("key");
        totalCorrect = getTotalCorrectAnswers();
        if (totalCorrect < 0)
            return;
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(STATE_PLAYER_NAME, playerName);
        intent.putExtra(STATE_SCORE, totalCorrect);
        intent.putExtra("key", keyword);
        startActivity(intent);

    }

    private int getTotalCorrectAnswers() {
        questionNumber = 0;
        try {
            checkRadioButtonAnswer(q1);
            checkRadioButtonAnswer(q2);
            checkRadioButtonAnswer(q3);
            checkRadioButtonAnswer(q4);
            checkRadioButtonAnswer(q5);
            checkRadioButtonAnswer(q6);
            checkRadioButtonAnswer(q7);
            checkRadioButtonAnswer(q8);
            checkRadioButtonAnswer(q9);
            checkRadioButtonAnswer(q10);

            return totalCorrect;

        } catch (Exception e) {
            Toast.makeText(this, R.string.validation_error, Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    private void checkRadioButtonAnswer(RadioGroup rg) {
        String answer = getCheckedRadioButtonId(rg).getText().toString();
        if (answer.equals(correctAnswers[questionNumber]))
            totalCorrect++;
        questionNumber++;
    }

    private RadioButton getCheckedRadioButtonId(RadioGroup rg) {
        return (RadioButton) findViewById(rg.getCheckedRadioButtonId());
    }
}
