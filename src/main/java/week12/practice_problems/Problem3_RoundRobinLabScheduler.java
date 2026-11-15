package week12.practice_problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Problem3_RoundRobinLabScheduler {
    static class Job {
        final String name;
        int remaining;

        Job(String name, int remaining) {
            this.name = name;
            this.remaining = remaining;
        }
    }

    static class LabScheduler {
        private final int quantum;
        private final Deque<Job> jobs = new ArrayDeque<>();

        LabScheduler(int quantum) {
            this.quantum = quantum;
        }

        void addJob(String name, int requiredTime) {
            jobs.addLast(new Job(name, requiredTime));
        }

        void run() {
            int currentTime = 0;
            List<String> slices = new ArrayList<>();
            List<String> finished = new ArrayList<>();

            while (!jobs.isEmpty()) {
                Job job = jobs.removeFirst();
                int start = currentTime;
                int runTime = Math.min(quantum, job.remaining);
                currentTime += runTime;
                job.remaining -= runTime;
                slices.add(job.name + "(" + start + "-" + currentTime + ")");

                if (job.remaining > 0) {
                    jobs.addLast(job);
                } else {
                    finished.add(job.name + " " + currentTime);
                }
            }

            System.out.println(String.join(" ", slices));
            System.out.println();
            System.out.println("finish " + String.join(", ", finished));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quantum = scanner.nextInt();
        int jobCount = scanner.nextInt();
        LabScheduler scheduler = new LabScheduler(quantum);
        for (int i = 0; i < jobCount; i++) {
            scheduler.addJob(scanner.next(), scanner.nextInt());
        }
        scheduler.run();
        scanner.close();
    }
}
