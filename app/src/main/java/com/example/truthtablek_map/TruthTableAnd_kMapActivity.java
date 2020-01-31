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
import android.view.Gravity;
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
    private RecyclerView viewRows,viewHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truth_table_and_k_map);
        intializeDate();
        setDataToTable();



    }
    private void setDataToTable() {
        ArrayList<String>header=SingleTone.getLetters();
        header.add("Result");
        LettersAdapter lettersAdapter=new LettersAdapter(header,this);
        viewHeader.setHasFixedSize(true);
        viewHeader.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        viewHeader.setAdapter(lettersAdapter);

        CellAdapter cellAdapter=new CellAdapter(SingleTone.getRow(),this);
        viewRows.setHasFixedSize(true);
        viewRows.setLayoutManager(new GridLayoutManager(this,header.size()));
        viewRows.setAdapter(cellAdapter);

    }

    private void intializeDate() {
        viewRows=findViewById(R.id.rows);
        viewHeader=findViewById(R.id.header);
//        viewNumbers=findViewById(R.id.first_column);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
