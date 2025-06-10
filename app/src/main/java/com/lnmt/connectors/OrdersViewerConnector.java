package com.lnmt.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lnmt.models.Customer;
import com.lnmt.models.OrderProductDetails;
import com.lnmt.models.OrdersViewer;

import java.util.ArrayList;
import java.util.List;

public class OrdersViewerConnector {
    public ArrayList<OrdersViewer>getAllOrdersViewer(SQLiteDatabase database)
    {
        ArrayList<OrdersViewer> datasets=new ArrayList<>();

        StringBuilder builder=new StringBuilder();

        builder.append(" SELECT ");
        builder.append(" o.Id AS OrderId, ");
        builder.append(" o.Code AS OrderCode, ");
        builder.append(" o.OrderDate, ");
        builder.append(" e.Name AS EmployeeName, ");
        builder.append(" c.Name AS CustomerName, ");
        builder.append(" SUM((od.Quantity * od.Price - od.Discount / 100.0 * od.Quantity * od.Price) * (1 + od.VAT / 100.0)) AS TotalOrderValue");
        builder.append(" FROM Orders o ");
        builder.append(" JOIN Employee e ON o.EmployeeId = e.Id ");
        builder.append(" JOIN Customer c ON o.CustomerId = c.Id ");
        builder.append(" JOIN OrderDetails od ON o.Id = od.OrderId ");
        builder.append(" GROUP BY o.Id, o.Code, o.OrderDate, e.Name, c.Name; ");

        String sql=builder.toString();

        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String code = cursor.getString(1);
            String orderdate = cursor.getString(2);
            String employeeName = cursor.getString(3);
            String customerName = cursor.getString(4);
            double totalvalue = cursor.getDouble(5);

            OrdersViewer ov=new OrdersViewer();
            ov.setId(id);
            ov.setCode(code);
            ov.setEmployeeName(employeeName);
            ov.setOrderDate(orderdate);
            ov.setCustomerName(customerName);
            ov.setTotalOrderValue(totalvalue);
            datasets.add(ov);
        }
        cursor.close();
        return datasets;
    }

    public List<OrderProductDetails> getOrderProductDetails(SQLiteDatabase database, int orderId) {
        List<OrderProductDetails> list = new ArrayList<>();
        String sql = "SELECT od.Id, od.OrderId, od.ProductId, od.Quantity, od.Price, p.Name " +
                "FROM OrderDetails od " +
                "JOIN Product p ON od.ProductId = p.ID " +
                "WHERE od.OrderId = ?";

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(orderId)});
        while (cursor.moveToNext()) {
            OrderProductDetails odwpn = new OrderProductDetails();
            odwpn.setId(cursor.getInt(0));
            odwpn.setOrderID(cursor.getInt(1));
            odwpn.setProductId(cursor.getInt(2));
            odwpn.setQuantity(cursor.getInt(3));
            odwpn.setPrice(cursor.getDouble(4));
            odwpn.setProductName(cursor.getString(5));
            list.add(odwpn);
        }
        cursor.close();
        return list;
    }
}
