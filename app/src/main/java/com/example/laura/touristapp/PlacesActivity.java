package com.example.laura.touristapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.laura.touristapp.Helper.LocaleHelper;

import io.paperdb.Paper;

import static android.view.View.GONE;

public class PlacesActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        //String language = Paper.book().read("language");
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "hu"));
    }

    Button templom1;
    Button templom2;
    Button templom3;
    Button templom4;
    Button templom5;
    Button templom6;
    Button templom7;
    Button templom8;
    Button templom9;
    Button templom10;
    Button templom11;
    Button templom12;
    Button templom13;
    Button museum1;
    Button museum2;
    Button museum3;
    Button museum4;
    Button museum5;
    Button museum6;
    Button museum7;
    Button museum8;
    Button museum9;
    Button monuments1;
    Button monuments2;
    Button monuments3;
    Button monuments4;
    Button monuments5;
    Button monuments6;
    Button monuments7;
    Button monuments8;
    Button monuments9;
    Button monuments10;
    Button monuments11;
    Button other1;
    Button other2;
    Button other3;
    Button other4;
    Button other5;
    Button other6;
    Button other7;
    Button other8;
    Button other9;
    TextView museums;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_places);
        Bundle extra = getIntent().getExtras();
        final String keyword = extra.getString("key");
        String language= Paper.book().read("language");

        museums=(TextView) findViewById(R.id.museums);
        templom1 = (Button) findViewById(R.id.templom1);
        templom2 = (Button) findViewById(R.id.templom2);
        templom3 = (Button) findViewById(R.id.templom3);
        templom4 = (Button) findViewById(R.id.templom4);
        templom5 = (Button) findViewById(R.id.templom5);
        templom6 = (Button) findViewById(R.id.templom6);
        templom7 = (Button) findViewById(R.id.templom7);
        templom8 = (Button) findViewById(R.id.templom8);
        templom9 = (Button) findViewById(R.id.templom9);
        templom10 = (Button) findViewById(R.id.templom10);
        templom11 = (Button) findViewById(R.id.templom11);
        templom12 = (Button) findViewById(R.id.templom12);
        templom13 = (Button) findViewById(R.id.templom13);
        museum1 = (Button) findViewById(R.id.museum1);
        museum2 = (Button) findViewById(R.id.museum2);
        museum3 = (Button) findViewById(R.id.museum3);
        museum4 = (Button) findViewById(R.id.museum4);
        museum5 = (Button) findViewById(R.id.museum5);
        museum6 = (Button) findViewById(R.id.museum6);
        museum7 = (Button) findViewById(R.id.museum7);
        museum8 = (Button) findViewById(R.id.museum8);
        museum9 = (Button) findViewById(R.id.museum9);
        monuments1 = (Button) findViewById(R.id.monuments1);
        monuments2 = (Button) findViewById(R.id.monuments2);
        monuments3 = (Button) findViewById(R.id.monuments3);
        monuments4 = (Button) findViewById(R.id.monuments4);
        monuments5 = (Button) findViewById(R.id.monuments5);
        monuments6 = (Button) findViewById(R.id.monuments6);
        monuments7 = (Button) findViewById(R.id.monuments7);
        monuments8 = (Button) findViewById(R.id.monuments8);
        monuments9 = (Button) findViewById(R.id.monuments9);
        monuments10 = (Button) findViewById(R.id.monuments10);
        monuments11 = (Button) findViewById(R.id.monuments11);
        other1 = (Button) findViewById(R.id.other1);
        other2 = (Button) findViewById(R.id.other2);
        other3 = (Button) findViewById(R.id.other3);
        other4 = (Button) findViewById(R.id.other4);
        other5 = (Button) findViewById(R.id.other5);
        other6 = (Button) findViewById(R.id.other6);
        other7 = (Button) findViewById(R.id.other7);
        other8 = (Button) findViewById(R.id.other8);
        other9 = (Button) findViewById(R.id.other9);

//----------------KESZTHELY--------------------------------------
        if(keyword.equals ("Keszthely")) {
            museum1.setText(getResources().getString(R.string.keszthmuseum1));
            museum3.setText(getResources().getString(R.string.keszthmuseum3));
            museum6.setText(getResources().getString(R.string.keszthmuseum6));
            templom1.setText(getResources().getString(R.string.keszthtemplom1));
            templom2.setText(getResources().getString(R.string.keszthtemplom2));
            templom3.setText(getResources().getString(R.string.keszthtemplom3));
            templom4.setText(getResources().getString(R.string.keszthtemplom4));
            monuments1.setText(getResources().getString(R.string.keszthmonuments1));
            monuments2.setText(getResources().getString(R.string.keszthmonuments2));
            monuments3.setText(getResources().getString(R.string.keszthmonuments3));
            monuments4.setText(getResources().getString(R.string.keszthmonuments4));
            other1.setText(getResources().getString(R.string.keszthother1));
            other2.setText(getResources().getString(R.string.keszthother2));
            other3.setText(getResources().getString(R.string.keszthother3));


            museum7.setVisibility(GONE);
            museum8.setVisibility(GONE);
            museum9.setVisibility(GONE);
            monuments5.setVisibility(GONE);
            monuments6.setVisibility(GONE);
            monuments7.setVisibility(GONE);
            monuments8.setVisibility(GONE);
            monuments9.setVisibility(GONE);
            monuments10.setVisibility(GONE);
            monuments11.setVisibility(GONE);
            templom5.setVisibility(GONE);
            templom6.setVisibility(GONE);
            templom7.setVisibility(GONE);
            templom8.setVisibility(GONE);
            templom9.setVisibility(GONE);
            templom10.setVisibility(GONE);
            templom11.setVisibility(GONE);
            templom12.setVisibility(GONE);
            templom13.setVisibility(GONE);
            other4.setVisibility(GONE);
            other5.setVisibility(GONE);
            other6.setVisibility(GONE);
            other7.setVisibility(GONE);
            other8.setVisibility(GONE);
            other9.setVisibility(GONE);

            museum1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = museum1.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthmus1);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            museum3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = museum3.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthmus3);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            museum6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = museum6.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthmus6);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            if (language.equals("hu")) {

                museum4.setText(getResources().getString(R.string.keszthmuseum4));
                museum5.setText(getResources().getString(R.string.keszthmuseum5));
                museum2.setText(getResources().getString(R.string.keszthmuseum2));
                //múzeumok

                museum2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum2.getText().toString();
                        String keyword1 = getResources().getString(R.string.keszthmus2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);


                        startActivity(intent);
                    }

                });
                museum4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum4.getText().toString();
                        String keyword1 = getResources().getString(R.string.keszthmus4);
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        startActivity(intent);
                    }

                });

                museum5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum5.getText().toString();
                        String keyword1 = getResources().getString(R.string.keszthmus5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
            }
            //templomok
            templom1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = templom1.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthtemp1);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            templom2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = templom2.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthtemp2);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            templom3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = templom3.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthtemp3);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            templom4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = templom4.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthtemp4);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            //műemlékek
            monuments1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = monuments1.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthmon1);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            monuments2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = monuments2.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthmon2);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            monuments3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = monuments3.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthmon3);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            monuments4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = monuments4.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthmon4);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            //egyéb
            other1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = other1.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthoth1);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            other2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = other2.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthoth2);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
            other3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = other3.getText().toString();
                    String keyword1 = getResources().getString(R.string.keszthoth3);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });

         if (language.equals("en") || language.equals("de")) {
                museum2.setVisibility(GONE);
                museum4.setVisibility(GONE);
                museum5.setVisibility(GONE);
            }
        }
//----------------EGER--------------------------------------
        else if (keyword.equals ("Eger")) {
            museum1.setText(getResources().getString(R.string.egermuseum1));
            museum2.setText(getResources().getString(R.string.egermuseum2));
            museum3.setText(getResources().getString(R.string.egermuseum3));
            museum4.setText(getResources().getString(R.string.egermuseum4));
            museum5.setText(getResources().getString(R.string.egermuseum5));
            museum6.setText(getResources().getString(R.string.egermuseum6));
            museum7.setText(getResources().getString(R.string.egermuseum7));
            museum8.setText(getResources().getString(R.string.egermuseum8));
            templom1.setText(getResources().getString(R.string.egertemplom1));
            templom2.setText(getResources().getString(R.string.egertemplom2));
            templom3.setText(getResources().getString(R.string.egertemplom3));
            templom4.setText(getResources().getString(R.string.egertemplom4));
            monuments1.setText(getResources().getString(R.string.egermonuments1));
            monuments2.setText(getResources().getString(R.string.egermonuments2));
            monuments3.setText(getResources().getString(R.string.egermonuments3));
            monuments4.setText(getResources().getString(R.string.egermonuments4));
            monuments5.setText(getResources().getString(R.string.egermonuments5));
            other3.setText(getResources().getString(R.string.egerother3));
            other4.setText(getResources().getString(R.string.egerother4));

            museum9.setVisibility (GONE);
            templom5.setVisibility (GONE);
            templom6.setVisibility (GONE);
            templom7.setVisibility (GONE);
            templom8.setVisibility (GONE);
            templom9.setVisibility (GONE);
            templom10.setVisibility (GONE);
            templom11.setVisibility (GONE);
            templom12.setVisibility (GONE);
            templom13.setVisibility (GONE);
            monuments6.setVisibility (GONE);
            monuments7.setVisibility (GONE);
            monuments8.setVisibility (GONE);
            monuments9.setVisibility (GONE);
            monuments10.setVisibility (GONE);
            monuments11.setVisibility (GONE);
            other1.setVisibility (GONE);
            other2.setVisibility (GONE);
            other5.setVisibility (GONE);
            other6.setVisibility (GONE);
            other7.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

            //if(language.equals ("hu"))
           // {
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= getResources().getString(R.string.egermus1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum2.getText().toString();
                        String keyword1= getResources().getString(R.string.egermus2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum3.getText().toString();
                        String keyword1= getResources().getString(R.string.egermus3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum4.getText().toString();
                        String keyword1= getResources().getString(R.string.egermus4);
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        startActivity(intent);
                    }

                });
                museum5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum5.getText().toString();
                        String keyword1= getResources().getString(R.string.egermus5);
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum6.getText().toString();
                        String keyword1= getResources().getString(R.string.egermus6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum7.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum7.getText().toString();
                        String keyword1= getResources().getString(R.string.egermus7);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum8.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum8.getText().toString();
                        String keyword1=getResources().getString(R.string.egermus8);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });

                //templomok
                templom1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText().toString();
                        String keyword1= getResources().getString(R.string.egertemp1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom2.getText().toString();
                        String keyword1= getResources().getString(R.string.egertemp2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom3.getText().toString();
                        String keyword1= getResources().getString(R.string.egertemp3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom4.getText().toString();
                        String keyword1= getResources().getString(R.string.egertemp4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //műemlékek
                monuments1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText().toString();
                        String keyword1= getResources().getString(R.string.egermon1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments2.getText().toString();
                        String keyword1= getResources().getString(R.string.egermon2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments3.getText().toString();
                        String keyword1= getResources().getString(R.string.egermon3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments4.getText().toString();
                        String keyword1= getResources().getString(R.string.egermon4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments5.getText().toString();
                        String keyword1= getResources().getString(R.string.egermon5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //egyéb

                other3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other3.getText().toString();
                        String keyword1= getResources().getString(R.string.egeroth3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other4.getText().toString();
                        String keyword1= getResources().getString(R.string.egeroth4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
        }
//----------------PÉCS--------------------------------------
        else if (keyword.equals ("Pécs")) {
            museum1.setText(getResources().getString(R.string.pecsmuseum1));
            museum2.setText(getResources().getString(R.string.pecsmuseum2));
            museum3.setText(getResources().getString(R.string.pecsmuseum3));
            museum4.setText(getResources().getString(R.string.pecsmuseum4));
            museum5.setText(getResources().getString(R.string.pecsmuseum5));
            museum6.setText(getResources().getString(R.string.pecsmuseum6));
            museum7.setText(getResources().getString(R.string.pecsmuseum7));
            museum8.setText(getResources().getString(R.string.pecsmuseum8));
            templom1.setText(getResources().getString(R.string.pecstemplom1));
            templom2.setText(getResources().getString(R.string.pecstemplom2));
            templom3.setText(getResources().getString(R.string.pecstemplom3));
            templom4.setText(getResources().getString(R.string.pecstemplom4));
            templom5.setText(getResources().getString(R.string.pecstemplom5));
            templom6.setText(getResources().getString(R.string.pecstemplom6));
            templom7.setText(getResources().getString(R.string.pecstemplom7));
            templom8.setText(getResources().getString(R.string.pecstemplom8));
            templom9.setText(getResources().getString(R.string.pecstemplom9));
            monuments1.setText(getResources().getString(R.string.pecsmonuments1));
            monuments2.setText(getResources().getString(R.string.pecsmonuments2));
            monuments3.setText(getResources().getString(R.string.pecsmonuments3));
            monuments4.setText(getResources().getString(R.string.pecsmonuments4));
            monuments5.setText(getResources().getString(R.string.pecsmonuments5));
            monuments6.setText(getResources().getString(R.string.pecsmonuments6));
            monuments7.setText(getResources().getString(R.string.pecsmonuments7));
            monuments9.setText(getResources().getString(R.string.pecsmonuments9));
            monuments10.setText(getResources().getString(R.string.pecsmonuments10));
            other1.setText(getResources().getString(R.string.pecsother1));
            other2.setText(getResources().getString(R.string.pecsother2));
            other3.setText(getResources().getString(R.string.pecsother3));
            other4.setText(getResources().getString(R.string.pecsother4));
            other5.setText(getResources().getString(R.string.pecsother5));
            other6.setText(getResources().getString(R.string.pecsother6));
            other7.setText(getResources().getString(R.string.pecsother7));

            museum9.setVisibility (GONE);
            templom10.setVisibility (GONE);
            templom11.setVisibility (GONE);
            templom12.setVisibility (GONE);
            templom13.setVisibility (GONE);
            monuments11.setVisibility (GONE);
            monuments8.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmus1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum2.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmus2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum3.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmus3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum4.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmus4);
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        startActivity(intent);
                    }

                });
                museum5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum5.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmus5);
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum6.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmus6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum7.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum7.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmus7);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum8.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum8.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmus8);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });

                //templomok
                templom1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText().toString();
                        String keyword1= getResources().getString(R.string.pecstemp1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom2.getText().toString();
                        String keyword1= getResources().getString(R.string.pecstemp2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom3.getText().toString();
                        String keyword1= getResources().getString(R.string.pecstemp3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom4.getText().toString();
                        String keyword1= getResources().getString(R.string.pecstemp4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom5.getText().toString();
                        String keyword1= getResources().getString(R.string.pecstemp5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom6.getText().toString();
                        String keyword1= getResources().getString(R.string.pecstemp6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom7.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom7.getText().toString();
                        String keyword1= getResources().getString(R.string.pecstemp7);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom8.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom8.getText().toString();
                        String keyword1= getResources().getString(R.string.pecstemp8);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom9.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom9.getText().toString();
                        String keyword1= getResources().getString(R.string.pecstemp9);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });

                //műemlékek
            monuments1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = monuments1.getText().toString();
                    String keyword1= getResources().getString(R.string.pecsmon1);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });
                monuments2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments2.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmon2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments3.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmon3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments4.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmon4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments5.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmon5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments6.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmon6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments7.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments7.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmon7);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments9.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments9.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmon9);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments10.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments10.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsmon10);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //egyéb
                other1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other1.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsoth1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other2.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsoth2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other3.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsoth3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other4.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsoth4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other5.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsoth5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other6.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsoth6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other7.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other7.getText().toString();
                        String keyword1= getResources().getString(R.string.pecsoth7);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });

        }
//----------------SOPRON--------------------------------------
        else if (keyword.equals ("Sopron")) {
            museum1.setText(getResources().getString(R.string.sopronmuseum1));
            museum2.setText(getResources().getString(R.string.sopronmuseum2));
            museum3.setText(getResources().getString(R.string.sopronmuseum3));
            museum4.setText(getResources().getString(R.string.sopronmuseum4));
            museum5.setText(getResources().getString(R.string.sopronmuseum5));
            museum6.setText(getResources().getString(R.string.sopronmuseum6));
            museum7.setText(getResources().getString(R.string.sopronmuseum7));
            museum8.setText(getResources().getString(R.string.sopronmuseum8));
            museum9.setText(getResources().getString(R.string.sopronmuseum9));
            templom1.setText(getResources().getString(R.string.soprontemplom1));
            templom2.setText(getResources().getString(R.string.soprontemplom2));
            templom3.setText(getResources().getString(R.string.soprontemplom3));
            templom4.setText(getResources().getString(R.string.soprontemplom4));
            templom5.setText(getResources().getString(R.string.soprontemplom5));
            templom6.setText(getResources().getString(R.string.soprontemplom6));
            templom7.setText(getResources().getString(R.string.soprontemplom7));
            templom8.setText(getResources().getString(R.string.soprontemplom8));
            templom9.setText(getResources().getString(R.string.soprontemplom9));
            templom10.setText(getResources().getString(R.string.soprontemplom10));
            templom11.setText(getResources().getString(R.string.soprontemplom11));
            templom12.setText(getResources().getString(R.string.soprontemplom12));
            templom13.setText(getResources().getString(R.string.soprontemplom13));
            monuments1.setText(getResources().getString(R.string.sopronmonuments1));
            monuments2.setText(getResources().getString(R.string.sopronmonuments2));
            monuments4.setText(getResources().getString(R.string.sopronmonuments4));
            monuments5.setText(getResources().getString(R.string.sopronmonuments5));
            monuments7.setText(getResources().getString(R.string.sopronmonuments7));
            monuments8.setText(getResources().getString(R.string.sopronmonuments8));
            monuments9.setText(getResources().getString(R.string.sopronmonuments9));
            monuments10.setText(getResources().getString(R.string.sopronmonuments10));
            monuments11.setText(getResources().getString(R.string.sopronmonuments11));
            other1.setText(getResources().getString(R.string.sopronother1));
            other2.setText(getResources().getString(R.string.sopronother2));
            other3.setText(getResources().getString(R.string.sopronother3));

            monuments3.setVisibility (GONE);
            monuments6.setVisibility (GONE);
            other4.setVisibility (GONE);
            other5.setVisibility (GONE);
            other6.setVisibility (GONE);
            other7.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmus1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);
                        startActivity(intent);
                    }

                });
                museum2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum2.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmus2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);
                        startActivity(intent);
                    }

                });
                museum3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum3.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmus3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);
                        startActivity(intent);
                    }

                });
                museum4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum4.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmus4);
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        startActivity(intent);
                    }

                });
                museum5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum5.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmus5);
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum6.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmus6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum7.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum7.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmus7);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum8.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum8.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmus8);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum9.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum9.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmus9);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });

                //templomok
                templom1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom2.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom3.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom4.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom5.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom6.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom7.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom7.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp7);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom8.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom8.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp8);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom9.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom9.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp9);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom10.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom10.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp10);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom11.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom11.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp11);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom12.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom12.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp12);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom13.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom13.getText().toString();
                        String keyword1= getResources().getString(R.string.soprtemp13);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //műemlékek
                monuments1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmon1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments2.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmon2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments4.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmon4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments5.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmon5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });

                monuments7.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments7.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmon7);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments8.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments8.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmon8);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments9.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments9.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmon9);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments10.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments10.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmon10);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments11.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments11.getText().toString();
                        String keyword1= getResources().getString(R.string.soprmon11);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //egyéb
                other1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other1.getText().toString();
                        String keyword1= getResources().getString(R.string.soproth1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other2.getText().toString();
                        String keyword1= getResources().getString(R.string.soproth2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other3.getText().toString();
                        String keyword1= getResources().getString(R.string.soproth3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });

        }
//----------------SZEGED--------------------------------------
        else if (keyword.equals ("Szeged")) {
            museum1.setText(getResources().getString(R.string.szegedmuseum1));
            museum2.setText(getResources().getString(R.string.szegedmuseum2));
            museum3.setText(getResources().getString(R.string.szegedmuseum3));
            museum4.setText(getResources().getString(R.string.szegedmuseum4));
            museum5.setText(getResources().getString(R.string.szegedmuseum5));
            museum6.setText(getResources().getString(R.string.szegedmuseum6));
            templom1.setText(getResources().getString(R.string.szegedtemplom1));
            templom2.setText(getResources().getString(R.string.szegedtemplom2));
            templom3.setText(getResources().getString(R.string.szegedtemplom3));
            templom4.setText(getResources().getString(R.string.szegedtemplom4));
            templom5.setText(getResources().getString(R.string.szegedtemplom5));
            templom6.setText(getResources().getString(R.string.szegedtemplom6));
            monuments1.setText(getResources().getString(R.string.szegedmonuments1));
            monuments2.setText(getResources().getString(R.string.szegedmonuments2));
            monuments3.setText(getResources().getString(R.string.szegedmonuments3));
            monuments4.setText(getResources().getString(R.string.szegedmonuments4));
            monuments5.setText(getResources().getString(R.string.szegedmonuments5));
            monuments6.setText(getResources().getString(R.string.szegedmonuments6));
            monuments7.setText(getResources().getString(R.string.szegedmonuments7));
            monuments8.setText(getResources().getString(R.string.szegedmonuments8));
            monuments9.setText(getResources().getString(R.string.szegedmonuments9));
            monuments10.setText(getResources().getString(R.string.szegedmonuments10));
            monuments11.setText(getResources().getString(R.string.szegedmonuments11));
            other1.setText(getResources().getString(R.string.szegedother1));
            other2.setText(getResources().getString(R.string.szegedother2));
            other3.setText(getResources().getString(R.string.szegedother3));
            other4.setText(getResources().getString(R.string.szegedother4));
            other5.setText(getResources().getString(R.string.szegedother5));
            other6.setText(getResources().getString(R.string.szegedother6));
            other7.setText(getResources().getString(R.string.szegedother7));
            other8.setText(getResources().getString(R.string.szegedother8));
            other9.setText(getResources().getString(R.string.szegedother9));

            museum7.setVisibility (GONE);
            museum8.setVisibility (GONE);
            museum9.setVisibility (GONE);
            templom7.setVisibility (GONE);
            templom8.setVisibility (GONE);
            templom9.setVisibility (GONE);
            templom10.setVisibility (GONE);
            templom11.setVisibility (GONE);
            templom12.setVisibility (GONE);
            templom13.setVisibility (GONE);

                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmus1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);
                        startActivity(intent);
                    }

                });
                museum2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum2.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmus2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);
                        startActivity(intent);
                    }

                });
                museum3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum3.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmus3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);
                        startActivity(intent);
                    }

                });
                museum4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum4.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmus4);
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        startActivity(intent);
                    }

                });
                museum5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum5.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmus5);
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                museum6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum6.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmus6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //templomok
                templom1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText().toString();
                        String keyword1= getResources().getString(R.string.szegtemp1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom2.getText().toString();
                        String keyword1= getResources().getString(R.string.szegtemp2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom3.getText().toString();
                        String keyword1= getResources().getString(R.string.szegtemp3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom4.getText().toString();
                        String keyword1=getResources().getString(R.string.szegtemp4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom5.getText().toString();
                        String keyword1= getResources().getString(R.string.szegtemp5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom6.getText().toString();
                        String keyword1= getResources().getString(R.string.szegtemp6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //műemlékek
                monuments1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments2.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments3.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments4.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments5.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments6.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments7.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments7.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon7);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments8.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments8.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon8);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments9.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments9.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon9);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments10.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments10.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon10);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments11.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments11.getText().toString();
                        String keyword1= getResources().getString(R.string.szegmon11);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //egyéb
                other1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other1.getText().toString();
                        String keyword1= getResources().getString(R.string.szegoth1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other2.getText().toString();
                        String keyword1= getResources().getString(R.string.szegoth2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other3.getText().toString();
                        String keyword1= getResources().getString(R.string.szegoth3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other4.getText().toString();
                        String keyword1= getResources().getString(R.string.szegoth4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other5.getText().toString();
                        String keyword1= getResources().getString(R.string.szegoth5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other6.getText().toString();
                        String keyword1= getResources().getString(R.string.szegoth6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other7.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other7.getText().toString();
                        String keyword1= getResources().getString(R.string.szegoth7);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other8.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other8.getText().toString();
                        String keyword1= getResources().getString(R.string.szegoth8);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other9.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other9.getText().toString();
                        String keyword1= getResources().getString(R.string.szegoth9);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
        }
//----------------VESZPRÉM--------------------------------------
        else if (keyword.equals ("Veszprém")) {
            museum1.setText(getResources().getString(R.string.veszpmuseum1));
            museum2.setText(getResources().getString(R.string.veszpmuseum2));
            museum3.setText(getResources().getString(R.string.veszpmuseum3));
            templom1.setText(getResources().getString(R.string.veszptemplom1));
            templom2.setText(getResources().getString(R.string.veszptemplom2));
            templom3.setText(getResources().getString(R.string.veszptemplom3));
            templom4.setText(getResources().getString(R.string.veszptemplom4));
            templom5.setText(getResources().getString(R.string.veszptemplom5));
            templom6.setText(getResources().getString(R.string.veszptemplom6));
            monuments1.setText(getResources().getString(R.string.veszpmonuments1));
            monuments2.setText(getResources().getString(R.string.veszpmonuments2));
            other1.setText(getResources().getString(R.string.veszpother1));
            other2.setText(getResources().getString(R.string.veszpother2));
            other3.setText(getResources().getString(R.string.veszpother3));
            other4.setText(getResources().getString(R.string.veszpother4));
            other5.setText(getResources().getString(R.string.veszpother5));
            other6.setText(getResources().getString(R.string.veszpother6));


            museum4.setVisibility (GONE);
            museum5.setVisibility (GONE);
            museum6.setVisibility (GONE);
            museum7.setVisibility (GONE);
            museum8.setVisibility (GONE);
            museum9.setVisibility (GONE);
            templom7.setVisibility (GONE);
            templom8.setVisibility (GONE);
            templom9.setVisibility (GONE);
            templom10.setVisibility (GONE);
            templom11.setVisibility (GONE);
            templom12.setVisibility (GONE);
            templom13.setVisibility (GONE);
            monuments3.setVisibility (GONE);
            monuments4.setVisibility (GONE);
            monuments5.setVisibility (GONE);
            monuments6.setVisibility (GONE);
            monuments7.setVisibility (GONE);
            monuments8.setVisibility (GONE);
            monuments9.setVisibility (GONE);
            monuments10.setVisibility (GONE);
            monuments11.setVisibility (GONE);
            //other6.setVisibility (GONE);
            other7.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= getResources().getString(R.string.veszpmus1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);
                        startActivity(intent);
                    }

                });
                museum2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum2.getText().toString();
                        String keyword1= getResources().getString(R.string.veszpmus2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);
                        startActivity(intent);
                    }

                });
                museum3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum3.getText().toString();
                        String keyword1= getResources().getString(R.string.veszpmus3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);
                        startActivity(intent);
                    }

                });
                //templomok
                templom1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText().toString();
                        String keyword1= getResources().getString(R.string.veszptemp1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom2.getText().toString();
                        String keyword1= getResources().getString(R.string.veszptemp2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom3.getText().toString();
                        String keyword1= getResources().getString(R.string.veszptemp3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom4.getText().toString();
                        String keyword1= getResources().getString(R.string.veszptemp4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom5.getText().toString();
                        String keyword1= getResources().getString(R.string.veszptemp5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                templom6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom6.getText().toString();
                        String keyword1= getResources().getString(R.string.veszptemp6);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //műemlékek
                monuments1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText().toString();
                        String keyword1= getResources().getString(R.string.veszpmon1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments2.getText().toString();
                        String keyword1= getResources().getString(R.string.veszpmon2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //egyéb
                other1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other1.getText().toString();
                        String keyword1= getResources().getString(R.string.veszpoth1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other2.getText().toString();
                        String keyword1= getResources().getString(R.string.veszpoth2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other3.getText().toString();
                        String keyword1= getResources().getString(R.string.veszpoth3);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other4.getText().toString();
                        String keyword1= getResources().getString(R.string.veszpoth4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                other5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other5.getText().toString();
                        String keyword1= getResources().getString(R.string.veszpoth5);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
            other6.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = other6.getText().toString();
                    String keyword1= getResources().getString(R.string.veszpoth6);
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    startActivity(intent);
                }

            });

        }
//----------------TIHANY--------------------------------------
        else if (keyword.equals ("Tihany")) {
            templom1.setText(getResources().getString(R.string.tihanytemplom1));
            monuments1.setText(getResources().getString(R.string.tihanymonuments1));
            monuments2.setText(getResources().getString(R.string.tihanymonuments2));
            other2.setText(getResources().getString(R.string.tihanyother2));
            other4.setText(getResources().getString(R.string.tihanyother4));

            museum2.setVisibility (GONE);
            museum3.setVisibility (GONE);
            museum4.setVisibility (GONE);
            museum5.setVisibility (GONE);
            museum6.setVisibility (GONE);
            museum7.setVisibility (GONE);
            museum8.setVisibility (GONE);
            museum9.setVisibility (GONE);
            monuments3.setVisibility (GONE);
            monuments4.setVisibility (GONE);
            monuments5.setVisibility (GONE);
            monuments6.setVisibility (GONE);
            monuments7.setVisibility (GONE);
            monuments8.setVisibility (GONE);
            monuments9.setVisibility (GONE);
            monuments10.setVisibility (GONE);
            monuments11.setVisibility (GONE);
            templom2.setVisibility (GONE);
            templom3.setVisibility (GONE);
            templom4.setVisibility (GONE);
            templom5.setVisibility (GONE);
            templom6.setVisibility (GONE);
            templom7.setVisibility (GONE);
            templom8.setVisibility (GONE);
            templom9.setVisibility (GONE);
            templom10.setVisibility (GONE);
            templom11.setVisibility (GONE);
            templom12.setVisibility (GONE);
            templom13.setVisibility (GONE);
            other1.setVisibility (GONE);
            other3.setVisibility (GONE);
            other5.setVisibility (GONE);
            other6.setVisibility (GONE);
            other7.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

            if(language.equals ("hu")||language.equals ("de")) {
                museum1.setText(getResources().getString(R.string.tihanymuseum1));
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1 = getResources().getString(R.string.tihanymus1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);
                        startActivity(intent);
                    }

                });
            }
            else if(language.equals ("en"))
            {

                //múzeumok
                museum1.setVisibility (GONE);
                museums.setVisibility (GONE);
            }
                //templomok
                templom1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText().toString();
                        String keyword1= getResources().getString(R.string.tihanytemp1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //műemlékek
                monuments1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText().toString();
                        String keyword1= getResources().getString(R.string.tihanymon1);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                monuments2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments2.getText().toString();
                        String keyword1= getResources().getString(R.string.tihanymon2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
                //egyéb
                other2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other2.getText().toString();
                        String keyword1= getResources().getString(R.string.tihanyoth2);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });

                other4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other4.getText().toString();
                        String keyword1= getResources().getString(R.string.tihanyoth4);
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        startActivity(intent);
                    }

                });
            }
    }
}
