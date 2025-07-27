public class Claim {
    int claimId;
    int policyId;
    String status;
    String reason;

    public Claim(int claimId, int policyId, String status, String reason) {
        this.claimId = claimId;
        this.policyId = policyId;
        this.status = status;
        this.reason = reason;
    }
}
