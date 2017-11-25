package app;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * list Node
 */
public class Node<T extends Comparable<T>> {
	/**
	 * actual item
	 */
	T item;
	/**
	 * item's hash code
	 */
	int key;
	/**
	 * next Node in list
	 */
	Node<T> next;
	/**
	 * If true, Node is logically deleted.
	 */
	boolean marked;
	
	boolean tagged;
	/**
	 * Synchronizes Node.
	 */
	Lock lock;

	/**
	 * Constructor for usual Node
	 * 
	 * @param item element in list
	 */
	Node(T item) { // usual constructor
		this.item = item;
		this.key = item.hashCode();
		this.next = null;
		this.marked = false;
		this.tagged = false;
		this.lock = new ReentrantLock();
	}

	/**
	 * Constructor for sentinel Node
	 * 
	 * @param key should be min or max int value
	 */
	Node(int key) { // sentinel constructor
		this.item = null;
		this.key = key;
		this.next = null;
		this.marked = false;
		this.tagged = false;
		this.lock = new ReentrantLock();
	}
	
	Node(T item, boolean tag) {
		this.item = item;
		this.key = item.hashCode();
		this.next = null;
		this.marked = false;
		this.tagged = tag;
		this.lock = new ReentrantLock();
	}

	/**
	 * Lock Node
	 */
	void lock() {
		lock.lock();
	}

	/**
	 * Unlock Node
	 */
	void unlock() {
		lock.unlock();
	}
}