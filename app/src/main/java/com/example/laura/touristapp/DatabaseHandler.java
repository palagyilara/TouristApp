package com.example.laura.touristapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final String TAG="DatabaseHelper";

    //adatbázis verzió
    public static int DATABASE_VERSION=1;

    //adatbázis neve
    public static final String DATABASE_NAME="cityinfos.db";

    //tábla név
    public static final String TABLE_NAME="cityinfos";
    public static final String TABLE1_NAME="sightinfos";

    //tábla oszlopok
    public static final String ID="id";
    public static final String CITYNAME="cityname";
    public static final String CITYINFO_HU="cityinfohu";
    public static final String CITYINFO_EN="cityinfoen";
    public static final String CITYINFO_DE="cityinfode";

    public static final String ID1="id";
    public static final String CITYNAME1="cityname";
    public static final String PLACENAME_HU="pnamehu";
    public static final String PLACENAME_EN="pnameen";
    public static final String PLACENAME_DE="pnamede";
    public static final String SIGHTINFO_HU="sightinfohu";
    public static final String SIGHTINFO_EN="sightinfoen";
    public static final String SIGHTINFO_DE="sightinfode";

    SQLiteDatabase database;
    public DatabaseHandler dbHelper;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //DATABASE_VERSION++;

        //database=getWritableDatabase();
        //this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+ID+" INTEGER PRIMARY KEY, "+CITYNAME+" TEXT, "+CITYINFO_HU+" TEXT, "+CITYINFO_EN+" TEXT, "+CITYINFO_DE+" TEXT);");
        db.execSQL("CREATE TABLE "+TABLE1_NAME+" ( "+ID1+" INTEGER PRIMARY KEY, "+CITYNAME1+" TEXT, "+PLACENAME_HU+" TEXT, "+PLACENAME_EN+" TEXT, "+PLACENAME_DE+" TEXT, "+SIGHTINFO_HU+" TEXT, "+SIGHTINFO_EN+" TEXT, "+SIGHTINFO_DE+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1_NAME);
        onCreate(db);
    }
    public boolean addData(String name,String infohu,String infoen, String infode)
    {
        database=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(CITYNAME,name);
        contentValues.put(CITYINFO_HU,infohu);
        contentValues.put(CITYINFO_EN,infoen);
        contentValues.put(CITYINFO_DE,infode);

        /*ContentValues contentValues1= new ContentValues();
        contentValues.put(CITYNAME1,name1);
        contentValues.put(SIGHTINFO_HU,infohu1);
        contentValues.put(SIGHTINFO_EN,infoen1);
        contentValues.put(SIGHTINFO_DE,infode1);*/

        Log.d(TAG, "addData: Adding "+name+" to "+TABLE_NAME);
        //Log.d(TAG, "addData: Adding "+name+" to "+TABLE1_NAME);


        long result=database.insert(TABLE_NAME,null, contentValues);
        //long result1=database.insert(TABLE1_NAME,null, contentValues);

        if(result==-1)
            return false;
        else return true;
    }
    public boolean addData1(String name,String pnamehu,String pnameen,String pnamede,String infohu,String infoen, String infode)
    {
        database=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(CITYNAME1,name);
        contentValues.put(PLACENAME_HU,pnamehu);
        contentValues.put(PLACENAME_EN,pnameen);
        contentValues.put(PLACENAME_DE,pnamede);
        contentValues.put(SIGHTINFO_HU,infohu);
        contentValues.put(SIGHTINFO_EN,infoen);
        contentValues.put(SIGHTINFO_DE,infode);

        Log.d(TAG, "addData: Adding "+name+" to "+TABLE1_NAME);

        long result=database.insert(TABLE1_NAME,null, contentValues);

        if(result==-1 )
            return false;
        else return true;
    }
    /*public int update(long _id, String name, String cityinfohu, String cityinfoen, String cityinfode) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHandler.CITYNAME, name);
        contentValues.put(DatabaseHandler.CITYINFO_HU, cityinfohu);
        contentValues.put(DatabaseHandler.CITYINFO_EN, cityinfoen);
        contentValues.put(DatabaseHandler.CITYINFO_DE, cityinfode);
        return this.database.update(DatabaseHandler.TABLE_NAME, contentValues, "id = " +id, null);
    }*/
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }
    /*public boolean isInfoExists(String cityinfohu,String cityinfoen,String cityinfode) {
        SQLiteDatabase db =null;
        DatabaseHandler.getInstance(context).getReadableDatabase();
        Cursor cursor = null;
        String selectQuery = "SELECT  *  FROM cityinfos WHERE cityinfohu != null;";

        cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.getCount() > 0) {
            return true;
        }
        return false;
    }*/
    /*public DatabaseHandler open() throws SQLException{
        this.dbHelper= new DatabaseHandler(DatabaseHandler.context);
        this.database=this.dbHelper.getWritableDatabase();
        return this;

    }*/
}
