//  Job Sequencing with Deadlines 

import java.util.*;

public class JobSequencingWithDeadlines {
    static class Job {
        char id;
        int deadline;
        int profit;
        
        Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }
    
    public static void findJobSequence(List<Job> jobs, int maxDeadline) {
        // Sort jobs based on profit in descending order
        Collections.sort(jobs, (a, b) -> b.profit - a.profit);
        
        // Array to store result (Sequence of jobs)
        char[] result = new char[maxDeadline];
        // Array to keep track of free time slots
        boolean[] slot = new boolean[maxDeadline];
        
        // Initialize all slots as free
        Arrays.fill(slot, false);
        
        // Iterate through all given jobs
        for (Job job : jobs) {
            // Find a free slot for this job (Note that we start from the last possible slot)
            for (int j = Math.min(maxDeadline - 1, job.deadline - 1); j >= 0; j--) {
                // If free slot found
                if (!slot[j]) {
                    result[j] = job.id;
                    slot[j] = true;
                    break;
                }
            }
        }
        
        // Print the result
        System.out.print("Following is maximum profit sequence of jobs: ");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                System.out.print(result[i] + " ");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job('a', 2, 100));
        jobs.add(new Job('b', 1, 19));
        jobs.add(new Job('c', 2, 27));
        jobs.add(new Job('d', 1, 25));
        jobs.add(new Job('e', 3, 15));
        
        int maxDeadline = 3;
        
        findJobSequence(jobs, maxDeadline);
    }
}