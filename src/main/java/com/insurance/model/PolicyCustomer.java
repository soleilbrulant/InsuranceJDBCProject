package com.insurance.model;

import java.sql.Date;

public class PolicyCustomer {
    private long customerpolicyid;
    private long customerid;
    private long policytypeid; 
    private String status;
    private Date applieddate;

    // Default constructor
    public PolicyCustomer() {}

    // Constructor with all fields except ID
    public PolicyCustomer(long customerid, long policytypeid, String status, Date applieddate) {
        this.customerid = customerid;
        this.policytypeid = policytypeid;
        this.status = status;
        this.applieddate = applieddate;
    }

    // Getters and Setters
    public long getcustomerpolicyid() { return customerpolicyid; }
    public void setcustomerpolicyid(long customerpolicyid) { this.customerpolicyid = customerpolicyid; }

    public long getcustomerid() { return customerid; }
    public void setcustomerid(long customerid) { this.customerid = customerid; }

    public long getpolicytypeid() { return policytypeid; }
    public void setpolicytypeid(long policytypeid) { this.policytypeid = policytypeid; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getapplieddate() { return applieddate; }
    public void setapplieddate(Date applieddate) { this.applieddate = applieddate; }

    @Override
    public String toString() {
        return "PolicyCustomer{" +
                "customerpolicyid=" + customerpolicyid +
                ", customerid=" + customerid +
                ", policytypeid=" + policytypeid +
                ", status='" + status + '\'' +
                ", applieddate=" + applieddate +
                '}';
    }
}
