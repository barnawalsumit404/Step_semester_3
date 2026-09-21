public class Problem3_BonusCalculator {
    abstract static class StaffMember {
        private double baseSalary;
        private final double bonusRate;

        StaffMember(double baseSalary) {
            this(baseSalary, 0.10);
        }

        StaffMember(double baseSalary, double bonusRate) {
            this.baseSalary = baseSalary;
            this.bonusRate = bonusRate;
        }

        public abstract double calculateBonus();

        public double getSalary() {
            return baseSalary;
        }

        public void setSalary(double baseSalary) {
            if (baseSalary < 0) {
                System.out.println("Salary update rejected. Negative salary not allowed.");
                return;
            }
            this.baseSalary = baseSalary;
        }

        public double getBonusRate() {
            return bonusRate;
        }
    }

    interface Auditable {
        String auditRecord();
    }

    static class TeamLead extends StaffMember implements Auditable {
        private final int teamSize;

        public TeamLead(double baseSalary, int teamSize) {
            super(baseSalary);
            this.teamSize = teamSize;
        }

        public TeamLead(double baseSalary, double bonusRate, int teamSize) {
            super(baseSalary, bonusRate);
            this.teamSize = teamSize;
        }

        @Override
        public double calculateBonus() {
            return getSalary() * getBonusRate();
        }

        @Override
        public String auditRecord() {
            return "TeamLead audit: " + teamSize + " team members, salary $" + getSalary();
        }
    }

    static String getAuditIfApplicable(StaffMember s) {
        if (s instanceof Auditable) {
            return ((Auditable) s).auditRecord();
        }
        return "No audit required";
    }

    public static void main(String[] args) {
        TeamLead t = new TeamLead(60000, 5);
        System.out.println(t.calculateBonus());

        TeamLead t2 = new TeamLead(60000, 0.20, 5);
        System.out.println(t2.calculateBonus());

        t.setSalary(-5000);
        System.out.println("Salary after rejected set: $" + t.getSalary());

        StaffMember ref = t;
        System.out.println(getAuditIfApplicable(ref));
    }
}
