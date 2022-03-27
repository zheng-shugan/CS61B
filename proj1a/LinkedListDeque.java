public class LinkedListDeque<T> {
    /* Inner class Node */
    public class Node {
        /* Stored data */
        private T item;
        /* Point to the before node */
        private Node prev;
        /* Point to the after node */
        private Node next;

        /* Constructor for Node */
        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }

        /* Constructor for Node.(especially for sentinel node)*/
        public Node(Node p, Node n) {
            prev = p;
            next = n;
        }
    }

    /* Sentinel node */
    private Node sentinel;
    /* Size of the deque */
    private int size;


    /* Constructor for deque */
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /* Check deque empty */
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /* Adding a node means having a pointer to that node */
    public void addFirst(T i) {
        Node p = new Node(i, sentinel, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size++;
    }

    public void addLast(T i) {
        Node p = new Node(i, sentinel.prev, sentinel);
        sentinel.next.prev = p;
        sentinel.prev = p;
        size++;
    }

    /* Deleting a node means that there is no pointer to that node */
    public T removeFirst() {
        if (size == 0) return null;
        T ret = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return ret;
    }

    public T removeLast() {
        if (size == 0) return null;
        T ret = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        size--;
        return ret;
    }


    public T get(int index) {
        if (index >= size) return null;
        int i = 0;
        Node p = sentinel.next;
        while (i <= index) {
            p = p.next;
            i++;
        }

        return p.item;
    }

    private T getRecursiveHelp(Node start, int index) {
        if (index == 0)
            return start.item;
        else
            return getRecursiveHelp(start.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) return null;
        return getRecursiveHelp(sentinel.next, index);
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }
}
