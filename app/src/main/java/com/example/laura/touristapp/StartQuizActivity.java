package com.example.laura.touristapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.laura.touristapp.Helper.LocaleHelper;

import io.paperdb.Paper;

public class StartQuizActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        //String language = Paper.book().read("language");
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "hu"));
    }

    private final String STATE_PLAYER_NAME = "PLAYER_NAME";
    private String playerName;
    private EditText nameEditText;
    private RelativeLayout layout;

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(STATE_PLAYER_NAME, nameEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playerName = savedInstanceState.getString(STATE_PLAYER_NAME);
        nameEditText.setText(playerName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = R.layout.activity_start_quiz;
        setContentView(layoutId);
        Bundle extra = getIntent().getExtras();
        final String keyword = extra.getString("key");
        String language= Paper.book().read("language");
        layout =(RelativeLayout)findViewById(R.id.startquizbg);

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

        initialStates();
    }

    private void initialStates() {
        nameEditText = findViewById(R.id.name);
    }

    public void start(View view) {
        Bundle extra = getIntent().getExtras();
        final String keyword = extra.getString("key");
        boolean valid = getPlayerName();
        if (valid) {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra(STATE_PLAYER_NAME, playerName);
            intent.putExtra("key", keyword);
            startActivity(intent);
        }
    }

    private boolean getPlayerName() {
        boolean valid = true;
        playerName = nameEditText.getText().toString();
        if (playerName.equals("")) {
            valid = false;
            Toast.makeText(this, R.string.validation_emptyName, Toast.LENGTH_SHORT).show();
        }
        return valid;
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
