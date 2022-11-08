package ben.app.a12sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DbUtil extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "batteryInfo";
    private static final int DATABASE_VERSION = 2;

    public static final String CREATE_TABLE = "CREATE TABLE " + BatteryInfo.TABLE_NAME +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            BatteryInfo.DATE + " TEXT  NOT NULL, " +
            BatteryInfo.TIME + " INTEGER  NOT NULL, " +
            BatteryInfo.TEMPERATURE + " REAL NOT NULL, " +
            BatteryInfo.PERCENTAGE + " INTEGER NOT NULL)";


    public DbUtil(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public static void insertBat(SQLiteDatabase db, long time, float temperature, int percent,String date) {
        ContentValues values = new ContentValues();
        values.put(BatteryInfo.PERCENTAGE, percent);
        values.put(BatteryInfo.TEMPERATURE, temperature);
        values.put(BatteryInfo.TIME, time);
        values.put(BatteryInfo.DATE,date);

        if (db.insert(BatteryInfo.TABLE_NAME, null, values) == -1)
            throw new SQLiteException("Error on query : " + values.toString());
    }
}