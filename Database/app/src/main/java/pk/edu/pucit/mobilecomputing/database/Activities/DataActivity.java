package pk.edu.pucit.mobilecomputing.database.Activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;

import java.util.ArrayList;

import pk.edu.pucit.mobilecomputing.database.Adapters.DataAdapter;
import pk.edu.pucit.mobilecomputing.database.Database.DBHelper;
import pk.edu.pucit.mobilecomputing.database.Models.UserInfo;
import pk.edu.pucit.mobilecomputing.database.R;

public class DataActivity extends AppCompatActivity {
    DataAdapter dataAdapter;
    RecyclerView recyclerView;
    DBHelper DB_Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        DB_Helper = new DBHelper(this);


        //Code to change the title bar of any activity that extends AppCompatActivity
        ActionBar ab = getSupportActionBar();
        ab.setTitle(Html.fromHtml("<font color='green'>All</font> <font color='yellow'><b>Records</b>"));
        ab.setSubtitle("All records are listed below");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ArrayList<UserInfo> ui = DB_Helper.getAll();
        if (ui.size() <= 0) {
            final Snackbar sb = Snackbar.make(findViewById(R.id.clayout), "No Records Found.", Snackbar.LENGTH_INDEFINITE);
            sb.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sb.dismiss();
                }
            });
            sb.show();
        } else {
            dataAdapter = new DataAdapter(this, ui);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);

            recyclerView.setAdapter(dataAdapter);
        }
    }
}
