import java.util.HashMap;

public class Lab07_1 {
    public interface Set<E> {
        public void add(E e);

        public void remove(E e);

        public boolean containsALL(HashSet<E> s);

        public void addAll(HashSet<E> s);

        public void removeAll(HashSet<E> s);

        public void retainAll(HashSet<E> s);
    }

    public static class HashSet<E> implements Set<E> {
        private HashMap<E, Boolean> map;

        public HashSet() {
            map = new HashMap<>();
        }

        @Override
        public void add(E e) {
            map.put(e, Boolean.TRUE);
        }

        @Override
        public void remove(E e) {
            map.remove(e);
        }

        @Override
        public boolean containsALL(HashSet<E> s) {
            for(E e : s.map.keySet()) {
                if(!map.containsKey(e)){
                    return false;
                }
            }
            return true;
        }

        @Override
        public void addAll(HashSet<E> s) {
            for(E e : s.map.keySet()) {
                map.put(e, Boolean.TRUE);
            }
        }

        @Override
        public void removeAll(HashSet<E> s) {
            for(E e : s.map.keySet()){
                map.remove(e);
            }
        }

        @Override
        public void retainAll(HashSet<E> s) {
            HashSet<E> toRetain = new HashSet<>();
            for(E e : s.map.keySet()){
                if(map.containsKey(e)){
                    toRetain.add(e);
                }
            }
            map.clear();
            map.putAll(toRetain.map);
        }
    }

    public static void main(String[] args) {
        HashSet<Integer> setInt = new HashSet<>();
        setInt.add(1);
        setInt.add(2);
        setInt.add(3);
        setInt.add(4);
        for(Integer i : setInt.map.keySet()){
            System.out.print(i + " ");
        }
        System.out.println();

        HashSet<Integer> mini_setInt = new HashSet<>();
        mini_setInt.add(1);
        mini_setInt.add(2);
        mini_setInt.add(3);
        if(setInt.containsALL(mini_setInt)){
            System.out.println("YES");
        }
        System.out.println();

        setInt.remove(3);
        for(Integer i : setInt.map.keySet()){
            System.out.print(i + " ");
        }
        System.out.println();

        HashSet<Integer> setInt2 = new HashSet<>();
        setInt2.add(1);
        setInt2.add(2);
        setInt2.add(3);
        setInt2.add(5);
        setInt2.add(6);
        setInt.addAll(setInt2);
        for(Integer i : setInt.map.keySet()){
            System.out.print(i + " ");
        }
        System.out.println();

        HashSet<Integer> setInt3 = new HashSet<>();
        setInt3.add(1);
        setInt3.add(7);
        setInt3.add(3);
        setInt3.add(9);
        setInt3.add(6);
        HashSet<Integer> setInt4 = new HashSet<>();
        setInt4.add(1);
        setInt4.add(7);
        setInt4.add(3);
        setInt4.add(9);
        setInt4.add(6);

        setInt3.removeAll(setInt2);
        for(Integer i : setInt3.map.keySet()){
            System.out.print(i + " ");
        }
        System.out.println();

        setInt4.retainAll(setInt2);
        for(Integer i : setInt4.map.keySet()){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

