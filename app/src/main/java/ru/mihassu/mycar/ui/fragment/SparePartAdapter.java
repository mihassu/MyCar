package ru.mihassu.mycar.ui.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mihassu.mycar.R;
import ru.mihassu.mycar.data.entity.SparePartRealmData;

public class SparePartAdapter extends RecyclerView.Adapter<SparePartAdapter.ViewHolder> {

    private SparePartAdapter.RVOnItemClickListener rVonItemClickListener;
    private List<SparePartRealmData> dataList = new ArrayList<>();

    private final String TAG = "SparePartAdapter";


    public void setDataList(List<SparePartRealmData> data){
        this.dataList = data;
        notifyDataSetChanged();
        Log.w(TAG, "setDataList()");
    }

    public List<SparePartRealmData> getDataList() {
        return dataList;
    }

    @NonNull
    @Override
    public SparePartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new SparePartAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SparePartAdapter.ViewHolder holder, int position) {
        Log.w(TAG, "onBindViewHolder()");

        if (!dataList.isEmpty()) {
            SparePartRealmData current = dataList.get(position);
            holder.itemName.setText(current.getName());
            holder.partLastDate.setText(current.getLastSpNote().getChangeDate());
            holder.partLastMileage.setText(current.getLastSpNote().getMileage());


        } else {
            holder.itemName.setText("0");
            holder.partLastDate.setText("0");
            holder.partLastMileage.setText("0");
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName; //имя
        public TextView partLastDate; //последняя дата
        public TextView partLastMileage; //последний пробег

        public ViewHolder(@NonNull View view) {
            super(view);
            this.itemName = view.findViewById(R.id.item_name);
            this.partLastDate = view.findViewById(R.id.item_changeDate);
            this.partLastMileage = view.findViewById(R.id.item_mileage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rVonItemClickListener != null) {
                        rVonItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }

    //Интерфейс для обработки нажатий
    public interface RVOnItemClickListener {
        void onItemClick(View v, int position);
    }

    //Сеттер
    public void setRVOnItemClickListener(SparePartAdapter.RVOnItemClickListener rVonItemClickListener) {
        this.rVonItemClickListener = rVonItemClickListener;
    }
}
