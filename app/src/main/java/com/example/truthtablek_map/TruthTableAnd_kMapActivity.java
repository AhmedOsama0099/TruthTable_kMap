package com.example.truthtablek_map;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
public class TruthTableAnd_kMapActivity extends AppCompatActivity {
    private ArrayList<TruthTableModel> dataRows=new ArrayList<>();
    private ArrayList<String> letters=new ArrayList<>();
    private TableLayout table;
    private LinearLayout header_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truth_table_and_k_map);
        intializeDate();
        setDataToTable();


    }
    private void setDataToTable() {
        table.removeAllViews();

        for(int i=0;i<letters.size();i++){
            TextView head = new TextView(TruthTableAnd_kMapActivity.this);
            head.setLayoutParams(new TableRow.LayoutParams(230,TableRow.LayoutParams.WRAP_CONTENT));
            head.setTextSize(22);
            head.setMaxLines(1);
            head.setEllipsize(TextUtils.TruncateAt.END);
            head.setSingleLine(true);
            head.setGravity(Gravity.CENTER);
            head.setText(letters.get(i));
            head.setTextColor(Color.parseColor("#ffffff"));
            header_layout.addView(head);
        }
        TextView head = new TextView(TruthTableAnd_kMapActivity.this);
        head.setLayoutParams(new TableRow.LayoutParams(230,TableRow.LayoutParams.WRAP_CONTENT));
        head.setTextSize(22);
        head.setMaxLines(1);
        head.setEllipsize(TextUtils.TruncateAt.END);
        head.setSingleLine(true);
        head.setText("Result");
        head.setGravity(Gravity.CENTER);
        head.setTextColor(Color.parseColor("#ffffff"));

        header_layout.addView(head);

        for(int i=0;i<dataRows.size();i++){
            TableRow row=new TableRow(TruthTableAnd_kMapActivity.this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            Boolean []Bin=dataRows.get(i).getBinNum();
            for (int j = 0; j < Bin.length; j++) {
                TextView edit = new TextView(TruthTableAnd_kMapActivity.this);
                edit.setLayoutParams(new TableRow.LayoutParams(230,TableRow.LayoutParams.WRAP_CONTENT));
                edit.setTextSize(22);
                edit.setMaxLines(1);
                edit.setEllipsize(TextUtils.TruncateAt.END);
                edit.setSingleLine(true);
                edit.setGravity(Gravity.CENTER);
                if(Bin[j])
                    edit.setText("1");
                else
                    edit.setText("0");
                edit.setKeyListener(null);
                row.addView(edit);
                if(j==Bin.length-1)
                {
                    TextView editt = new TextView(TruthTableAnd_kMapActivity.this);
                    editt.setLayoutParams(new TableRow.LayoutParams(230,TableRow.LayoutParams.WRAP_CONTENT));
                    editt.setTextSize(22);
                    editt.setMaxLines(1);
                    editt.setEllipsize(TextUtils.TruncateAt.END);
                    editt.setSingleLine(true);
                    editt.setGravity(Gravity.CENTER);
                    if(dataRows.get(i).getRes()){
                        editt.setText("1");
                    }
                    else
                        editt.setText("0");
                    row.addView(editt);
                }
            }
            table.addView(row);
        }
    }

    private void intializeDate() {
        Intent intent=getIntent();
        Bundle bundle = intent.getExtras();
        String jsonString = bundle.getString("dataRows");
        Gson gson = new Gson();
        Type listOfdoctorType = new TypeToken<List<TruthTableModel>>() {}.getType();
        dataRows = gson.fromJson(jsonString,listOfdoctorType );
        letters = intent.getStringArrayListExtra("letters");
        table=findViewById(R.id.truthTable);
        header_layout=findViewById(R.id.header);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
