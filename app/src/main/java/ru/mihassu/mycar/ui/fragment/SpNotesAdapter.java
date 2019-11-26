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
import ru.mihassu.mycar.domain.model.SpDetails;

public class SpNotesAdapter extends RecyclerView.Adapter<SpNotesAdapter.ViewHolder> {

    private SpNotesAdapter.RVOnItemClickListener rVonItemClickListener;
    private List<SpDetails> dataList = new ArrayList<>();
    private int dataListSize;

    private final String TAG = "SpNotesAdapter";


    public void setDataList(List<SpDetails> data){
        this.dataList = data;
        notifyDataSetChanged();
        Log.w(TAG, "setDataList()");
    }


    @NonNull
    @Override
    public SpNotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.part_card, parent, false);
        return new SpNotesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SpNotesAdapter.ViewHolder holder, int position) {
        Log.w(TAG, "onBindViewHolder()");

        if (!dataList.isEmpty()) {
            SpDetails current = dataList.get(position);
            holder.itemChangeDate.setText(current.getChangeDate());
            holder.itemMileage.setText(current.getMileage());
            dataListSize = dataList.size();
            Log.w(TAG, "!dataList.isEmpty() - onBindViewHolder()");

        } else {

            holder.itemChangeDate.setText("0");
            holder.itemMileage.setText("0");
        }
    }

    @Override
    public int getItemCount() {
//        return dataListSize;
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemChangeDate;
        public TextView itemMileage;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.itemChangeDate = view.findViewById(R.id.item_part_changeDate);
            this.itemMileage = view.findViewById(R.id.item_part_mileage);

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (rVonItemClickListener != null) {
//                        rVonItemClickListener.onItemClick(v, getAdapterPosition());
//                    }
//                }
//            });
        }
    }

    //Интерфейс для обработки нажатий
    public interface RVOnItemClickListener {
        void onItemClick(View v, int position);
    }

    //Сеттер
    public void setRVOnItemClickListener(SpNotesAdapter.RVOnItemClickListener rVonItemClickListener) {
        this.rVonItemClickListener = rVonItemClickListener;
    }
}
