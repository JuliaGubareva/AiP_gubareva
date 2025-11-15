class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class CircularLinkedList {
    private Node head;
    private int size;

    public CircularLinkedList() {
        this.head = null;
        this.size = 0;
    }

    //добавление элемента в начало списка (1)
    public void addFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node current = head;
            while (current.next != head) {
                current = current.next;
            }
            newNode.next = head;
            head = newNode;
            current.next = head;
        }
        size++;
    }

    //добавление элемента в конец списка (2)
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = head; 
        }
        size++;
    }

    //удаление первого элемента (3)
    public void removeFirst() {
        if (head == null) {
            return;
        }
        if (head.next == head) {
            head = null;
        } else {
            Node current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = head.next;
            head = head.next;
        }
        size--;
    }

    //удаление последнего элемента (4))
    public void removeLast() {
        if (head == null) {
            return;
        }
        if (head.next == head) {
            head = null;
        } else {
            Node current = head;
            while (current.next.next != head) {
                current = current.next;
            }
            current.next = head;
        }
        size--;
    }
    
    //получение размера списка (7)
    public int size() {
        return size;
    }

    //проверка на пустоту (8)
    public boolean isEmpty() {
        return head == null;
    }

    //вывод элементов списка (9)
    public void display() {
        if (head == null) {
            System.out.println("Список пуст");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(цикл)");
    }

    //очистка списка )(10)
    public void clear() {
        head = null;
        size = 0;
    }

    //новые методы
    //циклический сдвиг списка (первый элемент становится последним) (2)
    public void rotate() {
        if (head == null || head.next == head) {
            return; 
        }
        head = head.next;
    }

    //проверка наличия цикла (должна всегда возвращать true) (3)
    public boolean findCycle() {
        return true; 
    }

    //поиск элемента с учетом циклической природы (4) (замена contains)
    public boolean find(int data) {
        if (head == null) {
            return false;
        }
        Node current = head;
        do {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        } while (current != head);
        return false;
    }

    //разделение списка на два равных циклических списка (5)
    public CircularLinkedList[] splitIntoTwo() {
        if (head == null) {
            return new CircularLinkedList[] { new CircularLinkedList(), new CircularLinkedList() };
        }
    
        Node slow = head;
        Node fast = head;
    
        while (fast.next != head && fast.next.next != head) {
            fast = fast.next.next;
            slow = slow.next;
        }
    
        CircularLinkedList secondList = new CircularLinkedList();
        secondList.head = slow.next;
    
        Node secondCurrent = secondList.head;
        while (secondCurrent.next != head) {
            secondCurrent = secondCurrent.next;
        }
        secondCurrent.next = secondList.head;
    
        slow.next = head;
    
        secondList.size = size - size / 2;
        CircularLinkedList firstList = new CircularLinkedList();
        firstList.head = head;
        firstList.size = size / 2 + (size % 2);
    
        return new CircularLinkedList[] { firstList, secondList };
    }
}
    

//проверки
public class Main3 {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(4);
        list.addLast(5);
        list.removeLast();

        System.out.print("Список: ");
        list.display(); 

        list.rotate();
        System.out.print("Список после сдвига: ");
        list.display();

        System.out.println("Есть цикл: " + list.findCycle()); 

        System.out.println("Элемент 3 найден: " + list.find(3)); 
        System.out.println("Элемент 5 найден: " + list.find(5));

        CircularLinkedList[] splitLists = list.splitIntoTwo();

        System.out.print("Первый список: ");
        splitLists[0].display(); 

        System.out.print("Второй список: ");
        splitLists[1].display(); 
    }
}
