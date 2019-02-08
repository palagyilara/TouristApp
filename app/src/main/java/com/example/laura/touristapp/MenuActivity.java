package com.example.laura.touristapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    ImageButton btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle extra = getIntent().getExtras();
        //String text = extra.getString(MainActivity.TAG_TEXT);

        final String keyword = extra.getString("key");
		final String lang = extra.getString("key1");
       
        //language[].setText(language);

        btn1= (ImageButton) findViewById(R.id.cityinfo);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ContentActivity.class);
                if(lang.equals("Magyar")) {

                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", lang);
                    startActivity(intent);
                   
                }
                else if(lang.equals("English"))
                {
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", lang);
                    startActivity(intent);
                }
                else if(lang.equals("Deutsch"))
                {
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", lang);
                    startActivity(intent);
                }
            }
        });

    }
}
