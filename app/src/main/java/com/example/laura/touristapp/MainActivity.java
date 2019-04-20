package com.example.laura.touristapp;

import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laura.touristapp.Helper.LocaleHelper;

import io.paperdb.Paper;



public class MainActivity extends AppCompatActivity {
   // public static final String TAG_KEYWORD = "com.example.laura.touristapp.TAG_KEYWORD";
 //public static final String TAG_TEXT = "com.example.laura.touristapp.TAG_TEXT";
   public TextView choose;
   public Button btnFetchData1;
  // public Button btnFetchData2;
   //public Button btnFetchData3;
   public Button btnFetchData4;
  // public Button btnFetchData5;
   public Button btnFetchData6;
   public Button btnFetchData7;
   public Button btnFetchData8;
   public Button btnFetchData9;
    public Button btnFetchData10;
   public ImageButton popupbtn;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"hu"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //etxSearch = (EditText) findViewById(R.id.etxSearch);
       // txtWikiData = (TextView) findViewById(R.id.txtWikiData);
        choose =(TextView) findViewById(R.id.choose);
        btnFetchData1 = (Button) findViewById(R.id.btnFetchData1);
       /* btnFetchData2 = (Button) findViewById(R.id.btnFetchData2);
        btnFetchData3 = (Button) findViewById(R.id.btnFetchData3);*/
        btnFetchData4 = (Button) findViewById(R.id.btnFetchData4);
        //btnFetchData5 = (Button) findViewById(R.id.btnFetchData5);
        btnFetchData6 = (Button) findViewById(R.id.btnFetchData6);
        btnFetchData7 = (Button) findViewById(R.id.btnFetchData7);
        btnFetchData8 = (Button) findViewById(R.id.btnFetchData8);
        btnFetchData9 = (Button) findViewById(R.id.btnFetchData9);
        btnFetchData10 = (Button) findViewById(R.id.btnFetchData10);
        popupbtn=(ImageButton) findViewById(R.id.popup);


        Paper.init(this);

        //default nyelv magyar
        final String language= Paper.book().read("language");
        if(language==null){
            Paper.book().write("language","hu");}

        updateView((String)Paper.book().read("language"));

        popupbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, popupbtn);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.main_menu, popup.getMenu());
                //popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.language_hu) {
                            Paper.book().write("language", "hu");
                            updateView((String) Paper.book().read("language"));
                        } else if (item.getItemId() == R.id.language_en) {
                            Paper.book().write("language", "en");
                            updateView((String) Paper.book().read("language"));
                        } else if (item.getItemId() == R.id.language_de) {
                            Paper.book().write("language", "de");
                            updateView((String) Paper.book().read("language"));
                        }
                        return true;
                    }

                });
                popup.show();
            }
        });


        btnFetchData1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //openContentActivity(null);
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                String keyword = btnFetchData1.getText().toString();
                //Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword);
                //intent.putExtra("key1", lang);
                startActivity(intent);
                }

        });

        btnFetchData4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //openContentActivity(null);
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                String keyword = btnFetchData4.getText().toString();
                //Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword);
                //intent.putExtra("key1", lang);
                startActivity(intent);
            }

        });

        btnFetchData6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //openContentActivity(null);
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                String keyword = btnFetchData6.getText().toString();
                //Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword);
                //intent.putExtra("key1", lang);
                startActivity(intent);
            }

        });
        btnFetchData7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    String keyword = btnFetchData7.getText().toString();
                    intent.putExtra("key", keyword);

                    startActivity(intent);

                //intent.putExtra("key1", lang);
            }

        });
        btnFetchData8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //openContentActivity(null);
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                String keyword = btnFetchData8.getText().toString();
                //Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword);
                //intent.putExtra("key1", lang);
                startActivity(intent);
            }

        });
        btnFetchData9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //openContentActivity(null);
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                String keyword = btnFetchData9.getText().toString();
                //Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword);
                //intent.putExtra("key1", lang);
                startActivity(intent);
            }

        });
        btnFetchData10.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //openContentActivity(null);
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                String keyword = btnFetchData10.getText().toString();
                //Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword);
                //intent.putExtra("key1", lang);
                startActivity(intent);
            }

        });


/*
        Button button =(Button)findViewById(R.id.dbbtn);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent dbmanager = new Intent(MainActivity.this,AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });*/
    }


    private void updateView(String lang) {
        Context context= LocaleHelper.setLocale(this,lang);
        Resources resources = context.getResources();

        choose.setText(resources.getString(R.string.choose));
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}