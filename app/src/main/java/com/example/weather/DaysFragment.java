package com.example.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weather.days.RecyclerAdapterDays;
import com.example.weather.model.Example;


public class DaysFragment extends Fragment {

    Example weather;
    private RecyclerView recyclerView;
    RecyclerAdapterDays recyclerAdapter;

    public DaysFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_days, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){
            weather=getArguments().getParcelable("Weather");
            Log.d("Astro","Recieved"+weather);
            Toast.makeText(getActivity().getApplicationContext(),"Astro Received", Toast.LENGTH_LONG).show();
        }else{
            Log.d("Astro","Not Recieved");
            Toast.makeText(getActivity().getApplicationContext(),"Astro Not Received", Toast.LENGTH_LONG).show();
        }
        ((MainActivity) getActivity())
                .setActionBarTitle(weather.getLocation().getName()+" 3-Days Forecast");

        recyclerView=view.findViewById(R.id.main_recyclerViewDays);
        generateView();
    }

    private void generateView() {
        recyclerAdapter = new RecyclerAdapterDays(weather, getActivity().getApplicationContext());
        //LayoutInflater =new LayoutInflater(getActivity().getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView = getView().findViewById(R.id.main_recyclerViewDays);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}