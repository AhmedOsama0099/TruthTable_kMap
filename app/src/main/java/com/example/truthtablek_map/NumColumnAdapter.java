package com.example.truthtablek_map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumColumnAdapter extends RecyclerView.Adapter<NumColumnAdapter.ViewHolder> {
    Context context;
    NumColumnAdapter(Context context){
        this.context=context;
    }
    @NonNull
    @Override
    public NumColumnAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.num_column_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumColumnAdapter.ViewHolder holder, int position) {
        holder.numCell.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return SingleTone.tableData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView numCell;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numCell=itemView.findViewById(R.id.numCell);
        }
    }
}
