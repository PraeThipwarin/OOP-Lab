import java.util.*;

public class Lab08 {
    public static void main(String[] args) {
        Main.HashSet<String> setA = new Main.HashSet<>();
        setA.add("banana");
        setA.add("Apple");
        setA.add("Fig");
        setA.add("Grape");
        setA.add("Pineapple");
        setA.add("Orange");

        Object[] myArray = setA.toArray();
        System.out.println(Arrays.toString(myArray));
        
        List<String> listA = new ArrayList<>(setA);
        Collections.sort(listA, new Main.ShortLexComparator());
        System.out.println(listA);

        Main.HashSet<String> set1 = new Main.HashSet<>();
        set1.add("apple");
        set1.add("Banan");
        set1.add("Zherr");

        Main.HashSet<String> set2 = new Main.HashSet<>();
        set2.add("banan");
        set2.add("apple");
        set2.add("Datee");

        Main.HashSet<String> set3 = new Main.HashSet<>();
        set3.add("fig");
        set3.add("grape");

        Main.HashSet<String> set4 = new Main.HashSet<>();
        set4.add("Figerty");

        Comparator<Set<String>> comparator = Main.setComparator(new Main.ShortLexComparator());

        List<Set<String>> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);
        sets.add(set4);

        Collections.sort(sets, comparator);

        for (Set<String> set : sets) {
            System.out.print("Set: ");
            ((Main.HashSet<String>) set).GetElement();
        }

        Main.HashSet<String> set5 = new Main.HashSet<>();
        set5.add("banana");
        set5.add("apple");
        set5.add("date");

        Main.HashSet<String> set6 = new Main.HashSet<>();
        set6.add("date");
        set6.add("banana");
        set6.add("apple");

        System.out.println(set5.equals(set6));

        Set<String> javaSet1 = new HashSet<>(Arrays.asList("banana", "apple", "date"));
        Set<String> javaSet2 = new HashSet<>(Arrays.asList("date", "banana", "apple"));

        System.out.println(javaSet1.equals(javaSet2));
        System.out.println(set5.equals(javaSet2));

        HashSet<String> mySet = new HashSet<>();
        Set<String> javaSet = new HashSet<>();

        mySet.add("Apple");
        mySet.add("Banana");
        mySet.add("Orange");

        javaSet.add("Apple");
        javaSet.add("Banana");
        javaSet.add("Orange");

        int SetHashCode = mySet.hashCode();
        int javaSetHashCode = javaSet.hashCode();

        System.out.println(SetHashCode);
        System.out.println(javaSetHashCode);
    }
}
