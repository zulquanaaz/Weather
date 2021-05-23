package com.example.weather.days;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.model.Example;
import com.example.weather.model.Forecastday;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterDays extends RecyclerView.Adapter<RecyclerAdapterDays.ViewHolder>{

    Example weather;
    List<Forecastday> arrayList;
    Context context;

    public RecyclerAdapterDays( Example weather, Context context) {
        this.weather = weather;
        this.context = context;
        arrayList=weather.getForecast().getForecastday();
    }

    @Override
    public RecyclerAdapterDays.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weatherdays_layout,parent,false);
        return new RecyclerAdapterDays.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterDays.ViewHolder holder, int position) {
        holder.txt_temp.setText(arrayList.get(position).getDay().getAvgtempC()+"°");
        holder.txt_feels.setText(arrayList.get(position).getDay().getAvgtempC()+"°");
        holder.txt_state.setText(""+arrayList.get(position).getDay().getCondition().getText());
        holder.txt_day.setText(arrayList.get(position).getDate());
        Picasso.get().load("http://"+arrayList.get(position).getDay().getCondition().getIcon().split("//")[1]).into(holder.img_state);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_temp,txt_day,txt_state,txt_feels;
        ImageView img_state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_temp=itemView.findViewById(R.id.txt_daysTemp);
            txt_day=itemView.findViewById(R.id.txt_daysDate);
            txt_feels=itemView.findViewById(R.id.txt_daysFeels);
            txt_state=itemView.findViewById(R.id.txt_daysState);
            img_state=itemView.findViewById(R.id.img_dayState);
            itemView.setTag(this);
            //itemView.setOnClickListener(click);
        }
    }
}