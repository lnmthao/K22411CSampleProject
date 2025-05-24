package com.lnmt.utils;

public class BMIResult {
    private double BMI;
    private double description;

    public BMIResult(double BMI, double description) {
        this.BMI = BMI;
        this.description = description;
    }

    public BMIResult() {
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public double getDescription() {
        return description;
    }

    public void setDescription(double description) {
        this.description = description;
    }

    public void setDescription(String des) {
    }
}
