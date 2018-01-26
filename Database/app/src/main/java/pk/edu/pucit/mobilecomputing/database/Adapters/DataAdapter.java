package pk.edu.pucit.mobilecomputing.database.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pk.edu.pucit.mobilecomputing.database.Models.UserInfo;
import pk.edu.pucit.mobilecomputing.database.R;
import pk.edu.pucit.mobilecomputing.database.Activities.UpdateActivity;

/**
 * Created by abc on 1/20/18.
 *
 * @package pk.edu.pucit.mobilecomputing.database.Adapters
 * @project Database
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    ArrayList<UserInfo> userInfos;
    Context context;

    public DataAdapter(Context context, ArrayList<UserInfo> userInfos) {
        this.userInfos = userInfos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_info_layout,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, final int position) {
        UserInfo ui = userInfos.get(position);
        Toast.makeText(context, ""+ui.getEmail(), Toast.LENGTH_SHORT).show();
        holder.id.setText(ui.getId()+"");
        holder.name.setText(ui.getName()+"");
        holder.email.setText(ui.getEmail()+"");
        holder.addr.setText(ui.getAddress()+"");
        holder.ll_ui.setBackgroundColor(position%2==1?Color.RED:Color.GREEN);

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo ui = userInfos.get(position);

                Intent i = new Intent(context, UpdateActivity.class);
                i.putExtra("id",String.valueOf(ui.getId()));
                i.putExtra("name",ui.getName());
                i.putExtra("email",ui.getEmail());
                i.putExtra("address",ui.getAddress());

                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return userInfos.size();
    }


    /**
     * DataAdapter.ViewHolder Class is below It will be used for designing
     * and setting the data entries in the adapter for recyclerview
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView name;
        public TextView email;
        public TextView addr;
        public LinearLayout ll_ui;
        public ImageButton update;

        public ViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.txt_id);
            name = (TextView) view.findViewById(R.id.txt_name);
            email = (TextView) view.findViewById(R.id.txt_email);
            addr = (TextView) view.findViewById(R.id.txt_address);
            ll_ui = (LinearLayout) view.findViewById(R.id.ll_ui_layout);
            update = (ImageButton) view.findViewById(R.id.update);
        }
    }


}
