package com.ieteisf.iete_try1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class sponsorAdapter extends FirebaseRecyclerAdapter<sponsor,sponsorAdapter.myviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public sponsorAdapter(@NonNull FirebaseRecyclerOptions<sponsor> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull sponsor model) {

        try {


            //Picasso.get().load(model.getLogo()).into(holder.img);
            Glide.with(holder.itemView)
                    .load(model.getLogo())
                    .thumbnail(0.05f)
                    .transition(DrawableTransitionOptions.withCrossFade()).into(holder.img);

            if(model.getWeblink().isEmpty() != true)
            {


                gotoURL(holder, model.getWeblink());
            }

        }catch (Exception e){
            Toast.makeText(holder.itemView.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }


    private void gotoURL(myviewholder holder, String weblink) {

        try {



                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean connected = checkInternet(holder);

                        if(connected == false){
                            Toast.makeText(holder.itemView.getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                        else {


                            Uri uri = Uri.parse(weblink);
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(uri);
                            v.getContext().startActivity(i);
                        }
                    }
                });



        }catch (Exception e){
            Toast.makeText(holder.itemView.getContext(),"Unable to Load Data",Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkInternet(sponsorAdapter.myviewholder holder) {


        boolean connected = false;

        try {

            ConnectivityManager connectivityManager = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                connectivityManager = (ConnectivityManager) holder.itemView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                } else
                    connected = false;
            }
        }catch (Exception e)
        {
            Toast.makeText(holder.itemView.getContext(),"Switch on Internet Connection",Toast.LENGTH_SHORT).show();
        }
        return connected;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsor,parent,false);
        return new sponsorAdapter.myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        ImageView img;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.Logo);

        }
    }
}
