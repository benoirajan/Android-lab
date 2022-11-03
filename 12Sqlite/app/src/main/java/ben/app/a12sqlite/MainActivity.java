package ben.app.a12sqlite;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ben.app.a12sqlite.db.BatteryInfo;
import ben.app.a12sqlite.db.DbUtil;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.text);
        findViewById(R.id.button).setOnClickListener(v -> {
            recordBattery();
            showDb();
        });
        showDb();
    }

    private void recordBattery() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level * 100 / (float)scale;

        SQLiteDatabase db = new DbUtil(this).getReadableDatabase();
        DbUtil.insertBat(db, cal, 30f, 45);
    }

    private void showDb() {
        tv.setText("Percent, Time, Temperature\n");
        SQLiteDatabase db = new DbUtil(this).getReadableDatabase();
        String cal = "2022-11-03";
        String sql = "SELECT *  FROM " +
                BatteryInfo.TABLE_NAME + " " +
                "WHERE " + BatteryInfo.TIME +
                " = date('" + cal + "') " +
                "ORDER BY " + BatteryInfo.TIME + " DESC";
        Cursor cursor = db.rawQuery(sql, null);

//        data = new String[cursor.getCount()][DailyUtil.COUNT];
//        amount = new double[cursor.getCount()];
//        ids = new long[cursor.getCount()];
        while (cursor.moveToNext()) {
            long percentage = cursor.getLong(
                    cursor.getColumnIndexOrThrow(BatteryInfo.PERCENTAGE));
            String time = cursor.getString(
                    cursor.getColumnIndexOrThrow(BatteryInfo.TIME));
            double temperature = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(BatteryInfo.TEMPERATURE));

            tv.append(String.format("{ %d, %s, %f },\n", percentage, time, temperature));
        }
        cursor.close();

    }


}