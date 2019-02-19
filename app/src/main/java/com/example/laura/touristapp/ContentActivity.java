package com.example.laura.touristapp;

//import android.content.Intent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
//import android.provider.BaseColumns;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
//import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laura.touristapp.Helper.LocaleHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_DE;
import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_EN;
import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_HU;
import static com.example.laura.touristapp.DatabaseHandler.CITYNAME;
import static com.example.laura.touristapp.DatabaseHandler.ID;
import static com.example.laura.touristapp.DatabaseHandler.TABLE_NAME;

public class ContentActivity extends AppCompatActivity implements TextToSpeech.OnInitListener,View.OnClickListener {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "hu"));
    }


    //private Button btnFetchData;
    private TextView txtWikiData;
    private TextView title;
    private TextView wikipedia;
    private ImageButton speakbtn;
    private ProgressBar progressBar;
    private int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech myTTS;
    // private TextToSpeech tts;

    DatabaseHandler databaseHandler = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Bundle extra = getIntent().getExtras();
        //String text = extra.getString(MainActivity.TAG_TEXT);
        String keyword = extra.getString("key");

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //etxSearch = (EditText) findViewById(R.id.etxSearch);
        txtWikiData = (TextView) findViewById(R.id.txtWikiData);
        title = (TextView) findViewById(R.id.title);
        wikipedia = (TextView) findViewById(R.id.wikipedia);
        speakbtn = (ImageButton) findViewById(R.id.speakbtn);


        // final String[] language = extra.getStringArray("lang");
        // final String lang = extra.getString("key1");
        //String speech = null;
        Paper.init(this);
        //language[].setText(language);
        String language = Paper.book().read("language");
        if (language == null)
            Paper.book().write("language", "hu");

        updateView((String) Paper.book().read("language"));

        if (language.equals("hu")) {

            // title.setText("A városról");
            String WIKIPEDIA_URL = "https://hu.wikipedia.org/w/api.php?action=query&titles=" +
                    keyword +
                    "&prop=revisions&rvprop=content&format=json&prop=extracts";

            // Start AsyncTask
            ContentActivity.FetchWikiDataAsync fetchWikiDataAsync = new ContentActivity.FetchWikiDataAsync();

            fetchWikiDataAsync.execute(WIKIPEDIA_URL);
            speakbtn.setOnClickListener(ContentActivity.this);
        /*Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);*/
        /*Locale locHun = new Locale("hu");
        myTTS.setLanguage(locHun);
        myTTS.speak(speech, TextToSpeech.QUEUE_ADD, null);*/
        } else if (language.equals("en")) {
            // title.setText("About the city");
            String WIKIPEDIA_URL = "https://en.wikipedia.org/w/api.php?action=query&titles=" +
                    keyword +
                    "&prop=revisions&rvprop=content&format=json&prop=extracts";

            // Start AsyncTask
            ContentActivity.FetchWikiDataAsync fetchWikiDataAsync = new ContentActivity.FetchWikiDataAsync();

            fetchWikiDataAsync.execute(WIKIPEDIA_URL);
            speakbtn.setOnClickListener(ContentActivity.this);
            Intent checkTTSIntent = new Intent();
            checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        } else if (language.equals("de")) {
            //title.setText("A városról");
            String WIKIPEDIA_URL = "https://de.wikipedia.org/w/api.php?action=query&titles=" +
                    keyword +
                    "&prop=revisions&rvprop=content&format=json&prop=extracts";

            // Start AsyncTask
            ContentActivity.FetchWikiDataAsync fetchWikiDataAsync = new ContentActivity.FetchWikiDataAsync();
            fetchWikiDataAsync.execute(WIKIPEDIA_URL);
            speakbtn.setOnClickListener(ContentActivity.this);
            Intent checkTTSIntent = new Intent();
            checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        }

    }

    public void onClick(View v) {
        //handle user clicks here
        TextView txtWikiData = (TextView) findViewById(R.id.txtWikiData);
        String words = txtWikiData.getText().toString();
        speakWords(words);
        // speak(words);

    }

    private void speakWords(String speech) {
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                myTTS = new TextToSpeech(ContentActivity.this, ContentActivity.this);
            } else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    public void onInit(int initStatus) {
        if (initStatus == TextToSpeech.SUCCESS) {
            if (myTTS.isLanguageAvailable(Locale.UK) == TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.UK);
            myTTS.setLanguage(Locale.UK);
        } else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    /*private void speak(String text){

            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
       /* else{
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }*/
    //}
   /* @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }*/

    private class FetchWikiDataAsync extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            // Toast.makeText(ContentActivity.this, "Egy pillanat...", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected String doInBackground(String[] params) {
            try {
                String sURL = params[0];

                URL url = new URL(sURL);        // Convert String URL to java.net.URL
                // Connection: to Wikipedia API
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                String wikiData = stringBuilder.toString();

                // Parse JSON Data
                String formattedData = parseJSONData(wikiData);

                //openContentActivity(formattedData);
                return formattedData;


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String formattedData) {
            Bundle extra = getIntent().getExtras();
            String keyword = extra.getString("key");
            String language = Paper.book().read("language");
            try {
                super.onPostExecute(formattedData);
                progressBar.setVisibility(View.GONE);

                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    txtWikiData.setText(Html.fromHtml
                            (formattedData, Html.FROM_HTML_MODE_LEGACY));

                } else {*/
                // HTML Data

                txtWikiData.setText(Html.fromHtml(formattedData));
                ContentValues values = new ContentValues();
                //Toast.makeText(ContentActivity.this, "hiba!", Toast.LENGTH_LONG).show();
                if (language.equals("hu")) {
                    String infohu = txtWikiData.getText().toString();

                    values.put(databaseHandler.CITYINFO_HU, infohu);
                    addData(keyword, infohu, null, null);
                    //updateIfExistsElseInsert(keyword,infohu,null,null);
                    // String infohu = Html.fromHtml(formattedData, Html.FROM_HTML_MODE_LEGACY).toString();

                    addData(keyword, infohu, null, null);
                } else if (language.equals("en")) {
                    String infoen = txtWikiData.getText().toString();
                    // ContentValues values = new ContentValues();
                    values.put(CITYINFO_EN, infoen);
                    addData(keyword, null, infoen, null);
                } else if (language.equals("de")) {
                    String infode = txtWikiData.getText().toString();
                    // ContentValues values = new ContentValues();
                    values.put(CITYINFO_DE, infode);
                    addData(keyword, null, null, infode);
                }

            } catch (Exception e) {
                Toast.makeText(ContentActivity.this, "Wifi!", Toast.LENGTH_LONG).show();
                db = databaseHandler.getWritableDatabase();
                if (language.equals("hu")) {
                    //Cursor cursor = db.query(DatabaseHandler.TABLE_NAME, new String[] { "cityname","cityinfohu","cityinfoen","cityinfode" },null, null, null, null, null);
                    Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE " + CITYNAME + " = ? ", new String[]{keyword});
                    if (cursor.moveToFirst()) {
                        do {
                            String infohu = cursor.getString(cursor.getColumnIndex("cityinfohu")); // Here you can get data from table and stored in string if it has only one string.
                            txtWikiData.setText(infohu);


                        } while (cursor.moveToNext());
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }

                } else if (language.equals("en")) {
                    Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE " + CITYNAME + " = ? ", new String[]{keyword});
                    if (cursor.moveToFirst()) {
                        do {
                            String infoen = cursor.getString(cursor.getColumnIndex("cityinfoen")); // Here you can get data from table and stored in string if it has only one string.
                            txtWikiData.setText(infoen);

                        } while (cursor.moveToNext());
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }

                } else if (language.equals("de")) {
                    Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE " + CITYNAME + " = ? ", new String[]{keyword});
                    if (cursor.moveToFirst()) {
                        do {
                            String infode = cursor.getString(cursor.getColumnIndex("cityinfode")); // Here you can get data from table and stored in string if it has only one string.
                            txtWikiData.setText(infode);

                        } while (cursor.moveToNext());
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }
                }
            }

        }

    }

    private String parseJSONData(String wikiData) {
        try {
            // Convert String JSON (wikiData) to JSON Object
            JSONObject rootJSON = new JSONObject(wikiData);
            JSONObject query = rootJSON.getJSONObject("query");
            JSONObject pages = query.getJSONObject("pages");
            JSONObject number = pages.getJSONObject(pages.keys().next());
            String formattedData = number.getString("extract");

            return formattedData;


        } catch (JSONException json) {
            json.printStackTrace();
        }

        return null;
    }

    //nyelvnek megfelelően változtatja a címet
    private void updateView(String lang) {
        Context context = LocaleHelper.setLocale(this, lang);
        Resources resources = context.getResources();

        title.setText(resources.getString(R.string.citytitle));
        wikipedia.setText(resources.getString(R.string.wikipedia));
    }

    public boolean CheckIsDataAlreadyInDBorNot() {
        Bundle extra = getIntent().getExtras();
        String keyword = extra.getString("key");
        SQLiteDatabase db;
        Cursor c = null;
        db = databaseHandler.getReadableDatabase();
        //try {
        Toast.makeText(ContentActivity.this, "ellenőrzés!", Toast.LENGTH_SHORT).show();
        //db.getReadableDatabase();
        String query = "select cityname from cityinfos where cityname = ?";
        //String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = db.rawQuery(query, new String[]{keyword});
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;

    }

    SQLiteDatabase db;

    public void addData(String keyword, String infohu, String infoen, String infode) {
        String language = Paper.book().read("language");
        Toast.makeText(ContentActivity.this, "adddata!", Toast.LENGTH_SHORT).show();
        if (CheckIsDataAlreadyInDBorNot() == false) {
            //perform inserting
            Toast.makeText(ContentActivity.this, "insert!", Toast.LENGTH_SHORT).show();
            boolean insertData = databaseHandler.addData(keyword, infohu, infoen, infode);

            if (insertData)
                Toast.makeText(ContentActivity.this, "Sikeres!", Toast.LENGTH_SHORT).show();
            else Toast.makeText(ContentActivity.this, "Sikertelen !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ContentActivity.this, "Frissítés!", Toast.LENGTH_SHORT).show();
            db = databaseHandler.getWritableDatabase();
            ContentValues cv = new ContentValues();
            if (language.equals("hu")) {
                cv.put(CITYINFO_HU, infohu);
                db.update(TABLE_NAME, cv, CITYNAME + " = ?", new String[]{keyword});
            } else if (language.equals("en")) {
                cv.put(CITYINFO_EN, infoen);
                db.update(TABLE_NAME, cv, CITYNAME + "= ?", new String[]{keyword});
            } else if (language.equals("de")) {
                cv.put(CITYINFO_DE, infode);
                db.update(TABLE_NAME, cv, CITYNAME + "= ?", new String[]{keyword});
            }
        }

    }
}