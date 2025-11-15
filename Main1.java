class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    //доббавление элемента в начало списка (1)
    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    //добавление элемента в конец списка (2)
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    //удаление первого элемента (3)
    public void removeFirst() {
        if (head != null) {
            head = head.next;
            size--;
        }
    }

    //удаление последнего элемента (4)
    public void removeLast() {
        if (head == null) {
            return;
        }
        if (head.next == null) {
            head = null;
        } else {
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        size--;
    }

    //уудаление элемента по значению (5)
    public boolean remove(int data) {
        if (head == null) {
            return false;
        }
        if (head.data == data) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //проверка наличия элемента (6)
    public boolean contains(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //возврат раззмера списка (7)
    public int size() {
        return size;
    }

    //проверка на пустоту (8)
    public boolean isEmpty() {
        return head == null;
    }

    //вывод всех элементов списккаа (9)
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    //Очистка списка (10)
    public void clear() {
        head = null;
        size = 0;
    }
}

//Пример проверка всех методов по порядку
public class Main1 {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(3); 
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(4);
        list.display();
        list.removeFirst();
        list.removeLast();
        list.display();
        list.remove(2);
        list.display();
        System.out.println("содержит 3? : " + list.contains(3));
        System.out.println("содержит 4? : " + list.contains(4));
        System.out.println("размер списка: " + list.size());
        list.clear();
        System.out.println("заполнен? : " + list.isEmpty());
    }
}