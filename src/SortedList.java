import java.util.ArrayList;

/**
 * An ArrayList-like collection of Strings that is always kept in
 * ascending lexicographic order. Binary search (implemented by hand)
 * is used to find the insertion point for new elements.
 */
public class SortedList {

    private final ArrayList<String> items = new ArrayList<>();

    /**
     * Hand-written binary search.
     * @return index of target if found; otherwise -(insertionPoint) - 1
     *         (same convention as java.util.Arrays.binarySearch, but
     *         implemented here by hand).
     */
    public int binarySearch(String target) {
        int low = 0;
        int high = items.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = items.get(mid).compareTo(target);

            if (cmp == 0) {
                return mid;              // found
            } else if (cmp < 0) {
                low = mid + 1;           // target is in the upper half
            } else {
                high = mid - 1;          // target is in the lower half
            }
        }
        return -(low + 1);               // not found; low is where it belongs
    }

    /** Inserts the string at the position that keeps the list sorted. Returns that index. */
    public int add(String s) {
        int result = binarySearch(s);
        int index = (result >= 0) ? result : -(result + 1);
        items.add(index, s);             // duplicates are allowed; they sit next to each other
        return index;
    }

    public String get(int index) { return items.get(index); }

    public int size() { return items.size(); }

    public boolean isEmpty() { return items.isEmpty(); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(i).append(": ").append(items.get(i)).append("\n");
        }
        return sb.toString();
    }
}