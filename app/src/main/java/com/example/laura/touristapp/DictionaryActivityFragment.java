package com.example.laura.touristapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
/*
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;*/


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

import io.paperdb.Paper;

/**
 * A placeholder fragment containing a simple view.
 */
public class DictionaryActivityFragment extends AppCompatActivity{
	private TextToSpeech tts;
	EditText word;
	TextView text;

    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;
    TextView text9;
    TextView text10;
    TextView text11;

	TextView text01;
	TextView text02;
	TextView text03;
	TextView text04;
	TextView text05;
	TextView text06;
	TextView text07;
	TextView text08;
	TextView text09;
	TextView text010;
	TextView text011;

	LinearLayout lo1;
	LinearLayout lo2;
	LinearLayout lo3;
	LinearLayout lo4;
	LinearLayout lo5;
	LinearLayout lo6;
	LinearLayout lo7;
	LinearLayout lo8;
	LinearLayout lo9;
	LinearLayout lo10;
	LinearLayout lo11;

    LinearLayout searchlo;
	LinearLayout ll;
    private ProgressBar progressBar;

/*
	static final String URL_PREFIX = "https://glosbe.com/gapi/translate?";
	static final String URL_FROMLANG_PARAM = "from=";
	static final String URL_TOLANG_PARAM = "&dest=";
	static final String URL_PHRASE_PARAM = "&phrase=";
	static final String URL_FORMAT_PARAM = "&format=json";

	static InputStream iStream = null;
	URL JSONurl;*/

	/*public DictionaryActivityFragment() {
	    return;

	}
*/
	Context context=this;
	//Intent intent;

	/*public DictionaryActivityFragment(Context context) {
		this.context = context;
	}*/
	//@Override
	//public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                       //  Bundle savedInstanceState) {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_dictionary);

		final String language = Paper.book().read("language");

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        text = (TextView) findViewById(R.id.result);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        text7 = (TextView) findViewById(R.id.text7);
        text8 = (TextView) findViewById(R.id.text8);
        text9 = (TextView) findViewById(R.id.text9);
        text10 = (TextView) findViewById(R.id.text10);
        text11 = (TextView) findViewById(R.id.text11);

		text01 = (TextView) findViewById(R.id.text01);
		text02 = (TextView) findViewById(R.id.text02);
		text03 = (TextView) findViewById(R.id.text03);
		text04 = (TextView) findViewById(R.id.text04);
		text05 = (TextView) findViewById(R.id.text05);
		text06 = (TextView) findViewById(R.id.text06);
		text07 = (TextView) findViewById(R.id.text07);
		text08 = (TextView) findViewById(R.id.text08);
		text09 = (TextView) findViewById(R.id.text09);
		text010 = (TextView) findViewById(R.id.text010);
		text011 = (TextView) findViewById(R.id.text011);
        word = (EditText) findViewById(R.id.toTranslate);

        lo1=(LinearLayout) findViewById(R.id.lo1);
		lo2=(LinearLayout) findViewById(R.id.lo2);
		lo3=(LinearLayout) findViewById(R.id.lo3);
		lo4=(LinearLayout) findViewById(R.id.lo4);
		lo5=(LinearLayout) findViewById(R.id.lo5);
		lo6=(LinearLayout) findViewById(R.id.lo6);
		lo7=(LinearLayout) findViewById(R.id.lo7);
		lo8=(LinearLayout) findViewById(R.id.lo8);
		lo9=(LinearLayout) findViewById(R.id.lo9);
		lo10=(LinearLayout) findViewById(R.id.lo10);
		lo11=(LinearLayout) findViewById(R.id.lo11);
		ll=(LinearLayout) findViewById(R.id.ll);
        searchlo=(LinearLayout) findViewById(R.id.searchlo);

        searchlo.setVisibility(View.INVISIBLE);  //keresett szó eredménye


		word.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) { //szó keresés
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(language.equals("en"))
                    {
                        String languagePair = "en-hu";
                        performSearch(languagePair);
                    }
                    else if(language.equals("de")) {
                        String languagePair = "de-hu";
                        performSearch(languagePair);
                    }
                    return true;
				}
				return false;
			}
		});
		tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

			@Override
			public void onInit(int initStatus) {
				if (initStatus == TextToSpeech.SUCCESS) {
					// if (tts.isLanguageAvailable(Locale.UK) == TextToSpeech.LANG_AVAILABLE) {
					Locale loc = new Locale("hu_HU");
					int result = tts.setLanguage(loc);
					//tts.setLanguage(Locale.UK);
					//Toast.makeText(DictionaryActivityFragment.this, "Magyar beszéd kész", Toast.LENGTH_LONG).show();
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
					Toast.makeText(DictionaryActivityFragment.this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
				}
			}
		});
		lo1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text01.getText().toString();
				//Log.i("TTS", "button clicked: " + data);
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}

			}
		});
		lo2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text02.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}
			}
		});

		lo3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text03.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}
			}
		});
		lo4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text04.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}

			}
		});
		lo5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text05.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}
			}
		});
		lo6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text06.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}
			}
		});
		lo7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text07.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}
			}
		});
		lo8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text08.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}
			}
		});
		lo9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text09.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}
			}
		});
		lo10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text010.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}
			}
		});
		lo11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text011.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}
			}
		});
		text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				//Toast.makeText(DictionaryActivityFragment.this, "Kattintva!", Toast.LENGTH_SHORT).show();
				String data = text.getText().toString();
				int speechStatus = tts.speak(data, TextToSpeech.QUEUE_FLUSH, null);

				if (speechStatus == TextToSpeech.ERROR) {
					Log.e("TTS", "Error in converting Text to Speech!");
				}
			}
		});

	}
	private void performSearch(String languagePair) {
        searchlo.setVisibility(View.VISIBLE); //eredmény megjelenítése
		//progressBar.setVisibility(View.VISIBLE);
		ll.setVisibility(View.INVISIBLE); //alap szavak láthatatlanok lesznek
		word.clearFocus();
		InputMethodManager in = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		in.hideSoftInputFromWindow(word.getWindowToken(), 0);
        String textToBeTranslated = word.getText().toString();

            Translate(textToBeTranslated,languagePair);

	}
    //Function for calling executing the Translator Background Task
    void Translate(String textToBeTranslated,String languagePair){
        DictionaryActivityFragment.translatorBackgroundTask translatorBackgroundTask= new DictionaryActivityFragment.translatorBackgroundTask(context);
        translatorBackgroundTask.execute(textToBeTranslated,languagePair); // Returns the translated text as a String
    }
    public class translatorBackgroundTask extends AsyncTask<String, Void, String> {
        //Declare Context
        Context ctx;
        //Set Context
        translatorBackgroundTask(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
			//Toast.makeText(DictionaryActivityFragment.this, "Egy pillanat...", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(String[] params) {
            //String variables
            String textToBeTranslated = params[0];
            String languagePair = params[1];

            String jsonString;
            final String language = Paper.book().read("language");

            try {
                //Set up the translation call URL
                String yandexKey = "trnsl.1.1.20190301T221629Z.a280180c4220a34c.77d1ab3865e969a36823d3ae3c0920110f5643cd";
                String yandexUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                        + "&text=" + textToBeTranslated + "&lang=" + languagePair +"&format=html";
                URL yandexTranslateURL = new URL(yandexUrl);

                //Set Http Conncection, Input Stream, and Buffered Reader
                HttpURLConnection httpJsonConnection = (HttpURLConnection) yandexTranslateURL.openConnection();
                httpJsonConnection.setRequestMethod("GET");
				httpJsonConnection.setConnectTimeout(3000);
                httpJsonConnection.connect();

				if (httpJsonConnection.getResponseCode() == 200) {
					//return true;
					InputStream inputStream = httpJsonConnection.getInputStream();
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader (inputStream));

					//Set string builder and insert retrieved JSON result into it
					StringBuilder jsonStringBuilder = new StringBuilder();
					while ((jsonString = bufferedReader.readLine()) != null) {
						jsonStringBuilder.append(jsonString + "\n");
					}

					//Close and disconnect
					bufferedReader.close();
					inputStream.close();
					httpJsonConnection.disconnect();

					//Making result human readable
					String resultString = jsonStringBuilder.toString().trim();
					//Getting the characters between [ and ]
					resultString = resultString.substring(resultString.indexOf('[')+1);
					resultString = resultString.substring(0,resultString.indexOf("]"));
					//Getting the characters between " and "
					resultString = resultString.substring(resultString.indexOf("\"")+1);
					resultString = resultString.substring(0,resultString.indexOf("\""));

					Log.d("Translation Result:", resultString);
					//return jsonStringBuilder.toString().trim();
					return resultString;
				}

            } catch (MalformedURLException e) {
               // Toast.makeText(DictionaryActivityFragment.this, "nincs wifi!", Toast.LENGTH_LONG).show();

               e.printStackTrace();
               return null;

			} catch (IOException e) {
               // Toast.makeText(DictionaryActivityFragment.this, "nincs wifi!", Toast.LENGTH_LONG).show();
                /*if(language.equals("en")){
                    String resultString="No result";
                    e.printStackTrace();
                    return resultString;}
                else if(language.equals("de")){
                    String resultString="kein Ergebnis";
                    e.printStackTrace();
                    return resultString;}*/
                e.printStackTrace();
				return null;

			}
			if(language.equals("en")){
				String resultString="No result";
				//e.printStackTrace();
				return resultString;}
			else if(language.equals("de")){
				String resultString="kein Ergebnis";
				//e.printStackTrace();
				return resultString;}

				return null;
        }

        @Override
        protected void onPostExecute(String resultString) {
			final String textToBeTranslated = word.getText().toString();
            final String language = Paper.book().read("language");
            //if( isInternetAvailable()==true){
			if (resultString==null) { // code if not connected
				progressBar.setVisibility(View.GONE);
				//final String textToBeTranslated = word.getText().toString();
				AlertDialog.Builder builder = new AlertDialog.Builder(DictionaryActivityFragment.this);
				if(language.equals ("en")){
					builder.setMessage("Turn wifi on!");
					builder.setCancelable(true);
					builder.setPositiveButton (
							"TRY AGAIN",
							new DialogInterface.OnClickListener ( ) {
								public void onClick(DialogInterface dialog, int id) {
									dialog.cancel ( );
									String languagePair = "en-hu";
									new translatorBackgroundTask (context).execute (textToBeTranslated, languagePair);
								}
							});
				}
				else if(language.equals ("de")){
					builder.setMessage("Turn wifi on!/de");
					builder.setCancelable(true);
					builder.setPositiveButton (
							"TRY AGAIN/de",
							new DialogInterface.OnClickListener ( ) {
								public void onClick(DialogInterface dialog, int id) {
									dialog.cancel ( );
									String languagePair = "de-hu";
									new translatorBackgroundTask(context).execute(textToBeTranslated,languagePair);
								}
							});
				}
				AlertDialog alert11 = builder.create();
				alert11.show();

			}
			else { // code if connected
				super.onPostExecute(resultString);
				if(resultString.equals(textToBeTranslated)) {
                    if (language.equals("en")) {
                        progressBar.setVisibility(View.GONE);
                        text.setText("No result");
                        //Toast.makeText(DictionaryActivityFragment.this, "No result", Toast.LENGTH_LONG).show();
                    } else if (language.equals("de")) {
                        progressBar.setVisibility(View.GONE);
                        text.setText("kein Ergebnis");
                        //Toast.makeText(DictionaryActivityFragment.this, "kein Ergebnis", Toast.LENGTH_LONG).show();
                    }
                }else{
				progressBar.setVisibility(View.GONE);
				text.setText(resultString);
				}

			}


        }


        /*@Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate (values);
			progressBar.setVisibility(View.VISIBLE);
        }*/
    }/*
	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		return cm.getActiveNetworkInfo() != null;
	}*/
	/*public boolean isInternetAvailable() {
		try {
			InetAddress ipAddr = InetAddress.getByName("google.com");
			//You can replace it with your name
			return !ipAddr.equals("");

		} catch (Exception e) {
			return false;
		}
	}*/

	/*private void beginLookup(){
		String phrase = word.getText().toString();
		Log.i("TTS", "button clicked: " + fromLang);
		Log.i("TTS", "button clicked: " + toLang);
		Log.i("TTS", "button clicked: " + phrase);
       // Toast.makeText(DictionaryActivityFragment.this, "disctionaryActivityfragment", Toast.LENGTH_LONG).show();
		/*Intent intent = new Intent(DictionaryActivityFragment.this, DictionaryLookupTask.class);
		//String keyword = btnFetchData.getText().toString();
		//Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
		intent.putExtra("fromLang", fromLang);
		intent.putExtra("toLang", toLang);
		intent.putExtra("phrase", phrase);
		DictionaryActivityFragment.GoogleTranslate googleTranslate = new DictionaryActivityFragment.GoogleTranslate();
		googleTranslate.execute(fromLang,toLang,phrase);
		//intent.putExtra("key1", lang);
		//startActivity(intent);
		//new DictionaryLookupTask(getContext()).execute(fromLang, toLang, phrase);
	}*/
	/*
	public class GoogleTranslate extends AsyncTask<String, Void, String> {

		/*
		 * Your Google API Key here


		private final String API_KEY = "";

		/*
		 * Performing the translation in background process


		@Override
		protected String doInBackground(String... params){

			/*
			 *  The text which will be translated


			final String textToTranslate = params[0];

			/*
			 * The source language to be translated


			final String SOURCE_LANGUAGE = params[1];

			/*
			 * The wished language to be translated to


			final String TARGET_LANGUAGE = params[2];

			try {

				/*
				 * Objects needed for the translate object
				 */

				//NetHttpTransport netHttpTransport 	= new NetHttpTransport();

				//JacksonFactory jacksonFactory 		= new JacksonFactory();

				/*
				 * Creating the Google Translate object

				TranslateOptions options = TranslateOptions.newBuilder()
						.setApiKey(API_KEY)
						.build();
				Translate translate = options.getService();
				//Translate translate = new Translate.Builder(netHttpTransport, jacksonFactory, null).build();

				/*
				 * Setting the textToTranslate, the API_KEY and TARGET_LANGUAGE

				final Translation translation =
						translate.translate(textToTranslate, Translate.TranslateOption.sourceLanguage (SOURCE_LANGUAGE),Translate.TranslateOption.targetLanguage(TARGET_LANGUAGE));

				/*Translate.Translations.List listToTranslate = translate.new Translations().list(
						Arrays.asList(textToTranslate), TARGET_LANGUAGE).setKey(API_KEY);

				/*
				 * If you want to let Google detects the language automatically, remove the next line
				 * This line set the source language of the translated text
				 */

				//translation.setSource(SOURCE_LANGUAGE);

				/*
				 * Executing the translation and saving the response in the response object
				 */

				//TranslationsListResponse response = listToTranslate.execute();

				/*
				 * The response has the form of: {"translatedText":"blabla"}
				 * We need only the translated text between the second double quotes pair
				 * therefore using getTranslatedText

                Log.i("Google Response ", translation.getTranslatedText());
                //Log.i("Google Response ", e.getMessage());
				return translation.getTranslatedText();
			} catch (Exception e){

				Log.e("Google Response ", e.getMessage());

				/*
				 * I would return empty string if there is an error
				 * to let the method which invoked the translating method know that there is an error
				 * and subsequently it deals with it


				return "";
			}
		}
	}*/
	/*public class dictionaryLookupTask extends AsyncTask<String, Void, String> {

		/*Context context;
		//Intent intent;

		public dictionaryLookupTask(){
			this.context = context.getApplicationContext();
		}


		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String[] params) {
			String phrase = word.getText().toString();
			//String sURL = params[0];
			String url = urlMaker(fromLang, toLang, phrase);
			Log.i("TTS", fromLang);
			Log.i("TTS", toLang);
			Log.i("TTS", phrase);
			String line;
			String jsonString = "";
			try {
				JSONurl = new URL(url);
				HttpsURLConnection urlConnection = (HttpsURLConnection) JSONurl.openConnection();

				//URL url = new URL(sURL);        // Convert String URL to java.net.URL
				// Connection: to Wikipedia API
				//HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.connect();
				Log.i("TTS", "connect");

				BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				//StringBuilder stringBuilder = new StringBuilder();
				/*InputStream inputStream = urlConnection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader br = new BufferedReader(inputStreamReader);

				StringBuilder stringBuilder = new StringBuilder();
				//String line;
				while ((line = br.readLine()) != null){
					stringBuilder.append(line + '\n');
					Log.i("TTS", "stringbuilder");
				}
				jsonString = stringBuilder.toString();
				Log.i("json", jsonString);/*
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line);
				}*/

				/*String jsonString = stringBuilder.toString();
				Log.i("json", jsonString);

				return jsonString;
/*
                try {
                    JSONObject response = new JSONObject(jsonString);
                    JSONResponseParser parser = new JSONResponseParser(response);
                    String[] translations = parser.getTranslations();
                    //String phrase= parser.getPhrase();

                    /*toLangText.setText("\tTo: " + parser.getToLang());
                    fromLangText.setText("\tFrom: " + parser.getFromLang());
                    phraseText.setText(parser.getPhrase());*/
                    //text.setText(translations[0]);
                   // return translations;
/*
                } catch (JSONException e) {
                    e.printStackTrace();
					//Log.i("json", jsonString);
					Log.i("json", "rossz!");
                }
				// Parse JSON Data
				//String jsonS = parseJSONData(jsonString);

				//openContentActivity(formattedData);
				//return jsonS;


			} catch (IOException e) {
				e.printStackTrace();
				Log.i("exception", "hiba");
				return e.toString();
			}

			//return null;
		}
/*
		@Override
		protected JSONObject doInBackground(String[] params) {
			//DictionaryLookup lookup = new DictionaryLookup(params[0], params[1], params[2]); //meghívódik a dictionarylookup
			DictionaryLookup lookup = new DictionaryLookup(fromLang, toLang, word.getText().toString()); //meghívódik a dictionarylookup
       /* intent = new Intent(DictionaryActivityFragment.this, DictionaryLookup.class);
        intent.putExtra("fromLang", params[0]);
        intent.putExtra("toLang", params[1]);
        intent.putExtra("phrase", params[2]);
			try {
				return lookup.getLookUpData ();
			} catch (IOException e) {
				e.printStackTrace();
				return new JSONObject();
			}
		}

		@Override
		protected void onPostExecute(String jsonS) {

			//JSONObject response = null;
			try {
				/*response = new JSONObject(result);
				JSONResponseParser parser = new JSONResponseParser(response);
				String[] translations = parser.getTranslations();
				super.onPostExecute(jsonS);
				text.setText(jsonS);
			} catch (Exception e) {
				//e.printStackTrace();
				Toast.makeText(DictionaryActivityFragment.this, "Wifi!", Toast.LENGTH_LONG).show();
				Toast.makeText(DictionaryActivityFragment.this, "nincs eredmény!", Toast.LENGTH_LONG).show();
			}
			/*String jsonString = intent.getStringExtra("JSONObject");
			Intent intent = new Intent(context, DictionaryLookupActivityFragment.class);
			intent.putExtra("JSONObject", result.toString());
			//.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);*/
			//String res=result.toString();

			//String res = text.getText().toString();
			//Log.i("TTS", res);
				//super.onPostExecute(result);
					/*JSONObject response = new JSONObject(jsonString);
					JSONResponseParser parser = new JSONResponseParser(response);
					String[] translations = parser.getTranslations();

            /*toLangText.setText("\tTo: " + parser.getToLang());
            fromLangText.setText("\tFrom: " + parser.getFromLang());
            phraseText.setText(parser.getPhrase());*/
					//text.setText(translations[0]);

				/*} catch (JSONException e) {
					e.printStackTrace();
				Toast.makeText(DictionaryActivityFragment.this, "nincs eredmény!", Toast.LENGTH_LONG).show();
				}
				/*JSONObject response = new JSONObject(jsonString);
				JSONResponseParser parser = new JSONResponseParser(response);
				String[] translations = parser.getTranslations();
*/
				//toLang.setText("\tTo: " + parser.getToLang());
				//fromLangText.setText("\tFrom: " + parser.getFromLang());
				//phraseText.setText(parser.getPhrase());
				//text.setText(result);
				//progressBar.setVisibility(View.GONE);
				//text.setText(res);
			/*} catch (Exception e) {
				Toast.makeText(DictionaryActivityFragment.this, "nincs eredmény!", Toast.LENGTH_LONG).show();
			}


		}
	}*/
	/*public String urlMaker(String fromLang, String toLang, String phrase){
		return URL_PREFIX + URL_FROMLANG_PARAM + fromLang + URL_TOLANG_PARAM + toLang + URL_FORMAT_PARAM + URL_PHRASE_PARAM + phrase+ "&pretty=true" +"&tm=false";
	}*/
	@Override
	public void onPause() {
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onPause();
	}

	//JSONObject response;
	/*private String parseJSONData(String jsonString) {
		try {
			// Convert String JSON (wikiData) to JSON Object
			/*String translations = "";
			JSONArray tuc = response.getJSONArray("tuc");
			JSONObject meaning;
			for (int i = 0; ((i < tuc.length()) && (i < 6)); i++) {
				meaning = tuc.getJSONObject(i);
				meaning = meaning.getJSONObject("phrase");
				translations = meaning.getString("text");
			}
			return translations;*//*
			Log.i("json", "parse");
			JSONObject meaning = new JSONObject(jsonString);
			//JSONObject meaning = null;
			meaning = meaning.getJSONObject("phrase");
			String jsonS = meaning.getString("text");
			Log.i("json", jsonS);
			//JSONObject query = rootJSON.getJSONObject("query");
			//JSONObject pages = query.getJSONObject("pages");
			//JSONObject number = pages.getJSONObject(pages.keys().next());
			//String jsonS = number.getString("extract");

			return jsonS;


		} catch (JSONException json) {
			json.printStackTrace();
		}

		return null;
	}*/

}
