import java.util.Scanner;

public class QuizGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] questions = {
            "Which language is used to create Android apps?",
            "Which keyword is used to create a class in Java?",
            "Which method is the entry point of a Java program?",
            "Which symbol is used for single-line comments?",
            "Which data type stores whole numbers?"
        };

        String[][] options = {
            {"1. Java", "2. Python", "3. C", "4. HTML"},
            {"1. function", "2. class", "3. new", "4. object"},
            {"1. start()", "2. run()", "3. main()", "4. execute()"},
            {"1. /*", "2. //", "3. #", "4. <!--"},
            {"1. double", "2. String", "3. boolean", "4. int"}
        };

        int[] answers = {1, 2, 3, 2, 4};
        int score = 0;

        System.out.println("=================================");
        System.out.println("          🧠 JAVA QUIZ");
        System.out.println("=================================");
        System.out.println("Answer the following questions!");

        for (int i = 0; i < questions.length; i++) {

            System.out.println("\nQuestion " + (i + 1));
            System.out.println(questions[i]);

            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.print("Your answer: ");
            int userAnswer = sc.nextInt();

            if (userAnswer == answers[i]) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong!");
            }
        }

        System.out.println("\n=================================");
        System.out.println("           🏆 RESULT");
        System.out.println("=================================");

        System.out.println("Score: " + score + "/" + questions.length);

        if (score == questions.length) {
            System.out.println("🔥 Perfect Score!");
        } else if (score >= 3) {
            System.out.println("👏 Good Job!");
        } else {
            System.out.println("📚 Keep Practicing!");
        }

        System.out.println("=================================");

        sc.close();
    }
}