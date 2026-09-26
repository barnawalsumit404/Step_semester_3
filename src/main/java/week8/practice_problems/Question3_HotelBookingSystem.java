public class Question3_HotelBookingSystem {
    static class Customer {
        private final String name;

        public Customer(String name) {
            this.name = name;
        }
    }

    static class Reservation {
        private final Customer customer;
        private final String roomNumber;
        private final String dates;

        public Reservation(Customer customer, String roomNumber, String dates) {
            this.customer = customer;
            this.roomNumber = roomNumber;
            this.dates = dates;
        }
    }

    static class Room {
        private final String roomNumber;
        private final String category;
        private final double ratePerDay;

        public Room(String roomNumber, String category, double ratePerDay) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.ratePerDay = ratePerDay;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public double calculatePrice(int days) {
            return ratePerDay * days;
        }
    }

    static class BookingManager {
        private final Room[] rooms = new Room[10];
        private int roomCount = 0;
        private final Reservation[] bookings = new Reservation[50];
        private int bookingCount = 0;

        public void addRoom(Room room) {
            rooms[roomCount++] = room;
        }

        public boolean isRoomAvailable(String roomNumber) {
            for (int i = 0; i < bookingCount; i++) {
                if (bookings[i].roomNumber.equals(roomNumber)) {
                    return false;
                }
            }
            return true;
        }

        public void bookRoom(Customer customer, String roomNumber, int days) {
            if (!isRoomAvailable(roomNumber)) {
                System.out.println("Booking failed: Room " + roomNumber + " is not available.");
                return;
            }

            for (int i = 0; i < roomCount; i++) {
                if (rooms[i].getRoomNumber().equals(roomNumber)) {
                    double total = rooms[i].calculatePrice(days);
                    bookings[bookingCount++] = new Reservation(customer, roomNumber, "" + days + " days");
                    System.out.println("Room " + roomNumber + " booked. Total price: $" + total);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        BookingManager manager = new BookingManager();
        manager.addRoom(new Room("101", "Deluxe", 200));
        manager.addRoom(new Room("205", "Standard", 150));

        Customer c1 = new Customer("Amit");
        Customer c2 = new Customer("Riya");

        manager.bookRoom(c1, "101", 5);
        manager.bookRoom(c2, "205", 4);
        manager.bookRoom(c1, "101", 3);
    }
}
