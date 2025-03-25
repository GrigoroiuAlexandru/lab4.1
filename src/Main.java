

import java.util.*;

class Student {
    String nume;
    int grupa;
    List<Integer> note;

    public Student(String nume, int grupa) {
        this.nume = nume;
        this.grupa = grupa;
        this.note = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            this.note.add(rand.nextInt(7) + 4); // Note între 4 și 10
        }
    }

    public double getMedie() {
        return note.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public long getNumarRestante() {
        return note.stream().filter(n -> n < 5).count();
    }

    @Override
    public String toString() {
        return nume + " (Grupa " + grupa + "): " + note.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> studenti = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            studenti.add(new Student("Student" + (i + 1), new Random().nextInt(5) + 1));
        }

        System.out.println("\nAfișare pe grupe în ordine alfabetică:");
        studenti.stream().sorted(Comparator.comparing((Student s) -> s.grupa).thenComparing(s -> s.nume))
                .forEach(System.out::println);

        System.out.println("\nAfișare integraliști după media notelor:");
        studenti.stream().filter(s -> s.getNumarRestante() == 0)
                .sorted(Comparator.comparingDouble(Student::getMedie).reversed())
                .forEach(System.out::println);

        System.out.println("\nAfișare restanțieri după numărul de restanțe:");
        studenti.stream().filter(s -> s.getNumarRestante() > 0)
                .sorted(Comparator.comparingLong(Student::getNumarRestante))
                .forEach(System.out::println);
    }
}
