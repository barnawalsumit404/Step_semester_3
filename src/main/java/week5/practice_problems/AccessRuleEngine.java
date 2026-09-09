package week5.practice_problems;

public class AccessRuleEngine {
    private static final String[] MODIFIERS = {"private", "default", "protected", "public"};

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        String modifier = fieldModifier.trim().toLowerCase();
        String context = accessorContext.trim();

        if (!isKnownModifier(modifier)) {
            return "DENIED";
        }

        switch (modifier) {
            case "private":
                return ("SAME_CLASS".equals(context)) ? "ALLOWED" : "DENIED";

            case "default":
                return ("SAME_CLASS".equals(context) || "SAME_PACKAGE".equals(context)) ? "ALLOWED" : "DENIED";

            case "protected":
                if ("SAME_CLASS".equals(context) || "SAME_PACKAGE".equals(context)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeBatch(String[][] attempts) {
        if (attempts == null || attempts.length == 0) {
            return "Allowed: 0 | Denied: 0";
        }

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            if (attempt == null || attempt.length < 2) {
                denied++;
                continue;
            }

            String result = classifyAccess(attempt[0], attempt[1]);
            if ("ALLOWED".equals(result)) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    private static boolean isKnownModifier(String modifier) {
        for (String m : MODIFIERS) {
            if (m.equals(modifier)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("default", "DIFFERENT_PACKAGE"));
        System.out.println(summarizeBatch(new String[][] {
                {"protected", "SAME_PACKAGE"},
                {"protected", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        }));

        try {
            new PatientRecord("MT9", "W3", 98.2, "MediTrack Central");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }

    public static class PatientRecord {
        private String patientId;
        String wardCode;
        protected double vitalsScore;
        public String facilityName;

        public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
            String cleanId = (patientId == null) ? null : patientId.trim();
            if (cleanId == null || cleanId.isEmpty() || cleanId.length() < 4) {
                throw new IllegalArgumentException("patientId must be at least 4 characters.");
            }
            this.patientId = cleanId;
            this.wardCode = (wardCode == null) ? null : wardCode.trim();
            this.vitalsScore = vitalsScore;
            this.facilityName = (facilityName == null) ? null : facilityName.trim();
        }
    }
}
