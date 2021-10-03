package unit8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.TreeMap;

public class MathSet<GenericType extends Number> implements Comparator<GenericType> {
    private Map<GenericType, Object> map;
    private static final Object DEFAULT_VALUE = new Object();

    //Constructors
    public MathSet() {
        map = new HashMap<>();
    }

    public MathSet(int capacity) {
        map = new HashMap<>(capacity);
    }

    public MathSet(GenericType[] numbers) {
        map = new HashMap<>(Math.max((int) (numbers.length / .75f) + 1, 16));
        addArray(numbers);
    }

    @SafeVarargs
    public MathSet(GenericType[]... numbers) {
        map = new HashMap<>(Math.max((int) (numbers.length / .75f) + 1, 16));
        addArray(numbers);
    }

    public MathSet(MathSet<GenericType> numbers) {
        map = numbers.map;
    }

    @SafeVarargs
    public MathSet(MathSet<GenericType>... numbers) {
        map = new HashMap<>(Math.max((int) (numbers.length / .75f) + 1, 16));
        initMapFromVarargs(numbers);
    }

    //methods

    public void add(GenericType n) {
        map.put(n, DEFAULT_VALUE);
    }

    @SafeVarargs
    public final void add(GenericType... n) {
        for (GenericType number : n) {
            map.put(number, DEFAULT_VALUE);
        }
    }

    public void join(MathSet<GenericType> ms) {
        map.putAll(ms.map);
    }

    @SafeVarargs
    public final void join(MathSet<GenericType>... ms) {
        for (MathSet<GenericType> m : ms) {
            map.putAll(m.map);
        }
    }

    void intersection(MathSet<GenericType> ms) {
        Iterator<GenericType> it = iterator();
        while (it.hasNext()) {
            if (!ms.map.containsKey(it.next())) {
                it.remove();
            }
        }
    }

    @SafeVarargs
    final void intersection(MathSet<GenericType>... ms) {
        for (MathSet<GenericType> m : ms) {
            intersection(m);
        }
    }

    private void addArray(GenericType[] numbers) {
        for (GenericType number : numbers) {
            map.put(number, DEFAULT_VALUE);
        }
    }

    @SafeVarargs
    private void addArray(GenericType[]... numbers) {
        for (final GenericType[] subArray : numbers) {
            for (GenericType number : subArray) {
                map.put(number, DEFAULT_VALUE);
            }
        }
    }

    @SafeVarargs
    private void initMapFromVarargs(MathSet<GenericType>... numbers) {
        for (MathSet<GenericType> number : numbers) {
            map.putAll(number.map);
        }
    }

    public Iterator<GenericType> iterator() {
        return map.keySet().iterator();
    }

    public int size() {
        return map.size();
    }

    void sortDesc() {
        map = new TreeMap<>(map);
    }

    void sortDesc(int firstIndex, int lastIndex) {
        // super strange try sort something from index to index in Set
        final ArrayList<GenericType> listToBeSortedFromIndexToIndex = new ArrayList<>(map.keySet());
        final List<GenericType> subList = listToBeSortedFromIndexToIndex.subList(firstIndex, lastIndex);
        clear();
        map = new LinkedHashMap<>();
        for (GenericType genericType : subList) {
            map.put(genericType, DEFAULT_VALUE);
        }
    }

    void sortDesc(Number value) {
        // How can we sort by value? What it means?
    }

    void sortAsc(Number value) {
        // How can we sort by value? What it means?
    }

    GenericType get(int index) {
        final ArrayList<GenericType> genericTypes = new ArrayList<>(map.keySet());
        return genericTypes.get(index);
    }

    GenericType getMax() {
        final ArrayList<GenericType> list = new ArrayList<>(map.keySet());
        int max = Integer.MIN_VALUE;
        GenericType genericTypeMax = null;
        for (GenericType genericType : list) {
            if (genericType.intValue() > max) {
                max = genericType.intValue();
                genericTypeMax = genericType;
            }
        }
        return genericTypeMax;
    }

    GenericType getMin() {
        final ArrayList<GenericType> list = new ArrayList<>(map.keySet());
        int min = Integer.MAX_VALUE;
        GenericType genericTypeMax = null;
        for (GenericType genericType : list) {
            if (genericType.intValue() < min) {
                min = genericType.intValue();
                genericTypeMax = genericType;
            }
        }
        return genericTypeMax;
    }

    Number getAverage() {
        final ArrayList<GenericType> list = new ArrayList<>(map.keySet());
        int sum = 0;
        if (!list.isEmpty()) {
            for (GenericType genericType : list) {
                sum = sum + genericType.intValue();
            }
            return (double) sum / list.size();
        }
        return sum;
    }

    Number getMedian() {
        final ArrayList<GenericType> list = new ArrayList<>(new TreeMap<>(map).keySet());
        if (list.size() % 2 == 1)
            return list.get((list.size() + 1) / 2 - 1);
        else {
            final GenericType lower = list.get(list.size() / 2 - 1);
            final GenericType upper = list.get(list.size() / 2);

            return (lower.doubleValue() + upper.doubleValue()) / 2.0;
        }
    }

    void sortAsc() {
        AscSortByValue sort = new AscSortByValue(map);
        clear();
        map = new TreeMap<>(sort);
    }

    void sortAsc(int firstIndex, int lastIndex) {
        final ArrayList<GenericType> listToBeSortedFromIndexToIndex = new ArrayList<>(map.keySet());
        final List<GenericType> subList = listToBeSortedFromIndexToIndex.subList(firstIndex, lastIndex);
        reverse(subList);
        clear();
        map = new LinkedHashMap<>();
        for (GenericType genericType : subList) {
            map.put(genericType, DEFAULT_VALUE);
        }
    }

    void clear() {
        map = new HashMap<>();
    }

    void clear(GenericType[] numbers) {
        final Iterator<GenericType> iterator = map.keySet().iterator();
        for (GenericType number : numbers) {
            if (iterator.next().equals(number)) {
                iterator.remove();
            }
        }
    }

    public Number[] toArray() {
        final ArrayList<GenericType> list = new ArrayList<>(map.keySet());
        Number[] array = new Number[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    Number[] toArray(int firstIndex, int lastIndex) {
        final ArrayList<GenericType> list = new ArrayList<>(map.keySet());
        final List<GenericType> subList = list.subList(firstIndex, lastIndex);
        Number[] array = new Number[subList.size()];
        for (int i = 0; i < subList.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private void reverse(List<GenericType> list) {
        int size = list.size();
        if (size < 18 || list instanceof RandomAccess) {
            for (int i = 0, mid = size >> 1, j = size - 1; i < mid; i++, j--)
                swap(list, i, j);
        } else {
            final ListIterator<GenericType> fwd = list.listIterator();
            final ListIterator<GenericType> rev = list.listIterator(size);
            for (int i = 0, mid = list.size() >> 1; i < mid; i++) {
                GenericType tmp = fwd.next();
                fwd.set(rev.previous());
                rev.set(tmp);
            }
        }
    }

    private void swap(List<GenericType> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    @Override
    public int compare(GenericType o1, GenericType o2) {
        return compare(o1.longValue(), o2.longValue());
    }

    private static int compare(long a, long b) {
        return Long.compare(a, b);
    }

    private class AscSortByValue implements Comparator<GenericType> {
        private final Map<GenericType, Object> map;

        private AscSortByValue(Map<GenericType, Object> map) {
            this.map = map;
        }

        @Override
        public int compare(GenericType o1, GenericType o2) {
            return ((Integer) this.map.get(o1)).compareTo((Integer) this.map.get(o2));
        }
    }

    public String toString() {
        Iterator<GenericType> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            GenericType e = it.next();
            sb.append(e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}
