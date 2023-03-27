class Node<E>
   {
    private Node<E> next;
    private Node<E> prev;
    private E data;
    public Node(E arg)
      {
       this.data = arg;
      }
    public Node(E arg, Node<E> next, Node<E> prev)
      {
       this.data = arg;
       this.next = next;
       this.prev = prev;
      }
    public void setPtrNext(Node<E> next)
      { this.next = next; }
    public void setPtrPrev(Node<E> prev)
      { this.prev = prev; }
    public Node<E> getPtrNext()    
      { return this.next; }
    public Node<E> getPtrPrev()    
      { return this.prev; }
    public E getData()
      { return this.data; }
//     public String toString()
//       {
//        return data;
//       }
   }