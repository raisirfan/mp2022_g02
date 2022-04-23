package com.example.dqueuebookingappsnew;

public class Booking {
    public String fullname,phonenumber,date,hours,numberofguest,price,bookingnumber;

    public Booking() {
    }


    public Booking(String fullname, String phonenumber, String date, String hours, String numberofguest, String price, String bookingnumber) {
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.date = date;
        this.hours = hours;
        this.numberofguest = numberofguest;
        this.price = price;
        this.bookingnumber = bookingnumber;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getDate() {
        return date;
    }

    public String getHours() {
        return hours;
    }

    public String getNumberofguest() {
        return numberofguest;
    }

    public String getPrice() {
        return price;
    }

    public String getBookingnumber() {
        return bookingnumber;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setNumberofguest(String numberofguest) {
        this.numberofguest = numberofguest;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBookingnumber(String bookingnumber) {
        this.bookingnumber = bookingnumber;
    }
}
