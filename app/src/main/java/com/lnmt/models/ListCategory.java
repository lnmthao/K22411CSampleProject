package com.lnmt.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListCategory implements Serializable
{
    private ArrayList<Category> categories;

    public ListCategory() {
        categories = new ArrayList<>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
    public void addCategory(Category c)
    {
        categories.add(c);
    }
    public void generate_sample_dataset()
    {
        categories.add(new Category(1, "Son"));
        categories.add(new Category(2, "Kem nền"));
        categories.add(new Category(3, "Má hồng"));
        categories.add(new Category(4, "Phấn phủ"));
        categories.add(new Category(5, "Kem lót"));
        categories.add(new Category(6, "Chì kẻ môi"));
    }
}
