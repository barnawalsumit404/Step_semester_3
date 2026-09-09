package week5.practice_problems;

import java.util.Arrays;

public final class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be empty.");
        }

        if (medicationCodes == null) {
            throw new IllegalArgumentException("Medication codes cannot be null.");
        }

        String[] validated = new String[medicationCodes.length];
        for (int i = 0; i < medicationCodes.length; i++) {
            if (medicationCodes[i] == null || !medicationCodes[i].matches("MED-[A-Z]")) {
                throw new IllegalArgumentException("Invalid medication code at index " + i);
            }
            validated[i] = medicationCodes[i];
        }

        this.patientId = patientId.trim();
        this.medicationCodes = Arrays.copyOf(validated, validated.length);
    }

    public String[] getMedicationCodes() {
        return Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length) {
            throw new IllegalArgumentException("Index out of range.");
        }
        if (newCode == null || !newCode.matches("MED-[A-Z]")) {
            throw new IllegalArgumentException("Invalid medication code.");
        }

        String[] updated = Arrays.copyOf(medicationCodes, medicationCodes.length);
        updated[index] = newCode;
        return new DischargeSummary(this.patientId, updated);
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        if (summaries == null) {
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";
        }

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCare++;
            } else {
                routine++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + criticalCare + " critical-care | " + routine + " routine";
    }

    public static void main(String[] args) {
        try {
            new DischargeSummary("MT2026-0142", new String[]{"MED-A", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "MED-B"});
        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println(Arrays.toString(d.getMedicationCodes()));

        System.out.println(processNightlyBatch(new DischargeSummary[] {
                new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
                null,
                new DischargeSummary("MT002", new String[]{"MED-Y"})
        }));
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}
