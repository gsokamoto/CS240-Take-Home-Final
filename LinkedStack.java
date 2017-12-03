public class LinkedStack<T> implements StackInterface<T>
{
	private SLNode<T> head;
	
	public LinkedStack()
	{
		head = null;
	}
	
	public void push(T newEntry) 
	{
		SLNode<T> tempNode = new SLNode<T>();
		tempNode.setEntry(newEntry);
		if(isEmpty())
		{
			tempNode.setNext(null);
			head = tempNode;
		}
		else
		{
			tempNode.setNext(head);
			head = tempNode;
		}
	}

	public T pop() 
	{
		if(isEmpty())
		{
			throw new EmptyStackException();
		}
		else
		{
			T tempEntry = head.getEntry();
			head = head.getNext();
			return tempEntry;
		}
	}
	
	public T peek()
	{
		if(isEmpty())
		{
			throw new EmptyStackException();
		}
		else
		{
			return head.getEntry();
		}
	}

	public boolean isEmpty() 
	{
		return head == null;
	}

	public void clear() 
	{
		head = null;
	}
}
