package week3.practice_problems;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    public double getDue() {
        return Math.max(0, totalFee - amountPaid);
    }

    public String getRegNo() {
        return regNo;
    }
}

class HostelFeeAccount extends FeeAccount {
    public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    public void payInTwoInstallments(double amount) {
        pay(amount);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }

    public double effectiveDue() {
        return getDue() * (1 - scholarshipPercent / 100.0);
    }
}

public class FeeAccountSystem {
    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("REG-101", 150000, 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("REG-102", 200000, 60000);
        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("REG-103", 180000, 0, 20);

        FeeAccount[] accounts = {plain, hostel, scholarship};

        for (FeeAccount account : accounts) {
            if (account instanceof HostelFeeAccount) {
                HostelFeeAccount hostelAccount = (HostelFeeAccount) account;
                hostelAccount.payInTwoInstallments(0);
                System.out.println("Hostel account due: Rs " + account.getDue());
            } else if (account instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount scholarshipAccount = (ScholarshipFeeAccount) account;
                System.out.println("Scholarship account effective due: Rs " + scholarshipAccount.effectiveDue());
            } else {
                System.out.println("Plain account due: Rs " + account.getDue());
            }
        }
    }
}
