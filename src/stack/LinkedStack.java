package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
	
	// TODO: define class variables here
	LLNode<T> iter = null;
	int size = 0;
	/**
	 * Remove and return the top element on this stack.
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T pop() {
		// TODO
		if (isEmpty()) return null;
		T node = iter.info;
		iter = iter.link;
		size--;
		return node;
	}

	/**
	 * Return the top element of this stack (do not remove the top element).
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T top() {
		// TODO
		if (isEmpty()) return null;
		return iter.info;
	}

	/**
	 * Return true if the stack is empty and false otherwise.
	 */
	public boolean isEmpty() {
		// TODO
		return (iter == null);
	}

	/**
	 * Return the number of elements in this stack.
	 */
	public int size() {
		// TODO

		return size;
	}

	/**
	 * Pushes a new element to the top of this stack.
	 */
	public void push(T elem) {
		// TODO
		LLNode<T> newIter = new LLNode<T>(elem);
		newIter.link = iter;
		iter = newIter;
		size++;
	}

}
