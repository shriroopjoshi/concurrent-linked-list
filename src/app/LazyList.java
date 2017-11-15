package app;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class LazyList<T> {
    
    Node<T> head;

    public LazyList() {
        head = new Node<>(Integer.MIN_VALUE);
        Node<T> tail = new Node<>(Integer.MAX_VALUE);
        while(head.next.compareAndSet(null, tail, false, false));
    }

    public boolean add(T item) {
        int key = item.hashCode();
        while(true) {
            Window<T> window = this.find(head, key);
            Node<T> pred = window.prev;
            Node<T> curr = window.curr;
            if(curr.key == key) {
            	return false;
            } else {
            	Node<T> node = new Node<>(item);
            	node.next = new AtomicMarkableReference<Node<T>>(curr, false);
            	if(pred.next.compareAndSet(curr, node, false, false)) {
            		return true;
            	}
            }
        }
    }
    
    public boolean remove(T item) {
    	int key = item.hashCode();
    	boolean snip;
    	while(true) {
    		Window<T> window = find(head, key);
    		Node<T> pred = window.prev;
    		Node<T> curr = window.curr;
    		if(curr.key != key) {
    			return false;
    		} else {
    			Node<T> succ = curr.next.getReference();
    			snip = curr.next.attemptMark(succ, true);
    			if(!snip)
    				continue;
    			pred.next.compareAndSet(curr, succ, false, false);
    			return true;
    		}
    	}
    }
    
    public boolean contains(T item) {
    	int key = item.hashCode();
    	Window<T> window = find(head, key);
    	Node<T> curr = window.curr;
    	return curr.key == key;
    }
    
    public boolean replace(T previous, T next) {
    	// TODO: implement method
    	return false;
    }
    
    @Override
	public synchronized String toString() {
		StringBuilder sb = new StringBuilder();
		Node<T> curr = head.next.getReference();
		sb.append('[');
		while(curr.item != null) {
			sb.append(curr.item.toString() + ", ");
			curr = curr.next.getReference();
		}
		return sb.toString();
	}

	public Window<T> find(Node<T> head, int key) {
        Node<T> pred = null, curr = null, succ = null;
        boolean[] marked = { false }; // is curr marked?
        boolean snip;
retry:  while(true) {
            pred = head;
            curr = pred.next.getReference();
            while(true) {
                succ = curr.next.get(marked);
                while(marked[0]) { // replace curr if marked
                    snip = pred.next.compareAndSet(curr, succ, false, false);
                    if(!snip)
                        continue retry;
                    curr = pred.next.getReference();
                    succ = curr.next.get(marked);
                }
                if(curr.key >= key)
                    return new Window<T>(pred, curr);
                pred = curr;
                curr = succ;
            }
        }
    }
}