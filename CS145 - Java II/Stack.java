class Stack<E>
   {
    private Node<E> pointer;
    public Stack()
      {
       this.pointer = null;
      }
    public void push(Node<E> elem)
      {
       elem.setPtrPrev(pointer);
       elem.setPtrNext(null);
       if (pointer != null) pointer.setPtrNext(elem);
       pointer = elem;
      }
    public Node<E> pop()
      {
       pointer = pointer.getPtrPrev();
       Node<E> temp = pointer.getPtrNext();
       pointer.setPtrNext(null);
       return temp;
      }
    public Node<E> peek()
      {
       return pointer;
      }
    public String toString()
      {
       Node<E> temp = pointer;
       String holder = "Stack contents is: ' ";
       while (temp != null)
          {
           holder += peek().getData();
           if (temp.getPtrPrev() != null)
             {
              holder += ", ";
//              temp.setPtrNext(temp.getPtrNext()); 
             }
           temp = temp.getPtrPrev(); 
          }
       return holder += " '";
      }
   }