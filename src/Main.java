public class Main {
    public static void main(String[] args) {
        InsuranceService service = new InsuranceService();
        try {
            service.addUser("Shashwat", "shash@example.com", "9999999999");
            service.addPolicy(1, "Health", 5000.0, "Active");
            service.fileClaim(1, "Accident claim");

            System.out.println("Sample data inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
