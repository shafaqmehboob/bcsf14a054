package pk.edu.pucit.mobilecomputing.database.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pk.edu.pucit.mobilecomputing.database.Database.DBHelper;
import pk.edu.pucit.mobilecomputing.database.Globals;
import pk.edu.pucit.mobilecomputing.database.R;

public class LoginActivity extends AppCompatActivity {


    private EditText input_username;
    private EditText input_password;
    private TextView link_signup;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        input_username = (EditText) findViewById(R.id.input_username);
        input_password = (EditText) findViewById(R.id.input_password);
        link_signup = (TextView) findViewById(R.id.link_signup);
        btn_login = (Button) findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = input_username.getText().toString();
                String pass = input_password.getText().toString();
                DBHelper db = new DBHelper(LoginActivity.this);
                Cursor cursor = db.velidate(user);

                if (cursor.moveToFirst()) {
                    String dbUser = cursor.getString(cursor.getColumnIndex(DBHelper.USERNAME));
                    String dbPassword = cursor.getString(cursor.getColumnIndex(DBHelper.PASSWORD));

                    if ((user.equals(dbUser)) && (pass.equals(dbPassword))) {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);


                        preferences.edit().putBoolean(Globals.PREF_FIRST_TIME, false).apply();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        finish();
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Wrong Password", Toast.LENGTH_SHORT);

                    }
                }
                else {
                    Toast.makeText(LoginActivity.this,"Register First", Toast.LENGTH_SHORT);
                }
            }
        });
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                finish();
                startActivity(i);

            }
        });
    }
}
