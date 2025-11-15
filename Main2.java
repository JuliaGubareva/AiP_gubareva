class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    private Node<Integer> head;
    private Node<Integer> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //сначала напишем методы из задания с односвязным списком:

    //добавление элемента в начало списка (1)
    public void addFirst(int data) {
        Node<Integer> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    //добавление элемента в конец списка (2)
    public void addLast(int data) {
        Node<Integer> newNode = new Node<>(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

     //Удаление первого элемента (3)
    public void removeFirst() {
        if (head == null) {
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    //Удаление последнего элемента (4)
    public void removeLast() {
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
        size--;
    }
    
    //удаление элемента по значению (5)+ поменяла название методу, 
    public void removeByValue(int data) {   //чттобы не повторялось
        if (head == null) {
            return;
        }
    
        Node<Integer> current = head;
        while (current != null) {
            if (current.data == data) {
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } else if (current == head) { 
                    head = head.next;
                    head.prev = null;
                } else if (current == tail) { 
                    tail = tail.prev;
                    tail.next = null;
                } else { 
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }
    
    //проверка наличия элемента (6)
    public boolean contains(int data) {
        Node<Integer> current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    

    //возврат размера списка (7)
    public int size() {
        return size;
    }
    
    //Проверка на пустоту (8)
    public boolean isEmpty() {
        return head == null;
    }

    //Вывод всех элементов списка (9)
    public void display() {
        Node<Integer> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    //Очистка списка (10)
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    //тут закончились методы из SinglyLinkedList

    //вставка элемента по индексу (2)
    public void add(int index, int data) {
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            Node<Integer> newNode = new Node<>(data);
            Node<Integer> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
            size++;
        }
    }

    //удаление элемента по индексу (3)
    public void remove(int index) {
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node<Integer> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
    }

    //получение элемента по индексу (4)
    public int get(int index) {
        Node<Integer> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    //Вывод элементов списка в обратном порядке (5)
    public void displayReverse() {
        Node<Integer> current = tail;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    //получение первого элемента (6)
    public int getFirst() {
        return head.data;
    }

    //Получение последнего элемента (7)
    public int getLast() {
        return tail.data;
    }
}

//Проверки
public class Main2 {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(4);

        System.out.print("Список: ");
        list.display(); 

        list.add(2, 5);
        System.out.print("Список после вставки: ");
        list.display(); 

        list.remove(2);
        System.out.print("Список после удаления: ");
        list.display(); 
        
        System.out.println("Содержит 3: " + list.contains(3));
        System.out.println("Содержит 5: " + list.contains(5));

        list.removeByValue(3);
        System.out.print("Список после удаления элемента со значением 3: ");
        list.display();

        System.out.println("Первый элемент: " + list.getFirst());
        System.out.println("Последний элемент: " + list.getLast()); 
        
        System.out.print("Список в обратном порядке: ");
        list.displayReverse(); 
    }
}
