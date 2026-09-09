package week3.practice_problems;

public class StaticStudentDemo {
    static class BrokenSrmStudent {
        static String name;
        static String regNo;
        static int attendance;

        BrokenSrmStudent(String name, String regNo, int attendance) {
            BrokenSrmStudent.name = name;
            BrokenSrmStudent.regNo = regNo;
            BrokenSrmStudent.attendance = attendance;
        }
    }

    static class SrmStudent {
        private String name;
        private String regNo;
        private int attendance;

        static String university = "SRM University";
        static int admissionCount = 0;

        SrmStudent(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            admissionCount++;
            this.regNo = "RA" + String.format("%013d", 231100301000L + admissionCount);
        }

        void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + admissionCount);
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenSrmStudent first = new BrokenSrmStudent("Ravi", "RA1", 82);
        BrokenSrmStudent second = new BrokenSrmStudent("Meera", "RA2", 74);

        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);

        System.out.println("\nFixed version:");
        SrmStudent student1 = new SrmStudent("Ravi", 82);
        SrmStudent student2 = new SrmStudent("Meera", 74);

        student1.printIdCard();
        student2.printIdCard();
        SrmStudent.printTotalAdmissions();
    }
}

/*
 * name is static is wrong because each student has their own name and one student should not overwrite another's.
 * regNo is static is wrong because every student needs a separate registration number.
 * attendance is static is wrong because each student can have different attendance values.
 * Static fields are shared by all objects, so the second student overwrote the first student's data.
 */
