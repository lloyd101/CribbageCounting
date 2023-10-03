

public class PowerSet<T> {
    private Set<T>[] set; // declare an array of Sets to store the power set of elements

    public PowerSet(T[] elements) { // constructor to initialize the power set
        int n = elements.length; // get the number of elements
        int size = (int) Math.pow(2, n); // calculate the size of the power set (2^n)
        set = new Set[size]; // initialize the array of Sets to the size of the power set

        // loop through all possible combinations of the elements
        for (int i = 0; i < size; i++) {
            String binaryString = Integer.toBinaryString(i); // get the binary representation of the current index
            while (binaryString.length() < n) { // pad the binary string with leading zeros if necessary
                binaryString = "0" + binaryString;
            }
            // create a new Set to store the current combination of elements
            Set<T> newSet = new Set<T>();
            for (int a = 0; a < n; a++) {
                if (binaryString.charAt(a) == '1') { // if the current element is included in the combination
                    newSet.add(elements[a]); // add the element to the new Set
                }
            }
            set[i] = newSet; // add the new Set to the array of Sets
        }
    }

    public int getLength() { // method to get the length of the power set (i.e. the number of Sets)
        return set.length;
    }

    public Set<T> getSet(int i) { // method to get a specific Set from the power set by index
        return set[i];
    }
}







