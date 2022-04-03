/* Deque interface */
public interface Deque<T> {
    /* Add item in the deque front */
    void addFirst(T item);
    /* Add item in the deque end */
    void addLast(T item);
    /* Check the deque is empty */
    boolean isEmpty();
    /* Return the size of deque */
    int size();
    /* Traversal the deque */
    void printDeque();
    /* Remove and return first item */
    T removeFirst();
    /* Remove and return last item */
    T removeLast();
    /* Return the index item */
    T get(int index);
}
