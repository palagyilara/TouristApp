package com.example.laura.touristapp;

//import android.content.Intent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
//import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class ContentActivity extends AppCompatActivity implements TextToSpeech.OnInitListener,View.OnClickListener {

    //private Button btnFetchData;
    private TextView txtWikiData;
    private TextView title;
    private ImageButton speakbtn;
    private ProgressBar progressBar;
    private int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech myTTS;
    private TextToSpeech tts;
    //private Spinner spinner;
    //private static final String[] paths = {"Magyar","English","Deutsch"};
   // private static final String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);


        Bundle extra = getIntent().getExtras();
        //String text = extra.getString(MainActivity.TAG_TEXT);
        final String keyword = extra.getString("key");
       // final String[] language = extra.getStringArray("lang");
        final String lang = extra.getString("key1");
        //String speech = null;


        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //etxSearch = (EditText) findViewById(R.id.etxSearch);
        txtWikiData = (TextView) findViewById(R.id.txtWikiData);
        title=(TextView) findViewById(R.id.title);
        speakbtn=(ImageButton) findViewById(R.id.speakbtn);
        //TextView txtWikiData = (TextView) findViewById(R.id.txtWikiData);
       // TextView title = (TextView) findViewById(R.id.btnFetchData);

        //txtWikiData.setText(text);
//        title.setText(keyword);
    if(lang.equals("Magyar")){

        title.setText("A városról");
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
    }
      else if(lang.equals("English")){
        title.setText("About the city");
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
    }
     else if(lang.equals("Deutsch")){
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

    private void speak(String text){

            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
       /* else{
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }*/
    }
   /* @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }*/

    private class FetchWikiDataAsync extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
           // Toast.makeText(ContentActivity.this, "Egy pillanat...", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected String doInBackground(String[] params)
        {
            try
            {
                String sURL = params[0];

                URL url = new URL(sURL);        // Convert String URL to java.net.URL
                // Connection: to Wikipedia API
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(line);
                }

                String wikiData = stringBuilder.toString();

                // Parse JSON Data
                String formattedData = parseJSONData(wikiData);

                //openContentActivity(formattedData);
                return formattedData;


            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String formattedData)
        {
            try {
                super.onPostExecute(formattedData);
                progressBar.setVisibility(View.GONE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    // HTML Data
                    txtWikiData.setText(Html.fromHtml
                            (formattedData, Html.FROM_HTML_MODE_LEGACY));

                } else {
                    // HTML Data
                    txtWikiData.setText(Html.fromHtml(formattedData));
                }
            }catch (Exception e)
            {
                Toast.makeText(ContentActivity.this, "Wifi!", Toast.LENGTH_LONG).show();
            }

        }

    }

    private String parseJSONData(String wikiData)
    {
        try
        {
            // Convert String JSON (wikiData) to JSON Object
            JSONObject rootJSON = new JSONObject(wikiData);
            JSONObject query = rootJSON.getJSONObject("query");
            JSONObject pages = query.getJSONObject("pages");
            JSONObject number = pages.getJSONObject(pages.keys().next());
            String formattedData = number.getString("extract");


            return formattedData;


        }
        catch (JSONException json)
        {
            json.printStackTrace();
        }

        return null;
    }


}
