package com.example.calvo_linus.kuja;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Need_Blood extends Fragment {
    Button need;
    Spinner groupChoice,cityChoice;
    private List<String> names,live;


    public Need_Blood() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //Inflate the Menu in Action Bar
        inflater.inflate(R.menu.want,menu);
         super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //set title bar
        getActivity().setTitle("Need Blood");
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_need__blood, container, false);
        groupChoice = v.findViewById(R.id.needBlood);
        cityChoice = v.findViewById(R.id.needCity);
        need =v.findViewById(R.id.startSearch);


         // Performs the Spinner functionality for the City Selection
        ArrayAdapter adapter =  ArrayAdapter.createFromResource(v.getContext(),R.array.town_best, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityChoice.setAdapter(adapter);

        cityChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing

            }
        });

        // Performs the Spinner functionality for the Blood Selection
        ArrayAdapter poa =  ArrayAdapter.createFromResource(v.getContext(),R.array.best_array, android.R.layout.simple_spinner_dropdown_item);
        poa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupChoice.setAdapter(poa);

        groupChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String group = groupChoice.getSelectedItem().toString();
                String city = cityChoice.getSelectedItem().toString();

                //Intent intent = new Intent(Need_Blood.this, DonorLIst.class);
                //intent.putExtra("group", group);
                //intent.putExtra("city", city);
                //startActivity(intent);

                //android.support.v4.app.FragmentTransaction gt = getActivity().getSupportFragmentManager().beginTransaction();
                //gt.replace(R.id.screen_view, new DonorLIst());
                //gt.commit();

            }

        });

        return v;
    }

    public interface Callback {
        //
    }
}
