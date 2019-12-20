package com.datastructure;


import java.util.LinkedList;
import java.util.List;

public class BlockChainDatastructureImpl<T> implements BlockChainDatastructure<T> {

	private Node head;
	private Node tail;
	private int size;

	public BlockChainDatastructureImpl() {
		size = 0;
	}

	/**
	 * this class keeps track of each element information
	 * 
	 * @author java2novice
	 *
	 */
	private class Node {
		T element;
		Node next;
		Node prev;

		public Node(T element, Node next, Node prev) {
			this.element = element;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * returns the size of the linked list
	 * 
	 * @return
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * return whether the list is empty or not
	 * 
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * adds element at the starting of the linked list
	 * 
	 * @param element
	 */
	@Override
	public void addFirst(T element) {
		Node tmp = new Node(element, head, null);
		if (head != null) {
			head.prev = tmp;
		}
		head = tmp;
		if (tail == null) {
			tail = tmp;
		}
		size++;
	}

	/**
	 * adds element at the end of the linked list
	 * 
	 * @param element
	 */
	@Override
	public void addLast(T element) {

		Node tmp = new Node(element, null, tail);
		if (tail != null) {
			tail.next = tmp;
		}
		tail = tmp;
		if (head == null) {
			head = tmp;
		}
		size++;
	}

	/**
	 * this method walks forward through the linked list
	 */
	@Override
	public void iterateForward() {

		System.out.println("iterating forward..");
		Node tmp = head;
		while (tmp != null) {
			System.out.println(tmp.element);
			tmp = tmp.next;
		}
	}

	/**
	 * this method walks backward through the linked list
	 */
	@Override
	public void iterateBackward() {

		System.out.println("iterating backword..");
		Node tmp = tail;
		while (tmp != null) {
			System.out.println(tmp.element);
			tmp = tmp.prev;
		}
	}

	@Override
	public T iterateForward(int position) {
		T data = null;
		Node tmp = head;
		while (position > 0) {
			if (tmp == null) {
				return null;
			}
			data = tmp.element;
			tmp = tmp.next;
			
			position--;
		}
		return data;
	}
	
	@Override
	public T iterateBackward(int position) {
		T data = null;
		Node tmp = tail;
		while (position > 0) {
			if (tmp == null) {
				return data;
			}
			data = tmp.element;
			tmp = tmp.prev;
			position--;
		}
		return data;
	}

	@Override
	public List<T> getAllNodes() {
		List<T> list = new LinkedList<>();
		Node tmp = head;
		while (tmp != null) {
			list.add(tmp.element);
			tmp = tmp.next;
		}
		return list;
	}
}
