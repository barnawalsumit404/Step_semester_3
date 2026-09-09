package week5.practice_problems;

public class AccessRuleEngineExtended {
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
                if ("SAME_CLASS".equals(context) || "SAME_PACKAGE".equals(context) || "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(context)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "public":
                return "ALLOWED";
            default:
                return "DENIED";
        }
    }

    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.trim().isEmpty()) {
            return "";
        }

        String[] parts = accessorContext.trim().split("_");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
