package oop.assigment_problems;

public class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {
            ManagerEmployee manager = (ManagerEmployee) employee;
            pay = manager.effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        String parking;

        if (slot != null) {
            parking = slot.slotNo;
        } else {
            parking = "no parking assigned";
        }

        return name + " | Pay: Rs " + pay + " | Slot: " + parking;
    }

    public static void main(String[] args) {

        ParkingSlot[] slots = {
                new ParkingSlot("A1", 1, 0),
                new ParkingSlot("A2", 1, 0)
        };

        ManagerEmployee divyaEmployee =
                new ManagerEmployee(101, "Divya", 70000, 8000);

        Employee karanEmployee =
                new Employee(102, "Karan", 40000);

        Employee meeraEmployee =
                new Employee(103, "Meera", 12000);

        ParkingSlot slot1 = ParkingSlot.findAvailableSlot(slots);
        if (slot1 != null) {
            slot1.allot("DIVYA001");
        }

        ParkingSlot slot2 = ParkingSlot.findAvailableSlot(slots);
        if (slot2 != null) {
            slot2.allot("KARAN002");
        }

        CompanyEmployeeRecord record1 =
                new CompanyEmployeeRecord("Divya", "101",
                        divyaEmployee, slot1);

        CompanyEmployeeRecord record2 =
                new CompanyEmployeeRecord("Karan", "102",
                        karanEmployee, slot2);

        CompanyEmployeeRecord record3 =
                new CompanyEmployeeRecord("Meera", "103",
                        meeraEmployee, null);

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println("Total records: " + totalRecords);
    }
}