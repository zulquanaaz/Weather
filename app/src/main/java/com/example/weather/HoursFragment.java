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

import com.example.weather.Hourly.RecyclerAdapter;
import com.example.weather.model.Example;
import com.example.weather.model.Hour;

import java.util.List;


public class HoursFragment extends Fragment {

    private RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Example weather;
    List<Hour> wArrayList;

    public HoursFragment() {
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
        return inflater.inflate(R.layout.fragment_hours, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){
            weather=getArguments().getParcelable("Weather");
            Toast.makeText(getActivity().getApplicationContext(),"Received", Toast.LENGTH_LONG).show();
            wArrayList=weather.getForecast().getForecastday().get(0).getHour();
        }else{
            Log.d("Forecast","Not Recieved");
            Toast.makeText(getActivity().getApplicationContext(),"Not Received", Toast.LENGTH_LONG).show();
        }
        recyclerView=view.findViewById(R.id.main_recyclerView);
        ((MainActivity) getActivity())
                .setActionBarTitle(weather.getLocation().getName()+" Forecast");
        showData();
    }

    private void showData() {
        recyclerAdapter = new RecyclerAdapter(wArrayList, getActivity().getApplicationContext());
        //LayoutInflater =new LayoutInflater(getActivity().getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView = getView().findViewById(R.id.main_recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}