package com.lnmt.k22411csampleproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lnmt.utils.BMIResult;
import com.lnmt.utils.Healthcare;

public class EmployeeHealthCareActivity extends AppCompatActivity {
    EditText edtHeight;
    EditText edtWeight;
    Button btnCalculate;
    Button btnClear;
    Button btnClose;

    TextView txtResult;

    View.OnClickListener myClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_health_care);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addViews() {
        edtHeight=findViewById(R.id.edtHeight);
        edtWeight=findViewById(R.id.edtWeight);

        btnCalculate=findViewById(R.id.btnCalculate);
        btnClear=findViewById(R.id.btnClear);
        btnClose=findViewById(R.id.btnClose);

        txtResult=findViewById(R.id.txtResult);
    }

    private void addEvents() {
        myClick=new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                //kiểm tra các view sử dụng biến sự kiện ở đây
                if(v.equals(btnCalculate)) //view=v
                {
                    //view calculate đang sử dụng sự kiện này
                    double h=Double.parseDouble(edtHeight.getText().toString());
                    double w=Double.parseDouble(edtWeight.getText().toString());
                    BMIResult result= Healthcare.calculate(h,w, EmployeeHealthCareActivity.this);
                    txtResult.setText(result.getBMI()+"=>"+result.getDescription());
                }
                else if(v.equals(btnClear))
                {
                    //view Clear đang sử dụng sự kiện này
                    edtHeight.setText("");
                    edtWeight.setText("");
                    txtResult.setText("");
                    edtHeight.requestFocus();
                }
                else if (v.equals(btnClose))
                {
                    //view Close đang sử dụng sự kiện này
                    finish();
                }
            }
        };
        //gán biến sự kiện cho các view (sharing events):
        btnCalculate.setOnClickListener(myClick);
        btnClose.setOnClickListener(myClick);
        btnClear.setOnClickListener(myClick);
    }
}