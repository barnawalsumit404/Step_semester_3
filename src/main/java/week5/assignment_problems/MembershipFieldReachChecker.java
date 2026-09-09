package week5.assignment_problems;

import java.util.*;

public class MembershipFieldReachChecker {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        String modifier = fieldModifier.trim().toLowerCase();
        String context = accessorContext.trim();

        switch (modifier) {
            case "private":
                return "SAME_CLASS".equals(context) ? "ALLOWED" : "DENIED";
            case "default":
                return ("SAME_CLASS".equals(context) || "SAME_PACKAGE".equals(context)) ? "ALLOWED" : "DENIED";
            case "protected":
                return ("SAME_CLASS".equals(context) || "SAME_PACKAGE".equals(context)) ? "ALLOWED" : "DENIED";
            case "public":
                return "ALLOWED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        if (attempts == null || attempts.length == 0) {
            return "private: 0 allowed / 0 denied | default: 0 allowed / 0 denied | protected: 0 allowed / 0 denied | public: 0 allowed / 0 denied";
        }

        Map<String, int[]> summary = new LinkedHashMap<>();
        summary.put("private", new int[]{0, 0});
        summary.put("default", new int[]{0, 0});
        summary.put("protected", new int[]{0, 0});
        summary.put("public", new int[]{0, 0});

        for (String[] attempt : attempts) {
            if (attempt == null || attempt.length < 2) {
                continue;
            }

            String modifier = attempt[0] == null ? "" : attempt[0].trim().toLowerCase();
            if (!summary.containsKey(modifier)) {
                continue;
            }

            String result = classifyAccess(modifier, attempt[1]);
            if ("ALLOWED".equals(result)) {
                summary.get(modifier)[0]++;
            } else {
                summary.get(modifier)[1]++;
            }
        }

        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, int[]> entry : summary.entrySet()) {
            if (output.length() > 0) {
                output.append(" | ");
            }
            output.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue()[0])
                    .append(" allowed / ")
                    .append(entry.getValue()[1])
                    .append(" denied");
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));
        System.out.println(summarizeByModifier(new String[][]{
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        }));

        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }

    public static class LibraryMember {
        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
            String cleanId = (membershipId == null) ? null : membershipId.trim();
            if (cleanId == null || cleanId.isEmpty() || cleanId.length() < 4) {
                throw new IllegalArgumentException("membershipId must be at least 4 characters.");
            }
            this.membershipId = cleanId;
            this.branchCode = (branchCode == null) ? null : branchCode.trim();
            this.finesOwed = finesOwed;
            this.displayName = (displayName == null) ? null : displayName.trim();
        }
    }
}
