package week10.practice_problems;

public class Problem3_StudentMarksGrid {
    static class MarksGrid {
        private final String[] students;
        private final String[] subjects;
        private final int[][] marks;

        MarksGrid(String[] students, String[] subjects, int[][] marks) {
            this.students = students;
            this.subjects = subjects;
            this.marks = marks;
        }

        int totalForStudent(int studentIndex) {
            int total = 0;
            for (int mark : marks[studentIndex]) {
                if (mark >= 0) {
                    total += mark;
                }
            }
            return total;
        }

        String totalsReport() {
            StringBuilder report = new StringBuilder();
            for (int i = 0; i < students.length; i++) {
                if (i > 0) {
                    report.append(", ");
                }
                report.append(students[i]).append(" ").append(totalForStudent(i));
            }
            return report.toString();
        }

        String toppersReport() {
            StringBuilder report = new StringBuilder();
            for (int subject = 0; subject < subjects.length; subject++) {
                int topper = -1;
                int highest = -1;
                for (int student = 0; student < students.length; student++) {
                    if (marks[student][subject] >= 0 && marks[student][subject] > highest) {
                        highest = marks[student][subject];
                        topper = student;
                    }
                }
                if (subject > 0) {
                    report.append(", ");
                }
                report.append(subjects[subject]).append(" ")
                        .append(topper == -1 ? "No marks" : students[topper] + " " + highest);
            }
            return report.toString();
        }
    }

    public static void main(String[] args) {
        MarksGrid grid = new MarksGrid(
                new String[]{"Anu", "Ravi", "Meena"},
                new String[]{"Math", "Sci", "Eng"},
                new int[][]{{80, 90, 70}, {60, 85, -1}, {95, 75, 88}}
        );
        System.out.println("Totals " + grid.totalsReport());
        System.out.println("Toppers " + grid.toppersReport());
    }
}
