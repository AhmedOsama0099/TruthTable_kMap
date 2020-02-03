package com.example.truthtablek_map;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    Context context;
    ArrayList<ArrayList<String>> tableData;

    TableAdapter(ArrayList<ArrayList<String>> tableData, Context context) {
        this.context = context;
        this.tableData = tableData;
    }

    @NonNull
    @Override
    public TableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.ViewHolder holder, int position) {
        LinearLayout ll = holder.tableRow;
        ArrayList<String> rowData = tableData.get(position);

        int childCount = ll.getChildCount();
        if (childCount != 0) {
            for (int i = 0; i < childCount; i++) {
                TextView v = (TextView) ll.getChildAt(i);
                v.setText(rowData.get(i));
            }
        } else {
            for (int i = 0; i < rowData.size(); i++) {
                TextView rowView = new TextView(context);
                rowView.setText(rowData.get(i));
                rowView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                rowView.getLayoutParams().width = 57 * 3;
                rowView.setEllipsize(TextUtils.TruncateAt.END);
                rowView.setMaxLines(1);
                rowView.setBackgroundResource(R.drawable.table_content_cell_bg);
                rowView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                rowView.setGravity(Gravity.CENTER);
                (holder.tableRow).addView(rowView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return tableData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected LinearLayout tableRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tableRow = itemView.findViewById(R.id.tablerow);
        }
    }
}
