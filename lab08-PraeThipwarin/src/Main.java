import java.util.*;

public class Main {
    public static class HashSet<E> implements Set<E> {
        private HashMap<E, Boolean> map;

        public HashSet() {
            map = new HashMap<>();
        }

        @Override
        public int size() {
            return map.size();
        }

        @Override
        public boolean isEmpty() {
            return map.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return map.containsKey(o);
        }

        @Override
        public Iterator<E> iterator() {
            return map.keySet().iterator();
        }

        @Override
        public Object[] toArray() {
            return map.keySet().toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return map.keySet().toArray(a);
        }

        @Override
        public boolean add(E e) {
            if(!map.containsKey(e)){
                map.put(e,true);
                return true;
            }
            return false;
        }

        @Override
        public boolean remove(Object o) {
            if(contains(o)){
                map.remove(o);
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            for (Object e: c) {
                if (!map.containsKey(e)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            boolean edited = false;
            for (E e : c) {
                map.put(e, true);
                if(add(e)) {
                    edited = true;
                }
            }
            return edited;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            boolean edited = false;
            Iterator<E> it = map.keySet().iterator();
            while (it.hasNext()) {
                if (!c.contains(it.next())) {
                    it.remove();
                    edited = true;
                }
            }
            return edited;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            boolean edited = false;
            for (Object e : c) {
                map.remove(e);
                if(remove(e)){
                    edited = true;
                }
            }
            return edited;
        }

        @Override
        public void clear() {
            map.clear();
        }

        @Override
        public boolean equals(Object obj) {
            Set<?> other = (Set<?>) obj;
            return this.size() == other.size() && this.containsAll(other);
        }

        @Override
        public int hashCode() {
            int hash = 0;
            for (E element : map.keySet()) {
                hash += (element == null) ? 0 : element.hashCode();
            }
            return hash;
        }

        public void GetElement() {
            for (E e : map.keySet()) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    public static class ShortLexComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            int lengthComparison = Integer.compare(s1.length(), s2.length());
            if (lengthComparison != 0) {
                return lengthComparison;
            }
            return s1.compareToIgnoreCase(s2);
        }
    };

    public static <E> Comparator<Set<E>> setComparator(Comparator<E> cmp) {
        return new Comparator<Set<E>>() {
            @Override
            public int compare(Set<E> set1, Set<E> set2) {
                int sizeComparison = Integer.compare(set1.size(), set2.size());
                if (sizeComparison != 0) {
                    return sizeComparison;
                }

                List<E> list1 = new ArrayList<>(set1);
                List<E> list2 = new ArrayList<>(set2);

                list1.sort(cmp);
                list2.sort(cmp);

                for (int i = 0; i < list1.size(); i++) {
                    int elementComparison = cmp.compare(list1.get(i), list2.get(i));
                    if (elementComparison != 0) {
                        return elementComparison;
                    }
                }
                return 0;
            }
        };
    }
}
