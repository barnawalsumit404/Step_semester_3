public class Question4_EmployeeLeaveRequestManagement {
    abstract static class Employee {
        private final String name;

        public Employee(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public abstract String getLeavePolicy();
    }

    static class FullTimeEmployee extends Employee {
        public FullTimeEmployee(String name) {
            super(name);
        }

        public String getLeavePolicy() {
            return "Full-time policy";
        }
    }

    static class PartTimeEmployee extends Employee {
        public PartTimeEmployee(String name) {
            super(name);
        }

        public String getLeavePolicy() {
            return "Part-time policy";
        }
    }

    enum LeaveStatus {
        PENDING, APPROVED, REJECTED
    }

    static class LeaveRequest {
        private final Employee employee;
        private final String dates;
        private LeaveStatus status;

        public LeaveRequest(Employee employee, String dates) {
            this.employee = employee;
            this.dates = dates;
            this.status = LeaveStatus.PENDING;
        }

        public Employee getEmployee() {
            return employee;
        }

        public String getDates() {
            return dates;
        }

        public LeaveStatus getStatus() {
            return status;
        }

        public void setStatus(LeaveStatus newStatus) {
            if (status == LeaveStatus.APPROVED || status == LeaveStatus.REJECTED) {
                System.out.println("Cannot change status: Approved request cannot revert to Pending.");
                return;
            }
            status = newStatus;
        }
    }

    static class LeaveManager {
        private final LeaveRequest[] requests = new LeaveRequest[20];
        private int count = 0;

        public void submitRequest(LeaveRequest request) {
            requests[count++] = request;
            System.out.println("Leave request submitted by " + request.getEmployee().getName() + " for " + request.getDates() + ". Status: " + request.getStatus());
        }

        public void approveRequest(LeaveRequest request) {
            request.setStatus(LeaveStatus.APPROVED);
            System.out.println("Leave request for " + request.getEmployee().getName() + " approved. Status: " + request.getStatus());
        }
    }

    public static void main(String[] args) {
        LeaveManager manager = new LeaveManager();

        Employee john = new FullTimeEmployee("John Doe");
        Employee jane = new PartTimeEmployee("Jane Smith");

        LeaveRequest r1 = new LeaveRequest(john, "2024-10-10 to 2024-10-12");
        LeaveRequest r2 = new LeaveRequest(jane, "2024-11-01 to 2024-11-05");

        manager.submitRequest(r1);
        manager.approveRequest(r1);
        manager.submitRequest(r2);

        r1.setStatus(LeaveStatus.PENDING);
    }
}
