public class MyDataStructure {
    private Node head;
    private Node tail;

    public MyDataStructure() {
        head = null;
        tail = null;
    }

    // Append to front of list
    public void append(int value) {
        if (contains(value)) {
            return;
        }

        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
        }
        tail = newNode;
    }

    // Prepend to end of list
    public void prepend(int value) {
        if (contains(value)) {
            return;
        }

        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Get size of list
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Get head node
    public Node head() {
        return head;
    }

    // Get the value of the head node
    public int headValue() {
        return head.data;
    }

    // Get tail node
    public Node tail() {
        return tail;
    }

    // Get the value of the tail node
    public int tailValue() {
        return tail.data;
    }

    // Find a node at the index that's passed to
    public Node at(int index) {
        if (index < 0) {
            return null;
        }

        Node current = head;
        int count = 0;

        while (current != null) {
            if (count == index) {
                return current;
            }
            current = current.next;
            count++;
        }

        return null;
    }

    // Pop the tail node off
    public void pop() {
        if (tail == null) {
            return;
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    // Check if the list contains the value handed to it
    public boolean contains(int value) {
        Node current = head;

        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    // Get the index of the value handed to this method
    public int ind(int value) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.data == value) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    // Insert a value at the beginning (pretty much the same as append, but made it different for the sake of completion)
    public void insertAtBeginning(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Insert a value at the end (pretty much the same as prepend, but made it different for the sake of completion)
    public void insertAtEnd(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Insert a value AND sort the list
    public void insertSorted(int value) {
        Node newNode = new Node(value);
        if (head == null || head.data >= value) {
            insertAtBeginning(value);
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data < value) {
            current = current.next;
        }

        newNode.next = current.next;
        newNode.prev = current;
        if (current.next != null) {
            current.next.prev = newNode;
        } else {
            tail = newNode;
        }
        current.next = newNode;
    }

    // Delete value at the start
    public void deleteFirst() {
        if (head == null) return;

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
    }

    // Delete value at the end
    public void deleteLast() {
        if (tail == null) return;

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    // Delete a value at the specified index
    public void deleteAtIndex(int index) {
        if (head == null || index < 0) return;

        Node current = head;
        int count = 0;

        while (current != null && count < index) {
            current = current.next;
            count++;
        }

        if (current == null) return;

        if (current == head) {
            deleteFirst();
        } else if (current == tail) {
            deleteLast();
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }

    // Splits this data structure into two, setting the different halves to the two other data structures handed
    public void frontBackSplit(MyDataStructure front, MyDataStructure back) {
        if (head == null) return;

        // One is slow and one is fast since one moves one step at a time, and the other two steps
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        front.head = head;
        front.tail = slow;

        back.head = slow.next;
        if (slow.next != null) {
            back.tail = tail;
            slow.next.prev = null;
        }
        slow.next = null;
    }

    // Merge the two handed lists together using the other methods
    public static MyDataStructure mergeSorted(MyDataStructure list1, MyDataStructure list2) {
        MyDataStructure mergedList = new MyDataStructure();
        Node a = list1.head;
        Node b = list2.head;

        while (a != null && b != null) {
            if (a.data < b.data) {
                mergedList.insertAtEnd(a.data);
                a = a.next;
            } else {
                mergedList.insertAtEnd(b.data);
                b = b.next;
            }
        }

        while (a != null) {
            mergedList.insertAtEnd(a.data);
            a = a.next;
        }

        while (b != null) {
            mergedList.insertAtEnd(b.data);
            b = b.next;
        }

        return mergedList;
    }

    // Sort the list
    public void sort() {
        if (head == null || head.next == null) return;

        Node sorted = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            sorted = sortedInsert(sorted, current);
            current = next;
        }

        head = sorted;
        tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
    }

    // Helper method for sort
    private Node sortedInsert(Node sorted, Node newNode) {
        if (sorted == null || sorted.data >= newNode.data) {
            newNode.next = sorted;
            newNode.prev = null;
            return newNode;
        }

        Node current = sorted;
        while (current.next != null && current.next.data < newNode.data) {
            current = current.next;
        }

        newNode.next = current.next;
        if (current.next != null) {
            current.next.prev = newNode;
        }
        current.next = newNode;
        newNode.prev = current;

        return sorted;
    }

    // Print the list
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
