package week11.practice_problems;

public class Problem4_FindIntersectionNode {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static Node findIntersection(Node a, Node b) {
        int lengthA = length(a);
        int lengthB = length(b);
        if (lengthA > lengthB) {
            a = advance(a, lengthA - lengthB);
        } else {
            b = advance(b, lengthB - lengthA);
        }

        while (a != b) {
            a = a.next;
            b = b.next;
        }
        return a;
    }

    private static int length(Node head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    private static Node advance(Node head, int steps) {
        while (steps-- > 0) {
            head = head.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node shared = new Node(8);
        shared.next = new Node(4);
        shared.next.next = new Node(5);

        Node a = new Node(4);
        a.next = new Node(1);
        a.next.next = shared;
        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(1);
        b.next.next.next = shared;

        Node intersection = findIntersection(a, b);
        System.out.println(intersection == null ? "null" : "Node with value " + intersection.value);
        System.out.println(findIntersection(new Node(2), new Node(1)));
    }
}
