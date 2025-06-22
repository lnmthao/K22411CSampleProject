package com.lnmt.k22411csampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lnmt.adapters.TelephonyInfoAdapter;
import com.lnmt.models.TelephonyInfor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TelephonyActivity extends AppCompatActivity {

    ListView lvTelephony;
//    ArrayAdapter<TelephonyInfor> adapter;
    TelephonyInfoAdapter adapter;

    private List<TelephonyInfor> fullList = new ArrayList<>();
    private final String[] viettelPrefixes = {"086", "096", "097", "098", "032", "033", "034", "035", "036", "037", "038", "039"};
    private final String[] mobiPrefixes = {"089", "090", "093", "070", "076", "077", "078", "079"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        getAllContacts();

        addEvents();
    }

    private void addEvents() {
        lvTelephony.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                TelephonyInfor ti=adapter.getItem(i);
                makeAPhoneCall(ti);
            }
        });
    }

    private void makeAPhoneCall(TelephonyInfor ti) {
        Uri uri=Uri.parse("tel:"+ti.getPhone());
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void getAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        adapter.clear();

        while (cursor.moveToNext()){
            int nameIndex =cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex); //Get Name
            int phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds. Phone.NUMBER);
            String phone = cursor.getString(phoneIndex); //Get Phone Number

            TelephonyInfor ti=new TelephonyInfor();
            ti.setName(name);
            ti.setPhone(phone);
            adapter.add(ti);
        }
        cursor.close();
    }

    private void addViews() {
        lvTelephony=findViewById(R.id.lvTelephonyInfor);
//        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adapter = new TelephonyInfoAdapter(this, R.layout.item_telephony_info, fullList);
        lvTelephony.setAdapter(adapter);
    }

    public void directCall(TelephonyInfor ti)
    {
        Uri uri=Uri.parse("tel:"+ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    public void dialupCall(TelephonyInfor ti)
    {
        Uri uri=Uri.parse("tel:"+ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_telephony, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        List<TelephonyInfor> filteredList = new ArrayList<>();

        if (item.getItemId() == R.id.menu_viettel) {
            filteredList = filterByPrefix(fullList, List.of(viettelPrefixes));
        } else if (item.getItemId() == R.id.menu_mobiphone) {
            filteredList = filterByPrefix(fullList, List.of(mobiPrefixes));
        } else if (item.getItemId() == R.id.menu_others) {
            filteredList = filterOthers(fullList);
        } else {
            return super.onOptionsItemSelected(item);
        }

        adapter = new TelephonyInfoAdapter(this,R.layout.item_telephony_info, filteredList);
        lvTelephony.setAdapter(adapter);
        Log.d("DEBUG", "Filtered size = " + filteredList.size());
        return true;
    }


    private List<TelephonyInfor> filterByPrefix(List<TelephonyInfor> list, List<String> prefixes) {
        List<TelephonyInfor> result = new ArrayList<>();
        for (TelephonyInfor info : list) {
            for (String prefix : prefixes) {
                if (info.getPhone().startsWith(prefix)) {
                    result.add(info);
                    break;
                }
            }
        }
        return result;
    }

    private List<TelephonyInfor> filterOthers(List<TelephonyInfor> list) {
        List<TelephonyInfor> result = new ArrayList<>();
        for (TelephonyInfor info : list) {
            String num = info.getPhone();
            boolean isViettel = Arrays.asList(viettelPrefixes).stream().anyMatch(num::startsWith);
            boolean isMobi = Arrays.asList(mobiPrefixes).stream().anyMatch(num::startsWith);
            if (!isViettel && !isMobi) {
                result.add(info);
            }
        }
        return result;
    }
}