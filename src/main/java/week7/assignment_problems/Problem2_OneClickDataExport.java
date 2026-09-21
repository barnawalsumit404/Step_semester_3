public class Problem2_OneClickDataExport {
    interface Exportable {
        String exportData();
    }

    static class ReportGenerator implements Exportable {
        private static int totalExports = 0;
        private final String reportName;

        public ReportGenerator(String reportName) {
            this.reportName = reportName;
        }

        @Override
        public String exportData() {
            totalExports++;
            return "Exported report: " + reportName;
        }

        public static int getTotalExports() {
            return totalExports;
        }
    }

    static class UserProfile implements Exportable {
        private static int totalExports = 0;
        private final String username;

        public UserProfile(String username) {
            this.username = username;
        }

        @Override
        public String exportData() {
            totalExports++;
            return "Exported profile: " + username;
        }

        public static int getTotalExports() {
            return totalExports;
        }
    }

    static int getTotalExports() {
        return ReportGenerator.getTotalExports() + UserProfile.getTotalExports();
    }

    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            if (item != null) {
                System.out.println(item.exportData());
            }
        }
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        System.out.println(r.exportData());

        UserProfile u = new UserProfile("jane_doe");
        System.out.println(u.exportData());

        Exportable ref = r;
        exportAll(new Exportable[]{ref, u});
        System.out.println(getTotalExports());
    }
}
