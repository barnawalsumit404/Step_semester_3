package week3.practice_problems;

public class SrmStudent {
    private String name;
    private String regNo;
    private int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public boolean isEligible() {
        return attendance >= 75;
    }

    public static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) {
            return 0.0;
        }

        int total = 0;
        for (SrmStudent student : students) {
            if (student != null) {
                total += student.attendance;
            }
        }

        return total / (double) students.length;
    }

    public String getName() {
        return name;
    }

    public int getAttendance() {
        return attendance;
    }

    public static void main(String[] args) {
        SrmStudent[] students = {
                new SrmStudent("Ravi", "REG-101", 82),
                new SrmStudent("Anitha", "REG-102", 68),
                new SrmStudent("Karthik", "REG-103", 91),
                new SrmStudent("Meera", "REG-104", 74),
                new SrmStudent("Suresh", "REG-105", 60)
        };

        for (SrmStudent student : students) {
            String status = student.isEligible() ? "Eligible" : "Detained";
            System.out.println(student.getName() + " - " + student.getAttendance() + "% - " + status);
        }

        System.out.println("Class average: " + classAverage(students) + "%");
    }
}

/*
 * classAverage is static because it calculates a value across the whole set of SrmStudent objects,
 * so it belongs to the class as a whole. isEligible is not static because it checks the attendance
 * of one specific student instance.
 */
