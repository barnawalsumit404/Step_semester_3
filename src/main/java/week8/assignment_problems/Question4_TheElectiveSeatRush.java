public class Question4_TheElectiveSeatRush {
    static class Student {
        private final String name;
        private final String type;
        private int credits;

        public Student(String name, String type, int credits) {
            this.name = name;
            this.type = type;
            this.credits = credits;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getCredits() {
            return credits;
        }

        public void addCredits(int x) {
            credits += x;
        }

        public void removeCredits(int x) {
            credits -= x;
        }
    }

    static class Elective {
        private final String name;
        private final int credits;
        private final int capacity;
        private final Student[] enrolled = new Student[20];
        private final String[] waitlist = new String[20];
        private int enrolledCount = 0;
        private int waitCount = 0;

        public Elective(String name, int credits, int capacity) {
            this.name = name;
            this.credits = credits;
            this.capacity = capacity;
        }

        public boolean canEnroll(Student s) {
            int limit = getLimit(s.getType());
            return s.getCredits() + credits <= limit;
        }

        public int getLimit(String type) {
            if (type.equals("Regular")) return 24;
            if (type.equals("Honors")) return 28;
            if (type.equals("Exchange")) return 20;
            return 0;
        }

        public void enroll(Student s) {
            if (enrolledCount >= capacity) {
                waitlist[waitCount++] = s.getName();
                System.out.println("Waitlist: " + s.getName());
                return;
            }
            if (!canEnroll(s)) {
                System.out.println("Enrollment failed: " + s.getName() + " would exceed the credit limit.");
                return;
            }
            enrolled[enrolledCount++] = s;
            s.addCredits(credits);
            System.out.println(s.getName() + " enrolled in " + name + ".");
        }

        public void drop(Student s) {
            for (int i = 0; i < enrolledCount; i++) {
                if (enrolled[i].getName().equals(s.getName())) {
                    enrolled[i] = null;
                    s.removeCredits(credits);
                    enrolledCount--;
                    System.out.println(s.getName() + " dropped " + name + ".");
                    promoteWaitlist();
                    return;
                }
            }
        }

        public void promoteWaitlist() {
            if (waitCount == 0) return;
            for (int i = 0; i < waitCount; i++) {
                if (enrolledCount < capacity) {
                    String nameToEnroll = waitlist[i];
                    System.out.println(nameToEnroll + " promoted from waitlist and enrolled.");
                    waitlist[i] = null;
                    enrolledCount++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Elective e = new Elective("Cloud Computing", 4, 2);

        Student s1 = new Student("Asha", "Regular", 20);
        Student s2 = new Student("Ravi", "Honors", 22);
        Student s3 = new Student("Neha", "Exchange", 12);
        Student s4 = new Student("Kiran", "Regular", 22);

        e.enroll(s1);
        e.enroll(s2);
        e.enroll(s3);
        e.enroll(s4);

        e.drop(s1);
    }
}
