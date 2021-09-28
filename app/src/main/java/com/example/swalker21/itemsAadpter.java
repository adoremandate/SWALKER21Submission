package com.example.swalker21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class itemsAadpter extends RecyclerView.Adapter<itemsAadpter.ViewHolder>{

    public interface OnLonGClickListner{
        void onItemLongClicked(int position);


    }
    List<String> items;
    OnLonGClickListner longClickListner;

    public itemsAadpter(List<String> items,OnLonGClickListner longClickListner) {
        this.items = items;
        this.longClickListner = longClickListner;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        holder.bind(item);



    }

    @Override
    public int getItemCount() {
        return items.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvitems;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvitems = itemView.findViewById(android.R.id.text1);



        }

        public void bind(String item) {
            tvitems.setText(item);
            tvitems.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    longClickListner.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });



        }
    }
}