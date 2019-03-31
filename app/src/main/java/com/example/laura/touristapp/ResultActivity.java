package com.example.laura.touristapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.paperdb.Paper;

public class ResultActivity extends AppCompatActivity {
   private final String STATE_PLAYER_NAME = "PLAYER_NAME";
    private final String STATE_SCORE = "SCORE";
    private final int NUMBER_OF_QUESTIONS = 10;
    private int score;
    private String playerName;
    private int correctAnswer;
    private TextView nameTextView;
    private TextView scoreTextView;
    private TextView outOfTextView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_PLAYER_NAME, playerName);
        outState.putInt(STATE_SCORE, correctAnswer);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playerName = savedInstanceState.getString(STATE_PLAYER_NAME);
        correctAnswer = savedInstanceState.getInt(STATE_SCORE);
        FillTextPopUp();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = R.layout.activity_result;
        setContentView(layoutId);
        String language= Paper.book().read("language");

        Intent intent = getIntent();
        playerName = intent.getStringExtra(STATE_PLAYER_NAME);
        correctAnswer = intent.getIntExtra(STATE_SCORE, 0);
        nameTextView = findViewById(R.id.textCongrats);
        scoreTextView = findViewById(R.id.theScore);
        outOfTextView = findViewById(R.id.outOf);
        FillTextPopUp();
    }

    private void FillTextPopUp() {

        if(correctAnswer>=6){
        String congrats = getString(R.string.congrats);
        nameTextView.setText(String.format(congrats,playerName));
        //cimerek beállítása
        }
        else if(correctAnswer==5 || correctAnswer==4){
            String congrats = getString(R.string.congrats);
            nameTextView.setText(String.format(congrats,playerName));
            //:| fej
        }
        else {
            String congrats = getString(R.string.congrats);
            nameTextView.setText(String.format(congrats,playerName));
            //:( fej
        }

        score = 0;
        if (correctAnswer > 0)
            score = correctAnswer * NUMBER_OF_QUESTIONS;
        scoreTextView.setText(score + "%");

        outOfTextView.setText(String.format(getString(R.string.out_of), NUMBER_OF_QUESTIONS,correctAnswer));
    }

    public void startNewGame(View view) {
        Intent intent = new Intent(this, StartQuizActivity.class);
        startActivity(intent);
        finish();
    }

}
