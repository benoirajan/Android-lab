package ben.app.a12sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ben.app.a12sqlite.db.BatteryInfo;
import ben.app.a12sqlite.db.DbUtil;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        findViewById(R.id.button).setOnClickListener(v -> {
            recordBattery();
            showDb();
        });
        showDb();
    }

    private void recordBattery() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, filter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level * 100 / (float) scale;

        int temp = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String cal = df.format(c);

        SQLiteDatabase db = new DbUtil(this).getReadableDatabase();
        DbUtil.insertBat(db, System.currentTimeMillis(), temp, (int) batteryPct, cal);
    }

    @SuppressLint("DefaultLocale")
    private void showDb() {
//        listView.setText("Percent, Time, Temperature\n");
        SQLiteDatabase db = new DbUtil(this).getReadableDatabase();

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        String cal = df.format(c);
        Log.d(getPackageName(), cal);

        String sql = "SELECT *  FROM " +
                BatteryInfo.TABLE_NAME + " " +
                "WHERE " + BatteryInfo.DATE +
                " = date('" + cal + "') " +
                "ORDER BY " + BatteryInfo.TIME + " DESC";

        Log.d(getPackageName(), sql);

        Cursor cursor = db.rawQuery(sql, null);

//        data = new String[cursor.getCount()][DailyUtil.COUNT];
//        amount = new double[cursor.getCount()];
//        ids = new long[cursor.getCount()];\
        ArrayList<String> d = new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();
        ArrayList<Integer> t = new ArrayList<>();
        while (cursor.moveToNext()) {
            long percentage = cursor.getLong(
                    cursor.getColumnIndexOrThrow(BatteryInfo.PERCENTAGE));
            String time = cursor.getString(
                    cursor.getColumnIndexOrThrow(BatteryInfo.DATE));
            double temperature = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(BatteryInfo.TEMPERATURE));

            d.add(time);
            t.add((int) temperature);
            p.add((int) percentage);
        }
        cursor.close();

        MyAd adapter = new MyAd(this,R.layout.list,p,t,d);
        listView.setAdapter(adapter);
    }


    class MyAd extends ArrayAdapter<String> {

        private Integer[] perc, temp;
        private String[] date;

        public MyAd(@NonNull Context context, int resource, ArrayList<Integer> per, ArrayList<Integer> temp, ArrayList<String> d) {
            super(context, resource);
            perc = new Integer[per.size()];
            this.temp = new Integer[per.size()];
            date = new String[per.size()];

            per.toArray(perc);
            temp.toArray(this.temp);
            d.toArray(date);
        }

        @Override
        public int getCount() {
            return perc.length;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.list,null);

            TextView tv = convertView.findViewById(R.id.date);
            TextView per = convertView.findViewById(R.id.info);

            Log.d("shit",perc[position]+"% | "+ temp[position]+"°C");
            tv.setText(date[position]);
            per.setText(perc[position]+"% | "+ temp[position]+"°C");

            return convertView;
        }
    }
}