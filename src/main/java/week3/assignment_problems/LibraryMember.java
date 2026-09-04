package oop.assigment_problems;

// Broken version: all fields are static
class BrokenLibraryMember {

    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }

    /*
     * name is wrong as static because each member has a different name.
     * memberId is wrong as static because each member needs a unique ID.
     * booksIssued is wrong as static because each member can issue
     * a different number of books.
     *
     * Static fields are shared by all objects, so creating a second
     * member overwrites the first member's data.
     */
}


// Corrected version
public class LibraryMember {

    // Instance fields: separate copy for every member
    private String name;
    private String memberId;
    private int booksIssued;

    // Static fields: shared by the whole library
    static String libraryName = "Campus Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    public static void main(String[] args) {

        // -------- BROKEN VERSION --------
        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember member2 =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        // Both show Rohan because static fields are shared
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);


        // -------- FIXED VERSION --------
        System.out.println("\nFixed version:");

        LibraryMember member3 =
                new LibraryMember("Aditi", 2);

        LibraryMember member4 =
                new LibraryMember("Rohan", 3);

        member3.printMemberCard();
        member4.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}