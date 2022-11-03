package ben.app.a12sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        tv =findViewById(R.id.text);
        showDb();
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

            tv.append(String.format("{ %d, %s, %f },\n",percentage,time,temperature));
        }
        cursor.close();
        DbUtil.insertBat(db,cal,30f,45);
    }


}