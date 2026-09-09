package week3.practice_problems;

public class HostelRoomSystem {
    static class HostelRoom {
        private String roomNo;
        private int beds;
        private int occupied;

        public HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        public boolean allot(String name) {
            if (occupied < beds) {
                occupied++;
                System.out.println(name + " allotted to room " + roomNo);
                return true;
            }
            return false;
        }

        public int getOccupied() {
            return occupied;
        }

        public int getBeds() {
            return beds;
        }

        public String getRoomNo() {
            return roomNo;
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) {
            return null;
        }

        for (HostelRoom room : rooms) {
            if (room != null && room.getOccupied() < room.getBeds()) {
                return room;
            }
        }

        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);

        if (room == null) {
            System.out.println("No rooms available for " + studentName);
            return;
        }

        room.allot(studentName);
    }

    public static void main(String[] args) {
        HostelRoom[] roomsWithSpace = {
                new HostelRoom("C-214", 3, 2),
                new HostelRoom("C-507", 2, 2)
        };

        HostelRoom[] fullRooms = {
                new HostelRoom("C-214", 3, 3),
                new HostelRoom("C-507", 2, 2)
        };

        System.out.println("First attempt:");
        safeAllot(roomsWithSpace, "Divya");

        System.out.println("\nSecond attempt:");
        safeAllot(fullRooms, "Divya");
    }
}

/*
 * Passing the HostelRoom[] array into these methods does not copy the rooms themselves.
 * Java passes the reference to the original array, meaning both methods and the caller share the same array
 * object in memory. Only the array reference is copied, not the objects inside it.
 */
