package com.example.laura.touristapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import io.paperdb.Paper;

import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_DE;
import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_EN;
import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_HU;
import static com.example.laura.touristapp.DatabaseHandler.CITYNAME;
import static com.example.laura.touristapp.DatabaseHandler.CITYNAME1;
import static com.example.laura.touristapp.DatabaseHandler.PLACENAME_DE;
import static com.example.laura.touristapp.DatabaseHandler.PLACENAME_EN;
import static com.example.laura.touristapp.DatabaseHandler.PLACENAME_HU;
import static com.example.laura.touristapp.DatabaseHandler.SIGHTINFO_DE;
import static com.example.laura.touristapp.DatabaseHandler.SIGHTINFO_EN;
import static com.example.laura.touristapp.DatabaseHandler.SIGHTINFO_HU;
import static com.example.laura.touristapp.DatabaseHandler.TABLE1_NAME;
import static com.example.laura.touristapp.DatabaseHandler.TABLE_NAME;

public class PDescriptionActivity extends AppCompatActivity {

    private TextView txtData;
    private Button map;
    private ProgressBar progressBar;
    private TextView title;
    private ImageButton speakbtn;
    private TextView webpage;
    private TextToSpeech tts;


    DatabaseHandler databaseHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_pdescription);
        Bundle extra = getIntent().getExtras ();
        //String text = extra.getString(MainActivity.TAG_TEXT);
        String placetitle = extra.getString ("key1");
        final String keyword1 = extra.getString ("key2");
        final String keyword = extra.getString ("key");

        String language = Paper.book ( ).read ("language");

        txtData = (TextView) findViewById(R.id.txtData);
        map = (Button) findViewById(R.id.map);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        title = (TextView) findViewById(R.id.title);
        webpage = (TextView) findViewById(R.id.webpage);
        speakbtn = (ImageButton) findViewById(R.id.speakbtn);

        if(language.equals("en"))
        {
            tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

                @Override
                public void onInit(int initStatus) {
                    if (initStatus == TextToSpeech.SUCCESS) {
                        // if (tts.isLanguageAvailable(Locale.UK) == TextToSpeech.LANG_AVAILABLE) {
                        int result = tts.setLanguage(Locale.UK);
                        //tts.setLanguage(Locale.UK);
                        //Toast.makeText(PDescriptionActivity.this, "Angol beszéd kész", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(PDescriptionActivity.this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
                    }
                }
            });
            speakbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String data = txtData.getText().toString();
                    //Log.i("TTS", "button clicked: " + data);
                    int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

                    if (speechStatus == TextToSpeech.ERROR) {
                        Log.e("TTS", "Error in converting Text to Speech!");
                    }
                }
            });
        }
        else if(language.equals("de"))
        {
            tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

                @Override
                public void onInit(int initStatus) {
                    if (initStatus == TextToSpeech.SUCCESS) {
                        int result = tts.setLanguage(Locale.GERMAN);
                        //int result = tts.setLanguage(new Locale("de_AT"));
                        //if (tts.isLanguageAvailable(Locale.GERMANY) == TextToSpeech.LANG_AVAILABLE) {
                        //  tts.setLanguage(Locale.UK);
                        // tts.setLanguage(Locale.GERMANY);
                        //Toast.makeText(PDescriptionActivity.this, "Német beszéd kész", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(PDescriptionActivity.this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
                    }
                }
            });
            speakbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    String data = txtData.getText().toString();
                    //Log.i("TTS", "button clicked: " + data);
                    int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

                    if (speechStatus == TextToSpeech.ERROR) {
                        Log.e("TTS", "Error in converting Text to Speech!");
                    }

                }
            });
        }
        else if(language.equals("hu"))
        {
            speakbtn.setVisibility(View.INVISIBLE);
        }

        if (keyword.equals ("Keszthely")) { //getResources().getString(R.string.keszthely) //kész
            webpage.setText (getResources().getString(R.string.keszthwebpage));
            if (language.equals ("hu")) {
                title.setText (placetitle); //cim beállítása
                String url = "https://www.keszthely.hu/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }
            else if (language.equals ("en")) {
                title.setText (placetitle);
                String url = "https://www.keszthely.hu/en/tourism/sights/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);

            } else if (language.equals ("de")) {
                title.setText (placetitle);
                String url = "https://www.keszthely.hu/de/tourismus/sehenswurdigkeiten/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }
        }
        else if (keyword.equals ("Eger")) {  //kész
            webpage.setText (getResources().getString(R.string.egerwebpage));
            if (language.equals ("hu")) {
                title.setText (placetitle); //cim beállítása
                String url = "https://visiteger.com/latnivalok/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }
            else if (language.equals ("en")) {
                title.setText (placetitle);
                String url = "https://visiteger.com/en/sights/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);

            } else if (language.equals ("de")) {
                title.setText (placetitle);
                String url = "https://visiteger.com/de/sehenswurdigkeiten/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);

            }

        }
        else if (keyword.equals ("Pécs")) { //kész
            webpage.setText (getResources().getString(R.string.pecswebpage));
            if (language.equals ("hu")) {
                title.setText (placetitle); //cim beállítása
                String url = "https://www.iranypecs.hu/hu/info/latnivalok/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }
            else if (language.equals ("en")) {
                title.setText (placetitle);
                String url = "https://www.iranypecs.hu/en/info/attractions/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);

            } else if (language.equals ("de")) {
                title.setText (placetitle);
                String url = "https://www.iranypecs.hu/de/info/sehenswurdigkeiten/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);

            }

        }
        else if (keyword.equals ("Sopron")) { //kész
            webpage.setText (getResources().getString(R.string.sopronwebpage));
            title.setText (placetitle);
            if (language.equals ("hu")) {
                //cim beállítása
                String url = "http://turizmus.sopron.hu/hu/info/latnivalok/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }
            else  if (language.equals ("en")) {
                //title.setText (placetitle); //cim beállítása
                String url = "http://turizmus.sopron.hu/en/info/sights/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }
            else  if (language.equals ("de")) {
                //title.setText (placetitle); //cim beállítása
                String url = "http://turizmus.sopron.hu/de/info/sehenswertes/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }

        }
        else if (keyword.equals ("Szeged")) {
            webpage.setText (getResources().getString(R.string.szegedwebpage));
            if (language.equals ("hu")||language.equals ("en")||language.equals ("de")) {
                title.setText (placetitle); //cim beállítása
                String url = "http://szegedtourism.hu/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }
        }
        else if (keyword.equals ("Veszprém")) {
            webpage.setText (getResources().getString(R.string.veszpwebpage));
            if (language.equals ("hu")||language.equals ("en")||language.equals ("de")) {
                title.setText (placetitle); //cim beállítása
                String url = "https://veszpreminfo.hu/record/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }
        }
        else if (keyword.equals ("Tihany")) {
            webpage.setText (getResources().getString(R.string.veszpwebpage));
            if (language.equals ("hu")) {
                title.setText (placetitle); //cim beállítása
                String url = "http://www.tihany.hu/index.php/hu/latnivalok/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }
            else if (language.equals ("en")) {
                title.setText (placetitle); //cim beállítása
                String url = "http://www.tihany.hu/index.php/en/attractions/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }
            else if (language.equals ("de")) {
                title.setText (placetitle); //cim beállítása
                String url = "http://www.tihany.hu/index.php/de/latnivalok-de/" +
                        keyword1;
                // Start AsyncTask
                DownloadWebPageTask task = new DownloadWebPageTask ( );
                //url átadása
                task.execute (url);
            }


        }

        //címre kattintva megnyílik a térkép
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDescriptionActivity.this,MapsActivity.class);
                String placetitle = title.getText().toString();

                intent.putExtra("key", keyword); //város neve
                intent.putExtra("key1", placetitle); //látványosság neve
                // intent.putExtra("key2", keyword1); //url neve
                startActivity(intent);
            }
        });
    }
    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    SQLiteDatabase db;

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        String words;
        String text1;
        String text2;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            // Toast.makeText(ContentActivity.this, "Egy pillanat...", Toast.LENGTH_SHORT).show();

        }
        @Override
        protected String doInBackground(String[] urls) {
            Bundle extra = getIntent().getExtras ();
            final String keyword = extra.getString ("key");

            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                }
            } };

            // Install the all-trusting trust manager
            try {
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String url = urls[0];
            try {
                Document doc = Jsoup.connect (url).get ();

                //város feltételek
                if (keyword.equals ("Keszthely")){

                    Elements divs = doc.select ("div.content-text");

                    for (Element d : divs) {
                        words = d.text ( );
                        String text = d.html ( );
                        text1 = text.replaceAll ("</p>", "\n");
                    }
                }
                else if (keyword.equals ("Pécs")) {
                    Elements div = doc.select ("div#cnt_lead");
                    Elements divs = doc.select ("div#cnt_text");

                    for (Element d : div) {
                        words = d.text ( );
                        String text = d.html ( );
                        text1 = text.replaceAll ("</p>", "\n").replaceAll ("<h2>", "<p>").replaceAll ("</h2>", "</p>");

                    }
                    for (Element p : divs) {
                        words = p.text ( );
                        String text = p.html ( );
                        text2 = text.replaceAll ("</p>", "\n");
                    }
                }
                else if (keyword.equals ("Eger")) {

                    Elements article = doc.select ("article.entry.bottom-space-md.clearfix");
                    for (Element d : article) {
                        words = d.text ();
                        String text = d.html ( );
                        text1 = text.replaceAll ("</p>", "\n");

                    }
                }
                else if (keyword.equals ("Sopron")) {
                    Elements div = doc.select ("div#cnt_lead");
                    Elements divs = doc.select ("div#cnt_text");

                    for (Element d : div) {
                        words = d.text ();
                        String text = d.html ( );
                        text1 = text.replaceAll ("</p>", "\n").replaceAll ("<h2>", "<p>")
                                .replaceAll ("</h2>", "</p>")
                                .replaceAll ("<img[^>]+>","");

                    }
                    for (Element p : divs) {
                        words = p.text ();
                        String text = p.html ( );
                        text2 = text.replaceAll ("</p>", "\n").replaceAll ("<img[^>]+>","");

                    }
                }
                else if (keyword.equals ("Szeged")) {
                    Elements div = doc.select ("div.single-content");
                    for (Element p : div) {
                        words = p.text ();
                        String text = p.html ( );
                        text1 = text.replaceAll ("</p>", "\n").replaceAll ("<style([\\s\\S]+?)</style>", "")
                                .replaceAll ("<a class=\"synved-social-button.*\"([\\s\\S]+?)</a>", "")
                                .replaceAll ("<div id=\"upm-buttons\"([\\s\\S]+?)</div>", "")
                                .replaceAll ("<h2>", "<h1>")
                                .replaceAll ("</li>", "\n")
                                .replaceAll ("<div id=\"gallery-2\"([\\s\\S]+?)</div>", "");
                    }
                }
                else if (keyword.equals ("Veszprém")) {
                    Elements divs = doc.select ("div.list-item-details-left p");
                    Elements div = doc.select ("div.tab-pane.detail.fade.active.in");

                    for (Element d : divs) {
                        words = d.text ();
                        String text = d.html ( );
                        text1 = text.replaceAll ("</p>", "\n").replaceAll ("<h2>", "<p>").
                                replaceAll ("</h2>", "</p>").
                                replaceAll("<span class=\"expand-text\">(.+?)</span>","").
                                replaceAll("<span class=\"collapse-text\">(.+?)</span>","");
                    }
                    for (Element p : div) {
                        words = p.text ();
                        String text = p.html ( );
                        text2 = text.replaceAll ("</p>", "\n").replaceAll("Tekintse meg a helyszínt gömbpanorámás.*","")
                                .replaceAll("Click here to see the.*","")
                                .replaceAll("Klicken Sie bitte hier das.*","")
                                .replaceAll("<div class=\"row\"([\\s\\S]+?)</div>","")
                                .replaceAll("<div class=\"collapse\"(.+?)</div>","")
                                .replaceAll("<ul class=\"attachments\"([\\s\\S]+?)</ul>","")
                                .replaceAll("<table class=\"record-details-table\"([\\s\\S]+?)</table>","")
                                .replaceAll("<div class=\"col-sm-4.*\"([\\s\\S]+?)</div>","");
                    }
                }
                else if (keyword.equals ("Tihany")) {
                    Elements divs = doc.select ("div.item-page");
                    for (Element d : divs) {
                        words = d.text ();
                        String text = d.html ( );
                        text1 = text.replaceAll ("<div class=\"article-header\"([\\s\\S]+?)</div>","")
                                .replaceAll ("<div id=\"rt-wj\"([\\s\\S]+?)</div>","")
                                .replaceAll ("<div class=\"rt-category\"([\\s\\S]+?)</div>","")
                                .replaceAll ("<p>&nbsp;</p>","")
                                .replaceAll ("</div>","")
                                .replaceAll ("<a ([\\s\\S]+?)</a>","")
                                .replaceAll ("<img[^>]+>","")
                                .replaceAll ("<table([\\s\\S]+?)</table>","");
                    }
                }


            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            Bundle extra = getIntent().getExtras();
            String keyword = extra.getString("key");
            String placetitle = extra.getString ("key1");
            String language = Paper.book().read("language");
            try{
                //System.out.println(text1+text2);
                super.onPostExecute(result);
                Log.d("SZOVEG", text1);
                progressBar.setVisibility(View.GONE);
                if(text2!=null && text1!=null){ text1=text1+text2;
                    txtData.setText(Html.fromHtml(text1));
                }
                else {txtData.setText(Html.fromHtml(text1));}
                //txtData1.setText(words1);

                ContentValues values = new ContentValues();
                //Toast.makeText(ContentActivity.this, "hiba!", Toast.LENGTH_LONG).show();
                if (language.equals("hu")) {
                    //String infohu = txtWikiData.getText().toString();
                    String infohu=text1;
                    values.put(SIGHTINFO_HU, infohu);
                    values.put(PLACENAME_HU, placetitle);
                    addData(keyword, placetitle,null,null,infohu, null, null);
                } else if (language.equals("en")) {
                    //String infoen = txtWikiData.getText().toString();
                    String infoen=text1;
                    values.put(PLACENAME_EN, placetitle);
                    // ContentValues values = new ContentValues();
                    values.put(SIGHTINFO_EN, infoen);
                    addData(keyword,null,placetitle,null, null, infoen, null);
                } else if (language.equals("de")) {
                    //String infode = txtWikiData.getText().toString();
                    String infode=text1;
                    values.put(PLACENAME_DE, placetitle);
                    // ContentValues values = new ContentValues();
                    values.put(SIGHTINFO_DE, infode);
                    addData(keyword, null,null,placetitle,null, null, infode);
                }

            }
            catch (Exception e) {
                //Toast.makeText (PDescriptionActivity.this, "Wifi", Toast.LENGTH_SHORT).show ( );
                progressBar.setVisibility(View.GONE);
                db = databaseHandler.getReadableDatabase();
                if (language.equals("hu")) {
                    //Cursor cursor = db.query(DatabaseHandler.TABLE_NAME, new String[] { "cityname","cityinfohu","cityinfoen","cityinfode" },null, null, null, null, null);
                    Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE1_NAME + " WHERE " + PLACENAME_HU + " = ? ", new String[]{placetitle});
                    //try {
                        if (cursor.moveToFirst()) {
                            do {
                                String infohu = cursor.getString(cursor.getColumnIndex("sightinfohu")); // Here you can get data from table and stored in string if it has only one string.
                                text1 = infohu;
                                txtData.setText(Html.fromHtml(text1));


                            } while (cursor.moveToNext());
                        }
                        else {
                            //progressBar.setVisibility(View.GONE);
                            Toast.makeText(PDescriptionActivity.this, "Kapcsolja be a wifi-t!", Toast.LENGTH_LONG).show();
                        }
                    /*}
                    catch(NullPointerException e1){
                        //progressBar.setVisibility(View.GONE);
                        Toast.makeText(PDescriptionActivity.this, "Kapcsolja be a wifi-t!", Toast.LENGTH_LONG).show();
                    }
                    finally {
                        cursor.close();
                    }*/
                    /*if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }*/

                } else if (language.equals("en")) {
                    Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE1_NAME + " WHERE " + PLACENAME_EN + " = ? ", new String[]{placetitle});
                    //try {
                        if (cursor.moveToFirst()) {
                            do {
                                String infoen = cursor.getString(cursor.getColumnIndex("sightinfoen")); // Here you can get data from table and stored in string if it has only one string.
                                text1 = infoen;
                                txtData.setText(Html.fromHtml(text1));

                            } while (cursor.moveToNext());
                        }
                        else {
                            //progressBar.setVisibility(View.GONE);
                            Toast.makeText(PDescriptionActivity.this, "Turn wifi on!", Toast.LENGTH_LONG).show();
                        }
                   /* }
                    catch(NullPointerException e1){
                       // progressBar.setVisibility(View.GONE);
                        Toast.makeText(PDescriptionActivity.this, "Turn wifi on!", Toast.LENGTH_LONG).show();
                    }
                    finally {
                        cursor.close();
                    }*/
                    /*if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }*/

                } else if (language.equals("de")) {
                    Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE1_NAME + " WHERE " + PLACENAME_DE + " = ? ", new String[]{placetitle});
                    //try{
                    if (cursor.moveToFirst()) {
                        do {
                            String infode = cursor.getString(cursor.getColumnIndex("sightinfode")); // Here you can get data from table and stored in string if it has only one string.
                            text1 = infode;
                            txtData.setText(Html.fromHtml(text1));

                        } while (cursor.moveToNext());
                    }
                    else {
                        //progressBar.setVisibility(View.GONE);
                        Toast.makeText(PDescriptionActivity.this, "Wifi", Toast.LENGTH_LONG).show();
                    }
               /* }
                    catch(NullPointerException e1){
                        //progressBar.setVisibility(View.GONE);
                        Toast.makeText(PDescriptionActivity.this, "Turn wifi on!/de", Toast.LENGTH_LONG).show();
                    }
                    finally {
                        cursor.close();
                    }*/
                }
            }

        }
    }
    public boolean CheckIsDataAlreadyInDBorNot() {
        String language = Paper.book ( ).read ("language");
        Bundle extra = getIntent ( ).getExtras ( );
        String placetitle = extra.getString ("key1");
        // String keyword = extra.getString("key");
        SQLiteDatabase db;
        db = databaseHandler.getReadableDatabase ( );
        //try {
       // /Toast.makeText (PDescriptionActivity.this, "ellenőrzés!", Toast.LENGTH_SHORT).show ( );
        //db.getReadableDatabase();
        Cursor cursor = null;
        if (language.equals ("hu") ) {
            String query = "select cityname from sightinfos where pnamehu = ?";
            cursor = db.rawQuery (query, new String[]{placetitle});
        } else if (language.equals ("en")) {
            String query = "select cityname from sightinfos where pnameen = ?";
            cursor = db.rawQuery (query, new String[]{placetitle});
        } else if (language.equals ("de")) {
            String query = "select cityname from sightinfos where pnamede = ?";
            cursor = db.rawQuery (query, new String[]{placetitle});
        }
        if (cursor.getCount ( ) <= 0) {
            cursor.close ( );
            return false;
        }
        cursor.close ( );
        return true;
    }


    public void addData(String keyword, String pnamehu,String pnameen,String pnamede,String infohu, String infoen, String infode) {
        String language = Paper.book ( ).read ("language");
        //Toast.makeText (PDescriptionActivity.this, "adddata!", Toast.LENGTH_SHORT).show ( );
        if (CheckIsDataAlreadyInDBorNot ( ) == false) {
            //perform inserting
           // Toast.makeText (PDescriptionActivity.this, "insert!", Toast.LENGTH_SHORT).show ( );
            boolean insertData = databaseHandler.addData1(keyword, pnamehu,pnameen,pnamede, infohu, infoen, infode);

            /*if (insertData)
                Toast.makeText (PDescriptionActivity.this, "Sikeres!", Toast.LENGTH_SHORT).show ( );
            else Toast.makeText (PDescriptionActivity.this, "Sikertelen !", Toast.LENGTH_SHORT).show ( );*/
        } else {
            //Toast.makeText (PDescriptionActivity.this, "Frissítés!", Toast.LENGTH_SHORT).show ( );
            db = databaseHandler.getWritableDatabase ( );
            ContentValues cv = new ContentValues ( );
            if (language.equals ("hu")) {
                cv.put (SIGHTINFO_HU, infohu);
                cv.put (PLACENAME_HU, pnamehu);
                db.update (TABLE1_NAME, cv, PLACENAME_HU + " = ?", new String[]{pnamehu});
            } else if (language.equals ("en")) {
                cv.put (SIGHTINFO_EN, infoen);
                cv.put (PLACENAME_EN, pnameen);
                db.update (TABLE1_NAME, cv, PLACENAME_EN + "= ?", new String[]{pnameen});
            } else if (language.equals ("de")) {
                cv.put (SIGHTINFO_DE, infode);
                cv.put (PLACENAME_DE, pnamede);
                db.update (TABLE1_NAME, cv, PLACENAME_DE + "= ?", new String[]{pnamede});
            }
        }
    }

}

