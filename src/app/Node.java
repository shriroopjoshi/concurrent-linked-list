package app;

import java.util.concurrent.atomic.AtomicMarkableReference;

class Node<T> {

    T item;
    int key;
    AtomicMarkableReference<Node<T>> next;

    Node(T item) {
        this.item = item;
        this.key = item.hashCode();
        this.next = new AtomicMarkableReference<Node<T>>(null, false);
    }

    Node(int key) { 
        this.item = null;
        this.key = key;
        this.next = new AtomicMarkableReference<Node<T>>(null, false);
    }
}

class Window<T> {
    public Node<T> prev, curr;

    Window(Node<T> prev, Node<T> curr) {
        this.prev = prev;
        this.curr = curr;
    }
}