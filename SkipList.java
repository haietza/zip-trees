import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Iterator;
public class SkipList<T extends Comparable<? super T>> implements Iterable<T> {

    SkipNode<T> _head = new SkipNode<>(null, 33);
    private final Random rand = new Random();
    private int _levels = 1;
    private AtomicInteger size = new AtomicInteger(0);

    /// <summary>
    /// Inserts a value into the skip list.
    /// </summary>
    public void insert(T value) {
        // Determine the level of the new node. Generate a random number R. The
        // number of
        // 1-bits before we encounter the first 0-bit is the level of the node.
        // Since R is
        // 32-bit, the level can be at most 32.
        int level = 0;
        size.incrementAndGet();
        for (int R = rand.nextInt(); (R & 1) == 1; R >>= 1) {
            level++;
            if (level == _levels) {
                _levels++;
                break;
            }
        }

        // Insert this node into the skip list
        SkipNode<T> newNode = new SkipNode<>(value, level + 1);
        SkipNode<T> cur = _head;
        for (int i = _levels - 1; i >= 0; i--) {
            for (; cur.next[i] != null; cur = cur.next[i]) {
                if (cur.next[i].getValue().compareTo(value) > 0)
                    break;
            }

            if (i <= level) {
                newNode.next[i] = cur.next[i];
                cur.next[i] = newNode;
            }
        }
    }

    /// <summary>
    /// Returns whether a particular value already exists in the skip list
    /// </summary>
    public boolean contains(T value) {
        SkipNode<T> cur = _head;
        for (int i = _levels - 1; i >= 0; i--) {
            for (; cur.next[i] != null; cur = cur.next[i]) {
                if (cur.next[i].getValue().compareTo(value) > 0)
                    break;
                if (cur.next[i].getValue().compareTo(value) == 0)
                    return true;
            }
        }
        return false;
    }

    /// <summary>
    /// Attempts to remove one occurence of a particular value from the skip
    /// list. Returns
    /// whether the value was found in the skip list.
    /// </summary>
    public boolean remove(T value) {
    
        SkipNode<T> cur = _head;
    
        boolean found = false;
        for (int i = _levels - 1; i >= 0; i--) {
            for (; cur.next[i] != null; cur = cur.next[i]) {
                if (cur.next[i].getValue().compareTo(value) == 0) {
                    found = true;
                    cur.next[i] = cur.next[i].next[i];
                    break;
                }

                if (cur.next[i].getValue().compareTo(value) > 0)
                    break;
            }
        }
        if (found)
            size.decrementAndGet();
        return found;
    }

    public int printHeight() {
        return _levels;
    }

    public static void main(String args[]) {
        SkipList<Integer> s1 = new SkipList<Integer>();
        s1.insert(10);
        s1.insert(20);
        s1.insert(15);
        s1.insert(4);
        s1.insert(29);
        Iterator ir = s1.iterator();
        while(ir.hasNext()) {
            Object element = ir.next();
            System.out.println(element);
        }
        System.out.println(s1.remove(10));
        System.out.println(s1.remove(5));
        System.out.println(s1.contains(8));
        System.out.println(s1.contains(4));
    
        ir = s1.iterator();
        while(ir.hasNext()) {
            Object element = ir.next();
            System.out.println(element);
        }
    
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Iterator<T> iterator() {
        return new SkipListIterator(this, 0);
    }

    public int size() {
        return size.get();
    }

    public Double[] toArray() {
        Double[] a = new Double[size.get()];
        int i = 0;
        for (T t : this) {
            a[i] = (Double) t;
            i++;
        }
        return a;
    }

}

class SkipNode<N extends Comparable<? super N>> {
    public SkipNode<N>[] next;
    public N value;

    @SuppressWarnings("unchecked")
    public SkipNode(N value, int level) {
        this.value = value;
        next = new SkipNode[level];
    }
    
    public N getValue() {
        return value;
    }
    
    public SkipNode<N>[] getNext() {
        return next;
    }

    public SkipNode<N> getNext(int level) {
        return next[level];
    }

    public void setNext(SkipNode<N>[] next) {
        this.next = next;
    }
}

class SkipListIterator<E extends Comparable<E>> implements Iterator<E> {
    SkipList<E> list;
    SkipNode<E> current;
    int level;
    
    public SkipListIterator(SkipList<E> list, int level) {
        this.list = list;
        this.current = list._head;
        this.level = level;
    }

    public boolean hasNext() {
        return current.getNext(level) != null;
    }
    
    public E next() {
        current = current.getNext(level);
        return current.getValue();
    }
    
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
    
}
