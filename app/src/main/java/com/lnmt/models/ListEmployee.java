package com.lnmt.models;

import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;

    public ListEmployee() {
        employees=new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void gen_dataset()
    {
        Employee e1=new Employee();
        e1.setId(1);
        e1.setName("John");
        e1.setEmail("john@gmail.com");
        e1.setPhone("0123456789");
        e1.setUsername("john");
        e1.setPassword("123");
        employees.add(e1);

        Employee e2=new Employee();
        e2.setId(2);
        e2.setName("Mery");
        e2.setEmail("mery@gmail.com");
        e2.setPhone("0987654321");
        e2.setUsername("mery");
        e2.setPassword("456");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setId(3);
        e3.setName("Peter");
        e3.setEmail("peter@gmail.com");
        e3.setPhone("0135792468");
        e3.setUsername("peter");
        e3.setPassword("789");
        employees.add(e3);
    }

}
