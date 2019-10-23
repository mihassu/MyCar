package ru.mihassu.mycar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemCardAdapter extends RecyclerView.Adapter<ItemCardAdapter.ViewHolder> {

    private List<SparePart> itemsList;

    public ItemCardAdapter(List<SparePart> itemsList) {
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!itemsList.isEmpty()) {
            SparePart current = itemsList.get(position);
            holder.itemName.setText(current.getName());
            holder.itemChangeDate.setText(current.getLastChangeDate());
            holder.itemMileage.setText(String.format("%s", current.getLastMileage()));
        } else {
            holder.itemName.setText("0");
            holder.itemChangeDate.setText("0");
            holder.itemMileage.setText("0");
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView itemChangeDate;
        public TextView itemMileage;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.itemName = view.findViewById(R.id.item_name);
            this.itemChangeDate = view.findViewById(R.id.item_changeDate);
            this.itemMileage = view.findViewById(R.id.item_mileage);
        }
    }



}
