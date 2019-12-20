package com.datastructure;


import java.util.List;

public interface BlockChainDatastructure<T> {
	public int size();

	public boolean isEmpty();

	public void addFirst(T element);

	public void addLast(T element);

	public void iterateForward();

	public void iterateBackward();
	
	public T iterateForward(int position);
	
	public T iterateBackward(int position);
	
	public List<T> getAllNodes();
}
