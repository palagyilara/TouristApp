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
   public Button btnFetchData;
   public ImageButton popupbtn;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"hu"));
    }


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
        choose =(TextView) findViewById(R.id.choose);
        btnFetchData = (Button) findViewById(R.id.btnFetchData);
        popupbtn=(ImageButton) findViewById(R.id.popup);


        Paper.init(this);

        //default nyelv magyar
        String language= Paper.book().read("language");
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

        /*ActionBar actionBar= getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);*/


        btnFetchData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //openContentActivity(null);
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                String keyword = btnFetchData.getText().toString();
                //Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword);
                //intent.putExtra("key1", lang);
                startActivity(intent);
               /* final String[] language = {"Magyar", "English", "Deutsch"};

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
*/



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
        Button button =(Button)findViewById(R.id.dbbtn);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent dbmanager = new Intent(MainActivity.this,AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });
    }
   /* public void showPopup(View v)
    {
        PopupMenu popup= new PopupMenu(this,v);
        MenuInflater inflater= popup.getMenuInflater();
        inflater.inflate(R.menu.main_menu,popup.getMenu());
        popup.show();
    }
    public void showMenu(View v)
    {
        PopupMenu popup= new PopupMenu(this,v);

        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.main_menu);
        popup.show();
    }*/
   /* @Override
    public boolean onMenuItemClick(MenuItem item) {
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
    }*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        setTitle("");
        return true;
    }*/
/*
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
       /* else if(item.getItemId()== R.id.home)
        {
            Intent homeIntent= new Intent(this,MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }*/
/*
        return true;
    }*/

    private void updateView(String lang) {
        Context context= LocaleHelper.setLocale(this,lang);
        Resources resources = context.getResources();

        choose.setText(resources.getString(R.string.choose));
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