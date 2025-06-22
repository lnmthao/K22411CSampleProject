package com.lnmt.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lnmt.k22411csampleproject.R;
import com.lnmt.k22411csampleproject.SendSMSActivity;
import com.lnmt.k22411csampleproject.TelephonyActivity;
import com.lnmt.models.TelephonyInfor;

import java.util.List;

public class TelephonyInfoAdapter extends ArrayAdapter<TelephonyInfor> {
    Activity context;
    int resource;

    public TelephonyInfoAdapter(@NonNull Activity context, int resource, List<TelephonyInfor> filteredList) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource, null);
        TextView txtTelephonyName=item.findViewById(R.id.txtTelephonyName);
        TextView txtTelephonyNumber=item.findViewById(R.id.txtTelephonyNumber);
        ImageView imgDirectCall=item.findViewById(R.id.imgDirectCall);
        ImageView imgDialUp=item.findViewById(R.id.imgDialUp);
        ImageView imgSms=item.findViewById(R.id.imgSendSms);

        TelephonyInfor ti=getItem(position);
        txtTelephonyName.setText(ti.getName());
        txtTelephonyNumber.setText(ti.getName());

        //các sự kện making telephony làm sao
        imgDirectCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((TelephonyActivity)context).dialupCall(ti);
            }
        });

        imgSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SendSMSActivity.class);
                intent.putExtra("TI",ti);
                context.startActivity(intent);
            }
        });

        return item;
    }
}
