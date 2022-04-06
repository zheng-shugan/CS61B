package IntList;

import java.util.Formatter;

public class IntList {
    /** First element of the list */
    private int first;
    /** Remaining elements of list */
    private IntList rest;

    /** Constructor of list */
    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Constructor of list */
    public IntList() {
        first = 0;
        rest = null;
    }

    /** Return a list equal to L with all element squared. Destructive version. */
    public static void dSquareList(IntList L) {
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /** Returns a list equal to L with all elements squared. Non-destructive. none-Destructive and loop version. */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList p = res;
        L = L.rest;

        while (L != null) {
            p.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            p = p.rest;
        }

        return res;
    }

    /** Return a list equal to L with all element squared. Non-destructive and recursive version*/
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }

    /**
     * Return a list consisting of the elements of A followed by the elements of A
     * of B. May modify A. No use "new"
    */
    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }
        IntList p = A;
        while (p.rest != null) {
            p = p.rest;
        }
        p.rest = B;
        return A;
    }

    /**
     * Return a list consisting of the elements of A followed by the elements of A
     * of B. May NOT modify A. Use "new"
     */
    public static IntList catenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }

        if (A.rest == null) {
            return new IntList(A.first, B);
        }

        return new IntList(A.first, catenate(A.rest, B));
    }

    /**
     * Return the reverse of the list of A
     * This method is modify A
     * @param A the list to be destructively reversed
     */
    public static IntList reverse(IntList A) {
        if (A == null || A.rest == null) {
            return A;
        }
        IntList p = reverse(A.rest);
        A.rest.rest = A;
        A.rest = null;

        return p;
    }

    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Return a new IntList containing the ints in ARGS.
     * You are not expected to read or understand this method
     */
    public static IntList of(Integer... args) {
        IntList result, p;
        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k++, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }

        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }

        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */
    private int detectCycles(IntList A) {
        if (A == null) {
            return 0;
        }

        IntList pfast = A;
        IntList pslow = A;
        int cnt = 0;

        while (true) {
            cnt++;
            if (pfast.rest != null) {
                pfast = pfast.rest.rest;
            } else {
                return 0;
            }

           pslow = pslow.rest;
            if (pslow == null || pfast == null) {
                return 0;
            }
            if (pfast == pslow) {
                return cnt;
            }
        }
    }

    /**
     * Outputs the IntList as a String.
     * You are not expected to read or understand this method.
     */
    @Override
    public String toString() {
        Formatter out = new Formatter();
        String sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}
