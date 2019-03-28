package com.example.laura.touristapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.paperdb.Paper;

import static android.view.View.GONE;

public class PlacesActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_places);
        Bundle extra = getIntent().getExtras();
        final String keyword = extra.getString("key");
        String language= Paper.book().read("language");


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
        if(keyword.equals ("Keszthely")){
            museum1.setText(getResources().getString(R.string.keszthmuseum1));
            museum3.setText(getResources().getString(R.string.keszthmuseum3));
            museum8.setText(getResources().getString(R.string.keszthmuseum8));
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

            museum9.setVisibility (GONE);
            monuments5.setVisibility(GONE);
            monuments6.setVisibility (GONE);
            monuments7.setVisibility (GONE);
            monuments8.setVisibility (GONE);
            monuments9.setVisibility (GONE);
            monuments10.setVisibility (GONE);
            monuments11.setVisibility (GONE);
            templom5.setVisibility (GONE);
            templom6.setVisibility (GONE);
            templom7.setVisibility (GONE);
            templom8.setVisibility (GONE);
            templom9.setVisibility (GONE);
            templom10.setVisibility (GONE);
            templom11.setVisibility (GONE);
            templom12.setVisibility (GONE);
            templom13.setVisibility (GONE);
            other4.setVisibility (GONE);
            other5.setVisibility (GONE);
            other6.setVisibility (GONE);
            other7.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

            if(language.equals ("hu"))
        {
            museum4.setText(getResources().getString(R.string.keszthmuseum4));
            museum6.setText(getResources().getString(R.string.keszthmuseum6));
            museum7.setText(getResources().getString(R.string.keszthmuseum7));
            museum5.setText(getResources().getString(R.string.keszthmuseum5));
            museum2.setText(getResources().getString(R.string.keszthmuseum2));
            //múzeumok
            museum1.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                    String placetitle = museum1.getText().toString();
                    String keyword1= "muzeumok/balatoni_muzeum";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/cadillac-muzeum-veteran-auto-kiallitas";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                       /* System.out.println(keyword);
                        System.out.println(placetitle);
                        System.out.println(keyword1);*/
                        //intent.putExtra("key1", lang);
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
                    String keyword1= "muzeumok/helikon_kastelymuzeum_festetics_kastely";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                    String keyword1= "muzeumok/georgikon_majormuzeum";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword); //város neve
                    intent.putExtra("key1", placetitle); //látványosság neve
                    intent.putExtra("key2", keyword1); //url cime

                    //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/muzeum-zoo";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/radio_es_televizio_muzeum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/surber-zeneautomata-es-fonograf-muzeuma";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/muzeumok/szendrey-julia-emlekhaz";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/templomok/evangelikus_templom";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/templomok/kis_szent_terez_bazilika";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
            String keyword1= "latnivalok/templomok/gotikus_plebaniatemplom";
                //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                intent.putExtra("key", keyword);
            intent.putExtra("key1", placetitle);
            intent.putExtra("key2", keyword1);

                //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/templomok/reformatus_templom";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/muemlekek/festetics-mauzoleum";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/muemlekek/georgikon-haz";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/szobrok--emlekmuvek/helikon-emlekmu";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/muemlekek/varoshaza_";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/egyeb/balaton-kongresszusi-kozpont-es-szinhaz";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/egyeb/setaloutca";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                    String keyword1= "latnivalok/egyeb/szigetfurdo";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
                    startActivity(intent);
                }

            });
        }
        else if(language.equals ("en"))
        {
                //museums
                museum1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText ( ).toString ( );
                        String keyword1 = "museums/balatoni-muzeum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum3.getText ( ).toString ( );
                        String keyword1 = "museums/festetics-kastely";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum8.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum8.getText ( ).toString ( );
                        String keyword1 = "museums/szendrey-julia-emlekhaz";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum2.setVisibility (GONE);
                museum4.setVisibility (GONE);
                museum5.setVisibility (GONE);
                museum6.setVisibility (GONE);
                museum7.setVisibility (GONE);
                //temples
                templom1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText ( ).toString ( );
                        String keyword1 = "temples/evangelikus_templom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom2.getText ( ).toString ( );
                        String keyword1 = "temples/kis_szent_terez_bazilika";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom3.getText ( ).toString ( );
                        String keyword1 = "temples/gotikus_plebaniatemplom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom4.getText ( ).toString ( );
                        String keyword1 = "temples/reformatus_templom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText ( ).toString ( );
                        String keyword1 = "monuments/festetics-mauzoleum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments2.getText ( ).toString ( );
                        String keyword1 = "monuments/georgikon-haz";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments3.getText ( ).toString ( );
                        String keyword1 = "sculptures--monuments/helikon-emlekmu";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments4.getText ( ).toString ( );
                        String keyword1 = "monuments/varoshaza_";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                //egyéb
                other1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other1.getText ( ).toString ( );
                        String keyword1 = "other/balaton-kongresszusi-kozpont-es-szinhaz";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                other2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other2.getText ( ).toString ( );
                        String keyword1 = "other/setaloutca";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                other3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other3.getText ( ).toString ( );
                        String keyword1 = "other/szigetfurdo";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });

            }

        else if(language.equals ("de"))
        {
                //museums
                museum1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText ( ).toString ( );
                        String keyword1 = "museen/balatoni-muzeum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum3.getText ( ).toString ( );
                        String keyword1 = "museen/festetics-kastely";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum8.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum8.getText ( ).toString ( );
                        String keyword1 = "museen/szendrey-julia-emlekhaz";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
            museum2.setVisibility (GONE);
            museum4.setVisibility (GONE);
            museum5.setVisibility (GONE);
            museum6.setVisibility (GONE);
            museum7.setVisibility (GONE);
                templom1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText ( ).toString ( );
                        String keyword1 = "kirchen/evangelikus_templom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom2.getText ( ).toString ( );
                        String keyword1 = "kirchen/kis_szent_terez_bazilika";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom3.getText ( ).toString ( );
                        String keyword1 = "kirchen/gotikus_plebaniatemplom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom4.getText ( ).toString ( );
                        String keyword1 = "kirchen/reformatus_templom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText ( ).toString ( );
                        String keyword1 = "denkmler/festetics-mauzoleum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments2.getText ( ).toString ( );
                        String keyword1 = "denkmler/georgikon-haz";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments3.getText ( ).toString ( );
                        String keyword1 = "skulpturen--denkmler/helikon-emlekmu";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments4.getText ( ).toString ( );
                        String keyword1 = "denkmler/varoshaza_";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                //egyéb
                other1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other1.getText ( ).toString ( );
                        String keyword1 = "andere/balaton-kongresszusi-kozpont-es-szinhaz";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                other2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other2.getText ( ).toString ( );
                        String keyword1 = "andere/setaloutca";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                other3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other3.getText ( ).toString ( );
                        String keyword1 = "andere/szigetfurdo";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);
                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
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
            other1.setText(getResources().getString(R.string.egerother1));
            other2.setText(getResources().getString(R.string.egerother2));
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
            other5.setVisibility (GONE);
            other6.setVisibility (GONE);
            other7.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

            if(language.equals ("hu"))
            {
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "muzeumok/beatles-muzeum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/erseki-palota-eger";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "egri-var/bortonkiallitas";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/foegyhazmegyei-konyvtar";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/gardonyi-geza-emlekhaz";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "egri-var/panoptikum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/varazstorony";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/varos-a-varos-alatt";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "templomok/egri-bazilika";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "templomok/minorita-templom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "templomok/rac-templom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "templomok/servita-templom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "egri-var/gotikus-palota-1";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "egri-var/hosok-terme";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "egri-var/kazamata-eger-titkos-jaratok-a-var-alatt";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "egri-var/kalvaria-domb";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "torok-emlekek/minaret";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "erdekessegek/arviztabla";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "erdekessegek/lakatfal";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "torok-emlekek/torok-furdo";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "erdekessegek/zsolnay-kut";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });

            }
            else if(language.equals ("en")) {
                //múzeumok
                museum1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText ( ).toString ( );
                        String keyword1 = "museums/beatles-museum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum2.getText ( ).toString ( );
                        String keyword1 = "museums/archbishops-palace-and-visitor-center";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        startActivity (intent);
                    }

                });
                museum3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum3.getText ( ).toString ( );
                        String keyword1 = "castle-of-eger/prison-exhibition";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum4.getText ( ).toString ( );
                        String keyword1 = "museums/archdiocese-library";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword); //város neve
                        intent.putExtra ("key1", placetitle); //látványosság neve
                        intent.putExtra ("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum5.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum5.getText ( ).toString ( );
                        String keyword1 = "museums/geza-gardonyi-memorial-house";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword); //város neve
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum6.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum6.getText ( ).toString ( );
                        String keyword1 = "castle-of-eger/waxworks";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum7.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum7.getText ( ).toString ( );
                        String keyword1 = "museums/magictower";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum8.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum8.getText ( ).toString ( );
                        String keyword1 = "museums/town-under-the-town";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                //templomok
                templom1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText ( ).toString ( );
                        String keyword1 = "churches/basilica-of-eger";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom2.getText ( ).toString ( );
                        String keyword1 = "churches/minorite-church";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom3.getText ( ).toString ( );
                        String keyword1 = "churches/serbian-church";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom4.getText ( ).toString ( );
                        String keyword1 = "churches/servite-church";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                //műemlékek
                monuments1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText ( ).toString ( );
                        String keyword1 = "castle-of-eger/gothic-bishop-s-palace";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments2.getText ( ).toString ( );
                        String keyword1 = "castle-of-eger/heroes-hall";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments3.getText ( ).toString ( );
                        String keyword1 = "castle-of-eger/casamates";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments4.getText ( ).toString ( );
                        String keyword1 = "castle-of-eger/calvary-hill";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments5.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments5.getText ( ).toString ( );
                        String keyword1 = "turkish-memories/minaret-1";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                //egyéb
                other1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other1.getText ( ).toString ( );
                        String keyword1 = "curiosities/flood-signs";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                other2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other2.getText ( ).toString ( );
                        String keyword1 = "curiosities/padlock-wall";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                other3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other3.getText ( ).toString ( );
                        String keyword1 = "turkish-memories/turkish-bath";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                other4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other4.getText ( ).toString ( );
                        String keyword1 = "curiosities/zsolnay-fountain";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
            }
            else if(language.equals ("de")) {
                //múzeumok
                museum1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText ( ).toString ( );
                        String keyword1 = "museen/beatles-museum-1";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum2.getText ( ).toString ( );
                        String keyword1 = "museen/erzbischofspalast-und-besucherzentrum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        startActivity (intent);
                    }

                });
                museum3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum3.getText ( ).toString ( );
                        String keyword1 = "burg-von-eger/gefaengnisausstellung";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum4.getText ( ).toString ( );
                        String keyword1 = "museen/bibliothek-der-erzdiozese";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword); //város neve
                        intent.putExtra ("key1", placetitle); //látványosság neve
                        intent.putExtra ("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum5.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum5.getText ( ).toString ( );
                        String keyword1 = "museen/geza-gardonyi-gedenkhaus";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword); //város neve
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum6.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum6.getText ( ).toString ( );
                        String keyword1 = "burg-von-eger/panoptikum-1";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum7.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum7.getText ( ).toString ( );
                        String keyword1 = "museen/zauberturm";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum8.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum8.getText ( ).toString ( );
                        String keyword1 = "museen/stadt-unter-der-stadt";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });

                //templomok
                templom1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText ( ).toString ( );
                        String keyword1 = "kirchen/basilika-von-eger";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom2.getText ( ).toString ( );
                        String keyword1 = "kirchen/minoritenkirche";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom3.getText ( ).toString ( );
                        String keyword1 = "kirchen/raizenkirche";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                templom4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom4.getText ( ).toString ( );
                        String keyword1 = "kirchen/servitenkirche";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                //műemlékek
                monuments1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText ( ).toString ( );
                        String keyword1 = "burg-von-eger/gotischer-bischofspalast";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments2.getText ( ).toString ( );
                        String keyword1 = "burg-von-eger/heldensaal";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments3.getText ( ).toString ( );
                        String keyword1 = "burg-von-eger/kasematte";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments4.getText ( ).toString ( );
                        String keyword1 = "burg-von-eger/kalvarienhugel";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                monuments5.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments5.getText ( ).toString ( );
                        String keyword1 = "turkische-andenken/minarett";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                //egyéb
                other1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other1.getText ( ).toString ( );
                        String keyword1 = "interessantes/hochwasserschilder";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                other2.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other2.getText ( ).toString ( );
                        String keyword1 = "interessantes/schlosserwand";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                other3.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other3.getText ( ).toString ( );
                        String keyword1 = "turkische-andenken/turkisches-bad";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                other4.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other4.getText ( ).toString ( );
                        String keyword1 = "interessantes/zsolnay-brunnen";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra ("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
            }
        }
//----------------PÉCS--------------------------------------
        else if (keyword.equals ("Pécs")) {
            museum1.setText(getResources().getString(R.string.pecsmuseum1));
            museum2.setText(getResources().getString(R.string.pecsmuseum2));
            museum3.setText(getResources().getString(R.string.pecsmuseum3));
            museum4.setText(getResources().getString(R.string.pecsmuseum4));
            museum5.setText(getResources().getString(R.string.pecsmuseum1));
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
            monuments8.setText(getResources().getString(R.string.pecsmonuments8));
            monuments9.setText(getResources().getString(R.string.pecsmonuments9));
            monuments10.setText(getResources().getString(R.string.pecsmonuments10));
            other1.setText(getResources().getString(R.string.pecsother1));
            other2.setText(getResources().getString(R.string.pecsother2));
            other3.setText(getResources().getString(R.string.pecsother3));
            other4.setText(getResources().getString(R.string.pecsother4));
            other5.setText(getResources().getString(R.string.pecsother5));

            museum9.setVisibility (GONE);
            templom10.setVisibility (GONE);
            templom11.setVisibility (GONE);
            templom12.setVisibility (GONE);
            templom13.setVisibility (GONE);
            monuments11.setVisibility (GONE);
            other6.setVisibility (GONE);
            other7.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

            if(language.equals ("hu"))
            {
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "muzeumok-galeriak/csontvary-muzeum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok-galeriak/neprajzi-muzeum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "muzeumok-galeriak/bazilika-es-kornyeke/puspoki-palota-es-a-titkos-atjaro.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok-galeriak/termeszettudomanyi-muzeum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok-galeriak/vasarely-muzeum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok-galeriak/vasvary-haz.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok-galeriak/varostorteneti-muzeum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok-galeriak/zsolnay-csalad-es-gyartorteneti-kiallitas.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "vallasi-ertekek/evangelikus-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "vallasi-ertekek/ferences-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "a-torok-kor/gazi-kaszim-pasa-dzsamija.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "a-torok-kor/jakovali-hasszan-pasa-dzsamija.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "vallasi-ertekek/lyceum-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "vallasi-ertekek/pius-templom.html ";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "bazilika-es-kornyeke/a-szekesegyhaz.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "bazilika-es-kornyeke/zardatemplom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "vallasi-ertekek/zsinagoga.html ";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                    String keyword1= "szobrok-kutak/angster-emlekmu.html";
                    //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                    intent.putExtra("key", keyword);
                    intent.putExtra("key1", placetitle);
                    intent.putExtra("key2", keyword1);

                    //intent.putExtra("key1", lang);
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
                        String keyword1= "bazilika-es-kornyeke/barbakan.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szobrok-kutak/hunyadi-janos-lovasszobra.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szobrok-kutak/kossuth-lajos-szobra.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "bazilika-es-kornyeke/kozepkori-egyetem.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "bazilika-es-kornyeke/liszt-ferencszobor.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "unesco-vilagorokseg/okereszteny-mauzoleum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szobrok-kutak/orokmecsesemlekmu.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szobrok-kutak/szentharomsag-szobor.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "zsolnayak-varosa/zsolnay-kut.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "unesco-vilagorokseg/cella-septichora-latogatokozpont.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "erdekes-epuletek/kodaly-kozpont.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "egyeb-erdekessegek/lakatok.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "erdekes-epuletek/pecsi-nemzeti-szinhaz.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "erdekes-epuletek/varoshaza.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });

            }
            else if(language.equals ("en"))
            {
                //museums
                museum1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText ( ).toString ( );
                        String keyword1 = "museums-art-galleries/csontvary-museum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum2.getText().toString();
                        String keyword1= "museums-art-galleries/museum-of-ethnography.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "cathedralandbishophric/bishops-palace-and-the-secret-passage.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museums-art-galleries/natural-history-museum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museums-art-galleries/vasarely-museum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museums-art-galleries/vasvary-house.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museums-art-galleries/the-museum-of-the-history-of-the-city-of-pecs.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museums-art-galleries/zsolnay-family-and-factory-history-exhibition.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "religious-values/lutheran-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "turkish-age/franciscan-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "turkish-age/mosque-of-pasha-gazi-kasim.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "turkish-age/the-mosque-of-pasha-yakovali-hassan.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "religious-values/lyceum-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "religious-values/pius-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "cathedralandbishophric/cathedral.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "cathedralandbishophric/convent-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "religious-values/synagogue.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
                //műemlékek,emlékművek
                monuments1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText().toString();
                        String keyword1= "statues-fountains/angster-monument.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "cathedralandbishophric/barbican.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statues-fountains/equestrian-statue-of-janos-hunyadi.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statues-fountains/statue-of-lajos-kossuth.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "cathedralandbishophric/medieval-university.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "cathedralandbishophric/statue-of-ferenc-liszt.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "unesco-world-heritage/early-christian-mausoleum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statues-fountains/eternal-flame-monument.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statues-fountains/statue-of-the-holy-trinity.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "zsolnay-tradition/zsolnay-fountain.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "unesco-world-heritage/cella-septichora-visitors-centre.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "interesting-buildings/kodaly-centre.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "other-attractions/padlocks.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "interesting-buildings/national-theatre-of-pecs-1-theatre-square.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "interesting-buildings/city-hall-1-szechenyi-square.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }

            else if(language.equals ("de"))
            {
                museum1.setOnClickListener (new View.OnClickListener ( ) {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText ( ).toString ( );
                        String keyword1 = "museen-galerien/csontvary-museum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra ("key1", placetitle);
                        intent.putExtra ("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity (intent);
                    }

                });
                museum2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum2.getText().toString();
                        String keyword1= "museen-galerien/museum-fur-volkerkunde.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                       /* System.out.println(keyword);
                        System.out.println(placetitle);
                        System.out.println(keyword1);*/
                        //intent.putExtra("key1", lang);
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
                        String keyword1= "basilika-und-bischofsamt/bischofsburg.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museen-galerien/naturwissenschaftlichen-abteilung.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museen-galerien/vasarelymuseum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museen-galerien/vasvary-haus.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museen-galerien/stadthistorische-ausstellung.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museen-galerien/ausstellung-zur-geschichte-der-familie-zsolnay-und-ihrer-fabrik.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
               /* museum9.setVisibility (GONE);
               */

                //templomok
                templom1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText().toString();
                        String keyword1= "religiose-werte/evangelische-kirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "religiose-werte/franziskanerkirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "turkische-zeit/moschee-des-paschas-gasi-kassim.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "turkische-zeit/moschee-von-pascha-jakovali-hassan.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "religiose-werte/lyzeumkirche-kiraly-str-44.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "religiose-werte/pius-kirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "basilika-und-bischofsamt/dom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "basilika-und-bischofsamt/klosterkirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "religiose-werte/synagoge-furdo-str-1.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
                /*templom10.setVisibility (GONE);
                templom11.setVisibility (GONE);
                templom12.setVisibility (GONE);
                templom13.setVisibility (GONE);*/
                //műemlékek,emlékművek
                monuments1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = monuments1.getText().toString();
                        String keyword1= "interassante-gebaude/die-ehemalige-angster-orgelfabrik.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "basilika-und-bischofsamt/barbakane.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statuen-brunnen/reiterdenkmal-von-janos-hunyadi.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statuen-brunnen/statue-von-lajos-kossuth.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "basilika-und-bischofsamt/mittelalterliche-universitat.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "basilika-und-bischofsamt/franz-liszt-statue.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "unesco-weltkulturerbe/fruhchristliches-mausoleum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statuen-brunnen/ewiglichtdenkmal.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statuen-brunnen/dreifaltigkeitssaule.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "die-stadt-der-zsolnay/zsolnay-brunnen.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
                //monuments11.setVisibility (GONE);
                //egyéb
                other1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = other1.getText().toString();
                        String keyword1= "unesco-weltkulturerbe/besucherzentrum-cella-septichora.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "interassante-gebaude/kodalyzentrum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "weitere-sehenswurdigkeiten/schlosser.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "interassante-gebaude/pecser-nationaltheater.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "interassante-gebaude/rathaus-szechenyi-platz-1.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
                /*other6.setVisibility (GONE);
                other7.setVisibility (GONE);
                other8.setVisibility (GONE);
                other9.setVisibility (GONE);*/
            }
        }
//----------------SOPRON--------------------------------------
        else if (keyword.equals ("Sopron")) {
            museum1.setText(getResources().getString(R.string.sopronmuseum1));
            museum2.setText(getResources().getString(R.string.sopronmuseum2));
            museum3.setText(getResources().getString(R.string.sopronmuseum3));
            museum4.setText(getResources().getString(R.string.sopronmuseum4));
            museum5.setText(getResources().getString(R.string.sopronmuseum1));
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
            monuments3.setText(getResources().getString(R.string.sopronmonuments3));
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

            other4.setVisibility (GONE);
            other5.setVisibility (GONE);
            other6.setVisibility (GONE);
            other7.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

            if(language.equals ("hu"))
            {
                monuments6.setText(getResources().getString(R.string.sopronmonuments6));
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "muzeumok/erdeszeti-muzeum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "muzeumok/forum-scarbantiae.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "muzeumok/hatartalan-tortenet.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "muzeumok/kozponti-banyaszati-muzeum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/ligneum--soe-latogatokozpont.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/regeszeti-kiallitas.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/romai-kori-kotar.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/stornogyujtemeny.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muzeumok/ozsinagoga.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/arpadhazi-szent-margit-templom-julianum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/bences-kecske-templom-es-kaptalan-terem.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/domonkos-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/evangelikus-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/jezus-szive-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/keresztelo-szent-janos-johannita-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/papreti-zsinagoga.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/reformatus-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/szent-gyorgy-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/szent-mihaly-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/szent-orsolya-templom-es-zarda.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/szent-imre-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szakralis-emlekek/szent-istvan-templom.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/bezeredj-haz.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/fabriciushaz.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szobrok/piknikemlekmu--attores.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/postapalota.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/regi-kaszino.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/scarbantia-regeszeti-park.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/stornohaz.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szobrok/szentharomsagszobor.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/tuztorony.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/varoshaza.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/zichymesko-palota.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/petofi-szinhaz.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "kastelyok-es-varak/soproni-varfalsetany.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "kastelyok-es-varak/tarodi-var.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
            else if(language.equals ("en"))
            {
                monuments6.setVisibility (GONE);
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "museums/forestry-museum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "museums/forum-of-scarbantiae--museum-exhibition-site.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "museums/boundless-history.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "museums/central-museum-of-mining-sopron.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museums/ligneum--visitor-centre-of-the-sopron-university.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museums/archaeology-exhibition.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museums/roman-lapidary.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museums/storno-collection.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museums/old-synagogue.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/church-of-st-margaret-of-hungary-julianeum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/benedictine-goat-church-and-chapter-hall.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/dominican-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/lutheran-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/church-of-the-sacred-heart-of-jesus.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/church-of-st-john-the-baptist-johannite.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/synagogue-in-papret.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/reformed-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/the-domechurch-of-st-gyorgy-george.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/st-michaels-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/ursuline-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/church-of-st-imre-emmerich.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sacral-monuments/st-stephen-church.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "historical-monuments/bezeredj-house.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "historical-monuments/fabricius-house.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statues/picnic-memorial--breakrthrough.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "historical-monuments/post-palace.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "historical-monuments/old-casino.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "historical-monuments/storno-house.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statues/holy-trinity-statue.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "historical-monuments/firewatch-tower.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "historical-monuments/town-hall.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "historical-monuments/zichymesko-palace.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "historical-monuments/petofi-theater.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "manors-and-castles/sopron-castle-wall-promenade.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "manors-and-castles/tarodi-castle.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
            else if(language.equals ("de"))
            {
                monuments6.setVisibility (GONE);
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "museen/forstmuseum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "museen/forum-scarbantiae.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "museen/geschichte-ohne-grenzen.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "museen/das-zentrale-bergbaumuseum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museen/besucherzentrum-ligneum-der-soproner-universitat.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museen/archaologische-ausstellung.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museen/romisches-lapidarium.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "museen/storno-sammlung.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/alte-synagoge.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/kirche-sankt-margarethe-von-ungarn-julianum.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/benediktinerkirche-ziegenkirche-und-kapitelsaal.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/dominikanerkirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/evangelische-kirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/herzjesukirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/kirche-sankt-johannes-der-taufer-johanniterkirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/synagoge-in-papret.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/calvinistische-kirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/die-domkirche-sankt-georg.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/sankt-michaeliskirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/ursulinenkirche.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/kirche-sankt-emmerich-szent-imre.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sakrale-denkmaler/kirche-sankt-stephan.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "baudenkmaler/bezeredjhaus.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "baudenkmaler/fabriciushaus.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statuen/picknickdenkmal-umbruch.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "baudenkmaler/postpalast.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "baudenkmaler/altes-kasino.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "baudenkmaler/stornohaus.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "statuen/dreifaltigkeitssaule.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "baudenkmaler/feuerturm.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "baudenkmaler/rathaus.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "baudenkmaler/zichymesko-palais.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "baudenkmaler/theater-petofi.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "schlosser-und-burgen/soproner-stadtmauerpromenade.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "schlosser-und-burgen/burg-tarodi.html";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
        }
//----------------SZEGED--------------------------------------
        else if (keyword.equals ("Szeged")) {
            museum1.setText(getResources().getString(R.string.szegedmuseum1));
            museum2.setText(getResources().getString(R.string.szegedmuseum2));
            museum3.setText(getResources().getString(R.string.szegedmuseum3));
            museum4.setText(getResources().getString(R.string.szegedmuseum4));
            museum5.setText(getResources().getString(R.string.szegedmuseum1));
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
            if(language.equals ("hu"))
            {
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "fekete-haz/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "mora-ferenc-muzeum/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "reok-palota-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "szent-gyorgyi-albert-agora/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szent-gyorgyi-albert-emlekszoba/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "varmuzeum-es-kotar/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "alsovarosi-templom/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "fogadalmi-templom/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "reformatus-templom/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "regi-zsinagoga/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szerb-templom/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "uj-zsinagoga-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "beregi-haz/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "berhaz-vasalohaz-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "deutsch-palota/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "domotor-torony/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "goldschmidt-palota/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "grof-palota";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "marer-haz";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "moricz-haz/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "raichl-palota";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "reformatus-palota/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "schaffer-palota/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "ferences-latogatokozpont/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "jozsef-attila-tanulmanyi-es-informacios-kozpont/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "somogyi-konyvtar/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szegedi-hadi-park/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szegedi-nemzeti-szinhaz-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szent-istvan-teri-viztorony/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "vadaspark";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "varoshaza";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "zenelo-ora";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
            else if(language.equals ("en"))
            {
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "black-house/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "mora-ferenc-museum/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "reok-palace-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "szent-gyorgyi-albert-agora-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "albert-szent-gyorgyi-memorial-room/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "fortress-museum-and-stone-collection/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "alsovarosi-church/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "votive-church-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "protestant-church/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "old-synagogue/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "serbian-orthodox-church/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "new-synagogue-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "beregi-house/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "appartemant-house-iron-house/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "deutsch-palace/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "st-demetrius-tower/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "goldschmidt-palace/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "grof-palace/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "marer-house/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "moricz-house/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "raichl-house/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "422046-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "schaffer-palace/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "franciscan-visitor-centre-in-alsovaros/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "attila-jozsef-education-and-information-centre/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "somogyi-library/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szeged-military-park/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szeged-national-theatre/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "water-tower-in-st-steven-square/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "en/zoo-szeged/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "city-hall/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "musical-clock/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
            else if(language.equals ("de"))
            {
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "schwarzes-haus/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "museum-ferenc-mora/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "palais-reok-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "albert-szent-gyorgyi-agora/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle); //látványosság neve
                        intent.putExtra("key2", keyword1); //url cime

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "gedenkzimmer-albert-szent-gyorgyis/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword); //város neve
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "burgmuseum-und-lapidarium/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "unterstadtische-kirche/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "die-votivkirche/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "reformierte-kirche/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "alte-synagoge/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "serbische-kirche/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "neue-synagoge-2/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "beregi-haus/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "das-mietshaus-vasalo-haus/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "palais-deutsch/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "demetriusturm/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "der-goldschmidt-palast/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "palais-grof/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "das-marer-haus/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "haus-moricz/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "der-raichl-palast/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "der-reformierte-palast/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "der-schaffer-palast/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "besucherzentrum-der-franziskaner-in-der-unterstadt/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "studien-und-infozentrum-attila-jozsef/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "bibliothek-somogyi/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szegeder-militarpark/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "nationaltheater-von-szeged/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "wasserturm-auf-dem-szent-istvan-platz/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "de/tierpark/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "rathaus/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "musizierende-uhr/";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
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
            other6.setVisibility (GONE);
            other7.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

            if(language.equals ("hu"))
            {
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "boldog-gizella-foegyhazmegyei-gyujtemeny";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "dubniczay-palota-laszlo-karoly-gyujtemeny";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "hosok-kapuja";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "gizella-kapolna";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "ferences-templom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "regina-mundi-a-vilag-kiralyneja-plebaniatemplom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szent-mihaly-bazilika";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szent-imre-piarista-es-helyorsegi-templom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "veszpremvolgyi-gorog-apacakolostor";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "tuztorony";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "seta-a-varban";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "herendi-porcelanium";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szalezianum-erseksegi-turisztikai-kozpont";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "szent-istvan-volgyhid";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "varoshaza";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "a-veszpremi-allatkert";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
            else if(language.equals ("en"))
            {
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "beatified-gizella-diocese-collection";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "dubniczay-house-laszlo-karoly-collection";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "heroes-gate";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "gisella-chapel";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "franciscan-church";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "regina-mundi-church";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "saint-michaels-cathedral";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "piarist-and-garrison-church";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "ruins-of-the-greek-orthodox-nunnery-and-jesuit-church";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "fire-lookout-tower";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "walk-in-the-castle";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "herend-porcelanium";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "salesianum-archbishoprics-touristic-centre";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "saint-stephan-viaduct";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "city-hall";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "veszprem-zoo";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
            else if(language.equals ("de"))
            {
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "bischofliche-sammlung-selige-gisela";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "dubniczay-haus";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "das-heldentor";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "gisela-kapelle";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "die-franziskanerkirche";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "die-regina-mundi-kirche";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "st-michael-dom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "st-emmerich-piaristen-und-garnisonkirche";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "die-klosterruinen-der-nonnen-aus-dem-veszprem-tal-und-der-jesuisten-kirche";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "feuerturm";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "spaziergang-in-der-burg";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "besucherzentrum-porcelanium-in-herend";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "salesianum-erzbistum-veszprem-besucherzentrum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "sankt-stephan-talbrucke";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "rathaus";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "veszpremer-zoo";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }

        }
//----------------TIHANY--------------------------------------
        else if (keyword.equals ("Tihany")) {
            museum1.setText(getResources().getString(R.string.tihanymuseum1));
            templom1.setText(getResources().getString(R.string.tihanytemplom1));
            monuments1.setText(getResources().getString(R.string.tihanymonuments1));
            monuments2.setText(getResources().getString(R.string.tihanymonuments2));
            other1.setText(getResources().getString(R.string.tihanyother1));
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
            other5.setVisibility (GONE);
            other6.setVisibility (GONE);
            other7.setVisibility (GONE);
            other8.setVisibility (GONE);
            other9.setVisibility (GONE);

            if(language.equals ("hu"))
            {
                other3.setText(getResources().getString(R.string.tihanyother3));
                //múzeumok
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = museum1.getText().toString();
                        String keyword1= "muemlekek/7-bences-apatsagi-muzeum";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
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
                        String keyword1= "muemlekek/5-bences-apatsag-barokk-temploma";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/6-i-andras-kriptaja";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek/8-a-nepi-epiteszet-emlekei";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "latnivalok-inner/143-akaszto-domb";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "latnivalok-inner/24-apati-templomrom";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "latnivalok-inner/22-levendula-haz";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "latnivalok-inner/23-remetetelep-baratlakasok";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
            else if(language.equals ("en"))
            {
                //múzeumok
                museum1.setVisibility (GONE);
                other3.setVisibility (GONE);
                //templomok
                templom1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText().toString();
                        String keyword1= "artmemorials/140-bences-apatsag-barokk-temploma-en";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "artmemorials/158-i-andras-kriptaja-en";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "artmemorials/138-a-nepi-epiteszet-emlekei-en";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "25-akaszto-domb-en";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "144-apati-templomrom-en";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "151-remetetelep-baratlakasok-en";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
            else if(language.equals ("de"))
            {
                museum1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText().toString();
                        String keyword1= "muemlekek-de/280-bences-apatsagi-muzeum-de";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
                other3.setVisibility (GONE);
                //templomok
                templom1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(PlacesActivity.this, PDescriptionActivity.class);
                        String placetitle = templom1.getText().toString();
                        String keyword1= "muemlekek-de/210-bences-apatsag-barokk-temploma-de";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek-de/218-i-andras-kriptaja-de";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "muemlekek-de/208-a-nepi-epiteszet-emlekei-de";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "latnivalok-inner-de/225-akaszto-domb-de";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "latnivalok-inner-de/226-apati-templomrom-de";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
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
                        String keyword1= "latnivalok-inner-de/233-remetetelep-baratlakasok-de";
                        //Toast.makeText(MainActivity.this, keyword1, Toast.LENGTH_SHORT).show();
                        intent.putExtra("key", keyword);
                        intent.putExtra("key1", placetitle);
                        intent.putExtra("key2", keyword1);

                        //intent.putExtra("key1", lang);
                        startActivity(intent);
                    }

                });
            }
        }
    }
}
