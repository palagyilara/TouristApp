package com.example.laura.touristapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    public ImageView cimerek;
    private ConstraintLayout layout;

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
        //Bundle extra = getIntent().getExtras();
        layout =(ConstraintLayout)findViewById(R.id.resultbg);

        Intent intent = getIntent();
        playerName = intent.getStringExtra(STATE_PLAYER_NAME);
        String keyword = intent.getStringExtra("key");
        correctAnswer = intent.getIntExtra(STATE_SCORE, 0);
        nameTextView = findViewById(R.id.textCongrats);
        scoreTextView = findViewById(R.id.theScore);
        outOfTextView = findViewById(R.id.outOf);
        cimerek=findViewById(R.id.image);

        if(keyword.equals("Eger")){
            layout.setBackgroundResource(R.mipmap.egerbg);}
        else if(keyword.equals("Keszthely")){
            layout.setBackgroundResource(R.mipmap.keszthelybg);}
        else if(keyword.equals("Pécs")){
            layout.setBackgroundResource(R.mipmap.pecsbg);}
        else if(keyword.equals("Sopron")){
            layout.setBackgroundResource(R.mipmap.sopronbg);}
        else if(keyword.equals("Szeged")){
            layout.setBackgroundResource(R.mipmap.szegedbg);}
        else if(keyword.equals("Tihany")){
            layout.setBackgroundResource(R.mipmap.tihanybg);}
        else if(keyword.equals("Veszprém")){
            layout.setBackgroundResource(R.mipmap.veszprembg);}

        FillTextPopUp();
    }

    private void FillTextPopUp() {
        Bundle extra = getIntent().getExtras();
        String keyword = extra.getString("key");
        if(correctAnswer>=6){
        String congrats = getString(R.string.congrats);
        nameTextView.setText(String.format(congrats,playerName));
        //cimerek beállítása
        if(keyword.equals("Eger"))
        {
            cimerek.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.egercimer, null));
        }
        else if(keyword.equals("Keszthely"))
        {
            cimerek.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.keszthelycimer, null));
        }
        else if(keyword.equals("Pécs"))
        {
            cimerek.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.pecscimer, null));
        }
        else if(keyword.equals("Tihany"))
        {
            cimerek.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.tihanycimer, null));
        }
        else if(keyword.equals("Sopron"))
        {
            cimerek.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.soproncimer, null));
        }
        else if(keyword.equals("Szeged"))
        {
            cimerek.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.szegedcimer, null));
        }
        else if(keyword.equals("Veszprém"))
        {
            cimerek.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.veszpremcimer, null));
        }
        }
        else if(correctAnswer==5 || correctAnswer==4){
            String congrats = getString(R.string.congrats1);
            nameTextView.setText(String.format(congrats,playerName));
            //:| fej
            cimerek.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.confused, null));
        }
        else {
            String congrats = getString(R.string.congrats2);
            nameTextView.setText(String.format(congrats,""));
            //:( fej
            cimerek.setBackground(ResourcesCompat.getDrawable(getResources(), R.mipmap.sad, null));
        }

        score = 0;
        if (correctAnswer > 0)
            score = correctAnswer * NUMBER_OF_QUESTIONS;
        scoreTextView.setText(score + "%");

        outOfTextView.setText(String.format(getString(R.string.out_of), NUMBER_OF_QUESTIONS,correctAnswer));
    }

    public void startNewGame(View view) {
        Bundle extra = getIntent().getExtras();
        String keyword = extra.getString("key");
        Intent intent = new Intent(this, StartQuizActivity.class);
        intent.putExtra("key", keyword);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        Bundle extra = getIntent().getExtras();
        String keyword = extra.getString("key");
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("key", keyword);
        startActivity(intent);
    }

}
