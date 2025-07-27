package com.insurance.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Claim {
    private long claimid;
    private long customerpolicyid;
    private Date incidentdate;
    private Date applydate;
    private long claimtype;
    private BigDecimal amountclaimed;
    private String status;
    private String claimnote;

    // Default constructor
    public Claim() {}

    // Constructor with all fields except ID  
    public Claim(long customerpolicyid, Date incidentdate, Date applydate, 
                long claimtype, BigDecimal amountclaimed, String status, String claimnote) {
        this.customerpolicyid = customerpolicyid;
        this.incidentdate = incidentdate;
        this.applydate = applydate;
        this.claimtype = claimtype;
        this.amountclaimed = amountclaimed;
        this.status = status;
        this.claimnote = claimnote;
    }

    // Getters and Setters
    public long getclaimid() { return claimid; }
    public void setclaimid(long claimid) { this.claimid = claimid; }

    public long getcustomerpolicyid() { return customerpolicyid; }
    public void setcustomerpolicyid(long customerpolicyid) { this.customerpolicyid = customerpolicyid; }

    public Date getincidentdate() { return incidentdate; }
    public void setincidentdate(Date incidentdate) { this.incidentdate = incidentdate; }

    public Date getapplydate() { return applydate; }
    public void setapplydate(Date applydate) { this.applydate = applydate; }

    public long getclaimtype() { return claimtype; }
    public void setclaimtype(long claimtype) { this.claimtype = claimtype; }

    public BigDecimal getamountclaimed() { return amountclaimed; }
    public void setamountclaimed(BigDecimal amountclaimed) { this.amountclaimed = amountclaimed; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getclaimnote() { return claimnote; }
    public void setclaimnote(String claimnote) { this.claimnote = claimnote; }

    @Override
    public String toString() {
        return "Claim{" +
                "claimid=" + claimid +
                ", customerpolicyid=" + customerpolicyid +
                ", incidentdate=" + incidentdate +
                ", applydate=" + applydate +
                ", amountclaimed=" + amountclaimed +
                ", status='" + status + '\'' +
                '}';
    }
}
