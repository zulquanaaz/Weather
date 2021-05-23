package com.example.weather;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.weather.model.Example;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentFragment extends Fragment implements View.OnClickListener{

    TextView txt_weatherState,txt_curTemp,txt_feelTemp,txt_location,txt_curDay,txt_windDire,txt_windSpeed,txt_uv,txt_humid,txt_visibility,txt_pressure;

    ImageView img_main;
    private boolean connected;
    int a=R.id.c_montreal;
    Example weatherData;
    LinearLayout layout_forecastHour,layout_astronomy,layout_days;
    public CurrentFragment() {
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
        return inflater.inflate(R.layout.fragment_current, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img_main = (ImageView) view.findViewById(R.id.img_main);
        txt_weatherState=(TextView) view.findViewById(R.id.txt_weatherState);
        txt_curTemp=(TextView) view.findViewById(R.id.txt_curTemp);
        txt_feelTemp=(TextView) view.findViewById(R.id.txt_feelsTemp);
        txt_location=(TextView) view.findViewById(R.id.txt_location);
        txt_curDay=(TextView) view.findViewById(R.id.txt_curDay);
        txt_windDire=(TextView) view.findViewById(R.id.txt_windDire);
        txt_windSpeed=(TextView) view.findViewById(R.id.txt_windSpeed);
        txt_uv=(TextView) view.findViewById(R.id.txt_uv);
        txt_humid=(TextView) view.findViewById(R.id.txt_humid);
        txt_visibility=(TextView) view.findViewById(R.id.txt_visibility);
        txt_pressure=(TextView) view.findViewById(R.id.txt_pressure);
        layout_forecastHour=view.findViewById(R.id.layout_forecast);
        layout_astronomy=view.findViewById(R.id.layout_astronomy);
        layout_days=view.findViewById(R.id.layout_dayForecast);
        if(getArguments()!=null){
            a=getArguments().getInt("Country");
        }

        chooseRetroCall(a);
        layout_astronomy.setOnClickListener(this::onClick);
        layout_forecastHour.setOnClickListener(this::onClick);
        layout_days.setOnClickListener(this::onClick);

    }

    private void chooseRetroCall(int a) {
        if(checkInternetConnection()){
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<Example> call;
            switch (a){
                case R.id.c_montreal: call = service.getMontrealWeather();
                    getApiData(call);
                    break;
                case R.id.c_london:  call = service.getLondonWeather();
                    getApiData(call);
                    break;
                case R.id.c_toronto:  call = service.getTorontoWeather();
                    getApiData(call);
                    break;
                case R.id.c_vancouver:  call = service.getVancouverWeather();
                    getApiData(call);
                    break;
                case R.id.c_mumbai:  call = service.getMumbaiWeather();
                    getApiData(call);
                    break;

            }
        }else{
            noInternetConnection();
        }

    }

    private void getApiData(Call<Example> call) {
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                System.out.println("Response From URL :" + response.body());
                try {
                    weatherData = response.body();
                    ((MainActivity) getActivity())
                            .setActionBarTitle(weatherData.getLocation().getName());
                    showData();
                } catch (NullPointerException e) {
                    System.out.println("Nullpointer Exception :" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                System.out.println("In Failure :" + t.getMessage());

            }
        });
    }

    private void showData() {
        txt_curDay.setText(weatherData.getCurrent().getLastUpdated().split(" ")[0]);
        String uri="http://"+weatherData.getCurrent().getCondition().getIcon().split("//")[1];
        Picasso.get().load(uri).into(img_main);
        txt_location.setText(weatherData.getLocation().getName());
        txt_weatherState.setText(weatherData.getCurrent().getCondition().getText());
        txt_curTemp.setText(weatherData.getCurrent().getTempC().toString()+"°");
        txt_feelTemp.setText(weatherData.getCurrent().getFeelslikeC().toString()+"°");
        txt_windDire.setText(weatherData.getCurrent().getWindDir());
        txt_windSpeed.setText(weatherData.getCurrent().getWindKph()+" km/h");
        txt_humid.setText(weatherData.getCurrent().getHumidity()+" %");
        txt_uv.setText(weatherData.getCurrent().getUv()+"");
        txt_visibility.setText(weatherData.getCurrent().getVisKm()+" km");
        txt_pressure.setText(weatherData.getCurrent().getPressureMb()+" mB");
    }

    public boolean checkInternetConnection() {

        //Check internet connection:
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        //Means that we are connected to a network (mobile or wi-fi)
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        return connected;
    }

    private void noInternetConnection() {
        final Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.currentFragment),"No Internet Connection!!!",Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Retry!", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternetConnection()){
                    snackbar.dismiss();
                    chooseRetroCall(a);
                }else {
                    noInternetConnection();
                }
            }
        }).show();
    }

    @Override
    public void onClick(View v) {
        NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        Bundle b=new Bundle();
        b.putParcelable("Weather",weatherData);

        switch (v.getId()){
            case R.id.layout_forecast:
                navController.navigate(R.id.hoursFragment,b);
                break;
            case R.id.layout_astronomy:
                navController.navigate(R.id.astronomyFragment,b);
                break;
            case R.id.layout_dayForecast:
                navController.navigate(R.id.daysFragment,b);
                break;

        }

    }
}