package week12.assignment_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem3_RecentPagesCache {
    static class RecentCache {
        private static class Node {
            int key;
            String page;
            Node prev;
            Node next;

            Node(int key, String page) {
                this.key = key;
                this.page = page;
            }
        }

        private final int capacity;
        private final Map<Integer, Node> nodes = new HashMap<>();
        private final Node head = new Node(0, "");
        private final Node tail = new Node(0, "");
        private int lastEvictedKey = -1;

        RecentCache(int capacity) {
            if (capacity < 1) {
                throw new IllegalArgumentException("Capacity must be positive");
            }
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        String get(int key) {
            Node node = nodes.get(key);
            if (node == null) {
                return null;
            }
            moveToFront(node);
            return node.page;
        }

        void put(int key, String page) {
            lastEvictedKey = -1;
            Node node = nodes.get(key);
            if (node != null) {
                node.page = page;
                moveToFront(node);
                return;
            }

            node = new Node(key, page);
            nodes.put(key, node);
            addAfterHead(node);
            if (nodes.size() > capacity) {
                Node leastRecent = tail.prev;
                remove(leastRecent);
                nodes.remove(leastRecent.key);
                lastEvictedKey = leastRecent.key;
            }
        }

        int getLastEvictedKey() {
            return lastEvictedKey;
        }

        boolean containsKey(int key) {
            return nodes.containsKey(key);
        }

        void printMostRecentFirst() {
            StringBuilder result = new StringBuilder("Cache (most recent first): ");
            Node node = head.next;
            while (node != tail) {
                if (node != head.next) {
                    result.append(", ");
                }
                result.append(node.key).append("=").append(node.page);
                node = node.next;
            }
            System.out.println(result);
        }

        private void moveToFront(Node node) {
            remove(node);
            addAfterHead(node);
        }

        private void addAfterHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecentCache cache = new RecentCache(scanner.nextInt());
        int operations = scanner.nextInt();
        for (int i = 0; i < operations; i++) {
            String operation = scanner.next();
            int key = scanner.nextInt();
            if (operation.equals("put")) {
                String page = scanner.next();
                boolean updated = cache.containsKey(key);
                cache.put(key, page);
                System.out.print("put(" + key + "," + page + ")");
                if (cache.getLastEvictedKey() != -1) {
                    System.out.print(" evicted " + cache.getLastEvictedKey());
                } else if (updated) {
                    System.out.print(" updated");
                }
                System.out.println();
            } else if (operation.equals("get")) {
                String page = cache.get(key);
                System.out.println("get(" + key + ") -> " + (page == null ? "-1" : page));
            } else {
                throw new IllegalArgumentException("Unknown operation: " + operation);
            }
        }
        cache.printMostRecentFirst();
        scanner.close();
    }
}
