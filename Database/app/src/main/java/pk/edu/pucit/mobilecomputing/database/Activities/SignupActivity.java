package pk.edu.pucit.mobilecomputing.database.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pk.edu.pucit.mobilecomputing.database.Database.DBHelper;
import pk.edu.pucit.mobilecomputing.database.R;

public class SignupActivity extends AppCompatActivity {
    private EditText input_username_signup;
    private EditText input_password_signup;
    private Button btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        input_username_signup = (EditText) findViewById(R.id.input_username_signup);
        input_password_signup = (EditText) findViewById(R.id.input_password_signup);
        btn_signup = (Button) findViewById(R.id.btn_signup);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = input_username_signup.getText().toString();
                String pass = input_password_signup.getText().toString();
                DBHelper db = new DBHelper(SignupActivity.this);
                Long ss = db.register(user,pass);
                if(ss>0)
                {
                    Toast.makeText(SignupActivity.this,"Resisterd!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                    finish();
                    startActivity(i);
                }
            }
        });
    }
}
