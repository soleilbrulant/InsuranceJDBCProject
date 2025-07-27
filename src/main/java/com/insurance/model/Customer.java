package com.insurance.model;

import java.sql.Date;

public class Customer {
    private long customerid;
    private String name;
    private Date dob;
    private long contact;
    private String address;
    private String city;
    private String state;
    private String postalcode;
    private String country;
    private String gender;
    private String email;
    private String nationalid;
    private String background;

    // Default constructor
    public Customer() {}

    // Constructor with all fields except ID
    public Customer(String name, Date dob, long contact, String address, String city, 
                   String state, String postalcode, String country, String gender, 
                   String email, String nationalid, String background) {
        this.name = name;
        this.dob = dob;
        this.contact = contact;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalcode = postalcode;
        this.country = country;
        this.gender = gender;
        this.email = email;
        this.nationalid = nationalid;
        this.background = background;
    }

    // Getters and Setters
    public long getcustomerid() { return customerid; }
    public void setcustomerid(long customerid) { this.customerid = customerid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public long getContact() { return contact; }
    public void setContact(long contact) { this.contact = contact; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getpostalcode() { return postalcode; }
    public void setpostalcode(String postalcode) { this.postalcode = postalcode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getnationalid() { return nationalid; }
    public void setnationalid(String nationalid) { this.nationalid = nationalid; }

    public String getBackground() { return background; }
    public void setBackground(String background) { this.background = background; }

    @Override
    public String toString() {
        return "Customer{" +
                "customerid=" + customerid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact=" + contact +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
