import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Doubly-Linked Node implementation of the StreetParking Interface
 *
 * Implement the appropriate methods from StreetParking to get this to compile (and then delete this comment)
 */
public class GeorgeLinkedStreet implements StreetParking {
    Node first;
    Node last;
    int lengthOfStreet;
    int numberOfCars;
    int id;

    public GeorgeLinkedStreet(int lengthOfStreet, int lengthOfGeorgesCar) {
        this.lengthOfStreet = lengthOfStreet;
        first = null;
        last = null;
        this.lengthOfStreet = lengthOfStreet;
        id = 0;
        push(lengthOfGeorgesCar);
    }

    /**
     * when this method is called
     * @param length the length of the car being added to the street
     * @return
     */
    public int push(int length) {
        if (lengthOfStreet-length<0 || length == 0) {
            System.out.println("can't park a car with length 0 or No freeSpace");
            return -1;
        }

        id++;
        numberOfCars++;
        Node newNode = new Node();
        newNode.data = 'G';
        newNode.Id = id;
        newNode.length = length;
        lengthOfStreet = lengthOfStreet - length;
        newNode.next = first;
        if (first == null) {
            last = newNode;
        } else {
            first.previous = newNode;
        }

        first = newNode;
        return id;

    }

    public void removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        lengthOfStreet = lengthOfStreet + first.length;

        System.out.println(lengthOfStreet);
        first = first.next;
        if (first == null) {
            first = last;
        } else {
            first.previous = null;
        }

    }

    public void removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        lengthOfStreet = lengthOfStreet + last.length;

        last = last.previous;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
    }


    //you can't remove id 1 cause it georges car
    public void remove(int ticketNumber) {
        if(ticketNumber>id || ticketNumber<=1){
            System.out.println("Please enter a vaild ticketnumber");
            throw new NoSuchElementException();
        }
        Node current = first;
        if (current.Id == ticketNumber) {
            removeFirst();
        } else if (last.Id == ticketNumber) {
            removeLast();
        } else {
            while (current.next != null && current.Id != ticketNumber) {
                current = current.next;
            }
            if(current.Id!=ticketNumber){
                throw new NoSuchElementException();
            }

            lengthOfStreet = lengthOfStreet + current.length;
            if(!isBlocked()){
                last.next=first;
                first.previous=last;
                first=current.next;
                first.previous=null;
                current.previous.next=null;
                last=current.previous;
            }
            else {
                current.next.previous = current.previous;
                current.previous.next = current.next;
            }
        }

        numberOfCars--;
    }



    public boolean block(int size) {
        if (isBlocked() ||size > freeSpace() || size == 0) {
            return false;
        }
        Node newNode = new Node();
        newNode.data = "B";
        newNode.length = size;
        lengthOfStreet = lengthOfStreet - size;
        newNode.previous = last;
        last.next = newNode;
        last = newNode;

        return true;
    }


    public boolean isBlocked() {
        if (last.data.equals("B")) {
            return true;
        }
        return false;
    }


    public int numberOfCars() {
        return numberOfCars;

    }


    public int freeSpace() {

        return lengthOfStreet;

    }

    /**
     * since we already know the number of cars we parked print those then print the empty
     * then print if it still hasnext that means its blocked
     * @return
     */
    public String toString() {
        Iterator list =iterator();
        String answer="";
        for(int i=0; i< numberOfCars; i++){

            answer+="G"+list.next()+" ";
        }
        if(list.hasNext()&& lengthOfStreet!=0){
            answer+="E"+list.next()+" ";
        }
        if(list.hasNext()){
            answer+="B"+list.next();
        }

        return answer;
    }


    public Iterator<Integer> iterator() {

        return new MyIterator();


    }


    class Node {
        Object data;
        int Id;
        int length;
        Node next;
        Node previous;

    }


    /**
     * for this class how implemented is like the normal interator class however when it finished iteration
     * through number of cars if empty space is not equal to zero and there is an empty space it will
     * return the value of the empty space
     *
     */
    class MyIterator implements Iterator {
        int count=0;
        private Node position;
        MyIterator() {
            position = null;

        }

        @Override
        public boolean hasNext() {
            if (position == null) {
                return first != null;
            }
            else if(count==numberOfCars()&& lengthOfStreet!=0){
                return true;
            }
            else {
                return position.next != null;

            }

        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (position == null) {

                position = first;
            }
            else if(count==numberOfCars() && lengthOfStreet!=0){
                count++;
                return lengthOfStreet;
            }
            else {
                position = position.next;

            }
            count++;
            return position.length;
        }


    }
}
