import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Lab07 {
    public static class HashSet<E> implements Set<E> {
        private HashMap<E, Boolean> map;

        public HashSet() {
            map = new HashMap<>();
        }
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
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
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
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
//            Iterator<E> it = map.keySet().iterator();
//            while (it.hasNext()) {
//                if (!c.contains(it.next())) {
//                    it.remove();
//                    edited = true;
//                }
//            }
            for (Object e : map.keySet()) {
                if(!c.contains(e)){
                    remove(e);
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

        public void GetElement() {
            for (E e : map.keySet()) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Lab07.HashSet<String> set = new Lab07.HashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        set.GetElement();
        set.add("A");
        set.GetElement();
        set.remove("C");
        set.GetElement();

        Lab07.HashSet<String> set_2 = new Lab07.HashSet<>();
        set_2.add("A");
        set_2.add("C");
        set_2.add("D");
        set_2.add("E");
        boolean result = set.containsAll(set_2);
        System.out.println(result);

        set.addAll(set_2);
        set.GetElement();

        Lab07.HashSet<String> set_3 = new Lab07.HashSet<>();
        set_3.add("A");
        set_3.add("C");
        set.removeAll(set_3);
        set.GetElement();

        set.add("Z");
        set.add("Y");
        set.GetElement();

        Lab07.HashSet<String> set_4 = new Lab07.HashSet<>();
        set_4.add("A");
        set_4.add("B");
        set_4.add("D");
        set_4.add("Z");
        set.retainAll(set_4);
        set.GetElement();
    }
}
