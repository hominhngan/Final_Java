import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Main program = new Main();
        program.cau1();
        program.cau2();
        program.cau3();
        program.cau4();
        program.cau5();
    }
    abstract class Student {
        // Private attributes
        private String name;
        private String id;

        // Constructor to initialize the attributes
        public Student(String name, String id) {
            this.name = name;
            this.id = id;
        }

        // Getter and Setter for name
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // Getter and Setter for id
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        // Abstract method to show information
        public abstract void showInfo();

        // Abstract method to calculate GPA
        public abstract double calcGPA();
    }

    class UndergradStudent extends Student {
        private double gpa;
        private int credits;

        public UndergradStudent(String name, String id, double gpa, int credits) {
            super(name, id);
            this.gpa = gpa;
            this.credits = credits;
        }

        @Override
        public void showInfo() {
            System.out.println("Undergraduate Student Info:");
            System.out.println("Name: " + getName());
            System.out.println("ID: " + getId());
            System.out.println("GPA: " + gpa);
            System.out.println("Credits: " + credits);
        }

        @Override
        public double calcGPA() {
            return gpa;
        }
    }

    class GradStudent extends Student {
        private boolean qualified;
        private int paper;

        public GradStudent(String name, String id, boolean qualified, int paper) {
            super(name, id);
            this.qualified = qualified;
            this.paper = paper;
        }

        @Override
        public void showInfo() {
            System.out.println("Graduate Student Info:");
            System.out.println("Name: " + getName());
            System.out.println("ID: " + getId());
            System.out.println("Qualified: " + (qualified ? "Yes" : "No"));
            System.out.println("Number of Papers: " + paper);
        }

        @Override
        public double calcGPA() {
            return 4.0;
        }
    }
    public void cau1(){
        Student undergradStudent = new UndergradStudent("John Doe", "1234", 3.75, 120);
        Student gradStudent = new GradStudent("Jane Smith", "5678", true, 3);
        undergradStudent.showInfo();
        gradStudent.showInfo();
    }
    public void cau2(){
        String data = "Hello world";
        String fileName = "raw.txt";

        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(data.getBytes());
        } catch (IOException e) {
            System.out.println("Loi: " + e.getMessage());
        }

        try (FileInputStream fis = new FileInputStream(fileName)) {
            int i;
            StringBuilder content = new StringBuilder();
            while ((i = fis.read()) != -1) {
                content.append((char) i);
            }
            String upperContent = content.toString().toUpperCase();
            System.out.println(upperContent);
        } catch (IOException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
    public void cau3(){
        Thread randomNumberThread = new Thread(new RandomNumberTask());
        randomNumberThread.start();

        Thread userInputThread = new Thread(new UserInputTask());
        userInputThread.start();
    }
    class RandomNumberTask implements Runnable {
        private Random random = new Random();

        @Override
        public void run() {
            while (true) {
                int randomNumber = 100 + random.nextInt(101); // Generates a number between 100 and 200
                System.out.println("Random number: " + randomNumber);

                try {
                    Thread.sleep(7000); // Sleep for 7 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class UserInputTask implements Runnable {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            int sum = 0;

            while (true) {
                System.out.print("Enter a number (0 to quit): ");
                int number = scanner.nextInt();

                if (number == 0) {
                    break;
                }

                sum += number;
                System.out.println("Current sum: " + sum);
            }

            scanner.close();
            System.out.println("Final sum: " + sum);
            System.exit(0); // Exit the program
        }
    }
    public void cau4(){
        Set<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> setB = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> setC = new HashSet<>(Arrays.asList(4, 5, 6));

        System.out.println("Set A là tập con của Set B: " + isSubset(setA, setB));
        System.out.println("Set C là tập con của Set B: " + isSubset(setC, setB));
    }
    public static <T> boolean isSubset(Set<T> subset, Set<T> superset) {
        return superset.containsAll(subset);
    }
    public void cau5(){
        List<String> strings = Arrays.asList("hello", "world", "java", "programming", "language", "functional", "interface", "lambda", "expression", "example");

        StringFilter filter = str -> str.length() > 5;

        List<String> filteredStrings = strings.stream()
                .filter(filter::filter)
                .collect(Collectors.toList());

        System.out.println("Danh sách các chuỗi có độ dài lớn hơn 5: " + filteredStrings);
    }

    @FunctionalInterface
    interface StringFilter {
        boolean filter(String str);
    }

}