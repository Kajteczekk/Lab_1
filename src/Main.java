import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n==========================================");
            System.out.println("   DEMONSTRACJA KOLEKCJI W JĘZYKU JAVA   ");
            System.out.println("==========================================");
            System.out.println("1. Demonstruj SET (HashSet vs TreeSet)");
            System.out.println("2. Demonstruj LIST (ArrayList vs LinkedList)");
            System.out.println("3. Demonstruj MAP (HashMap vs TreeMap)");
            System.out.println("4. Demonstruj znaczenie equals() i hashCode()");
            System.out.println("5. Demonstruj Comparable vs Comparator");
            System.out.println("0. Wyjście");
            System.out.print("Wybierz opcję: ");

            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1" -> demoSet();
                case "2" -> demoList();
                case "3" -> demoMap();
                case "4" -> demoEqualsHashCode();
                case "5" -> demoComparableVsComparator();
                case "0" -> {
                    running = false;
                    System.out.println("Koniec programu.");
                }
                default -> System.out.println("Niepoprawna opcja! Spróbuj ponownie.");
            }
        }
    }

    // ==========================================
    // 1. SET (HashSet vs TreeSet)
    // ==========================================
    private static void demoSet() {
        System.out.println("--- DEMO: SET (HashSet vs TreeSet) ---");

        // HashSet - brak kolejności, brak duplikatów
        Set<String> hashSet = new HashSet<>();
        System.out.println("--> Dodawanie do HashSet (Banan, Jabłko, Ananas, Jabłko):");
        hashSet.add("Banan");
        hashSet.add("Jabłko");
        hashSet.add("Ananas");
        boolean addedDuplicate = hashSet.add("Jabłko"); // Zwraca false, duplikat zignorowany
        System.out.println("Czy powtórnie dodano 'Jabłko'? " + addedDuplicate);

        System.out.println("HashSet (kolejność elementów jest nieokreślona): " + hashSet);

        // Usuwanie z HashSet
        hashSet.remove("Banan");
        System.out.println("Po usunięciu 'Banan': " + hashSet);

        // Iteracja
        System.out.print("Iteracja po HashSet: ");
        for (String item : hashSet) {
            System.out.print("[" + item + "] ");
        }
        System.out.println("\n");

        // TreeSet - automatycznie sortuje elementy naturalnie (alfabetycznie)
        Set<String> treeSet = new TreeSet<>();
        System.out.println("--> Dodawanie do TreeSet (Banan, Jabłko, Ananas, Cytryna):");
        treeSet.add("Banan");
        treeSet.add("Jabłko");
        treeSet.add("Ananas");
        treeSet.add("Cytryna");

        System.out.println("TreeSet (elementy są automatycznie posortowane): " + treeSet);
    }

    // ==========================================
    // 2. LIST (ArrayList vs LinkedList)
    // ==========================================
    private static void demoList() {
        System.out.println("--- DEMO: LIST (ArrayList vs LinkedList) ---");

        // ArrayList - szybki dostęp po indeksie, wolniejsza przy usuwaniu/wstawianiu w środku
        List<String> arrayList = new ArrayList<>();
        System.out.println("--> Dodawanie do ArrayList (z duplikatami):");
        arrayList.add("Kawa");
        arrayList.add("Herbata");
        arrayList.add("Kawa"); // Listy POZWALAJĄ na duplikaty
        arrayList.add("Woda");

        System.out.println("ArrayList: " + arrayList);

        // Dostęp po indeksie
        System.out.println("Element na indeksie 1: " + arrayList.get(1));

        // Usuwanie
        arrayList.remove("Kawa"); // Usuwa PIERWSZE wystąpienie
        System.out.println("Po usunięciu 'Kawa': " + arrayList);

        // LinkedList - listy dwukierunkowe (szybkie dodawanie/usuwanie na końcach)
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Zupa");
        linkedList.addFirst("Przystawka"); // Dedykowane metody LinkedList
        linkedList.addLast("Deser");

        System.out.println("LinkedList: " + linkedList);

        // Iteracja za pomocą Iteratora
        System.out.print("Iteracja po LinkedList (Iterator): ");
        Iterator<String> it = linkedList.iterator();
        while (it.hasNext()) {
            System.out.print("<" + it.next() + "> ");
        }
        System.out.println();
    }

    // ==========================================
    // 3. MAP (HashMap vs TreeMap)
    // ==========================================
    private static void demoMap() {
        System.out.println("--- DEMO: MAP (HashMap vs TreeMap) ---");

        // HashMap - brak kolejności, klucze unikalne
        Map<String, Integer> hashMap = new HashMap<>();
        System.out.println("--> Dodawanie do HashMap (klucz -> wartość):");
        hashMap.put("Jan", 25);
        hashMap.put("Anna", 30);
        hashMap.put("Piotr", 20);

        System.out.println("HashMap: " + hashMap);

        // Nadpisanie wartości dla istniejącego klucza
        System.out.println("Ponowne dodanie klucza 'Jan' z nową wartością 99...");
        hashMap.put("Jan", 99);
        System.out.println("HashMap po zmianie: " + hashMap);

        // Usuwanie
        hashMap.remove("Piotr");
        System.out.println("Po usunięciu klucza 'Piotr': " + hashMap);

        // Iteracja po kluczach i wartościach (Map.Entry)
        System.out.println("Iteracja po pary klucz-wartość (entrySet):");
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            System.out.println("  Klucz: " + entry.getKey() + " -> Wartość: " + entry.getValue());
        }

        System.out.println();

        // TreeMap - sortuje klucze naturalnie
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Zofia", 100);
        treeMap.put("Adam", 200);
        treeMap.put("Barbara", 150);

        System.out.println("TreeMap (klucze posortowane alfabetycznie): " + treeMap);
    }

    // ==========================================
    // 4. EQUALS I HASHCODE DEMO
    // ==========================================
    private static void demoEqualsHashCode() {
        System.out.println("--- DEMO: Różnica po zdefiniowaniu equals() i hashCode() ---");

        // Obiekty BEZ nadpisanych equals() i hashCode()
        System.out.println("1. Obiekty BEZ equals()/hashCode():");
        Set<OsobaBezEquals> setBez = new HashSet<>();
        OsobaBezEquals o1 = new OsobaBezEquals("Jan", "Kowalski", 12345);
        OsobaBezEquals o2 = new OsobaBezEquals("Jan", "Kowalski", 12345); // Taki sam PESEL i dane!

        setBez.add(o1);
        setBez.add(o2);

        System.out.println("Czy o1.equals(o2)? " + o1.equals(o2) + " (porównuje referencje w pamięci)");
        System.out.println("Rozmiar HashSet (powinno być 1, ale jest 2 przez brak equals/hashCode!): " + setBez.size());
        System.out.println("Zawartość HashSet: " + setBez);

        System.out.println("\n2. Obiekty Z NADPISANYMI equals() i hashCode():");
        Set<OsobaZEquals> setZ = new HashSet<>();
        OsobaZEquals o3 = new OsobaZEquals("Jan", "Kowalski", 12345);
        OsobaZEquals o4 = new OsobaZEquals("Jan", "Kowalski", 12345);

        setZ.add(o3);
        boolean dodanoO4 = setZ.add(o4);

        System.out.println("Czy o3.equals(o4)? " + o3.equals(o4) + " (porównuje logiczne pola/PESEL)");
        System.out.println("Czy dodano o4 do HashSet? " + dodanoO4);
        System.out.println("Rozmiar HashSet (poprawnie zignorowano duplikat): " + setZ.size());
        System.out.println("Zawartość HashSet: " + setZ);
    }

    // ==========================================
    // 5. COMPARABLE VS COMPARATOR DEMO
    // ==========================================
    private static void demoComparableVsComparator() {
        System.out.println("--- DEMO: Comparable vs Comparator ---");

        // 1. Comparable - domyślne sortowanie zdefiniowane W EWNĄTRZ klasy (np. po wieku)
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Marek", 24, 4.5));
        studentList.add(new Student("Kasia", 20, 5.0));
        studentList.add(new Student("Tomek", 22, 3.5));

        System.out.println("Przed sortowaniem: " + studentList);

        // Sortowanie przy użyciu interfejsu Comparable (compareTo)
        Collections.sort(studentList);
        System.out.println("Po sortowaniu domyślnym (Comparable -> po WIEKU rosnąco):");
        System.out.println(studentList);

        // 2. Comparator - zewnętrzne kryteria sortowania tworzone NA ZEWNĄTRZ klasy
        System.out.println("\n--> Sortowanie za pomocą Comparatorów:");

        // A) Sortowanie po średniej (od najwyższej do najniższej)
        studentList.sort(new SredniaComparator().reversed());
        System.out.println("Sortowanie po ŚREDNIEJ (malejąco):");
        System.out.println(studentList);

        // B) Sortowanie alfabetyczne po imieniu (za pomocą lambda / Comparator.comparing)
        studentList.sort(Comparator.comparing(Student::getImie));
        System.out.println("Sortowanie po IMIENIU (alfabetycznie):");
        System.out.println(studentList);
    }
}

// ============================================================================
// KLASY POMOCNICZE
// ============================================================================

// --- Klasa BEZ equals i hashCode ---
class OsobaBezEquals {
    private String imie;
    private String nazwisko;
    private long pesel;

    public OsobaBezEquals(String imie, String nazwisko, long pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko + " (" + pesel + ")";
    }
}

// --- Klasa Z EQUALS i HASHCODE ---
class OsobaZEquals {
    private String imie;
    private String nazwisko;
    private long pesel;

    public OsobaZEquals(String imie, String nazwisko, long pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }

    // Porównanie logiczne: dwie osoby są identyczne, jeśli mają ten sam PESEL
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OsobaZEquals osoba = (OsobaZEquals) o;
        return pesel == osoba.pesel;
    }

    // HashCode wyliczany z PESEL-u (musi być spójny z equals)
    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko + " (" + pesel + ")";
    }
}

// --- Klasa implementująca COMPARABLE (sortowanie domyślne po wieku) ---
class Student implements Comparable<Student> {
    private String imie;
    private int wiek;
    private double srednia;

    public Student(String imie, int wiek, double srednia) {
        this.imie = imie;
        this.wiek = wiek;
        this.srednia = srednia;
    }

    public String getImie() { return imie; }
    public int getWiek() { return wiek; }
    public double getSrednia() { return srednia; }

    // Implementacja metody z interfejsu Comparable
    @Override
    public int compareTo(Student o) {
        // Zwraca <0 gdy ten obiekt jest mniejszy, 0 gdy równe, >0 gdy większy
        return Integer.compare(this.wiek, o.wiek);
    }

    @Override
    public String toString() {
        return imie + " [wiek=" + wiek + ", średnia=" + srednia + "]";
    }
}

// --- Zewnętrzny COMPARATOR do sortowania po średniej ---
class SredniaComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s1.getSrednia(), s2.getSrednia());
    }
}