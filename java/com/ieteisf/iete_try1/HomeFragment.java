package com.ieteisf.iete_try1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    TextView tv_days,tv_hour,tv_minute,tv_second;
    private String EVENT_DATE_TIME = "2021-03-19 22:30:00";
    private String EVENT_END_TIME = "2021-03-21 10:30:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private Handler handler = new Handler();
    private Runnable runnable;
    Button submit,discord,camp;
    TextView status;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        try {



            tv_days = view.findViewById(R.id.tv_days);
            tv_hour = view.findViewById(R.id.tv_hour);
            tv_minute = view.findViewById(R.id.tv_minute);
            tv_second = view.findViewById(R.id.tv_second);
            submit = view.findViewById(R.id.register);
            discord = view.findViewById(R.id.discord);
            camp = view.findViewById(R.id.campus);
            status = view.findViewById(R.id.status);
            countDownStart(tv_days,tv_hour,tv_minute,tv_second);


            getdata();




        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        return view;

    }

    private void getdata() {


        try {


            boolean connected = checkInternet();

            if(connected == false){
                Toast.makeText(getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
            }

            else {


                firebaseDatabase = FirebaseDatabase.getInstance();

                databaseReference = firebaseDatabase.getReference("home buttons");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String campuslink = snapshot.child("campus").getValue(String.class);
                        String discordlink = snapshot.child("discord").getValue(String.class);
                        String registerlink = snapshot.child("register").getValue(String.class);

                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                boolean con = checkInternet();

                                if(con == false){
                                    Toast.makeText(getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    Uri uri = Uri.parse(registerlink);
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(uri);
                                    v.getContext().startActivity(i);
                                }
                            }
                        });

                        camp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                boolean connected = checkInternet();

                                if(connected == false){
                                    Toast.makeText(getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    Uri uri = Uri.parse(campuslink);
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(uri);
                                    v.getContext().startActivity(i);
                                }
                            }
                        });

                        discord.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                boolean connected = checkInternet();

                                if(connected == false){
                                    Toast.makeText(getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    Uri uri = Uri.parse(discordlink);
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(uri);
                                    v.getContext().startActivity(i);
                                }
                            }
                        });


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(getContext(),"Unable to fetch data",Toast.LENGTH_SHORT).show();
                    }

                });
            }
        }catch (Exception e){
            Toast.makeText(getContext(),"Error: Unable to load data",Toast.LENGTH_SHORT).show();
        }


    }

    private boolean checkInternet() {


        boolean connected = false;
        try {

        ConnectivityManager connectivityManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                //we are connected to a network
                connected = true;
            }
            else
                connected = false;
        }
        }catch (Exception e){
            Toast.makeText(getContext(),"Switch on Internet Connection",Toast.LENGTH_SHORT).show();
        }
        return connected;
    }

    private void countDownStart(TextView tv_days, TextView tv_hour, TextView tv_minute, TextView tv_second) {

            runnable = new Runnable() {
                @Override
                public void run() {
                    try {handler.postDelayed(this, 1000);
                        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                        Date event_start_date = dateFormat.parse(EVENT_DATE_TIME);
                        Date evemt_end_date = dateFormat.parse(EVENT_END_TIME);
                        Date current_date = new Date();
                        if (!current_date.after(event_start_date)) {
                            status.setText("Hackathon Starts in");
                            long diff = event_start_date.getTime() - current_date.getTime();
                            long Days = diff / (24 * 60 * 60 * 1000);
                            long Hours = diff / (60 * 60 * 1000) % 24;
                            long Minutes = diff / (60 * 1000) % 60;
                            long Seconds = diff / 1000 % 60;
                            //
                            tv_days.setText(String.format("%02d", Days));
                            tv_hour.setText(String.format("%02d", Hours));
                            tv_minute.setText(String.format("%02d", Minutes));
                            tv_second.setText(String.format("%02d", Seconds));
                        }
                        else if (current_date.after(event_start_date) && (!current_date.after(evemt_end_date))){

                            status.setText("Hackathon Ends in");
                            long diff = evemt_end_date.getTime() - current_date.getTime();
                            long Days = diff / (24 * 60 * 60 * 1000);
                            long Hours = diff / (60 * 60 * 1000) % 24;
                            long Minutes = diff / (60 * 1000) % 60;
                            long Seconds = diff / 1000 % 60;
                            //
                            tv_days.setText(String.format("%02d", Days));
                            tv_hour.setText(String.format("%02d", Hours));
                            tv_minute.setText(String.format("%02d", Minutes));
                            tv_second.setText(String.format("%02d", Seconds));
                        }

                        else{
                            status.setText("Hackathon Ended");
                            tv_days.setText("00");
                            tv_hour.setText("00");
                            tv_minute.setText("00");
                            tv_second.setText("00");
                            handler.removeCallbacks(runnable);
                        }

                    }
                    catch (Exception e){
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }
            };
            handler.postDelayed(runnable,0);

    }
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        countDownStart(tv_days,tv_hour,tv_minute,tv_second);

    }
}
