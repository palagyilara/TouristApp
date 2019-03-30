package com.example.laura.touristapp;

//import android.content.Intent;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laura.touristapp.Helper.LocaleHelper;/*
import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;*/

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;/*
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Dataset;
import com.google.cloud.bigquery.DatasetInfo;*/

import io.paperdb.Paper;

import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_DE;
import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_EN;
import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_HU;
import static com.example.laura.touristapp.DatabaseHandler.CITYNAME;
import static com.example.laura.touristapp.DatabaseHandler.TABLE_NAME;

//import android.provider.BaseColumns;
//import android.widget.Button;

public class ContentActivity extends AppCompatActivity {


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
   // private int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech tts;
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

        String language = Paper.book().read("language");

        if (language.equals("hu")) {

            // title.setText("A városról");
            String WIKIPEDIA_URL = "https://hu.wikipedia.org/w/api.php?action=query&titles=" +
                    keyword +
                    "&prop=revisions&rvprop=content&format=json&prop=extracts";

            // Start AsyncTask
            ContentActivity.FetchWikiDataAsync fetchWikiDataAsync = new ContentActivity.FetchWikiDataAsync();

            fetchWikiDataAsync.execute(WIKIPEDIA_URL);

            speakbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //handle user clicks here
                    //TextView txtWikiData = (TextView) findViewById(R.id.txtWikiData);
                   // String words = txtWikiData.getText().toString();
                   // tts.speak(words, TextToSpeech.QUEUE_FLUSH, null);
                    //speakWords(words);
                    // speak(words);
                    String data = txtWikiData.getText().toString();
                    Log.i("TTS", "button clicked: " + data);
                    int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

                    if (speechStatus == TextToSpeech.ERROR) {
                        Log.e("TTS", "Error in converting Text to Speech!");
                    }

                }
            });

        } else if (language.equals("en")) {
            // title.setText("About the city");
            String WIKIPEDIA_URL = "https://en.wikipedia.org/w/api.php?action=query&titles=" +
                    keyword +
                    "&prop=revisions&rvprop=content&format=json&prop=extracts";

            // Start AsyncTask
            ContentActivity.FetchWikiDataAsync fetchWikiDataAsync = new ContentActivity.FetchWikiDataAsync();

            fetchWikiDataAsync.execute(WIKIPEDIA_URL);
            //speakbtn.setOnClickListener(ContentActivity.this);
            tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

                @Override
                public void onInit(int initStatus) {
                    if (initStatus == TextToSpeech.SUCCESS) {
                       // if (tts.isLanguageAvailable(Locale.UK) == TextToSpeech.LANG_AVAILABLE) {
                        int result = tts.setLanguage(Locale.UK);
                            //tts.setLanguage(Locale.UK);
                            Toast.makeText(ContentActivity.this, "Angol beszéd kész", Toast.LENGTH_LONG).show();
                            //tts.setLanguage(Locale.GERMANY);
                        if (result == TextToSpeech.LANG_MISSING_DATA
                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e("TTS", "The Language is not supported!");
                            Intent installIntent = new Intent();
                            installIntent.setAction(
                                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                            startActivity(installIntent);
                        } else {
                            Log.i("TTS", "Language Supported.");
                        }
                        Log.i("TTS", "Initialization success.");
                       // }
                        // tts.setLanguage(Locale.UK);
                    } else if (initStatus == TextToSpeech.ERROR) {
                        Toast.makeText(ContentActivity.this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
                    }
                }
            });
            speakbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String data = txtWikiData.getText().toString();
                    Log.i("TTS", "button clicked: " + data);
                    int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

                    if (speechStatus == TextToSpeech.ERROR) {
                        Log.e("TTS", "Error in converting Text to Speech!");
                    }
                }
            });

        } else if (language.equals("de")) {
            //title.setText("A városról");
            String WIKIPEDIA_URL = "https://de.wikipedia.org/w/api.php?action=query&titles=" +
                    keyword +
                    "&prop=revisions&rvprop=content&format=json&prop=extracts";

            // Start AsyncTask
            ContentActivity.FetchWikiDataAsync fetchWikiDataAsync = new ContentActivity.FetchWikiDataAsync();
            fetchWikiDataAsync.execute(WIKIPEDIA_URL);
            tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

                @Override
                public void onInit(int initStatus) {
                    if (initStatus == TextToSpeech.SUCCESS) {
                        Locale locale = new Locale("de", "de_AT");
                        int result = tts.setLanguage(locale);
                        //int result = tts.setLanguage(new Locale("de_AT"));
                        //if (tts.isLanguageAvailable(Locale.GERMANY) == TextToSpeech.LANG_AVAILABLE) {
                            //  tts.setLanguage(Locale.UK);
                           // tts.setLanguage(Locale.GERMANY);
                            Toast.makeText(ContentActivity.this, "Német beszéd kész", Toast.LENGTH_LONG).show();
                        if (result == TextToSpeech.LANG_MISSING_DATA
                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            Log.e("TTS", "The Language is not supported!");
                            Intent installIntent = new Intent();
                            installIntent.setAction(
                                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                            startActivity(installIntent);
                            /*Intent checkTTSIntent = new Intent();
                            checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                            startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);*/
                        } else {
                            Log.i("TTS", "Language Supported.");

                        }
                        Log.i("TTS", "Initialization success.");
                      //  }
                        // tts.setLanguage(Locale.UK);
                    } else if (initStatus == TextToSpeech.ERROR) {
                        Toast.makeText(ContentActivity.this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
                    }
                }
            });
            speakbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    String data = txtWikiData.getText().toString();
                    Log.i("TTS", "button clicked: " + data);
                    int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);
                    if (speechStatus == TextToSpeech.ERROR) {
                        Log.e("TTS", "Error in converting Text to Speech!");
                        }
                        }
            });
        }

    }

   @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


    private class FetchWikiDataAsync extends AsyncTask<String, Void, String> {
        String text1;

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
                Document doc = Jsoup.parse(formattedData);


                text1 = formattedData.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                        .replaceAll("<span id=\"Képgaléria\">(.+?)</span>","")
                        .replaceAll("<span id=\"Képek\">(.+?)</span>","")
                        .replaceAll("<span id=\"Galéria\">(.+?)</span>","")
                        .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>","")
                        .replaceAll("<span id=\"Képek_Egerről\">(.+?)</span>","")
                        .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>","")
                        .replaceAll("<ul class=\"gallery mw-gallery-packed\"([\\s\\S]+?)</ul>","")
                        .replaceAll("<ul class=\"attachments\"([\\s\\S]+?)</ul>","")
                        .replaceAll("<span id=\"Címer\">(.+?)</span>","")
                        .replaceAll("<span id=\"References\">(.+?)</span>","")
                        .replaceAll("<span id=\"Testvérváros\">(.+?)</span>","")
                        .replaceAll("<span id=\"Partnerváros\">(.+?)</span>","")
                        .replaceAll("<span id=\"Population\">(.+?)</span>","")
                        .replaceAll("<span id=\"Image_gallery\">(.+?)</span>","")
                        .replaceAll("<span>Vorlage:Panorama/Wartung/Para4</span>","")
                        .replaceAll("<span id=\"Éghajlat\">(.+?)</span>","")
                        .replaceAll("<span id=\"Éghajlata\">(.+?)</span>","")
                        .replaceAll ("<a>","<p>")
                        .replaceAll ("<table>","<div>")
                        .replaceAll("<span id=\"Klima\">(.+?)</span>","")
                        //.replaceAll("<table class=\"wikitable\"([\\s\\S]+?)</table>","")
                        .replaceAll("<span id=\"Címer\">(.+?)</span>","")
                        .replaceAll("<span id=\"Climate\">(.+?)</span>","");

                txtWikiData.setText(Html.fromHtml(text1));
                ContentValues values = new ContentValues();
                //Toast.makeText(ContentActivity.this, "hiba!", Toast.LENGTH_LONG).show();
                if (language.equals("hu")) {
                    //String infohu = txtWikiData.getText().toString();
                    String infohu=formattedData;
                    values.put(CITYINFO_HU, infohu);
                    addData(keyword, infohu, null, null);
                    //updateIfExistsElseInsert(keyword,infohu,null,null);
                    // String infohu = Html.fromHtml(formattedData, Html.FROM_HTML_MODE_LEGACY).toString();
                } else if (language.equals("en")) {
                    //String infoen = txtWikiData.getText().toString();
                    String infoen=formattedData;
                    // ContentValues values = new ContentValues();
                    values.put(CITYINFO_EN, infoen);
                    addData(keyword, null, infoen, null);
                } else if (language.equals("de")) {
                    //String infode = txtWikiData.getText().toString();
                    String infode=formattedData;
                    // ContentValues values = new ContentValues();
                    values.put(CITYINFO_DE, infode);
                    addData(keyword, null, null, infode);
                }

            } catch (Exception e) {
               // progressBar.setVisibility(View.GONE);
                //super.onPostExecute(formattedData);
                Toast.makeText(ContentActivity.this, "Wifi!", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.VISIBLE);
                db = databaseHandler.getWritableDatabase();
                if (language.equals("hu")) {
                    //Cursor cursor = db.query(DatabaseHandler.TABLE_NAME, new String[] { "cityname","cityinfohu","cityinfoen","cityinfode" },null, null, null, null, null);
                    Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE " + CITYNAME + " = ? ", new String[]{keyword});
                    if (cursor.moveToFirst()) {
                        do {
                            String infohu = cursor.getString(cursor.getColumnIndex("cityinfohu")); // Here you can get data from table and stored in string if it has only one string.
                            text1 = infohu.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>").replaceAll("<span id=\"Jegyzetek\">(.+?)</span>","").replaceAll("<span id=\"Képgaléria\">(.+?)</span>","").replaceAll("<span id=\"Képek\">(.+?)</span>","")
                                    .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>","");
                            progressBar.setVisibility(View.GONE);
                            txtWikiData.setText(Html.fromHtml(text1));


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
                            text1 = infoen.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>");
                            txtWikiData.setText(Html.fromHtml(text1));

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
                            text1 = infode.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>");
                            txtWikiData.setText(Html.fromHtml(text1));

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
       // Cursor c = null;
        db = databaseHandler.getReadableDatabase();
        //try {
       // Toast.makeText(ContentActivity.this, "ellenőrzés!", Toast.LENGTH_SHORT).show();
        //db.getReadableDatabase();
        if (keyword.equals("Eger_(Ungarn)")) {
            String keyword1 = "Eger";
            String query = "select cityname from cityinfos where cityname = ?";
            //String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
            Cursor cursor = db.rawQuery(query, new String[]{keyword1});

            if (cursor.getCount() <= 0) {
                cursor.close();
                return false;
            }
            cursor.close();
            return true;
        }
        else {
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
    }

    SQLiteDatabase db;

    public void addData(String keyword, String infohu, String infoen, String infode) {
        String language = Paper.book().read("language");
        //Toast.makeText(ContentActivity.this, "adddata!", Toast.LENGTH_SHORT).show();
        if (CheckIsDataAlreadyInDBorNot() == false) {
            //perform inserting
            Toast.makeText(ContentActivity.this, "insert!", Toast.LENGTH_SHORT).show();
            boolean insertData = databaseHandler.addData(keyword, infohu, infoen, infode);

            /*if (insertData)
                Toast.makeText(ContentActivity.this, "Sikeres!", Toast.LENGTH_SHORT).show();
            else Toast.makeText(ContentActivity.this, "Sikertelen !", Toast.LENGTH_SHORT).show();*/
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