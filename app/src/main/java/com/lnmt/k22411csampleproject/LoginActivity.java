package com.lnmt.k22411csampleproject;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lnmt.connectors.EmployeeConnector;
import com.lnmt.connectors.SQLiteConnector;
import com.lnmt.models.Employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPassword;
    CheckBox chkSaveLogin;

    String DATABASE_NAME="SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    BroadcastReceiver networkReceiver=null;
    Button btnLogin;

    TextView txtNetworkType;

    View mainLayout;


    private long lastBackPressedTime = 0;
    private static final long BACK_PRESS_THRESHOLD = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        addViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        processCopy();

        setupBroadcastReceiver();
    }

    private void setupBroadcastReceiver() {
        networkReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //chút nưa sẽ tự động nhảy vào đây khi
                //Internet bị thay đổi trạng thái
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected())
                { //vào đây tức là có internet (không quan tâm wifi hay 4g)
                    btnLogin.setVisibility(View.VISIBLE);
                }
                else
                {
                    btnLogin.setVisibility(View.INVISIBLE);
                }

                if (networkInfo != null && networkInfo.isConnected()) {
                    if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        // Chỉ thay đổi màu văn bản cho Wi-Fi
                        txtNetworkType.setText("Kết nối Wi-Fi");
                        txtNetworkType.setTextColor(Color.parseColor("#03A9F4"));  // Màu xanh da trời (Wi-Fi)

                        // Thay đổi màu nền khi kết nối Wi-Fi
                        mainLayout.setBackgroundColor(Color.parseColor("#BBDEFB"));  // Màu nền xanh dương nhạt (Wi-Fi)
                    } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        // Chỉ thay đổi màu văn bản cho Mobile Data
                        txtNetworkType.setText("Kết nối Dữ liệu di động");
                        txtNetworkType.setTextColor(Color.parseColor("#4CAF50"));  // Màu xanh lá cây (Mobile Data)

                        // Thay đổi màu nền khi kết nối Mobile Data
                        mainLayout.setBackgroundColor(Color.parseColor("#C8E6C9"));  // Màu nền xanh lá cây nhạt (Mobile Data)
                    }
                } else {
                    // Thay đổi màu văn bản khi không có kết nối
                    txtNetworkType.setText("Không có kết nối Internet");
                    txtNetworkType.setTextColor(Color.parseColor("#9E9E9E"));  // Màu xám (Không có kết nối)

                    // Thay đổi màu nền khi không có kết nối
                    mainLayout.setBackgroundColor(Color.parseColor("#B0BEC5"));  // Màu nền xám (Không có kết nối)
                }


            }
        };
    }

    private void addViews() {
        edtUserName=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassword);
        chkSaveLogin=findViewById(R.id.chkSaveLoginInfor);
        btnLogin=findViewById(R.id.btnLogin);
        txtNetworkType = findViewById(R.id.txtNetworkType);
        mainLayout = findViewById(R.id.main);
    }

    public void do_login(View view) {

        String usr=edtUserName.getText().toString();
        String pwd=edtPassword.getText().toString();
        EmployeeConnector ec=new EmployeeConnector();

//        SQLiteConnector sqLiteConnector=new SQLiteConnector(this);
//        sqLiteConnector.openDatabase();
//        Employee emp=ec.login(sqLiteConnector.getDatabase(),usr,pwd);

        Employee emp=ec.login(new SQLiteConnector(this).openDatabase(),usr,pwd);
        if (emp!=null)
        {
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,
                    "Login failed - please check your account again!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void do_exit(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        Resources res=getResources();
        //thiet lap tieu de
        builder.setTitle(res.getText(R.string.confirm_exit_title));
        //noi dung cua so
        builder.setMessage(res.getText(R.string.confirm_exit_message));
        //bieu tuong
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //thiet lap tuong tac Yes
        builder.setPositiveButton(res.getText(R.string.confirm_exit_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //System.exit(0);
                finish();
            }
        });
        builder.setNegativeButton(res.getText(R.string.confirm_exit_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBackPressedTime <= BACK_PRESS_THRESHOLD) {
            do_exit(null);
        } else {
            lastBackPressedTime = currentTime;
            Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        String usr=edtUserName.getText().toString();
        String pwd=edtPassword.getText().toString();
        boolean isSave=chkSaveLogin.isChecked();
        editor.putString("USERNAME:",usr);
        editor.putString("PASSWORD",pwd);
        editor.putBoolean("SAVED",isSave);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLoginInformation();
        if(networkReceiver!=null)
        {
            unregisterReceiver(networkReceiver);
        }
    }

    public void restoreLoginInformation()
    {
        SharedPreferences preferences=getSharedPreferences("LOGIN_INFORMATION",MODE_PRIVATE);
        String usr=preferences.getString("USERNAME", "");
        String pwd=preferences.getString("PASSWORD","");
        boolean isSave=preferences.getBoolean("SAVED",true);
        if(isSave)
        {
            edtUserName.setText(usr);
            edtPassword.setText(pwd);
            chkSaveLogin.setChecked(isSave);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreLoginInformation();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists())
        {
            try
            {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }

    public void CopyDataBaseFromAsset()
    {
        try {
            InputStream myInput;

            myInput = getAssets().open(DATABASE_NAME);


            // Path to the just created empty db
            String outFileName = getDatabasePath();

            // if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}