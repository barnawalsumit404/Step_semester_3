package week10.assignment_problems;

public class Problem2_CampusPortalBrowserHistory {
    public static class Node {
        String url;
        Node previous;
        Node next;

        Node(String url) {
            this.url = url;
        }
    }

    public static class BrowserHistory {
        private Node current;

        public BrowserHistory(String homePage) {
            current = new Node(homePage);
        }

        public void visit(String url) {
            Node visited = new Node(url);
            current.next = null;
            visited.previous = current;
            current.next = visited;
            current = visited;
        }

        public String back(int steps) {
            while (steps > 0 && current.previous != null) {
                current = current.previous;
                steps--;
            }
            return current.url;
        }

        public String forward(int steps) {
            while (steps > 0 && current.next != null) {
                current = current.next;
                steps--;
            }
            return current.url;
        }
    }

    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory("srm.edu");
        history.visit("a.com");
        history.visit("b.com");
        history.visit("c.com");
        System.out.println(history.back(1));
        System.out.println(history.back(1));
        System.out.println(history.forward(1));
        history.visit("d.com");
        System.out.println(history.forward(2));
        System.out.println(history.back(2));
        System.out.println(history.back(7));
    }
}
