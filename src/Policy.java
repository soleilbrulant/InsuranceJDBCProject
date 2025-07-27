public class Policy {
    int policyId;
    String policyType;
    double premium;
    String status;

    public Policy(int policyId, String policyType, double premium, String status) {
        this.policyId = policyId;
        this.policyType = policyType;
        this.premium = premium;
        this.status = status;
    }
}
