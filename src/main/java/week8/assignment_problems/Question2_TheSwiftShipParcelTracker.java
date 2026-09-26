public class Question2_TheSwiftShipParcelTracker {
    enum ParcelStatus {
        BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED
    }

    interface ShippingType {
        double calculateCharge(double weight);
    }

    static class StandardShipping implements ShippingType {
        public double calculateCharge(double weight) {
            return 40 + (10 * weight);
        }
    }

    static class ExpressShipping implements ShippingType {
        public double calculateCharge(double weight) {
            return 80 + (15 * weight);
        }
    }

    static class FragileShipping implements ShippingType {
        public double calculateCharge(double weight) {
            return new StandardShipping().calculateCharge(weight) + 50;
        }
    }

    interface NotificationChannel {
        void notify(String parcelId, ParcelStatus status);
    }

    static class SmsChannel implements NotificationChannel {
        public void notify(String parcelId, ParcelStatus status) {
            System.out.println("[SMS] " + parcelId + " is now " + status);
        }
    }

    static class EmailChannel implements NotificationChannel {
        public void notify(String parcelId, ParcelStatus status) {
            System.out.println("[Email] " + parcelId + " is now " + status);
        }
    }

    static class Customer {
        private final NotificationChannel[] channels = new NotificationChannel[5];
        private int channelCount = 0;

        public void subscribe(NotificationChannel channel) {
            channels[channelCount++] = channel;
        }

        public void notifyChannels(String parcelId, ParcelStatus status) {
            for (int i = 0; i < channelCount; i++) {
                channels[i].notify(parcelId, status);
            }
        }
    }

    static class Parcel {
        private final String parcelId;
        private final ShippingType shippingType;
        private final double weight;
        private final Customer customer;
        private ParcelStatus status;

        public Parcel(String parcelId, ShippingType shippingType, double weight, Customer customer) {
            this.parcelId = parcelId;
            this.shippingType = shippingType;
            this.weight = weight;
            this.customer = customer;
            this.status = ParcelStatus.BOOKED;
            customer.notifyChannels(parcelId, status);
        }

        public void updateStatus(ParcelStatus newStatus) {
            if (status == ParcelStatus.BOOKED && newStatus != ParcelStatus.PICKED_UP) {
                System.out.println("Invalid transition: BOOKED -> " + newStatus + " not allowed.");
                return;
            }
            if (status == ParcelStatus.PICKED_UP && newStatus != ParcelStatus.IN_TRANSIT) {
                System.out.println("Invalid transition: PICKED_UP -> " + newStatus + " not allowed.");
                return;
            }
            if (status == ParcelStatus.IN_TRANSIT && newStatus != ParcelStatus.OUT_FOR_DELIVERY) {
                System.out.println("Invalid transition: IN_TRANSIT -> " + newStatus + " not allowed.");
                return;
            }
            if (status == ParcelStatus.OUT_FOR_DELIVERY && newStatus != ParcelStatus.DELIVERED) {
                System.out.println("Invalid transition: OUT_FOR_DELIVERY -> " + newStatus + " not allowed.");
                return;
            }

            status = newStatus;
            customer.notifyChannels(parcelId, status);
        }

        public void cancel() {
            if (status == ParcelStatus.BOOKED) {
                System.out.println("Cancellation failed: " + parcelId + " can be cancelled only while BOOKED.");
            } else {
                System.out.println("Cancellation failed: " + parcelId + " can be cancelled only while BOOKED.");
            }
        }

        public double getCharge() {
            return shippingType.calculateCharge(weight);
        }
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.subscribe(new SmsChannel());
        customer.subscribe(new EmailChannel());

        Parcel parcel = new Parcel("P101", new ExpressShipping(), 2, customer);
        System.out.println("Parcel P101 booked (Express, 2 kg). Charge: ₹" + parcel.getCharge());

        parcel.updateStatus(ParcelStatus.PICKED_UP);
        parcel.cancel();
        parcel.updateStatus(ParcelStatus.IN_TRANSIT);
        parcel.updateStatus(ParcelStatus.DELIVERED);
    }
}
