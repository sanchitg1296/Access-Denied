package com.ieteisf.iete_try1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    CardView AboutAD,AboutIETE,devinfo,contactus,faq,track,prize;


    private RecyclerView recyclerView;
    sponsorAdapter sponsorAdapter;
    DatabaseReference mbase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        AboutAD = view.findViewById(R.id.cdaboutad);
        AboutIETE = view.findViewById(R.id.cdaboutiete);
        devinfo = view.findViewById(R.id.cddevinfo);
        contactus = view.findViewById(R.id.cdcontactus);
        faq = view.findViewById(R.id.cdfaq);
        track = view.findViewById(R.id.cdtrack);
        prize = view.findViewById(R.id.cdprize);

        try {



            AboutAD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AboutADFragment adFragment = new AboutADFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, adFragment);
                    transaction.commit();
                }
            });

            }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        try {
            AboutIETE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AboutIETEFragment ieteFragment = new AboutIETEFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,ieteFragment);
                    transaction.commit();
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        try {
            devinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DeveloperFragment developerFragment = new DeveloperFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,developerFragment);
                    transaction.commit();
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        try {
            contactus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ContactUsFragment contactUsFragment = new ContactUsFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,contactUsFragment);
                    transaction.commit();
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        try {
            faq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FaqFragment faqFragment = new FaqFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,faqFragment);
                    transaction.commit();
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }


        try {
            track.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TracksFragment tracksFragment = new TracksFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,tracksFragment);
                    transaction.commit();
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }


        try {
            prize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PrizeFragment prizeFragment = new PrizeFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container,prizeFragment);
                    transaction.commit();
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }


        return view;
    }



}