public class Question1_OnlineExaminationSystem {
    interface Question {
        String getQuestionText();
        String getCorrectAnswer();
        boolean isCorrect(String answer);
    }

    static class MultipleChoiceQuestion implements Question {
        private final String questionText;
        private final String correctAnswer;

        public MultipleChoiceQuestion(String questionText, String correctAnswer) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public boolean isCorrect(String answer) {
            return correctAnswer.equalsIgnoreCase(answer);
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

    static class Examination {
        private final String title;
        private final Question[] questions;

        public Examination(String title, Question[] questions) {
            this.title = title;
            this.questions = questions;
        }

        public Question[] getQuestions() {
            return questions;
        }

        public String getTitle() {
            return title;
        }
    }

    static class Attempt {
        private final Student student;
        private final Examination examination;
        private final String[] answers;
        private boolean submitted;

        public Attempt(Student student, Examination examination) {
            this.student = student;
            this.examination = examination;
            this.answers = new String[examination.getQuestions().length];
        }

        public void answerQuestion(int index, String answer) {
            if (submitted) {
                System.out.println("Attempt already submitted.");
                return;
            }
            if (index >= 0 && index < answers.length) {
                answers[index] = answer;
            }
        }

        public void submit() {
            submitted = true;
        }

        public String evaluate() {
            int correct = 0;
            Question[] q = examination.getQuestions();

            for (int i = 0; i < q.length; i++) {
                if (q[i].isCorrect(answers[i])) {
                    correct++;
                }
            }

            return "Result for '" + examination.getTitle() + "' attempt: " + correct + "/" + q.length + " correct";
        }
    }

    public static void main(String[] args) {
        Student s = new Student("Alice");
        Question[] questions = {
                new MultipleChoiceQuestion("1 + 1 = ?", "A"),
                new MultipleChoiceQuestion("3 + 3 = ?", "B")
        };

        Examination exam = new Examination("Math Quiz", questions);
        Attempt attempt = new Attempt(s, exam);

        System.out.println("Examination 'Math Quiz' started by Student Alice.");
        attempt.answerQuestion(0, "A");
        attempt.answerQuestion(1, "C");
        attempt.submit();
        System.out.println("Examination 'Math Quiz' submitted successfully.");
        System.out.println(attempt.evaluate());
    }
}
