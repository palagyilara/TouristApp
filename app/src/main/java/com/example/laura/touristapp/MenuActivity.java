package com.example.laura.touristapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laura.touristapp.Helper.LocaleHelper;

import io.paperdb.Paper;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"hu"));
    }


    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;

    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle extra = getIntent().getExtras();
        //String text = extra.getString(MainActivity.TAG_TEXT);

        final String keyword = extra.getString("key");


		//final String lang = extra.getString("key1");

        btn1= (ImageButton) findViewById(R.id.cityinfo);
        btn2= (ImageButton) findViewById(R.id.places);
        btn3= (ImageButton) findViewById(R.id.map);
        btn4= (ImageButton) findViewById(R.id.quiz);
        btn5= (ImageButton) findViewById(R.id.dictionary);


        text1= (TextView) findViewById(R.id.textcity);
        text2= (TextView) findViewById(R.id.textplaces);
        text3= (TextView) findViewById(R.id.textmap);
        text4= (TextView) findViewById(R.id.textquiz);
        text5= (TextView) findViewById(R.id.textdictionary);


        Paper.init(this);
        //language[].setText(language);
        final String language= Paper.book().read("language");
        if(language==null){
            Paper.book().write("language","hu");}

        updateView((String)Paper.book().read("language"));



        //városról
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(language.equals("de")&&keyword.equals("Eger")){
                    Intent intent = new Intent(MenuActivity.this, ContentActivity.class);
                    String keyword = "Eger_(Ungarn)";
                    //Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    startActivity(intent);

                }
                else {
                    Intent intent = new Intent(MenuActivity.this, ContentActivity.class);
                    intent.putExtra("key", keyword);
                    startActivity(intent);
                }
            }
        });
        //látványosságok
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this, PlacesActivity.class);
                    intent.putExtra("key", keyword);
                    startActivity(intent);

            }
        });
        //térkép
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
                    intent.putExtra("key", keyword);
                    startActivity(intent);
            }
        });
        //quiz
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(MenuActivity.this, StartQuizActivity.class);
                    intent.putExtra("key", keyword);
                    startActivity(intent);

            }
        });

        //szótár
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DictionaryActivityFragment.class);
               // intent.putExtra("key", keyword);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.language_hu)
        {
            Paper.book().write("language","hu");
            updateView((String)Paper.book().read("language"));
        }
        else if(item.getItemId()== R.id.language_en)
        {
            Paper.book().write("language","en");
            updateView((String)Paper.book().read("language"));
        }
        else if(item.getItemId()== R.id.language_de)
        {
            Paper.book().write("language","de");
            updateView((String)Paper.book().read("language"));
        }

        return true;
    }
    private void updateView(String lang) {
        Context context= LocaleHelper.setLocale(this,lang);
        Resources resources =context.getResources();

        text1.setText(resources.getString(R.string.city));
        text2.setText(resources.getString(R.string.places));
        text3.setText(resources.getString(R.string.map));
        text4.setText(resources.getString(R.string.quiz));
        if(lang.equals("en") || lang.equals("de")){
        text5.setText(resources.getString(R.string.dictionary));}
        else if(lang.equals("hu"))
        {
            text5.setVisibility(View.INVISIBLE);
            btn5.setVisibility(View.INVISIBLE);
        }

    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
