package week3.practice_problems;

public class SrmMiniSystem {
    static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        void pay(double amount) {
            if (amount > 0) {
                amountPaid += amount;
            }
        }

        double getDue() {
            return Math.max(0, totalFee - amountPaid);
        }
    }

    static class HostelFeeAccount extends FeeAccount {
        HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        void payInTwoInstallments(double amount) {
            pay(amount);
        }
    }

    static class HostelRoom {
        String roomNo;
        int beds;
        int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        boolean allot(String name) {
            if (occupied < beds) {
                occupied++;
                return true;
            }
            return false;
        }
    }

    static class SrmStudent {
        static int totalStudents = 0;

        String name;
        String regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;

        SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = room;
            totalStudents++;
        }

        String fullStatus() {
            String roomDisplay = (room == null) ? "unallotted" : room.roomNo;
            return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomDisplay;
        }
    }

    public static void main(String[] args) {
        HostelRoom c214 = new HostelRoom("C-214", 3, 1);
        HostelRoom c507 = new HostelRoom("C-507", 3, 1);

        SrmStudent ravi = new SrmStudent("Ravi", "RA1", new HostelFeeAccount("HF1", 200000, 60000), c214);
        SrmStudent anitha = new SrmStudent("Anitha", "RA2", new HostelFeeAccount("HF2", 180000, 0), c507);
        SrmStudent karthik = new SrmStudent("Karthik", "RA3", new HostelFeeAccount("HF3", 200000, 0), null);

        ravi.feeAccount.pay(-5000);
        anitha.feeAccount.pay(25000);
        karthik.feeAccount.pay(15000);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
