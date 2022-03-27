public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int length;
    private int front;
    private int last;

   public ArrayDeque() {
       array = (T[]) new Object[8];
       size = 0;
       length = 8;
       front = 4;
       last = 4;
   }

   public boolean isEmpty() {
       return size == 0;
   }

   public int size() {
       return size;
   }

   private int minusOne(int index) {
       if (index == 0)
           return length - 1;

       return index - 1;
   }

   private int plusOne(int index, int module) {
       index %= module;
       if (index == module - 1)
           return 0;

       return index + 1;
   }

   private void grow() {
       T[] newArray = (T[]) new Object[length * 2];
       int p1 = front;
       int p2 = length;
       while (p1 != p2) {
           newArray[p2] = array[p1];
           p1 = plusOne(p1, length);
           p2 = plusOne(p2, length * 2);
       }

       front = length;
       last = p2;
       array = newArray;
       length *= 2;
   }

   private void shrink() {
       T[] newArray = (T[]) new Object[length / 2];
       int p1 = front;
       int p2 = last;
       while(p1 != p2) {
           newArray[p2] = array[p1];
           p1 = plusOne(p1, length);
           p2 = plusOne(p2, length / 2);
       }
       front = length / 4;
       last = p2;
       array = newArray;
       length /= 2;
   }

   public void addFirst(T i) {
       if (size == length - 1) grow();
       front = minusOne(front);
       array[front] = i;
       size++;
   }

   public T removeFirst() {
       if (length >= 16 && length / size >= 4) shrink();
       if (size == 0) return null;

       T ret = array[front];
       front = plusOne(front, length);
       size--;
       return ret;
   }

   public T removeLast() {
       if (length >= 16 && length / size >= 4) shrink();
       if (size == 0) return null;
       last = minusOne(last);
       size--;
       return array[last];
   }

   public T get(int index) {
       if (index >= size) return null;
       int p = front;
       for (int i = 0; i < index; i++) {
           p = plusOne(p, length);
       }

       return array[p];
   }

   public void printDeque() {
       int p = front;
       while (p != last) {
           System.out.print(array[p] + " ");
           p = plusOne(p, length);
       }
   }
}
