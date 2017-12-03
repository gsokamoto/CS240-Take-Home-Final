/*
 * A program that sorts objects by value and key (Dictionary)
 * using a dictionary node
 * @author Grant Okamoto
 * @version 1.0
 * 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {

	private DNode<K, V> head;
	private int numOfEntries;
	
	public SortedLinkedDictionary()
	{
		head = null;
		numOfEntries = 0;
	}
	
	@Override
	public V add(K key, V value) {
		V result = null;
		DNode<K, V> currentNode = head;
		DNode<K, V> prevNode = null;
		while((currentNode != null) && key.compareTo(currentNode.getKey()) > 0)
		{
			prevNode = currentNode;
			currentNode = currentNode.getNext();
		}
		if((currentNode != null) && key.equals(currentNode.getKey())){
			result = currentNode.getValue();
			currentNode.setValue(value);
		}
		else
		{
			DNode<K, V> newNode = new DNode<K, V>();
			newNode.setKey(key);
			newNode.setValue(value);
			if(prevNode == null)
			{
				newNode.setNext(head);
				head = newNode;
			}
			else
			{
				newNode.setNext(currentNode);
				prevNode.setNext(newNode);
			}
			numOfEntries++;
		}
		
		return result;
	}

	@Override
	public V remove(K key) {
		DNode<K, V> currentNode = head;
		DNode<K, V> prevNode = null;
		V tempValue;
		while(currentNode != null)
		{
			if(currentNode.getKey() == key)
			{
				break;
			}
			else{
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		if(currentNode == null)
		{
			return null;
		}
		else if(prevNode == null)
		{
			tempValue = currentNode.getValue();
			head = head.getNext();
			return tempValue;
		}
		else if(currentNode.getNext() == null)
		{
			tempValue = currentNode.getValue();
			prevNode.setNext(null);
			return tempValue;
		}
		else
		{
			tempValue = currentNode.getValue();
			prevNode.setNext(currentNode.getNext());
			return tempValue;
		}
	}

	@Override
	public V getValue(K key) {
		DNode<K, V> currentNode = head;
		while(currentNode != null)
		{
			if(currentNode.getKey() == key)
			{
				return currentNode.getValue();
			}
			else
			{
				currentNode = currentNode.getNext();
			}
		}
		return null;
	}

	@Override
	public boolean contains(K key) {
		DNode<K, V> currentNode = head;
		while(currentNode != null)
		{
			if(currentNode.getKey() == key)
			{
				return true;
			}
			else
			{
				currentNode = currentNode.getNext();
			}
		}
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	@Override
	public boolean isEmpty() {
		return numOfEntries == 0;
	}

	@Override
	public int getSize() {
		return numOfEntries;
	}

	@Override
	public void clear() {
		head = null;
		numOfEntries = 0;
	}
	
	private class KeyIterator implements Iterator<K>{

		private DNode<K, V> nextNode;
		
		private KeyIterator()
		{
			nextNode = head;
		}
		@Override
		public boolean hasNext() {
			
			return nextNode != null;
		}

		@Override
		public K next() {
			K result = null;
			if(hasNext())
			{
				result = nextNode.getKey();
				nextNode = nextNode.getNext();
			}
			else
			{
				throw new NoSuchElementException();
			}
			return result;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	private class ValueIterator implements Iterator<V>{

		private DNode<K, V> nextNode;
		
		private ValueIterator()
		{
			nextNode = head;
		}
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public V next() {
			V result = null;
			if(hasNext())
			{
				result = nextNode.getValue();
				nextNode = nextNode.getNext();
			}
			else
			{
				throw new NoSuchElementException();
			}
			return result;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}

