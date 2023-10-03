

public class Set<T> {

    private LinearNode<T> setStart;

    public Set() {
        // Constructor that initializes an empty set
        this.setStart = null;
    }

    public void add(T element) {
        // Adds an element to the set
        LinearNode<T> newNode = new LinearNode<>(element);

        if (setStart == null) {
            setStart = newNode;
        } else {
            LinearNode<T> current = setStart;

            while (current.getNext() != null) {
                current = current.getNext();
            }

            current.setNext(newNode);
        }
    }

    public int getLength() {
        // Returns the length (number of elements) of the set
        int length = 0;

        for (LinearNode<T> current = setStart; current != null; current = current.getNext()) {
            length++;
        }

        return length;
    }

    public T getElement(int i) {
        // Returns the element at the specified index in the set
        LinearNode<T> current = setStart;

        for (int j = 0; j < i; j++) {
            current = current.getNext();
        }

        return current.getElement();
    }

    public boolean contains(T element) {
        // Returns true if the set contains the specified element, false otherwise
        for (LinearNode<T> current = setStart; current != null; current = current.getNext()) {
            if (current.getElement().equals(element)) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        // Returns a string representation of the set
        StringBuilder sb = new StringBuilder();

        for (LinearNode<T> current = setStart; current != null; current = current.getNext()) {
            sb.append(current.getElement()).append(" ");
        }

        return sb.toString().trim();
    }
}


