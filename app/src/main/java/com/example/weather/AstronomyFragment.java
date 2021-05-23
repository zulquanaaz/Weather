package com.example.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weather.model.Astro;
import com.example.weather.model.Example;


public class AstronomyFragment extends Fragment {

    TextView txt_city,txt_country,txt_time,txt_lat,txt_lon,txt_sunRise,txt_sunSet,txt_moonSet,txt_moonRise;
    Example weather;
    Astro astro;
    public AstronomyFragment() {
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
        return inflater.inflate(R.layout.fragment_astronomy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){
            weather=getArguments().getParcelable("Weather");
            astro=weather.getForecast().getForecastday().get(0).getAstro();
        }
        ((MainActivity) getActivity())
                .setActionBarTitle(weather.getLocation().getName()+" Astro");

        txt_city=view.findViewById(R.id.txt_cityAstro);
        txt_country=view.findViewById(R.id.txt_countryAstro);
        txt_time=view.findViewById(R.id.txt_timeAstro);
        txt_lat=view.findViewById(R.id.txt_latAstro);
        txt_lon=view.findViewById(R.id.txt_lonAstro);
        txt_sunRise=view.findViewById(R.id.txt_sunrise);
        txt_sunSet=view.findViewById(R.id.txt_sunset);
        txt_moonRise=view.findViewById(R.id.txt_moonrise);
        txt_moonSet=view.findViewById(R.id.txt_moonset);
        generateView();

    }

    private void generateView() {
        txt_city.setText(weather.getLocation().getName());
        txt_country.setText(weather.getLocation().getCountry());
        txt_time.setText(weather.getLocation().getLocaltime());
        txt_lat.setText(""+weather.getLocation().getLat());
        txt_lon.setText(""+weather.getLocation().getLon());
        txt_sunSet.setText(""+weather.getForecast().getForecastday().get(0).getAstro().getSunset());
        txt_sunRise.setText(""+weather.getForecast().getForecastday().get(0).getAstro().getSunrise());
        txt_moonSet.setText(""+weather.getForecast().getForecastday().get(0).getAstro().getMoonset());
        txt_moonRise.setText(""+weather.getForecast().getForecastday().get(0).getAstro().getMoonrise());
    }
}