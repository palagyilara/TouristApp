package com.example.laura.touristapp;

import android.content.DialogInterface;
import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.text.Html;
import android.view.View;
import android.widget.Button;
/*import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;*/


public class MainActivity extends AppCompatActivity {
   // public static final String TAG_KEYWORD = "com.example.laura.touristapp.TAG_KEYWORD";
 //public static final String TAG_TEXT = "com.example.laura.touristapp.TAG_TEXT";

    private Button btnFetchData;
   // private TextView txtWikiData;
    //private ProgressBar progressBar;
   // private EditText etxSearch;

    // Http Request Methods
    // http://www.restapitutorial.com/lessons/httpmethods.html

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //etxSearch = (EditText) findViewById(R.id.etxSearch);
       // txtWikiData = (TextView) findViewById(R.id.txtWikiData);

        btnFetchData = (Button) findViewById(R.id.btnFetchData);
        btnFetchData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //openContentActivity(null);
                final String[] language = {"Magyar", "English", "Deutsch"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Nyelv:");
                builder.setItems(language, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        String keyword = btnFetchData.getText().toString();
                        if ("Magyar".equals(language[which])){
							
                            String lang= language[0];
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", lang);
                            startActivity(intent);
                        }
                        else if ("English".equals(language[which])){
                            
                            String lang= language[1];
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", lang);
                            startActivity(intent);
                        }
                        else if ("Deutsch".equals(language[which])){
                           
                            String lang= language[2];
                            intent.putExtra("key", keyword);
                            intent.putExtra("key1", lang);
                            startActivity(intent);
                        }
                    }
                });
                builder.show();




/*
                String WIKIPEDIA_URL = "https://hu.wikipedia.org/w/api.php?action=query&titles=" +
            keyword +
                        "&prop=revisions&rvprop=content&format=json&prop=extracts";

                //megvizsgálni h a keyword része e magyaro. városainak
                 if(keyword.equals("Keszthely") ||  keyword.equals("Pécs") ) {
                     // Start AsyncTask
                     FetchWikiDataAsync fetchWikiDataAsync = new FetchWikiDataAsync();
                    fetchWikiDataAsync.execute(WIKIPEDIA_URL);
                    // openContentActivity(WIKIPEDIA_URL);

                 }
                else //hibaüzenet
               {
                //progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Kérem Keszthely-t vagy Pécset válassza!", Toast.LENGTH_SHORT).show();
                }

*/
            }

        });
    }
/*
    public String openContentActivity(String WIKIPEDIA_URL) {
        //try {
        FetchWikiDataAsync fetchWikiDataAsync = new FetchWikiDataAsync();
        fetchWikiDataAsync.execute(WIKIPEDIA_URL);


          // txtWikiData.setText(formattedData);
            //progressBar = (ProgressBar)findViewById(R.id.progressbar);
            /* EditText etxSearch = (EditText) findViewById(R.id.etxSearch);
            //String text = etxSearch.getText().toString();
            TextView txtWikiData = (TextView) findViewById(R.id.txtWikiData);
            String text = txtWikiData.getText().toString();
            //title = (TextView) findViewById(R.id.title);
            String keyword = etxSearch.getText().toString();
            //String formattedData = number.getString("extract");

            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            // intent.putExtra(EXTRA_TITLE, text);
            //intent.putExtra(TAG_KEYWORD, keyword);
          //  intent.putExtra(TAG_TEXT, text);
            startActivity(intent);

        return null;
    }
*//*
    private class FetchWikiDataAsync extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "Egy pillanat...", Toast.LENGTH_SHORT).show();



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
            super.onPostExecute(formattedData);
            progressBar.setVisibility(View.GONE);

           // openContentActivity(formattedData);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                // HTML Data
                txtWikiData.setText(Html.fromHtml
                        (formattedData,Html.FROM_HTML_MODE_LEGACY));

               // openContentActivity();
            }
            else
            {
                // HTML Data
                txtWikiData.setText(Html.fromHtml(formattedData));
            }
            /*txtWikiData.setText(formattedData);
            //progressBar = (ProgressBar)findViewById(R.id.progressbar);
            // EditText etxSearch = (EditText) findViewById(R.id.etxSearch);
            //String text = etxSearch.getText().toString();
            TextView txtWikiData = (TextView) findViewById(R.id.txtWikiData);
            String text =txtWikiData.getText().toString();
            //title = (TextView) findViewById(R.id.title);
            String keyword = etxSearch.getText().toString();
            //String formattedData = number.getString("extract");


            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            // intent.putExtra(EXTRA_TITLE, text);
            //intent.putExtra(TAG_KEYWORD, keyword);
            intent.putExtra(TAG_TEXT,text);
            startActivity(intent);
     // openContentActivity(formattedData);


        }

    }
/*
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
    }*/

}