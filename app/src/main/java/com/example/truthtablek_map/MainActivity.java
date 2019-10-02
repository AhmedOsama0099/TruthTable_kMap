package com.example.truthtablek_map;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText formula;
    Button not, and, or, xor, generate;
    String back = "";
    ArrayList<String> letters = new ArrayList<>();
    ArrayList<String> letters_and_operators = new ArrayList<>();
    ArrayList<String> posfix = new ArrayList<>();
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, Integer.toBinaryString(5)+"", Toast.LENGTH_SHORT).show();
        formula = findViewById(R.id.Formula);
        not = findViewById(R.id.btn_not);
        and = findViewById(R.id.btn_and);
        or = findViewById(R.id.btn_or);
        xor = findViewById(R.id.btn_xor);
        generate = findViewById(R.id.generate);
        not.setOnClickListener(this);
        and.setOnClickListener(this);
        or.setOnClickListener(this);
        xor.setOnClickListener(this);
        generate.setOnClickListener(this);
        mProgress = new ProgressDialog(this);
        /*formula.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String form = formula.getText().toString().trim();
                if (!form.isEmpty()) {
                    if (back.length() < form.length()) {//added a char


                    } else {//removed a char

                    }
                }
                back = formula.getText().toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
    }

    Boolean isBow(Character x) {
        if (x == '(' || x == ')')
            return true;
        else
            return false;
    }

    Boolean isOperator(Character x) {
        if (x == '∧' || x == '∨' || x == '¬' || x == '^')
            return true;
        else
            return false;
    }

    Boolean isBow(String x) {
        if (x.equals("(") || x.equals(")"))
            return true;
        else
            return false;
    }

    Boolean isOperator(String x) {
        if (x.equals("∧") || x.equals("∨") || x.equals("¬") || x.equals("^"))
            return true;
        else
            return false;
    }

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
        for (T element : list) {
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }
        return newList;
    }

    public void letterSplitter_withRemovingDuplicates(String formula) {
        String temp = "";
        int siz = formula.length();
        for (int i = 0; i < siz; i++) {
            if (formula.charAt(i) != ' ') {
                if (!(isOperator(formula.charAt(i)) || isBow(formula.charAt(i)))) {
                    temp += formula.charAt(i);

                }
                if ((isOperator(formula.charAt(i)) || isBow(formula.charAt(i)))) {
                    if (!temp.isEmpty()) {
                        letters.add(temp);
                        letters_and_operators.add(temp);
                    }
                    letters_and_operators.add(String.valueOf(formula.charAt(i)));

                    temp = "";
                } else if (i == siz - 1 && !(isOperator(formula.charAt(i)) || isBow(formula.charAt(i)))) {
                    letters.add(temp);
                    letters_and_operators.add(temp);
                    temp = "";
                }
            }
            letters = removeDuplicates(letters);
            Collections.sort(letters);
        }

    }


    Boolean valid(ArrayList<String> arr) {


        Stack<String> stack = new Stack<>();
        int siz = arr.size();
        Boolean chk = false;
        for (int i = 0; i < siz; i++) {
            if (arr.get(i).equals(")") && stack.empty()) {
                return false;
            }

            if (arr.get(i).equals("(")) {
                stack.push("(");
                chk = true;
            } else if (arr.get(i).equals(")") && !stack.empty()) {
                stack.pop();
            }
            if(i>0){
                if(!isOperator(arr.get(i))&&!isBow(arr.get(i))&&arr.get(i-1).equals(")"))
                    return false;
            }
            if (i + 1 < siz) {
                if ((isOperator(arr.get(i)) && isOperator(arr.get(i + 1)) && !arr.get(i + 1).equals("¬"))
                        || (isOperator(arr.get(i)) && arr.get(i + 1).equals(")") && !arr.get(i).equals("¬"))
                        || (isBow(arr.get(i)) && isOperator(arr.get(i + 1)) && !arr.get(i).equals(")"))
                        || (isBow(arr.get(i)) && isBow(arr.get(i + 1)))
                        || arr.get(i).equals(")") && arr.get(i + 1).equals("¬")
                        || !isOperator(arr.get(i)) && !isBow(arr.get(i)) && arr.get(i + 1).equals("¬")
                        || !isOperator(arr.get(i))&&!isBow(arr.get(i))&&arr.get(i+1).equals("("))

                {
                    return false;

                }
            }
            if (i == siz - 1 && isOperator(arr.get(i))) {
                return false;
            }


        }
        if ((stack.empty() && chk))
            return true;
        if (stack.empty() && !chk)
            return true;


        return false;


    }

    private void infixToprefix(ArrayList<String> arr) {
        Stack<String> operators = new Stack<>();
        int siz = arr.size();
        for (int i = 0; i < siz; i++) {
            if (!isOperator(arr.get(i)) && !isBow(arr.get(i)))
                posfix.add(arr.get(i));
            else {
                if (operators.empty())
                    operators.push(arr.get(i));
                else {
                    if (isHigherPriority(arr.get(i), operators.peek()))
                        operators.push(arr.get(i));
                    else if (!isHigherPriority(arr.get(i), operators.peek()) && isBow(operators.peek())) {
                        operators.push(arr.get(i));
                    } else {
                        posfix.add(operators.peek());
                        operators.pop();
                        operators.push(arr.get(i));
                    }
                    if (!operators.empty()) {
                        if (operators.peek().equals(")")) {
                            operators.pop();
                            while (true) {
                                if (!operators.peek().equals("(")) {
                                    posfix.add(operators.peek());
                                    operators.pop();
                                } else {
                                    operators.pop();
                                    break;
                                }

                            }

                        }
                    }

                }
            }
        }
        while (!operators.empty()) {
            posfix.add(operators.peek());
            operators.pop();
        }

    }

    private boolean isHigherPriority(String s, String peek) {
        if (isBow(s))
            return true;
        if (s.equals("¬") && (peek.equals("∧") || peek.equals("∨") || peek.equals("^")))
            return true;
        if (s.equals("^") && (peek.equals("∧") || peek.equals("∨")))
            return true;
        if(s.equals("∧")&&peek.equals("∨"))
            return true;
        if (s.equals("¬") && peek.equals("¬"))
            return true;
        return false;
    }

    Boolean true_And_false_Converter(Character x) {
        if (x == '0')
            return false;
        else
            return true;
    }

    private void rawResult() {


        Stack<Boolean> res = new Stack<>();
        int lastNumber = (int) Math.pow(2, letters.size());
        ArrayList<TruthTableModel> dataShow = new ArrayList<>();
        for (int i = 0; i < lastNumber; i++) {
            Boolean[] binaryRaw = new Boolean[letters.size()];
            Arrays.fill(binaryRaw, Boolean.FALSE);
            int fromEndToStart = binaryRaw.length - 1;
            String binaryNum = Integer.toBinaryString(i);
            for (int j = binaryNum.length() - 1; j >= 0; j--) {
                binaryRaw[fromEndToStart] = true_And_false_Converter(binaryNum.charAt(j));
                fromEndToStart--;
            }

            for (int posfixPosition = 0; posfixPosition < posfix.size(); posfixPosition++) {
                if (!isOperator(posfix.get(posfixPosition))) {
                    int rawBos = letters.indexOf(posfix.get(posfixPosition));
                    res.push(binaryRaw[rawBos]);
                }
                if (isOperator(posfix.get(posfixPosition)) && !posfix.get(posfixPosition).equals("¬")) {
                    Boolean top = res.peek();
                    res.pop();
                    if (posfix.get(posfixPosition).equals("∧")) {
                        if (top && res.peek()) {
                            res.pop();
                            res.push(Boolean.TRUE);
                        } else {
                            res.pop();
                            res.push(Boolean.FALSE);
                        }

                    } else if (posfix.get(posfixPosition).equals("∨")) {
                        if (top || res.peek()) {
                            res.pop();
                            res.push(Boolean.TRUE);
                        } else {
                            res.pop();
                            res.push(Boolean.FALSE);
                        }

                    } else if (posfix.get(posfixPosition).equals("^")) {
                        if (top ^ res.peek()) {
                            res.pop();
                            res.push(Boolean.TRUE);
                        } else {
                            res.pop();
                            res.push(Boolean.FALSE);
                        }

                    }

                }
                if (isOperator(posfix.get(posfixPosition)) && posfix.get(posfixPosition).equals("¬")) {
                    Boolean top = res.peek();
                    res.pop();
                    if (!top)
                        res.push(Boolean.TRUE);
                    else
                        res.push(Boolean.FALSE);
                }

            }
            TruthTableModel mod = new TruthTableModel(i, binaryRaw, res.peek());
            dataShow.add(mod);
            res.pop();
        }

        Intent intent = new Intent(MainActivity.this, TruthTableAnd_kMapActivity.class);
        Gson gson = new Gson();
        String jsonString = gson.toJson(dataShow);
        intent.putExtra("dataRows", jsonString);
        intent.putExtra("letters", letters);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == not.getId()) {
            int start = formula.getSelectionStart();
            formula.setText(formula.getText().insert(start, "¬"));
            formula.setSelection(start + 1);

        } else if (v.getId() == and.getId()) {
            int start = formula.getSelectionStart();
            formula.setText(formula.getText().insert(start, "∧"));
            formula.setSelection(start + 1);
        } else if (v.getId() == or.getId()) {
            int start = formula.getSelectionStart();
            formula.setText(formula.getText().insert(start, "∨"));
            formula.setSelection(start + 1);
        } else if (v.getId() == xor.getId()) {
            int start = formula.getSelectionStart();
            formula.setText(formula.getText().insert(start, "^"));
            formula.setSelection(start + 1);
        } else if (v.getId() == generate.getId()) {
            if (!formula.getText().toString().isEmpty()) {
                posfix.clear();
                letters_and_operators.clear();
                letters.clear();
                mProgress.setMessage("Generating");
                mProgress.show();
                letterSplitter_withRemovingDuplicates(formula.getText().toString());

                if (valid(letters_and_operators)) {
                    infixToprefix(letters_and_operators);
                    rawResult();
                } else {
                    formula.setError("Wrong Syntax!");
                }
                mProgress.dismiss();
            }
            else
                formula.setError("Empty!");
        }
    }


}
