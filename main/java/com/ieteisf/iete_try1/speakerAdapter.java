package com.ieteisf.iete_try1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class speakerAdapter extends FirebaseRecyclerAdapter<speaker,speakerAdapter.myviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public speakerAdapter(@NonNull FirebaseRecyclerOptions<speaker> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull speaker model) {

        try {


            holder.Name.setText(model.getName());
            holder.Description.setText(model.getDescription());

            Glide.with(holder.itemView)
                    .load(model.getPhoto())
                    .circleCrop()
                    .thumbnail(0.05f).into(holder.img);
            holder.Designation.setText(model.getDesignation());
            holder.Date.setText(model.getDate());
            holder.Time.setText(model.getTime());
            gotoURL(holder, model.getSocial());
            ExpandCard(holder);
        }catch (Exception e){
            Toast.makeText(holder.itemView.getContext(),"Unable to Load Data",Toast.LENGTH_LONG).show();
        }
    }

    private void gotoURL(myviewholder holder, String social) {

        try {


            holder.social.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    boolean connected = checkInternet(holder);

                    if(connected == false){
                        Toast.makeText(holder.itemView.getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Uri uri = Uri.parse(social);
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(uri);
                        v.getContext().startActivity(i);
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(holder.itemView.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }


    private void ExpandCard(myviewholder holder) {

        try {


            holder.mainview.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {

                    if (holder.Description.getVisibility() == View.VISIBLE && holder.bottomlayout.getVisibility() == View.VISIBLE) {
                        //TransitionManager.beginDelayedTransition((ViewGroup) holder.itemView,new AutoTransition());
                        holder.arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24);
                        holder.Description.setVisibility(View.GONE);
                        holder.bottomlayout.setVisibility(View.GONE);
                    } else {
                        //TransitionManager.beginDelayedTransition((ViewGroup) holder.itemView, new AutoTransition());
                        holder.arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                        holder.Description.setVisibility(View.VISIBLE);
                        holder.bottomlayout.setVisibility(View.VISIBLE);

                    }

                }
            });
        }catch (Exception e){
            Toast.makeText(holder.itemView.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkInternet(speakerAdapter.myviewholder holder) {
        boolean connected = false;
        ConnectivityManager connectivityManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            connectivityManager = (ConnectivityManager) holder.itemView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;
            }
            else
                connected = false;
        }
        return connected;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.speaker,parent,false);
        return new speakerAdapter.myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView img,social,arrow;
        TextView Name,Description,Designation,Date,Time;
        RelativeLayout mainview;
        RelativeLayout bottomlayout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.photo);
            Name = itemView.findViewById(R.id.Name);
            Description = itemView.findViewById(R.id.description);
            bottomlayout = itemView.findViewById(R.id.bottomlayout);
            Designation = itemView.findViewById(R.id.Designation);
            Date = itemView.findViewById(R.id.date);
            Time = itemView.findViewById(R.id.starttime);
            mainview = itemView.findViewById(R.id.mainview);
            social = itemView.findViewById(R.id.social);
            arrow = itemView.findViewById(R.id.arrow);
        }
    }
}
