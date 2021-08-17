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
 * Use the {@link SponsorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SponsorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SponsorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SponsorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SponsorFragment newInstance(String param1, String param2) {
        SponsorFragment fragment = new SponsorFragment();
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


    private RecyclerView poweredrecyclerView,associaterecyclerView,educationrecyclerView,communityrecyclerView,mediarecyclerView,titlerecyclerView;
    sponsorAdapter poweredsponsorAdapter,associatesponsorAdapter,educationsponsorAdapter,communitysponsorAdapter,mediasponsorAdapter,titlesponsoradapter;
    DatabaseReference mbase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sponsor, container, false);



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
            else {

                mbase = FirebaseDatabase.getInstance().getReference().child("Sponsors");

                poweredrecyclerView = view.findViewById(R.id.poweredrecyclerview);
                associaterecyclerView = view.findViewById(R.id.associaterecyclerview);
                educationrecyclerView = view.findViewById(R.id.educationrecyclerview);
                communityrecyclerView = view.findViewById(R.id.communityrecyclerview);
                mediarecyclerView = view.findViewById(R.id.mediarecyclerview);
                titlerecyclerView = view.findViewById(R.id.titlerecyclerview);

                poweredrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                associaterecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                educationrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                communityrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                mediarecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                titlerecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));


                FirebaseRecyclerOptions<sponsor> titleoptions =
                        new FirebaseRecyclerOptions.Builder<sponsor>()
                                .setQuery(mbase.child("Title"), sponsor.class)
                                .build();

                FirebaseRecyclerOptions<sponsor> poweredoptions =
                        new FirebaseRecyclerOptions.Builder<sponsor>()
                                .setQuery(mbase.child("PoweredBy"), sponsor.class)
                                .build();


                FirebaseRecyclerOptions<sponsor> associateoptions =
                        new FirebaseRecyclerOptions.Builder<sponsor>()
                                .setQuery(mbase.child("Associate"), sponsor.class)
                                .build();


                FirebaseRecyclerOptions<sponsor> educationoptions =
                        new FirebaseRecyclerOptions.Builder<sponsor>()
                                .setQuery(mbase.child("Education"), sponsor.class)
                                .build();


                FirebaseRecyclerOptions<sponsor> communityoptions =
                        new FirebaseRecyclerOptions.Builder<sponsor>()
                                .setQuery(mbase.child("Community"), sponsor.class)
                                .build();


                FirebaseRecyclerOptions<sponsor> mediaoptions =
                        new FirebaseRecyclerOptions.Builder<sponsor>()
                                .setQuery(mbase.child("Media"), sponsor.class)
                                .build();

                poweredsponsorAdapter = new sponsorAdapter(poweredoptions);
                associatesponsorAdapter = new sponsorAdapter(associateoptions);
                educationsponsorAdapter = new sponsorAdapter(educationoptions);
                communitysponsorAdapter = new sponsorAdapter(communityoptions);
                mediasponsorAdapter = new sponsorAdapter(mediaoptions);
                titlesponsoradapter = new sponsorAdapter(titleoptions);

                poweredrecyclerView.setAdapter(poweredsponsorAdapter);
                associaterecyclerView.setAdapter(associatesponsorAdapter);
                educationrecyclerView.setAdapter(educationsponsorAdapter);
                communityrecyclerView.setAdapter(communitysponsorAdapter);
                mediarecyclerView.setAdapter(mediasponsorAdapter);
                titlerecyclerView.setAdapter(titlesponsoradapter);
            }


        }catch (Exception e)
        {
            Toast.makeText(getContext(),"Unable to Load Data",Toast.LENGTH_LONG).show();
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            poweredsponsorAdapter.startListening();
            associatesponsorAdapter.startListening();
            educationsponsorAdapter.startListening();
            communitysponsorAdapter.startListening();
            mediasponsorAdapter.startListening();
            titlesponsoradapter.startListening();
        }catch (Exception e ){

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            poweredsponsorAdapter.stopListening();
            associatesponsorAdapter.stopListening();
            educationsponsorAdapter.stopListening();
            communitysponsorAdapter.stopListening();
            mediasponsorAdapter.stopListening();
            titlesponsoradapter.stopListening();
        }catch (Exception e){

        }
    }
}