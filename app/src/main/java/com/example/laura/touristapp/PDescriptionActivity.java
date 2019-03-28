package com.example.laura.touristapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
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
import java.util.Iterator;

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
    private TextView webpage;

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


        if (keyword.equals ("Keszthely")) { //getResources().getString(R.string.keszthely)
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
        else if (keyword.equals ("Eger")) {
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
        else if (keyword.equals ("Pécs")) {
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
        else if (keyword.equals ("Sopron")) {
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
                //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                //intent.putExtra("key", placetitle);
                //String address = txtData1.getText().toString();
                //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword); //város neve
                intent.putExtra("key1", placetitle); //látványosság neve
               // intent.putExtra("key2", keyword1); //url neve
                startActivity(intent);
            }
        });
    }
    SQLiteDatabase db;

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        String words;
        //String words1;
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
            String url = urls[0];
            try {
                Document doc = Jsoup.connect (url).get ();
                //Elements divs=doc.select("div.content-text");
                //Element paragraphs=doc.select("div.content-text p").first(); //cim

                //város feltételek
                if (keyword.equals ("Keszthely")){

                    Elements divs = doc.select ("div.content-text");

                //for(Element p : paragraphs){
                //  words1=paragraphs.text();
                // }
                for (Element d : divs) {
                    words = d.text ( );
                    String text = d.html ( );
                    text1 = text.replaceAll ("</p>", "\n");
                    // System.out.println(Html.fromHtml(text1));
                    //String[] w=words.split("<br>");
                    /*for(String str:w)
                    {
                        System.out.println(str);
                    }*/
                }
            }
                else if (keyword.equals ("Pécs")) {
                    Elements div = doc.select ("div#cnt_lead");
                    Elements divs = doc.select ("div#cnt_text");
                    //for(Element p : paragraphs){
                    //  words1=paragraphs.text();
                    // }
                    for (Element d : div) {
                        words = d.text ( );
                        String text = d.html ( );
                        text1 = text.replaceAll ("</p>", "\n").replaceAll ("<h2>", "<p>").replaceAll ("</h2>", "</p>");
                        // System.out.println(Html.fromHtml(text1));
                        //String[] w=words.split("<br>");
                    /*for(String str:w)
                    {
                        System.out.println(str);
                    }*/
                    }
                    for (Element p : divs) {
                        words = p.text ( );
                        String text = p.html ( );
                        text2 = text.replaceAll ("</p>", "\n");
                        // System.out.println(Html.fromHtml(text1));
                        //String[] w=words.split("<br>");
                    /*for(String str:w)
                    {
                        System.out.println(str);
                    }*/
                    }
                }

                else if (keyword.equals ("Eger")) {

                    Elements article = doc.select ("article.entry.bottom-space-md.clearfix");
                    for (Element d : article) {
                        words = d.text ();
                        String text = d.html ( );
                        text1 = text.replaceAll ("</p>", "\n");
                        // System.out.println(Html.fromHtml(text1));
                        //String[] w=words.split("<br>");
                    /*for(String str:w)
                    {
                        System.out.println(str);
                    }*/
                    }
                }
                else if (keyword.equals ("Sopron")) {
                    Elements div = doc.select ("div#cnt_lead");
                    Elements divs = doc.select ("div#cnt_text");

                    //for(Element p : paragraphs){
                    //  words1=paragraphs.text();
                    // }
                    for (Element d : div) {
                        words = d.text ();
                        String text = d.html ( );
                        text1 = text.replaceAll ("</p>", "\n").replaceAll ("<h2>", "<p>").replaceAll ("</h2>", "</p>");
                        // System.out.println(Html.fromHtml(text1));
                        //String[] w=words.split("<br>");
                    /*for(String str:w)
                    {
                        System.out.println(str);
                    }*/
                    }
                    for (Element p : divs) {
                        words = p.text ();
                        String text = p.html ( );
                        text2 = text.replaceAll ("</p>", "\n");
                        // System.out.println(Html.fromHtml(text1));
                        //String[] w=words.split("<br>");
                    /*for(String str:w)
                    {
                        System.out.println(str);
                    }*/
                    }

                }
                else if (keyword.equals ("Szeged")) {
                    Elements div = doc.select ("div.single-content");
                    for (Element p : div) {
                        words = p.text ();
                        String text = p.html ( );
                        text1 = text.replaceAll ("</p>", "\n").replaceAll ("<style>(.+?)</style>", "\n").replaceAll ("<a class=\"synwed-social-button(.+?)>(.+?)</a>", "");
                        // System.out.println(Html.fromHtml(text1));
                        //String[] w=words.split("<br>");
                    /*for(String str:w)
                    {
                        System.out.println(str);
                    }*/
                    }
                }
                else if (keyword.equals ("Veszprém")) {
                    Elements divs = doc.select ("div.list-item-details-left p");
                    Elements div = doc.select ("div.tab-pane.detail.fade.active.in");

                    for (Element d : divs) {
                        words = d.text ();
                        String text = d.html ( );
                        text1 = text.replaceAll ("</p>", "\n").replaceAll ("<h2>", "<p>").replaceAll ("</h2>", "</p>").replaceAll("<span class=\"expand-text\">(.+?)</span>","").replaceAll("<span class=\"collapse-text\">(.+?)</span>","").replaceAll("Tekintse meg a helyszínt gömbpanorámás felvételeinken is!(.+?)","").replaceAll("<div class=\"row\">(.+?)</div>","").replaceAll("<div class=\"collapse\">(.+?)</div>","").replaceAll("Tekintse meg a helyszínt gömbpanorámás felvételeinken is!","");
                        // System.out.println(Html.fromHtml(text1));
                        //String[] w=words.split("<br>");
                    /*for(String str:w)
                    {
                        System.out.println(str);
                    }*/
                    }
                    for (Element p : div) {
                        words = p.text ();
                        String text = p.html ( );
                        text2 = text.replaceAll ("</p>", "\n");
                        // System.out.println(Html.fromHtml(text1));
                        //String[] w=words.split("<br>");
                    /*for(String str:w)
                    {
                        System.out.println(str);
                    }*/
                    }
                }
                else if (keyword.equals ("Tihany")) {
                    Elements divs = doc.select ("div#asamodule_search_results span");
                    for (Element d : divs) {
                        words = d.text ();
                        String text = d.html ( );
                        text1 = text.replaceAll ("</p>", "\n");
                        // System.out.println(Html.fromHtml(text1));
                        //String[] w=words.split("<br>");
                    /*for(String str:w)
                    {
                        System.out.println(str);
                    }*/
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
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            if(text2!=null){ text1=text1+text2;
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
                    //updateIfExistsElseInsert(keyword,infohu,null,null);
                    // String infohu = Html.fromHtml(formattedData, Html.FROM_HTML_MODE_LEGACY).toString();
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
                Toast.makeText (PDescriptionActivity.this, "Wifi", Toast.LENGTH_SHORT).show ( );
                progressBar.setVisibility(View.GONE);
                db = databaseHandler.getWritableDatabase();
                if (language.equals("hu")) {
                    //Cursor cursor = db.query(DatabaseHandler.TABLE_NAME, new String[] { "cityname","cityinfohu","cityinfoen","cityinfode" },null, null, null, null, null);
                    Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE1_NAME + " WHERE " + PLACENAME_HU + " = ? ", new String[]{placetitle});
                    if (cursor.moveToFirst()) {
                        do {
                            String infohu = cursor.getString(cursor.getColumnIndex("sightinfohu")); // Here you can get data from table and stored in string if it has only one string.
                            text1 = infohu.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>");
                            txtData.setText(Html.fromHtml(text1));


                        } while (cursor.moveToNext());
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }

                } else if (language.equals("en")) {
                    Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE1_NAME + " WHERE " + PLACENAME_EN + " = ? ", new String[]{placetitle});
                    if (cursor.moveToFirst()) {
                        do {
                            String infoen = cursor.getString(cursor.getColumnIndex("sightinfoen")); // Here you can get data from table and stored in string if it has only one string.
                            text1 = infoen.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>");
                            txtData.setText(Html.fromHtml(text1));

                        } while (cursor.moveToNext());
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }

                } else if (language.equals("de")) {
                    Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE1_NAME + " WHERE " + PLACENAME_DE + " = ? ", new String[]{placetitle});
                    if (cursor.moveToFirst()) {
                        do {
                            String infode = cursor.getString(cursor.getColumnIndex("sightinfode")); // Here you can get data from table and stored in string if it has only one string.
                            text1 = infode.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>");
                            txtData.setText(Html.fromHtml(text1));

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
    public boolean CheckIsDataAlreadyInDBorNot() {
        String language = Paper.book ( ).read ("language");
        Bundle extra = getIntent ( ).getExtras ( );
        String placetitle = extra.getString ("key1");
        // String keyword = extra.getString("key");
        SQLiteDatabase db;
        //Cursor c = null;
        db = databaseHandler.getReadableDatabase ( );
        //try {
        Toast.makeText (PDescriptionActivity.this, "ellenőrzés!", Toast.LENGTH_SHORT).show ( );
        //db.getReadableDatabase();
        Cursor cursor = null;
        if (language.equals ("hu") /*||  language.equals ("en") ||  language.equals ("de")*/) {
            String query = "select cityname from sightinfos where pnamehu = ?";
            cursor = db.rawQuery (query, new String[]{placetitle});
            /*if (cursor.getCount ( ) <= 0) {
                cursor.close ( );
                return false;
            }
            cursor.close ( );
            return true;*/
        } else if (language.equals ("en")) {
            String query = "select cityname from sightinfos where pnameen = ?";
            cursor = db.rawQuery (query, new String[]{placetitle});/*
            if (cursor.getCount ( ) <= 0) {
                cursor.close ( );
                return false;
            }
            cursor.close ( );
            return true;*/
        } else if (language.equals ("de")) {
            String query = "select cityname from sightinfos where pnamede = ?";
            cursor = db.rawQuery (query, new String[]{placetitle});
            /*if (cursor.getCount ( ) <= 0) {
                cursor.close ( );
                return false;
            }
            cursor.close ( );
            return true;*/
        }
        //return
        //String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        // Cursor cursor = db.rawQuery(query, new String[]{placetitle});
        if (cursor.getCount ( ) <= 0) {
            cursor.close ( );
            return false;
        }
        cursor.close ( );
        return true;
    }


    public void addData(String keyword, String pnamehu,String pnameen,String pnamede,String infohu, String infoen, String infode) {
        String language = Paper.book ( ).read ("language");
        Toast.makeText (PDescriptionActivity.this, "adddata!", Toast.LENGTH_SHORT).show ( );
        if (CheckIsDataAlreadyInDBorNot ( ) == false) {
            //perform inserting
            Toast.makeText (PDescriptionActivity.this, "insert!", Toast.LENGTH_SHORT).show ( );
            boolean insertData = databaseHandler.addData1(keyword, pnamehu,pnameen,pnamede, infohu, infoen, infode);

            if (insertData)
                Toast.makeText (PDescriptionActivity.this, "Sikeres!", Toast.LENGTH_SHORT).show ( );
            else Toast.makeText (PDescriptionActivity.this, "Sikertelen !", Toast.LENGTH_SHORT).show ( );
        } else {
            Toast.makeText (PDescriptionActivity.this, "Frissítés!", Toast.LENGTH_SHORT).show ( );
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

