package com.example.laura.touristapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
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
import io.paperdb.Paper;
import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_DE;
import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_EN;
import static com.example.laura.touristapp.DatabaseHandler.CITYINFO_HU;
import static com.example.laura.touristapp.DatabaseHandler.CITYNAME;
import static com.example.laura.touristapp.DatabaseHandler.TABLE_NAME;

public class ContentActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        String language = Paper.book().read("language");
        super.attachBaseContext(LocaleHelper.onAttach(newBase, language));
    }

    private TextView txtWikiData;
    private TextView title;
    private TextView wikipedia;
    private ProgressBar progressBar;
    private TextToSpeech tts;

    DatabaseHandler databaseHandler = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Bundle extra = getIntent().getExtras();
        String keyword = extra.getString("key");
        final String language = Paper.book().read("language");

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        txtWikiData = (TextView) findViewById(R.id.txtWikiData);
        title = (TextView) findViewById(R.id.title);
        wikipedia = (TextView) findViewById(R.id.wikipedia);

        wikipedia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (language.equals("hu")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("https://hu.wikipedia.org/wiki/Kezd%C5%91lap"));
                    startActivity(intent);
                } else if (language.equals("en")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("https://en.wikipedia.org/wiki/Main_Page"));
                    startActivity(intent);
                } else if (language.equals("de")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("https://de.wikipedia.org/wiki/Wikipedia:Hauptseite"));
                    startActivity(intent);
                }
            }
        });


        if (language.equals("hu")) {

            // title.setText("A városról");
            String WIKIPEDIA_URL = "https://hu.wikipedia.org/w/api.php?action=query&titles=" +
                    keyword +
                    "&format=json&prop=extracts";

            // Start AsyncTask
            ContentActivity.FetchWikiDataAsync fetchWikiDataAsync = new ContentActivity.FetchWikiDataAsync();

            fetchWikiDataAsync.execute(WIKIPEDIA_URL);


        } else if (language.equals("en")) {
            // title.setText("About the city");
            String WIKIPEDIA_URL = "https://en.wikipedia.org/w/api.php?action=query&titles=" +
                    keyword +
                    "&prop=revisions&rvprop=content&format=json&prop=extracts";

            // Start AsyncTask
            ContentActivity.FetchWikiDataAsync fetchWikiDataAsync = new ContentActivity.FetchWikiDataAsync();

            fetchWikiDataAsync.execute(WIKIPEDIA_URL);


        } else if (language.equals("de")) {
            //title.setText("A városról");
            String WIKIPEDIA_URL = "https://de.wikipedia.org/w/api.php?action=query&titles=" +
                    keyword +
                    "&prop=revisions&rvprop=content&format=json&prop=extracts";

            // Start AsyncTask
            ContentActivity.FetchWikiDataAsync fetchWikiDataAsync = new ContentActivity.FetchWikiDataAsync();
            fetchWikiDataAsync.execute(WIKIPEDIA_URL);

        }

    }

    private class FetchWikiDataAsync extends AsyncTask<String, Void, String> {
        String text1;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
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

                if (keyword.equals("Eger") || keyword.equals("Eger_(Ungarn)")) //kész
                {
                    text1 = formattedData.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                            .replaceAll("<span id=\"Képek_Egerről\">(.+?)</span>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\">([\\s\\S]+?)</ul>", "")
                            .replaceAll("<span id=\"Éghajlat\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Klima\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Címer\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Population\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Twin_towns_–_sister_cities\">(.+?)</span>", "")
                            .replaceAll("Twin towns - sister cities", "")
                            .replaceAll("Eger is twinned with:", "")
                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "");
                } else if (keyword.equals("Keszthely")) //kész
                {
                    text1 = formattedData.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-packed center\">([\\s\\S]+?)</ul>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-packed\">([\\s\\S]+?)</ul>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                            .replaceAll("<span id=\"Image_gallery\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "")
                            .replaceAll("<span id=\"References\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                            .replaceAll("<span>Vorlage:Panorama/Wartung/Para4</span>", "")
                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "");
                } else if (keyword.equals("Pécs")) //kész
                {
                    text1 = formattedData.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Éghajlata\">(.+?)</span>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\"([\\s\\S]+?)</ul>", "")
                            .replaceAll("<span id=\"Színházak\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Egyéb_művelődési_intézmények\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Kulturális_események\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Templomok,_vallási_épületek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Egyéb_látnivalók\">(.+?)</span>", "")
                            .replaceAll("<b>Helyi üzemeltetésű rádióadók:</b>", "")
                            .replaceAll("<b>Korábban működő (megszűnt) helyi rádióadók:</b>", "")
                            .replaceAll("<span id=\"Híres_emberek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"További_információk\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Partnerváros\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Megjegyzések\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Klimatabelle\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Städtepartnerschaften\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Twin_towns_—_Sister_cities\"([\\s\\S]+?)</span>", "")
                            .replaceAll("Pécs is twinned with:", "")
                            .replaceAll("Pécs testvérvárosai és partnervárosai a következők:", "")
                            .replaceAll("<span id=\"Image_gallery\">(.+?)</span>", "");
                } else if (keyword.equals("Tihany")) //kész
                {
                    text1 = formattedData.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                            .replaceAll("<dl><dt>Korábban</dt></dl>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-packed center\">([\\s\\S]+?)</ul>", "")
                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Kultur_und_Sehenswürdigkeiten\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Persönlichkeiten\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                            .replaceAll("<span id=\"Bildergalerie\">(.+?)</span>", "")
                            .replaceAll("<span id=\"References\">(.+?)</span>", "");
                } else if (keyword.equals("Sopron")) //kész
                {
                    text1 = formattedData.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                            .replaceAll("<span id=\"Bevölkerungsentwicklung\">(.+?)</span>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\">([\\s\\S]+?)</ul>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional left\">([\\s\\S]+?)</ul>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional([\\s\\S]+?)</ul>", "")
                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                            .replaceAll("<span id=\"References\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                            .replaceAll("<span id=\"International_relations\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Galéria\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Twin_towns_—_sister_cities\">(.+?)</span>", "")
                            .replaceAll("Sopron is twinned with:", "")
                            .replaceAll("Sopron testvérvárosai a következők:", "")
                            .replaceAll("Profanbauten", "")
                            .replaceAll("Moderne Bauten", "")
                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "");

                } else if (keyword.equals("Szeged")) //kész
                {
                    text1 = formattedData.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                            .replaceAll("<span id=\"Városrészek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                            //.replaceAll("<sup>([\\s\\S]*?)</sup>", "")
                            //.replaceAll("<li><sup>1</sup>\" 2005-től Gyálarét, Kecskés- és Klebelsberg-telep valamint Szentmihály közös városrésze\"</li>", "")
                            //.replace("<li>² 1973-ig önálló település</li>", "")
                            //.replace("<li>³ régi nevén Hattyas-telep</li>", "")
                            //.replaceAll("<li><sup>([\\s\\S]+?)</li>", "")
                            //.replaceAll("<li> 1973-ig önálló település, a 19. században mezőváros; lásd: külső hivatkozások</li>", "")
                            //.replace("<li> 1973-ig önálló település, jelentős szerb kisebbséggel</li>", "")
                            //.replace("<li> 1973-ig önálló település</li>", "")
                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"References\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Források\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Munkanélküliség\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Largest_employers\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Main_sights\">(.+?)</span>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                            .replaceAll("<span id=\"A_településen_gyűjtött_népdalok\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Twin_towns_–_Sister_cities\">(.+?)</span>", "")
                            .replaceAll("Szeged is twinned with:", "")
                            .replaceAll("Notes", "")
                            .replaceAll("<span id=\"Stadtteile_und_Bezirke\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Städtepartnerschaften\">(.+?)</span>", "")
                            .replaceAll("A táblázat és a szöveg között eltérés előfordulhat!", "")
                            .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "");

                } else if (keyword.equals("Veszprém")) //kész
                {
                    text1 = formattedData.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                            .replaceAll("<span id=\"International_relations\">(.+?)</span>", "")
                            //.replaceAll("<span id='Twin_towns_-_Sister_cities'>(.+?)</span>","")
                            .replaceAll("<span id=\"Twin_towns_—_Sister_cities\">(.+?)</span>", "")
                            .replaceAll("Veszprém is twinned with:", "")
                            .replaceAll("<span id=\"Címere\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Látnivalók\">(.+?)</span>", "")
                            .replaceAll("<span id=\"A_várnegyedben\">(.+?)</span>", "")
                            .replaceAll("<span id=\"A_várnegyeden_kívül\">(.+?)</span>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\"([\\s\\S]+?)</ul>", "")
                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                            .replaceAll("Lásd még: Veszprém címere", "")
                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Kerékpársport\">(.+?)</span>", "")
                            .replaceAll("<span id=\"A_városban_éltek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                            .replaceAll("<span id=\"References\">(.+?)</span>", "");
                }

                txtWikiData.setText(Html.fromHtml(text1));
                ContentValues values = new ContentValues();
                if (language.equals("hu")) {
                    String infohu = formattedData;
                    values.put(CITYINFO_HU, infohu);
                    addData(keyword, infohu, null, null);
                } else if (language.equals("en")) {
                    String infoen = formattedData;
                    values.put(CITYINFO_EN, infoen);
                    addData(keyword, null, infoen, null);
                } else if (language.equals("de")) {
                    String infode = formattedData;
                    values.put(CITYINFO_DE, infode);
                    addData(keyword, null, null, infode);
                }

            } catch (Exception e) { //ha nincs wifi*/
                progressBar.setVisibility(View.GONE);
                db = databaseHandler.getReadableDatabase();
                if (language.equals("hu")) {
                    Cursor cursor = db.rawQuery(" SELECT "+CITYINFO_HU+" FROM " + TABLE_NAME + " WHERE " + CITYNAME + " = ?", new String[]{keyword});

                    try {
                        if (cursor.moveToFirst()) {
                            do {
                                String infohu = cursor.getString(cursor.getColumnIndex("cityinfohu")); // Here you can get data from table and stored in string if it has only one string.
                                if (keyword.equals("Eger")) //kész
                                {
                                    text1 = infohu.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Képek_Egerről\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"Éghajlat\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Klima\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Population\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_–_sister_cities\">(.+?)</span>", "")
                                            .replaceAll("Twin towns - sister cities", "")
                                            .replaceAll("Eger is twinned with:", "")
                                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "");
                                } else if (keyword.equals("Keszthely")) //kész
                                {
                                    text1 = infohu.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-packed center\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-packed\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"Image_gallery\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"References\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                            .replaceAll("<span>Vorlage:Panorama/Wartung/Para4</span>", "")
                                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "");
                                } else if (keyword.equals("Pécs")) //kész
                                {
                                    text1 = infohu.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Éghajlata\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Színházak\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"Egyéb_művelődési_intézmények\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Kulturális_események\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Templomok,_vallási_épületek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Egyéb_látnivalók\">(.+?)</span>", "")
                                            .replaceAll("<b>Helyi üzemeltetésű rádióadók:</b>", "")
                                            .replaceAll("<b>Korábban működő (megszűnt) helyi rádióadók:</b>", "")
                                            .replaceAll("<span id=\"Híres_emberek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"További_információk\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Partnerváros\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Megjegyzések\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Klimatabelle\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Städtepartnerschaften\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_—_Sister_cities\"([\\s\\S]+?)</span>", "")
                                            .replaceAll("Pécs is twinned with:", "")
                                            .replaceAll("Pécs testvérvárosai és partnervárosai a következők:", "")
                                            .replaceAll("<span id=\"Image_gallery\">(.+?)</span>", "");
                                } else if (keyword.equals("Tihany")) //kész
                                {
                                    text1 = infohu.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<dl><dt>Korábban</dt></dl>", "")
                                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Kultur_und_Sehenswürdigkeiten\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Persönlichkeiten\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-packed center\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"Bildergalerie\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"References\">(.+?)</span>", "");
                                } else if (keyword.equals("Sopron")) //kész
                                {
                                    text1 = infohu.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Bevölkerungsentwicklung\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional left\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"References\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"International_relations\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Galéria\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_—_sister_cities\">(.+?)</span>", "")
                                            .replaceAll("Sopron is twinned with:", "")
                                            .replaceAll("Sopron testvérvárosai a következők:", "")
                                            .replaceAll("Profanbauten", "")
                                            .replaceAll("Moderne Bauten", "")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "");

                                } else if (keyword.equals("Szeged")) //kész
                                {
                                    text1 = infohu.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Városrészek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                           // .replaceAll("<sup ([\\s\\S]+?)</sup>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"References\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Források\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Munkanélküliség\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Largest_employers\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Main_sights\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"A_településen_gyűjtött_népdalok\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_–_Sister_cities\">(.+?)</span>", "")
                                            .replaceAll("Szeged is twinned with:", "")
                                            .replaceAll("Notes", "")
                                            .replaceAll("<span id=\"Stadtteile_und_Bezirke\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Städtepartnerschaften\">(.+?)</span>", "")
                                            .replaceAll("A táblázat és a szöveg között eltérés előfordulhat!", "")
                                            .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "");

                                } else if (keyword.equals("Veszprém")) //kész
                                {
                                    text1 = infohu.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"International_relations\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_—_Sister_cities\">(.+?)</span>", "")
                                            .replaceAll("Veszprém is twinned with:", "")
                                            .replaceAll("<span id=\"Címere\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Látnivalók\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"A_várnegyedben\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"A_várnegyeden_kívül\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("Lásd még: Veszprém címere", "")
                                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Kerékpársport\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"A_városban_éltek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"References\">(.+?)</span>", "");
                                }
                                txtWikiData.setText(Html.fromHtml(text1));


                            } while (cursor.moveToNext());
                        }
                        else {
                            Toast.makeText(ContentActivity.this, "Kapcsolja be a wifi-t!", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch(NullPointerException e1){
                        Toast.makeText(ContentActivity.this, "Kapcsolja be a wifi-t!", Toast.LENGTH_LONG).show();
                    }
                    finally {
                        cursor.close();
                        db.close();
                    }

                } else if (language.equals("en")) {
                    Cursor cursor = db.rawQuery(" SELECT "+CITYINFO_EN+" FROM " + TABLE_NAME + " WHERE " + CITYNAME + " = ? ", new String[]{keyword});
                    try {
                        if (cursor.moveToFirst()) {
                            do {
                                String infoen = cursor.getString(cursor.getColumnIndex("cityinfoen")); // Here you can get data from table and stored in string if it has only one string.
                                if (keyword.equals("Eger")) //kész
                                {
                                    text1 = infoen.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Képek_Egerről\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"Éghajlat\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Klima\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Population\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_–_sister_cities\">(.+?)</span>", "")
                                            .replaceAll("Twin towns - sister cities", "")
                                            .replaceAll("Eger is twinned with:", "")
                                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "");
                                } else if (keyword.equals("Keszthely")) //kész
                                {
                                    text1 = infoen.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-packed center\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-packed\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"Image_gallery\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"References\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                            .replaceAll("<span>Vorlage:Panorama/Wartung/Para4</span>", "")
                                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "");
                                } else if (keyword.equals("Pécs")) //kész
                                {
                                    text1 = infoen.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Éghajlata\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Színházak\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"Egyéb_művelődési_intézmények\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Kulturális_események\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Templomok,_vallási_épületek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Egyéb_látnivalók\">(.+?)</span>", "")
                                            .replaceAll("<b>Helyi üzemeltetésű rádióadók:</b>", "")
                                            .replaceAll("<b>Korábban működő (megszűnt) helyi rádióadók:</b>", "")
                                            .replaceAll("<span id=\"Híres_emberek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"További_információk\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Partnerváros\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Megjegyzések\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Klimatabelle\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Städtepartnerschaften\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_—_Sister_cities\"([\\s\\S]+?)</span>", "")
                                            .replaceAll("Pécs is twinned with:", "")
                                            .replaceAll("Pécs testvérvárosai és partnervárosai a következők:", "")
                                            .replaceAll("<span id=\"Image_gallery\">(.+?)</span>", "");
                                } else if (keyword.equals("Tihany")) //kész
                                {
                                    text1 = infoen.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<dl><dt>Korábban</dt></dl>", "")
                                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Kultur_und_Sehenswürdigkeiten\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Persönlichkeiten\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-packed center\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"Bildergalerie\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"References\">(.+?)</span>", "");
                                } else if (keyword.equals("Sopron")) //kész
                                {
                                    text1 = infoen.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Bevölkerungsentwicklung\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional left\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"References\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"International_relations\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Galéria\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_—_sister_cities\">(.+?)</span>", "")
                                            .replaceAll("Sopron is twinned with:", "")
                                            .replaceAll("Sopron testvérvárosai a következők:", "")
                                            .replaceAll("Profanbauten", "")
                                            .replaceAll("Moderne Bauten", "")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "");

                                } else if (keyword.equals("Szeged")) //kész
                                {
                                    text1 = infoen.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Városrészek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                            //.replaceAll("<sup ([\\s\\S]+?)</sup>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"References\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Források\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Munkanélküliség\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Largest_employers\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Main_sights\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"A_településen_gyűjtött_népdalok\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_–_Sister_cities\">(.+?)</span>", "")
                                            .replaceAll("Szeged is twinned with:", "")
                                            .replaceAll("Notes", "")
                                            .replaceAll("<span id=\"Stadtteile_und_Bezirke\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Städtepartnerschaften\">(.+?)</span>", "")
                                            .replaceAll("A táblázat és a szöveg között eltérés előfordulhat!", "")
                                            .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "");

                                } else if (keyword.equals("Veszprém")) //kész
                                {
                                    text1 = infoen.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"International_relations\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_—_Sister_cities\">(.+?)</span>", "")
                                            .replaceAll("Veszprém is twinned with:", "")
                                            .replaceAll("<span id=\"Címere\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Látnivalók\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"A_várnegyedben\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"A_várnegyeden_kívül\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("Lásd még: Veszprém címere", "")
                                            .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Kerékpársport\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"A_városban_éltek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"References\">(.+?)</span>", "");
                                }
                                txtWikiData.setText(Html.fromHtml(text1));

                            } while (cursor.moveToNext());
                        }
                        else {
                            Toast.makeText(ContentActivity.this, "Turn wifi on!", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch(NullPointerException e1){
                        Toast.makeText(ContentActivity.this, "Turn wifi on!", Toast.LENGTH_LONG).show();
                    }
                    finally {
                        cursor.close();
                    }

                } else if (language.equals("de")) {
                    if (keyword.equals("Eger_(Ungarn)")) //kész
                    {
                        String keyword1 = "Eger";
                        Cursor cursor = db.rawQuery(" SELECT "+CITYINFO_DE+" FROM " + TABLE_NAME + " WHERE " + CITYNAME + " = ? /*AND " + CITYINFO_HU + " !=null OR "+ CITYINFO_EN +" !=null OR "+ CITYINFO_DE +" !=null*/", new String[]{keyword1});
                        try {
                            if (cursor.moveToFirst()) {
                                do {
                                    String infode = cursor.getString(cursor.getColumnIndex("cityinfode")); // Here you can get data from table and stored in string if it has only one string.

                                    text1 = infode.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                            .replaceAll("<span id=\"Képek_Egerről\">(.+?)</span>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<ul class=\"gallery mw-gallery-traditional center\">([\\s\\S]+?)</ul>", "")
                                            .replaceAll("<span id=\"Éghajlat\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Klima\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Population\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                            .replaceAll("<span id=\"Twin_towns_–_sister_cities\">(.+?)</span>", "")
                                            .replaceAll("Twin towns - sister cities", "")
                                            .replaceAll("Eger is twinned with:", "")
                                            .replaceAll("<span id=\"Climate\">(.+?)</span>", "");

                                    txtWikiData.setText(Html.fromHtml(text1));

                                } while (cursor.moveToNext());
                            }
                            else
                            {
                                Toast.makeText(ContentActivity.this, "Schalten Sie Ihr  Wlan ein!", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch(NullPointerException e1){
                            Toast.makeText(ContentActivity.this, "Schalten Sie Ihr  Wlan ein!", Toast.LENGTH_LONG).show();
                        }
                        finally {
                            cursor.close();
                            db.close();
                        }
                        }
                    else {
                        Cursor cursor = db.rawQuery(" SELECT "+CITYINFO_DE+" FROM " + TABLE_NAME + " WHERE " + CITYNAME + " = ? "/* + CITYINFO_HU + " !=null OR "+ CITYINFO_EN +" !=null OR "+ CITYINFO_DE +" !=null"*/, new String[]{keyword});

                        try {
                            if (cursor.moveToFirst()) {
                                do {
                                    String infode = cursor.getString(cursor.getColumnIndex("cityinfode")); // Here you can get data from table and stored in string if it has only one string.
                                    if (keyword.equals("Keszthely")) //kész
                                    {
                                        text1 = infode.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                                .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Image_gallery\">(.+?)</span>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-packed center\">([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-traditional\">([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-packed\">([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Climate\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"References\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                                .replaceAll("<span>Vorlage:Panorama/Wartung/Para4</span>", "")
                                                .replaceAll("<span id=\"Climate\">(.+?)</span>", "");
                                    } else if (keyword.equals("Pécs")) //kész
                                    {
                                        text1 = infode.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                                .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Climate\">(.+?)</span>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-traditional center\"([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<span id=\"Éghajlata\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Színházak\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Egyéb_művelődési_intézmények\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Kulturális_események\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Templomok,_vallási_épületek\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Egyéb_látnivalók\">(.+?)</span>", "")
                                                .replaceAll("<b>Helyi üzemeltetésű rádióadók:</b>", "")
                                                .replaceAll("<b>Korábban működő (megszűnt) helyi rádióadók:</b>", "")
                                                .replaceAll("<span id=\"Híres_emberek\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"További_információk\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Partnerváros\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Megjegyzések\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Klimatabelle\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Städtepartnerschaften\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Twin_towns_—_Sister_cities\"([\\s\\S]+?)</span>", "")
                                                .replaceAll("Pécs is twinned with:", "")
                                                .replaceAll("Pécs testvérvárosai és partnervárosai a következők:", "")
                                                .replaceAll("<span id=\"Image_gallery\">(.+?)</span>", "");
                                    } else if (keyword.equals("Tihany")) //kész
                                    {
                                        text1 = infode.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                                .replaceAll("<dl><dt>Korábban</dt></dl>", "")
                                                .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Kultur_und_Sehenswürdigkeiten\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Persönlichkeiten\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-packed center\">([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<span id=\"Bildergalerie\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"References\">(.+?)</span>", "");
                                    } else if (keyword.equals("Sopron")) //kész
                                    {
                                        text1 = infode.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                                .replaceAll("<span id=\"Bevölkerungsentwicklung\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-traditional center\">([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-traditional left\">([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-traditional([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<span id=\"References\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"International_relations\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Galéria\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Twin_towns_—_sister_cities\">(.+?)</span>", "")
                                                .replaceAll("Sopron is twinned with:", "")
                                                .replaceAll("Sopron testvérvárosai a következők:", "")
                                                .replaceAll("Profanbauten", "")
                                                .replaceAll("Moderne Bauten", "")
                                                .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "");

                                    } else if (keyword.equals("Szeged")) //kész
                                    {
                                        text1 = infode.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                                .replaceAll("<span id=\"Városrészek\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                               // .replaceAll("<sup ([\\s\\S]+?)</sup>", "")
                                                .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"References\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Források\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Munkanélküliség\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Largest_employers\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Main_sights\">(.+?)</span>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<span id=\"A_településen_gyűjtött_népdalok\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Nemzetközi_kapcsolatok\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Twin_towns_–_Sister_cities\">(.+?)</span>", "")
                                                .replaceAll("Szeged is twinned with:", "")
                                                .replaceAll("Notes", "")
                                                .replaceAll("<span id=\"Stadtteile_und_Bezirke\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Städtepartnerschaften\">(.+?)</span>", "")
                                                .replaceAll("A táblázat és a szöveg között eltérés előfordulhat!", "")
                                                .replaceAll("<span id=\"Testvérváros\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "");

                                    } else if (keyword.equals("Veszprém")) //kész
                                    {
                                        text1 = infode.replaceAll("<li>", "\n•").replaceAll("</li>", "<br>")
                                                .replaceAll("<span id=\"Einzelnachweise\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"International_relations\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Twin_towns_—_Sister_cities\">(.+?)</span>", "")
                                                .replaceAll("Veszprém is twinned with:", "")
                                                .replaceAll("<span id=\"Címere\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Látnivalók\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"A_várnegyedben\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"A_várnegyeden_kívül\">(.+?)</span>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-traditional center\"([\\s\\S]+?)</ul>", "")
                                                .replaceAll("<ul class=\"gallery mw-gallery-traditional\"([\\s\\S]+?)</ul>", "")
                                                .replaceAll("Lásd még: Veszprém címere", "")
                                                .replaceAll("<span id=\"Gallery\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Kerékpársport\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"A_városban_éltek\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Képgaléria\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"Jegyzetek\">(.+?)</span>", "")
                                                .replaceAll("<span id=\"References\">(.+?)</span>", "");
                                    }
                                    txtWikiData.setText(Html.fromHtml(text1));

                                } while (cursor.moveToNext());
                            }
                            else {
                                Toast.makeText(ContentActivity.this, "Schalten Sie Ihr  Wlan ein!", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch(NullPointerException e1){
                            Toast.makeText(ContentActivity.this, "Schalten Sie Ihr  Wlan ein!", Toast.LENGTH_LONG).show();
                        }
                        finally {
                            cursor.close();
                            db.close();
                        }
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


        public boolean CheckIsDataAlreadyInDBorNot() {
            Bundle extra = getIntent().getExtras();
            String keyword = extra.getString("key");
            SQLiteDatabase db;
            db = databaseHandler.getReadableDatabase();
            if (keyword.equals("Eger_(Ungarn)")) {
                String keyword1 = "Eger";
                String query = "select cityname from cityinfos where cityname = ?";
                Cursor cursor = db.rawQuery(query, new String[]{keyword1});

                if (cursor.getCount() <= 0) {
                    cursor.close();
                    return false;
                }
                cursor.close();
                return true;
            } else {
                String query = "select cityname from cityinfos where cityname = ?";
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
            if (CheckIsDataAlreadyInDBorNot() == false) {
                //perform inserting
                //Toast.makeText(ContentActivity.this, "insert!", Toast.LENGTH_SHORT).show();
                boolean insertData = databaseHandler.addData(keyword, infohu, infoen, infode);

            /*if (insertData)
                Toast.makeText(ContentActivity.this, "Sikeres!", Toast.LENGTH_SHORT).show();
            else Toast.makeText(ContentActivity.this, "Sikertelen !", Toast.LENGTH_SHORT).show();*/
            } else {
                //Toast.makeText(ContentActivity.this, "Frissítés!", Toast.LENGTH_SHORT).show();
                db = databaseHandler.getWritableDatabase();
                ContentValues cv = new ContentValues();
                if (language.equals("hu")) {
                    cv.put(CITYINFO_HU, infohu);
                    db.update(TABLE_NAME, cv, CITYNAME + " = ?", new String[]{keyword});
                } else if (language.equals("en")) {
                    cv.put(CITYINFO_EN, infoen);
                    db.update(TABLE_NAME, cv, CITYNAME + "= ?", new String[]{keyword});
                } else if (language.equals("de")) {
                    if (keyword.equals("Eger_(Ungarn)")) {
                        String keyword1 = "Eger";
                        cv.put(CITYINFO_DE, infode);
                        db.update(TABLE_NAME, cv, CITYNAME + "= ?", new String[]{keyword1});
                    } else {
                        cv.put(CITYINFO_DE, infode);
                        db.update(TABLE_NAME, cv, CITYNAME + "= ?", new String[]{keyword});
                    }
                }
            }

        }
}