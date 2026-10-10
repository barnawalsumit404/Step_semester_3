package week10.practice_problems;

public class Problem5_LibraryPlatform {
    static abstract class Resource {
        private final String id;

        Resource(String id) {
            this.id = id;
        }

        String getId() {
            return id;
        }

        boolean canReserve() {
            return false;
        }

        boolean canDownload() {
            return false;
        }
    }

    static class Book extends Resource {
        Book(String id) {
            super(id);
        }

        boolean canReserve() {
            return true;
        }
    }

    static class EBook extends Resource {
        EBook(String id) {
            super(id);
        }

        boolean canDownload() {
            return true;
        }
    }

    static class Library {
        private Resource[] resources = new Resource[4];
        private int size;

        String add(Resource resource) {
            int index = findIndex(resource.getId());
            if (index >= 0) {
                return "duplicate rejected";
            }
            int insertionPoint = -index - 1;
            ensureCapacity();
            System.arraycopy(resources, insertionPoint, resources, insertionPoint + 1, size - insertionPoint);
            resources[insertionPoint] = resource;
            size++;
            return resource.getId() + " added";
        }

        String reserve(String id, String memberId) {
            int index = findIndex(id);
            if (index < 0) {
                return id + " rejected: resource not found";
            }
            if (!resources[index].canReserve()) {
                return id + " rejected: reserve unsupported";
            }
            return id + " reserved for " + memberId;
        }

        String download(String id) {
            int index = findIndex(id);
            if (index < 0) {
                return id + " rejected: resource not found";
            }
            if (!resources[index].canDownload()) {
                return id + " rejected: download unsupported";
            }
            return id + " downloaded";
        }

        String find(String id) {
            int index = findIndex(id);
            return index < 0 ? id + " not found" : id + " found at index " + index;
        }

        private int findIndex(String id) {
            int left = 0;
            int right = size - 1;
            while (left <= right) {
                int middle = left + (right - left) / 2;
                int comparison = resources[middle].getId().compareTo(id);
                if (comparison == 0) {
                    return middle;
                }
                if (comparison < 0) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
            return -left - 1;
        }

        private void ensureCapacity() {
            if (size == resources.length) {
                Resource[] expanded = new Resource[resources.length * 2];
                System.arraycopy(resources, 0, expanded, 0, size);
                resources = expanded;
            }
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        System.out.println(library.add(new Book("B1")));
        System.out.println(library.add(new Book("B1")));
        System.out.println(library.add(new EBook("E1")));
        System.out.println(library.reserve("B1", "M1"));
        System.out.println(library.download("B1"));
        System.out.println(library.download("E1"));
        System.out.println(library.find("E1"));
    }
}
