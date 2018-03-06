import java.util.*;
import java.lang.*;

/**
 * LinkedList class to setup and modify a linked list.
 *
 * @author Ernest Carter
 * @revised 29 September 2017
 */
public class SinglyLinkedList<T> implements Iterable<T>  {

    Node<T> headNode;
    Node<T> tailNode;
	Node<T> lastNode;

    public SinglyLinkedList() {

        headNode = new Node(null);
        tailNode = new Node(null);
		lastNode = headNode;
		headNode.setNextNode(tailNode);
    } // end LinkedList constructor

    /**
	 * Adds an element to the linked list.
	 */
    public void add(T element) {

        Node<T> node = new Node(element);
		
		node.setNextNode(tailNode);
		lastNode.setNextNode(node);
		lastNode = node;
    } // end method add
	
	public void remove(T element) {
		
		Node<T> currentNode = headNode;
		while (currentNode.getNextNode().getData() != null) {
			if (element.equals(currentNode.getNextNode().getData()) == true) {
				currentNode.setNextNode(currentNode.getNextNode().getNextNode());
				break;
			}
			currentNode = currentNode.getNextNode();
		}
	}
	
	public void clear() {
		headNode.setNextNode(tailNode);
	}
	
	/**
	* Creates a new iterator that has this linked list.
	*/
	public Iterator<T> iterator() {
		return new SinglyLinkedListIterator(this);
	} // end method getIterator
	

	class SinglyLinkedListIterator implements Iterator<T> {
		
		private SinglyLinkedList<T> myList = null;
		private SinglyLinkedList<T>.Node<T> currentNode = null;
		private SinglyLinkedList<T>.Node<T> priorNode = null;

		public SinglyLinkedListIterator(SinglyLinkedList<T> list) {
			myList = list;
			currentNode =  myList.headNode;
		} // end constructor
		
		/**
		 * @return true if there is a "next" element, otherwise returns false
		 */
		public boolean hasNext() {
			if (currentNode.getNextNode().getData() != null) {
				return true;
			}
			return false;
		} // end method hasNext
		
		public T next() {
			if (hasNext() == false) {
				throw new NoSuchElementException();
			}
			else {
				priorNode = currentNode;
				currentNode = currentNode.getNextNode();
				return currentNode.getData();
			}
		} // end method next
		
		public void remove() {
			if (currentNode.getData() != null) {	
				priorNode.setNextNode(currentNode.getNextNode());
			}
			else {
				throw new IllegalStateException();
			}
		} // end of method remove
		
		/**
		 *  Adds a node after the current node.
		 */
		public void add(Node<T> node) {
			node.setNextNode(currentNode.getNextNode());
			currentNode.setNextNode(node);
		} // end of method add
	} // end of class SinglyLinkedListIterator

	/**
	 * Inner class of LinkedList.
	 * Sets up a node.
	 */
    class Node<T> {
    
        private T data;
        private Node<T> itsNext;
    
        public Node(T data) {
            itsNext = null;
            this.data = data;
        } // end Node constructor
    
		/**
		 * @returns the data of the node
		 */
        public T getData() {
			return this.data;
        } // end getData
    
		/**
		 * @returns the next node
		 */
        public Node<T> getNextNode() {
            return itsNext;
        } // end method getNextNode

		/**
		 * sets the node's next node
		 */
        public void setNextNode(Node<T> next) {
            itsNext = next;
        } // end method setNextNode

		/**
		 * @returns the data as a String
		 */
        public String toString() {
            return data.toString();
        } // end method toString
    
    }  // end of Node<T>
} // end class SinglyLinkedList<T>