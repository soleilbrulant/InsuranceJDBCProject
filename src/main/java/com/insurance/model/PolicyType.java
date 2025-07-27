package com.insurance.model;

public class PolicyType {
    private long policytypeid;
    private String policyname;
    private String description;
    private long riskscore;

    // Default constructor
    public PolicyType() {}

    // Constructor with all fields except ID
    public PolicyType(String policyname, String description, long riskscore) {
        this.policyname = policyname;
        this.description = description;
        this.riskscore = riskscore;
    }

    // Getters and Setters
    public long getpolicytypeid() { return policytypeid; }
    public void setpolicytypeid(long policytypeid) { this.policytypeid = policytypeid; }

    public String getpolicyname() { return policyname; }
    public void setpolicyname(String policyname) { this.policyname = policyname; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public long getriskscore() { return riskscore; }
    public void setriskscore(long riskscore) { this.riskscore = riskscore; }

    @Override
    public String toString() {
        return "PolicyType{" +
                "policytypeid=" + policytypeid +
                ", policyname='" + policyname + '\'' +
                ", description='" + description + '\'' +
                ", riskscore=" + riskscore +
                '}';
    }
}
