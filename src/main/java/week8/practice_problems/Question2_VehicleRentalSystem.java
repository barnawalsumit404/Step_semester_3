public class Question2_VehicleRentalSystem {
    abstract static class Vehicle {
        private final String name;
        private boolean available = true;

        public Vehicle(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public abstract double calculateRentalCharge(int days);
    }

    static class StandardCar extends Vehicle {
        public StandardCar(String name) {
            super(name);
        }

        public double calculateRentalCharge(int days) {
            return days * 50.0;
        }
    }

    static class LuxuryCar extends Vehicle {
        public LuxuryCar(String name) {
            super(name);
        }

        public double calculateRentalCharge(int days) {
            return days * 100.0;
        }
    }

    static class SUV extends Vehicle {
        public SUV(String name) {
            super(name);
        }

        public double calculateRentalCharge(int days) {
            return days * 80.0;
        }
    }

    static class Rental {
        private final Vehicle vehicle;
        private final int days;

        public Rental(Vehicle vehicle, int days) {
            this.vehicle = vehicle;
            this.days = days;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public int getDays() {
            return days;
        }

        public double getTotalCharge() {
            return vehicle.calculateRentalCharge(days);
        }
    }

    static class RentalService {
        private final Vehicle[] vehicles = new Vehicle[10];
        private int count = 0;

        public void addVehicle(Vehicle vehicle) {
            vehicles[count++] = vehicle;
        }

        public Rental rentVehicle(String vehicleName, int days) {
            for (int i = 0; i < count; i++) {
                Vehicle v = vehicles[i];
                if (v.getName().equals(vehicleName) && v.isAvailable()) {
                    v.setAvailable(false);
                    return new Rental(v, days);
                }
            }
            return null;
        }

        public void returnVehicle(Vehicle vehicle) {
            vehicle.setAvailable(true);
            System.out.println(vehicle.getName() + " returned. Now available.");
        }
    }

    public static void main(String[] args) {
        RentalService service = new RentalService();
        service.addVehicle(new LuxuryCar("Luxury Car A"));
        service.addVehicle(new StandardCar("Standard Car B"));

        Rental r1 = service.rentVehicle("Luxury Car A", 3);
        System.out.println(r1.getVehicle().getName() + " rented for " + r1.getDays() + " days. Total charge: $" + r1.getTotalCharge());

        Rental r2 = service.rentVehicle("Standard Car B", 5);
        System.out.println(r2.getVehicle().getName() + " rented for " + r2.getDays() + " days. Total charge: $" + r2.getTotalCharge());

        service.returnVehicle(r1.getVehicle());
    }
}
