package com.example.weather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    LinearLayout layout_forecastHour;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments()!=null){
            a=getArguments().getInt("Country");
        }
        img_main = view.findViewById(R.id.img_main);
        txt_weatherState=view.findViewById(R.id.txt_weatherState);
        txt_curTemp=view.findViewById(R.id.txt_curTemp);
        txt_feelTemp=view.findViewById(R.id.txt_feelsTemp);
        txt_curDay=view.findViewById(R.id.txt_curDay);
        txt_windDire=view.findViewById(R.id.txt_windDire);
        txt_windSpeed=view.findViewById(R.id.txt_windSpeed);
        txt_uv=view.findViewById(R.id.txt_uv);
        txt_humid=view.findViewById(R.id.txt_humid);
        txt_visibility=view.findViewById(R.id.txt_visibility);
        txt_pressure=view.findViewById(R.id.txt_pressure);
        layout_forecastHour=view.findViewById(R.id.layout_forecast);

        chooseRetroCall(a);
        layout_forecastHour.setOnClickListener(this::onClick);
    }

    private void chooseRetroCall(int a) {
        if(checkInternetConnection()){
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<Example> call;
            switch (a){
                case R.id.c_montreal: call = service.getMontrealWeather();
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

        }

    }
}