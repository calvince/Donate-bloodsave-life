package com.example.calvo_linus.kuja;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment {
    private EditText omera, wache, number;
    private Button reg;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    String selectedBloodGroup="";
    String selectedCity="";
    String [] cities = {"Nairobi","Mombasa","Kisumu","Eldoret","Naivasha"};
    String [] bloodGroup = {"0+","0-","A+","B+","AB+","A-","B-","AB"};
    String selectedGender="";
    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu., menu);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View oyah = inflater.inflate(R.layout.fragment_create, container, false);

        omera = oyah.findViewById(R.id.ed_address);
        wache = oyah.findViewById(R.id.ed_Pass);

        reg=   oyah.findViewById(R.id.bt_Reg);
        progressBar = oyah.findViewById(R.id.progressBar);
        number = oyah.findViewById(R.id.ed_Phone);
        mAuth = FirebaseAuth.getInstance();

        //Spinner for the blood
        Spinner sp = oyah.findViewById(R.id.ed_blood);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(oyah.getContext(), R.array.best_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                Context context = parent.getContext();
                CharSequence text = selected;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Object item = parent.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(getContext(), item.toString(),
                            duration).show();
                }
                Toast.makeText(getContext(), text,
                        duration).show();

                selectedBloodGroup=bloodGroup[position];


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Radio Group functionality
        RadioGroup choose = oyah.findViewById(R.id.hezwa);
        choose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //CheckedID is the Radio button selected
                switch (checkedId){
                    case R.id.rb_female:
                        //Female selection
                        selectedGender="F";
                        Toast.makeText(getContext(),"Female Selection",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_male:
                        //Male selection
                        selectedGender="M";
                        Toast.makeText(getContext(),"Male Selection",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });



        //Spinner for the City
        Spinner citriz = oyah.findViewById(R.id.city);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(oyah.getContext(), R.array.town_best, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citriz.setAdapter(adapter2);


        citriz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                Context context = parent.getContext();
                CharSequence text = selected;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Object item = parent.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(getContext(), item.toString(),
                            duration).show();
                }
                Toast.makeText(getContext(), text,
                        duration).show();
                selectedCity = cities[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        setHasOptionsMenu(true);
        // Registration Done here
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email =omera.getText().toString().trim();
                String password =wache.getText().toString().trim();
                String contact = number.getText().toString().trim();
                String city=selectedCity;
                String blood= selectedBloodGroup;
                String gender = selectedGender;

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getContext(),"Enter your email address",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getContext(),"Input your Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length()<8){
                    Toast.makeText(getContext(),"Password Too Short",Toast.LENGTH_SHORT).show();
                    return;
                }




//                progressBar.setVisibility(View.VISIBLE);

                StringBuilder b = new StringBuilder();
                b.append("email : "+email+"\n").
                        append("password : "+password+"\n")
                        .append("contact : "+contact+"\n")
                        .append("city : "+city+"\n")
                        .append("blood : "+blood+"\n")
                        .append("gender : "+gender+"\n");
                String filePath =
                        getActivity().getFilesDir().getAbsolutePath().toString()+ File.separator+"register.txt";

                try {
                    FileOutputStream out = new FileOutputStream(filePath);
                    out.write(b.toString().getBytes());
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        return oyah;
    }



}
