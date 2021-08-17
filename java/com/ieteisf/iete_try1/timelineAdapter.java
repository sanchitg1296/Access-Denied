package com.ieteisf.iete_try1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import static android.content.Context.CLIPBOARD_SERVICE;

public class timelineAdapter extends FirebaseRecyclerAdapter<timeline,timelineAdapter.myviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public timelineAdapter(@NonNull FirebaseRecyclerOptions<timeline> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull timeline model) {

        try {

            holder.starttime.setText(model.getStartTime());
            holder.intro.setText(model.getEventName());


            if(model.getEndTime() != null){
                holder.endtime.setText(model.getEndTime());
            }
            else {
                holder.endtime.setVisibility(View.GONE);
                holder.to.setVisibility(View.GONE);
            }

            if (model.getLink() != null) {
                holder.playbutton.setVisibility(View.VISIBLE);
                holder.copy.setVisibility(View.VISIBLE);
                gotoURL(holder, model.getLink());
                copyURL(holder, model.getLink());
            }

            if (model.getSpeakerPhoto() != null) {
                holder.speakerspic.setVisibility(View.VISIBLE);
                Glide.with(holder.itemView)
                        .load(model.getSpeakerPhoto())
                        .circleCrop()
                        .thumbnail(0.05f).into(holder.speakerspic);


                holder.speakerspic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        boolean connected = checkInternet(holder);

                        if(connected == false){
                            Toast.makeText(holder.itemView.getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            Uri uri = Uri.parse(model.getSocial());
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(uri);
                            v.getContext().startActivity(i);
                        }
                    }
                });

            }

            if (model.getStatus() == 1){
                holder.ll_timeline.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.speaker_border_active));
            }
        }catch (Exception e){
            Toast.makeText(holder.itemView.getContext(),"Unable to Load Data",Toast.LENGTH_LONG).show();
        }

    }

    private void copyURL(myviewholder holder, String link) {
        final ClipboardManager[] myClipboard = new ClipboardManager[1];
        final ClipData[] myClip = new ClipData[1];
        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean connected = checkInternet(holder);

                if(connected == false){
                    Toast.makeText(holder.itemView.getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
                }
                else {
                    myClipboard[0] = (ClipboardManager) v.getContext().getSystemService(CLIPBOARD_SERVICE);
                    String text;
                    text = link;

                    myClip[0] = ClipData.newPlainText("link", text);
                    myClipboard[0].setPrimaryClip(myClip[0]);

                    Toast.makeText(v.getContext(), "Link Copied", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

    private boolean checkInternet(myviewholder holder) {
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

    private void gotoURL(myviewholder holder, String link) {


        holder.playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean connected = checkInternet(holder);

                if(connected == false){
                    Toast.makeText(holder.itemView.getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
                }
                else {

                    Uri uri = Uri.parse(link);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline,parent,false);

        return new  timelineAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView starttime,endtime,intro,to;
        ImageView playbutton,speakerspic,copy;
        LinearLayout ll_timeline;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            starttime = itemView.findViewById(R.id.starttime);
            endtime = itemView.findViewById(R.id.endtime);
            intro = itemView.findViewById(R.id.intro);
            playbutton = itemView.findViewById(R.id.playbutton);
            speakerspic = itemView.findViewById(R.id.speakerpic);
            copy = itemView.findViewById(R.id.copy);
            ll_timeline = itemView.findViewById(R.id.ll_timeline);
            to = itemView.findViewById(R.id.to);
        }
    }
}
