package week10.practice_problems;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Problem2_CustomerRegistry {
    static class Customer {
        private final int id;
        private final String name;

        Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Customer)) {
                return false;
            }
            Customer customer = (Customer) other;
            return id == customer.id && Objects.equals(name, customer.name);
        }

        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    static class CustomerRegistry {
        private final Set<Customer> customers = new HashSet<>();

        boolean add(Customer customer) {
            return customers.add(customer);
        }

        boolean contains(Customer customer) {
            return customers.contains(customer);
        }

        int uniqueCount() {
            return customers.size();
        }
    }

    public static void main(String[] args) {
        CustomerRegistry registry = new CustomerRegistry();
        System.out.println(registry.add(new Customer(101, "Asha")));
        System.out.println(registry.add(new Customer(101, "Asha")));
        System.out.println(registry.add(new Customer(102, "Ravi")));
        System.out.println("unique count " + registry.uniqueCount());
        System.out.println("contains: " + registry.contains(new Customer(101, "Asha")));
    }
}
