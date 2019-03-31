package com.example.laura.touristapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.paperdb.Paper;

public class StartQuizActivity extends AppCompatActivity {

    private final String STATE_PLAYER_NAME = "PLAYER_NAME";
    private String playerName;
    private EditText nameEditText;

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
        String language= Paper.book().read("language");
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
}
