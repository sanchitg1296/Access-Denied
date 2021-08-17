package com.ieteisf.iete_try1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link March18Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class March18Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public March18Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment March18Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static March18Fragment newInstance(String param1, String param2) {
        March18Fragment fragment = new March18Fragment();
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


    private RecyclerView recyclerView;
    timelineAdapter timelineAdapter;
    DatabaseReference mbase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_march18, container, false);




        try {


            boolean connected = checkInternet();

            if(connected == false){
                Toast.makeText(getContext(), "Switch on Internet Connection", Toast.LENGTH_SHORT).show();
            }

            else {

                mbase = FirebaseDatabase.getInstance().getReference().child("Timeline").child("18");

                recyclerView = view.findViewById(R.id.recyclerview_march18);

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                FirebaseRecyclerOptions<timeline> options =
                        new FirebaseRecyclerOptions.Builder<timeline>()
                                .setQuery(mbase,timeline.class)
                                .build();

                timelineAdapter = new timelineAdapter(options);
                recyclerView.setAdapter(timelineAdapter);

            }





        }
        catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }


        return view;
    }


    private boolean checkInternet() {
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
        return connected;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            timelineAdapter.startListening();
        }catch (Exception e){
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            timelineAdapter.stopListening();
        }catch (Exception e){
        }
    }
}