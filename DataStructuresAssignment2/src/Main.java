public class Main {
    public static void main(String[] args) {

        MyDataStructure list = new MyDataStructure();
        list.insertSorted(7);
        list.insertSorted(3);
        list.insertSorted(11);
        list.insertSorted(5);
        list.insertSorted(2);

        System.out.println("Original Sorted List:");
        list.printList();

        list.deleteFirst();
        System.out.println("After Deleting First:");
        list.printList();

        list.deleteLast();
        System.out.println("After Deleting Last:");
        list.printList();

        list.deleteAtIndex(1);
        System.out.println("After Deleting Index 1:");
        list.printList();

        MyDataStructure front = new MyDataStructure();
        MyDataStructure back = new MyDataStructure();
        list.frontBackSplit(front, back);
        System.out.println("Front Half:");
        front.printList();
        System.out.println("Back Half:");
        back.printList();

        MyDataStructure listA = new MyDataStructure();
        listA.insertAtEnd(8);
        listA.insertAtEnd(2);
        listA.insertAtEnd(10);
        listA.insertAtEnd(4);

        MyDataStructure listB = new MyDataStructure();
        listB.insertAtEnd(7);
        listB.insertAtEnd(3);
        listB.insertAtEnd(9);
        listB.insertAtEnd(1);

        listA.sort();
        listB.sort();

        System.out.println("Sorted List A:");
        listA.printList();
        System.out.println("Sorted List B:");
        listB.printList();

        MyDataStructure mergedList = MyDataStructure.mergeSorted(listA, listB);
        System.out.println("Merged Sorted List:");
        mergedList.printList();
    }
}