package com.lnmt.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lnmt.k22411csampleproject.R;
import com.lnmt.models.OrderProductDetails;

import java.util.Collections;
import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {

    private final List<OrderProductDetails> orderDetailsList;

    public OrderDetailsAdapter(List<OrderProductDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_detail, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderProductDetails detail = orderDetailsList.get(position);
        holder.txtProductName.setText(detail.getProductName());
        holder.txtQuantity.setText("Quantity: " + detail.getQuantity());
        holder.txtPrice.setText("UnitPrice: " + detail.getPrice() + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtQuantity, txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
