package Library;

import java.util.ArrayList;
import java.util.List;

public class BorrowingHistory {
    private class Node {
        String borrower;
        Node next;

        Node(String borrower) {
            this.borrower = borrower;
            this.next = null;
        }
    }

    private Node head;

    public void addBorrower(String borrower) {
        Node newNode = new Node(borrower);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void printBorrowers() {
        Node current = head;
        while (current != null) {
            System.out.println(current.borrower);
            current = current.next;
        }
    }
    public List<String> getBorrowers() {
        List<String> borrowers = new ArrayList<>();
        Node current = head;
        while (current != null) {
            borrowers.add(current.borrower);
            current = current.next;
        }
        return borrowers;
    }
}

