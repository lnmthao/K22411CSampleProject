package com.lnmt.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lnmt.models.ListPaymentMethod;
import com.lnmt.models.PaymentMethod;

public class PaymentMethodConnector {
    ListPaymentMethod listPaymentMethod;

    public PaymentMethodConnector() {
    }

    public ListPaymentMethod getListPaymentMethod() {
        return listPaymentMethod;
    }

    public void setListPaymentMethod(ListPaymentMethod listPaymentMethod) {
        this.listPaymentMethod = listPaymentMethod;
    }
    public ListPaymentMethod getAllPaymentMethods(SQLiteDatabase database)
    {
        listPaymentMethod = new ListPaymentMethod();
        Cursor cursor = database.rawQuery("SELECT * FROM PaymentMethod", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            PaymentMethod pm = new PaymentMethod();
            pm.setId(id);
            pm.setName(name);
            pm.setDescription(des);
            listPaymentMethod.addPaymentMethod(pm);
        }
        cursor.close();

        return listPaymentMethod;
    }
}
