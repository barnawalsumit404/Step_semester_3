package week12.practice_problems;

import java.util.Scanner;

public class Problem5_PlacementDriveShortlist {
    static class Candidate {
        final String name;
        final int[] scores;
        final int total;
        int bestGain;
        int bestStart;
        int bestEnd;

        Candidate(String name, int[] scores) {
            this.name = name;
            this.scores = scores;
            total = sum(scores);
            findBestClimb();
        }

        private void findBestClimb() {
            int currentGain = scores[1] - scores[0];
            int currentStart = 0;
            bestGain = currentGain;
            bestStart = 0;
            bestEnd = 1;

            for (int i = 1; i < scores.length - 1; i++) {
                int change = scores[i + 1] - scores[i];
                if (currentGain < 0) {
                    currentGain = change;
                    currentStart = i;
                } else {
                    currentGain += change;
                }
                if (currentGain > bestGain) {
                    bestGain = currentGain;
                    bestStart = currentStart;
                    bestEnd = i + 1;
                }
            }
        }

        private static int sum(int[] values) {
            int result = 0;
            for (int value : values) {
                result += value;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int candidateCount = scanner.nextInt();
        Candidate[] candidates = new Candidate[candidateCount];
        for (int i = 0; i < candidateCount; i++) {
            String name = scanner.next();
            int[] scores = new int[6];
            for (int j = 0; j < scores.length; j++) {
                scores[j] = scanner.nextInt();
            }
            candidates[i] = new Candidate(name, scores);
        }

        mergeSort(candidates);
        System.out.println("Shortlist:");
        for (int i = 0; i < candidates.length; i++) {
            Candidate candidate = candidates[i];
            System.out.println((i + 1) + ". " + candidate.name + " " + candidate.total
                    + " | best climb +" + candidate.bestGain + " (test "
                    + (candidate.bestStart + 1) + " to " + (candidate.bestEnd + 1) + ")");
        }

        while (scanner.hasNext()) {
            String command = scanner.next();
            int value = scanner.nextInt();
            if (command.equals("CUTOFF")) {
                System.out.println("CUTOFF " + value + ": " + countAtLeast(candidates, value)
                        + " candidates");
            } else if (command.equals("PAIR")) {
                printPair(candidates, value);
            } else {
                throw new IllegalArgumentException("Unknown query: " + command);
            }
        }
        scanner.close();
    }

    private static void mergeSort(Candidate[] candidates) {
        Candidate[] buffer = new Candidate[candidates.length];
        mergeSort(candidates, buffer, 0, candidates.length);
    }

    private static void mergeSort(Candidate[] candidates, Candidate[] buffer, int left, int right) {
        if (right - left <= 1) {
            return;
        }
        int middle = (left + right) / 2;
        mergeSort(candidates, buffer, left, middle);
        mergeSort(candidates, buffer, middle, right);

        int i = left;
        int j = middle;
        int next = left;
        while (i < middle && j < right) {
            if (candidates[i].total >= candidates[j].total) {
                buffer[next++] = candidates[i++];
            } else {
                buffer[next++] = candidates[j++];
            }
        }
        while (i < middle) {
            buffer[next++] = candidates[i++];
        }
        while (j < right) {
            buffer[next++] = candidates[j++];
        }
        for (i = left; i < right; i++) {
            candidates[i] = buffer[i];
        }
    }

    private static int countAtLeast(Candidate[] candidates, int cutoff) {
        int left = 0;
        int right = candidates.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (candidates[middle].total >= cutoff) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    private static void printPair(Candidate[] candidates, int target) {
        int left = 0;
        int right = candidates.length - 1;
        while (left < right) {
            int sum = candidates[left].total + candidates[right].total;
            if (sum == target) {
                System.out.println("PAIR " + target + ": " + candidates[left].name
                        + " + " + candidates[right].name);
                return;
            }
            if (sum > target) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println("PAIR " + target + ": no pair");
    }
}
