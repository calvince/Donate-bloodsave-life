package com.example.calvo_linus.kuja;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonorListF extends Fragment {
    String city;
    String group;
    ArrayList<String> donorList;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    public static ArrayList<Donor> donorInfo;
    Button buttonMAp;


    public DonorListF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment using the view
        View ug = inflater.inflate(R.layout.fragment_donor_list, container, false);
        Bundle extras = getActivity().getIntent().getExtras();
        city = extras.getString("city");
        group = extras.getString("group");
        Log.i("NAME", city);
        Log.i("NAME", group);

        donorList = new ArrayList<>();
        donorInfo = new ArrayList<>();
        listView = ug.findViewById(R.id.list_donor);
        arrayAdapter = new ArrayAdapter<String>(ug.getContext(), android.R.layout.simple_spinner_dropdown_item, donorList);
        listView.setAdapter(arrayAdapter);

        buttonMAp = ug.findViewById(R.id.Button_mapShow);
        buttonMAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MaMap.class));
            }
        });


        FirebaseDatabase database = null;
        DatabaseReference myRef = database.getReference("Donors");
        myRef.child(city).child(group).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Donor donor = dataSnapshot.getValue(Donor.class);
                donorInfo.add(donor);
                String donorInfo = donor.name + "   \n" + donor.contuctNumber;
                donorList.add(donorInfo);
                arrayAdapter.notifyDataSetChanged();
                donorList.add(donorInfo);
                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setHasOptionsMenu(true);

        return ug;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        ////
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void getIntent() {
        //  return intent;
    }
}

