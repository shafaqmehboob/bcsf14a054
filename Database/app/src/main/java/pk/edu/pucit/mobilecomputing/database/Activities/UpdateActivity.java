package pk.edu.pucit.mobilecomputing.database.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pk.edu.pucit.mobilecomputing.database.Database.DBHelper;
import pk.edu.pucit.mobilecomputing.database.R;

public class UpdateActivity extends AppCompatActivity {
    EditText et_name, et_email, et_addr;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        et_name = (EditText) findViewById(R.id.et_name_update);
        et_email = (EditText) findViewById(R.id.et_email_update);
        et_addr = (EditText) findViewById(R.id.et_address_update);
        btn = (Button) findViewById(R.id.btn_update);
        Intent  i = getIntent();


        et_name.setText(i.getStringExtra("name"));
        et_email.setText(i.getStringExtra("email"));
        et_addr.setText(i.getStringExtra("address"));
         final String id = i.getStringExtra("id");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(UpdateActivity.this);
                Long res = db.update(id,et_name.getText().toString(), et_email.getText().toString(), et_addr.getText().toString());
                if(res > 0)
                {
                    Toast.makeText(UpdateActivity.this,"Updated!",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(UpdateActivity.this, MainActivity.class);
        finish();
        startActivity(i);
    }

}
