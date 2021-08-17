package com.ieteisf.iete_try1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
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


    ImageButton call,website,mail,back;
    Button submit;
    EditText name,email,sub,message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);

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

            submit = view.findViewById(R.id.submit);
            call = view.findViewById(R.id.callbutton);
            website = view.findViewById(R.id.website);
            mail = view.findViewById(R.id.mail);
            back = view.findViewById(R.id.contact_back);
            name = view.findViewById(R.id.name);
            email = view.findViewById(R.id.email);
            sub = view.findViewById(R.id.sub);
            message = view.findViewById(R.id.message);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    /*
                    Intent intent =new Intent(Intent.ACTION_SEND);
                    intent.setData(Uri.parse("mailto:sanchitgupta02@hotmail.com"));
                    intent.setType("text/plain");*/

                    /*
                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                    emailIntent.setType("application/octet-stream");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"iete@vit.ac.in"});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, sub.getText());
                    emailIntent.putExtra(Intent.EXTRA_TEXT,message.getText());
                    emailIntent.putExtra(Intent.EXTRA_REFERRER,email.getText());
                    startActivity(Intent.createChooser(emailIntent, "Send mail using..."));*/


                    Editable subject = sub.getText();

                    if(message.getText().toString() == null || subject.toString() == null ||  email.getText().toString() == null || name.getText().toString() == null  ){

                        Toast.makeText(getContext(),"Please Enter all Details",Toast.LENGTH_SHORT).show();


                    }
                    else {



                        Intent emailSelectorIntent = new Intent(Intent.ACTION_SENDTO);
                        emailSelectorIntent.setData(Uri.parse("mailto:"));

                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"iete@vit.ac.in"});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.toString());
                        emailIntent.putExtra(Intent.EXTRA_TEXT,message.getText().toString());
                        emailIntent.putExtra(Intent.EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER,email.getText().toString());
                        emailIntent.setSelector( emailSelectorIntent );
                        startActivity(emailIntent);
                    }

                }

            });


            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int REQUEST_PHONE_CALL = 1;
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + "8754583601"));


                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                        }
                        else
                        {
                            startActivity(callIntent);
                        }
                    }
                    else
                    {
                        startActivity(callIntent);
                    }
                }
            });

            website.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse("https://ietevit.com/accessdenied/");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(uri);
                    v.getContext().startActivity(i);
                }
            });

            mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:iete@vit.ac.in")));
                }
            });

            back.setOnClickListener(new View.OnClickListener() {
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