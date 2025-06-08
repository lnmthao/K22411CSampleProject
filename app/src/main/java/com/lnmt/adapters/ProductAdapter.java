package com.lnmt.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lnmt.k22411csampleproject.R;
import com.lnmt.models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    Activity context;
    int resource;
    public ProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        //nhân bản giao diện thep từ vị trí position mà dữ liệu được duyệt qua:
        View item=inflater.inflate(this.resource,null);
        //lúc này: Toàn bộ view nằm trong Layout resource(item_advances_product)
        //sẽ được mô hình hóa hướng đối tượng và ddwuocjw quản lý bởi biến item
        //tức là item là Tổng Tài view
        //như vậy muốn truy xuất tới các view con tròn nó thì phải thông qua item
        ImageView imgProduct=item.findViewById(R.id.imgProduct);
        TextView txtProductID=item.findViewById(R.id.txtProductID);
        TextView txtProductName=item.findViewById(R.id.txtProductName);
        TextView txtProductQuantity=item.findViewById(R.id.txtProductQuantity);
        TextView txtProductPrice=item.findViewById(R.id.txtProductPrice);
        ImageView imgCart=item.findViewById(R.id.imgCart);

        //lấy mô hinình đối tuọn đang được nhân bản ở vị trí position (đối số 1):
        Product p=getItem(position);
        //Rải dữ liệu của Product lên giao diện trong item:
        imgProduct.setImageResource(p.getImage_id());
        txtProductID.setText(p.getId()+"");
        txtProductName.setText(p.getName());
        txtProductQuantity.setText(p.getQuantity()+"");
        txtProductPrice.setText(p.getPrice()+"(VNĐ)");
        //xử lý bấm vào nút mua

        return item;
    }
}
