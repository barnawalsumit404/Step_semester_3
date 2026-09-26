public class Question1_CodeSprintJudgingDesk {
    interface Track {
        double calculateScore(double idea, double execution, double presentation);
    }

    static class InnovationTrack implements Track {
        public double calculateScore(double idea, double execution, double presentation) {
            return (idea * 0.50) + (execution * 0.30) + (presentation * 0.20);
        }
    }

    static class OpenTrack implements Track {
        public double calculateScore(double idea, double execution, double presentation) {
            return (idea + execution + presentation) / 3.0;
        }
    }

    static class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Team {
        private final String teamName;
        private final Student[] members;
        private final Track track;
        private Project project;

        public Team(String teamName, Student[] members, Track track) {
            this.teamName = teamName;
            this.members = members;
            this.track = track;
        }

        public String getTeamName() {
            return teamName;
        }

        public Student[] getMembers() {
            return members;
        }

        public Track getTrack() {
            return track;
        }

        public void submitProject(Project project) {
            this.project = project;
        }

        public Project getProject() {
            return project;
        }
    }

    static class Project {
        private final String projectName;
        private Score score;

        public Project(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectName() {
            return projectName;
        }

        public Score getScore() {
            return score;
        }

        public void setScore(Score score) {
            this.score = score;
        }
    }

    static class Score {
        private double idea;
        private double execution;
        private double presentation;
        private double finalScore;
        private boolean published = false;

        public Score(double idea, double execution, double presentation) {
            this.idea = idea;
            this.execution = execution;
            this.presentation = presentation;
        }

        public void setIdea(double idea) {
            if (published) {
                throw new IllegalStateException("Results have already been published.");
            }
            this.idea = idea;
        }

        public double getFinalScore() {
            return finalScore;
        }

        public void publish(Track track) {
            finalScore = track.calculateScore(idea, execution, presentation);
            published = true;
        }
    }

    static class Hackathon {
        private final Team[] teams = new Team[20];
        private int teamCount = 0;
        private String state = "Open";

        public void registerTeam(String teamName, Student[] members, Track track) {
            if (members.length < 2 || members.length > 4) {
                System.out.println("Registration failed: A team must have 2 to 4 members.");
                return;
            }
            Team t = new Team(teamName, members, track);
            teams[teamCount++] = t;
            System.out.println("Team " + teamName + " registered (" + members.length + " members). ");
        }

        public void submitProject(String teamName, String projectName) {
            for (int i = 0; i < teamCount; i++) {
                if (teams[i].getTeamName().equals(teamName)) {
                    teams[i].submitProject(new Project(projectName));
                    System.out.println("Project '" + projectName + "' submitted by " + teamName + ".");
                    return;
                }
            }
        }

        public void addScore(String teamName, double idea, double execution, double presentation) {
            for (int i = 0; i < teamCount; i++) {
                if (teams[i].getTeamName().equals(teamName)) {
                    teams[i].getProject().setScore(new Score(idea, execution, presentation));
                    System.out.println("Score recorded for '" + teams[i].getProject().getProjectName() + "'.");
                    return;
                }
            }
        }

        public void publishResults() {
            state = "Published";
            for (int i = 0; i < teamCount; i++) {
                Team t = teams[i];
                if (t.getProject() != null && t.getProject().getScore() != null) {
                    t.getProject().getScore().publish(t.getTrack());
                }
            }
            System.out.println("Results published.");
        }
    }

    public static void main(String[] args) {
        Hackathon h = new Hackathon();

        h.registerTeam("ByteBusters", new Student[]{new Student("Asha"), new Student("Ravi"), new Student("Neha")}, new InnovationTrack());
        h.registerTeam("SoloCoder", new Student[]{new Student("Kiran")}, new OpenTrack());
        h.submitProject("ByteBusters", "SmartAttend");
        h.addScore("ByteBusters", 8, 7, 9);
        h.publishResults();

        try {
            h.teams[0].getProject().getScore().setIdea(10);
        } catch (IllegalStateException e) {
            System.out.println("Rescore rejected: " + e.getMessage());
        }
    }
}
