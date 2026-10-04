package week9.practice_problems;

public class practiceThree {
    
    public static void main(String[] args) {
        String[][] catalog = {
            {"0001112223","Introduction to Algebra"},
            {"0002223334","Beginning Python"},
            {"0003334445","Classic Mythology"},
            {"0004445556","Data and Society"},
            {"0005556667","European History"}
        };

        System.out.println(findBook(catalog, "0003334445")); 
        System.out.println(findBook(catalog, "0009998887")); 
    }

    public static String findBook(String[][] catalog, String targetIsbn) {
        int left = 0;
        int right = catalog.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midIsbn = catalog[mid][0];

            int cmp = targetIsbn.compareTo(midIsbn);

            if (cmp == 0) {
                return catalog[mid][1]; // Found → return title
            } else if (cmp < 0) {
                right = mid - 1; // Search left half
            } else {
                left = mid + 1; // Search right half
            }
        }

        return "Not Found";
    }
}
