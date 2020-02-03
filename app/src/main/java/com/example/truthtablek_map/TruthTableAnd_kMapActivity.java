package com.example.truthtablek_map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TruthTableAnd_kMapActivity extends AppCompatActivity {
    private RecyclerView tableData,numColumn;
    private LinearLayout header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truth_table_and_k_map);
        intializeDate();
        setDataToTable();
        synchronizeScrolling();
    }
    private void synchronizeScrolling(){
        final RecyclerView.OnScrollListener[] scrollListeners = new RecyclerView.OnScrollListener[2];
        scrollListeners[0] = new RecyclerView.OnScrollListener( )
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                numColumn.removeOnScrollListener(scrollListeners[1]);
                numColumn.scrollBy(dx, dy);
                numColumn.addOnScrollListener(scrollListeners[1]);
            }
        };
        scrollListeners[1] = new RecyclerView.OnScrollListener( )
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                tableData.removeOnScrollListener(scrollListeners[0]);
                tableData.scrollBy(dx, dy);
                tableData.addOnScrollListener(scrollListeners[0]);
            }
        };
        tableData.addOnScrollListener(scrollListeners[0]);
        numColumn.addOnScrollListener(scrollListeners[1]);
    }
    private void setDataToTable() {
        SingleTone.letters.add("Res");
        for (String headCell : SingleTone.letters) {
            TextView row = new TextView(this);
            row.setText(headCell);
            row.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            row.getLayoutParams().width = 57 * 3;
            row.setEllipsize(TextUtils.TruncateAt.END);
            row.setMaxLines(1);
            row.setBackgroundResource(R.drawable.table_header_cell_bg);
            row.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            row.setGravity(Gravity.CENTER);
            header.addView(row);
        }
        TableAdapter adapter = new TableAdapter(SingleTone.tableData, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tableData.setLayoutManager(linearLayoutManager);
        tableData.setAdapter(adapter);

        NumColumnAdapter numColumnAdapter=new NumColumnAdapter(this);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this);
        numColumn.setLayoutManager(linearLayoutManager1);
        numColumn.setAdapter(numColumnAdapter);

    }

    private void intializeDate() {
        tableData = findViewById(R.id.tableData);
        header = findViewById(R.id.header);
        numColumn=findViewById(R.id.numColumn);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
