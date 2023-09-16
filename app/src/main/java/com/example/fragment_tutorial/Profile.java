package com.example.fragment_tutorial;

import java.io.Serializable;

public class Profile implements Serializable {
    String name;
    double age;
    String education;

    public Profile(String name, double age, String education) {
        this.name = name;
        this.age = age;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", education='" + education + '\'' +
                '}';
    }
}
