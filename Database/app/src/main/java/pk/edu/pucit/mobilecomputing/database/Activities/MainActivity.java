package pk.edu.pucit.mobilecomputing.database.Activities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import pk.edu.pucit.mobilecomputing.database.Database.DBHelper;
import pk.edu.pucit.mobilecomputing.database.Globals;
import pk.edu.pucit.mobilecomputing.database.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DBHelper DB_Helper;
    EditText et_name, et_email, et_addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getBoolean(Globals.PREF_FIRST_TIME, true)) {

            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            finish();
            startActivity(i);
        }
        else {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            DB_Helper = new DBHelper(this);
            et_name = (EditText) findViewById(R.id.et_name);
            et_email = (EditText) findViewById(R.id.et_email);
            et_addr = (EditText) findViewById(R.id.et_address);

            //Code to change the title bar of any activity that extends AppCompatActivity
            ActionBar ab = getSupportActionBar();
            ab.setTitle(Html.fromHtml("<font color='white'>Welcome To</font> <font color='red'><b>My Form</b></font>"));
            ab.setSubtitle("A small database dependent app");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_all:
                startActivity(new Intent(this, DataActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Displays the retrieved rows in a notification
     *
     * @param rows a String[] of rows from the cursor
     *             <p>
     *             <p>read more at<br/>
     *             https://developer.android.com/guide/topics/ui/notifiers/notifications.html<br/>
     *             https://www.tutorialspoint.com/android/android_notifications.htm
     *             https://www.gcflearnfree.org/androidbasics/managing-notifications-on-android/1/</p>
     */

    protected void show_notification(String[] rows, int count) {


        //NotificationManager is required for creating and displaying notifications
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        Notification.Builder notify = new Notification.Builder(this)
                .setContentTitle("Records retrieved :: " + count)
                .setSmallIcon(android.R.drawable.ic_dialog_map)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.gallery_thumb))
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[]{100, 200, 300, 200, 222, 233, 55, 499, 89, 254, 156, 213, 21, 321, 121, 300, 200, 222, 233, 55, 499, 89, 254, 156, 213, 21, 321, 121, 300, 200, 222, 233, 55, 499, 89, 254, 156, 213, 21, 321, 121, 254, 25, 400, 300, 200, 10})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL))
                .setLights(Color.RED, 300, 700)
                .setAutoCancel(true);

        //style for displaying data
        Notification.InboxStyle is = new Notification.InboxStyle()
                .setBigContentTitle("Total Data: " + count);
        for (int i = 0; i < rows.length; i++) {
            is.addLine(rows[i]);
        }
        is.setSummaryText("All the data is now displayed in this notification");
        notify.setStyle(is);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // create android channel
            NotificationChannel androidChannel = new NotificationChannel(Globals.NOTIFICATION_CHANNEL_ID,
                    Globals.NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            // Sets whether notifications posted to this channel should display notification lights
            androidChannel.enableLights(true);
            // Sets the notification light color for notifications posted to this channel
            androidChannel.setLightColor(Color.GREEN);
            // Sets whether notifications posted to this channel appear on the lockscreen or not
            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            // Sets whether notification posted to this channel should vibrate.
            // "android.permission.VIBRATE" permission required
            androidChannel.enableVibration(true);
            // Sets the Vibration Pattern vibration must be enabled beforehand
            androidChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            manager.createNotificationChannel(androidChannel);

            notify.setChannelId(Globals.NOTIFICATION_CHANNEL_ID);

        }


        manager.notify(Globals.NOTIFICATION_ID, notify.build());
//        Log.d(Globals.LOG_TAG, "Notification sent");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset:
                et_name.setText("");
                et_email.setText("");
                et_addr.setText("");
                break;
            case R.id.btn_save:
                long rid = DB_Helper.insert(et_name.getText().toString(), et_email.getText().toString(), et_addr.getText().toString());
                Cursor b = DB_Helper.read(rid);
                String[] rows = new String[b.getCount()];
                while (b.moveToNext()) {
                    rows[b.getPosition()] = "ID: " + b.getInt(b.getColumnIndex(DBHelper.ID))
                            + "\n" + "NAME: " + b.getString(b.getColumnIndex(DBHelper.NAME));
                }
//                Toast.makeText(this,rows,Toast.LENGTH_LONG).show();
                show_notification(rows, b.getCount());
                b.close();
                break;
        }

    }
}

