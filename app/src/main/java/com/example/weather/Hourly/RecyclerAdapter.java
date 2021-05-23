package com.example.weather.Hourly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.R;
import com.example.weather.model.Hour;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private List<Hour> arrayList;
    private Context context;

    public RecyclerAdapter(List<Hour> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weartherhour_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        holder.txt_temp.setText(arrayList.get(position).getTempC()+"°");
        holder.txt_feels.setText(arrayList.get(position).getFeelslikeC()+"°");
        holder.txt_state.setText(""+arrayList.get(position).getCondition().getText());
        holder.txt_day.setText(arrayList.get(position).getTime().split(" ")[0]);
        holder.txt_hour.setText(arrayList.get(position).getTime().split(" ")[1]);
        // Log.d("Adapter","Size="+arrayList.get(position).getHour().size());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_temp,txt_day,txt_hour,txt_state,txt_feels;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_temp=itemView.findViewById(R.id.txt_temp);
            txt_day=itemView.findViewById(R.id.txt_day);
            txt_hour=itemView.findViewById(R.id.txt_hour);
            txt_feels=itemView.findViewById(R.id.txt_feels);
            txt_state=itemView.findViewById(R.id.txt_state);
            itemView.setTag(this);
            //itemView.setOnClickListener(click);
        }
    }
}