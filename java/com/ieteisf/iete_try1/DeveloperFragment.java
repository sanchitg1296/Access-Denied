package com.ieteisf.iete_try1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeveloperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeveloperFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeveloperFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeveloperFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeveloperFragment newInstance(String param1, String param2) {
        DeveloperFragment fragment = new DeveloperFragment();
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

    ImageButton dev_info_back;
    ImageView sanchitlinkedin,sanchitgit;
    ImageView siddharthlinkedin,siddharthgit;
    ImageView riddhilinkedin,riddhigit;
    ImageView aishaanlinkedin,aishaangit;
    ImageView rohanlinkedin,rohangit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_developer, container, false);



        try {

            boolean connected = false;
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

            if(connected == false){
                Toast.makeText(getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
            }

            dev_info_back = view.findViewById(R.id.dev_info_back);
            sanchitlinkedin = view.findViewById(R.id.sanchitlinkedin);
            sanchitgit = view.findViewById(R.id.sanchitgit);

            siddharthlinkedin = view.findViewById(R.id.siddharthlinkedin);
            siddharthgit = view.findViewById(R.id.siddharthgit);

            riddhilinkedin = view.findViewById(R.id.riddhilinkedin);
            riddhigit = view.findViewById(R.id.riddhigit);

            aishaanlinkedin = view.findViewById(R.id.aishaanlinkedin);
            aishaangit = view.findViewById(R.id.aishaangit);

            rohanlinkedin = view.findViewById(R.id.rohanlinkedin);
            rohangit = view.findViewById(R.id.rohangit);

            sanchitlinkedin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("https://www.linkedin.com/in/sanchit-gupta-258a7319b/");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });

            sanchitgit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("https://github.com/sanchitg1296");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });


            siddharthlinkedin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("https://www.linkedin.com/in/siddharth-dinkar-3896b81b4/");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });

            siddharthgit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("https://github.com/siddharth9901/");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });

            riddhilinkedin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("http://linkedin.com/in/riddhi-gupta-95858b1b3");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });

            riddhigit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("https://github.com/riddhi-30");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });

            aishaanlinkedin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("https://www.linkedin.com/in/aishaan-datt-b89208190/");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });


            aishaangit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("https://github.com/aishaandatt");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });

            rohanlinkedin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("https://www.linkedin.com/in/rohan-mittal-1b502617b/");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });


            rohangit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("https://github.com/rohanmittal01");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });






            dev_info_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InfoFragment infoFragment = new InfoFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,infoFragment);
                    transaction.commit();
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        return view;
    }
}