
public class ArrayQueue<T> implements QueueInterface<T>
{
	private static final int DEFAULT_CAPACITY = 10;
	private final T[] queue;
	private int front;
	private int back;
	private int size;
	
	public ArrayQueue()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayQueue(int desiredSize)
	{
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[])new Object[desiredSize]; 
		queue = tempQueue;
		front = back = size = 0;
	}
	
	public void enqueue(T newEntry) 
	{
		queue[back] = newEntry;
		back++;
		if(back == queue.length)
		{
			back = 0;
		}
		size++;
	}

	public T dequeue() 
	{
		T object = null;
		if(isEmpty())
		{
			throw new EmptyQueueException("Error: The queue is empty");
		}
		else
		{
			object = queue[front];
			queue[front] = null;
			front++;
			if(front == queue.length)
			{
				front = 0;
			}
			size--;
		}
		
		return object;
	}

	public T getFront() 
	{
		T object = null;
		if(isEmpty())
		{
			throw new EmptyQueueException("Error: The queue is empty");
		}
		else
		{
			object = queue[front];
		}
		return object;
	}

	public boolean isEmpty() 
	{
		if(size == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isFull()
	{
		return queue.length == size;
	}

	public void clear() 
	{
		queue[back] = null;
		while(front != back)
		{
			queue[front] = null;
			front++;
			if(front == queue.length - 1)
			{
				front = 0;
			}
		}
		front = 0;
		back = 0;
	}
	
}
