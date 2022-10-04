
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://github.com/eugenp/tutorials/tree/master/algorithms-modules/algorithms-sorting
 * https://sites.math.rutgers.edu/~ajl213/CLRS/CLRS.html
 * https://www.baeldung.com/java-quicksort
 * https://www.geeksforgeeks.org/quick-sort/
 * https://www.geeksforgeeks.org/in-place-algorithm/
 */

import java.util.Random;
import java.util.Arrays;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors


public class HeapSort {
    public static void main(String [] args){
        Random g = new Random();
        int [] number = new int [10];
        FileWriter myWriter;
        System.out.println();


        try {
            myWriter = new FileWriter("random-integers.txt");    

            for (int d = 0 ; d<number.length ; d++){
                number[d] = g.nextInt(100) + 1;

                try {
                    //myWriter.write("\r\n");
                    myWriter.write(new Integer(number[d]).toString());  
                    myWriter.write("\r\n");
                    System.out.println(number[d]);

                } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                }
            }
            
            myWriter.close();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        System.out.print("\nUnsorted Numbers:" + Arrays.toString(number));
        //System.out.print("\nSorted Numbers:  " + Arrays.toString(mergeSort(number,number.length));
        
        HeapSort ob = new HeapSort();
        ob.sort(number);

        int n = number.length;
    

        System.out.print("\nSorted Numbers:  [");
        for(int i = 0; i < n; ++i){
            if(i < n - 1)
                System.out.print(number[i]+ ", ");
            else
                System.out.print(number[i]);
        }

        System.out.print("]\n");

    }


    public void sort(int arr[])
    {
        int n = arr.length;
  
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
  
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
  
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
  
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
  
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
  
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
  
        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
  
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
  
    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

}

/* 
 * 
public class Heap<E extends Comparable<E>> {

    private List<E> elements = new ArrayList<>();

    public static <E extends Comparable<E>> List<E> sort(Iterable<E> elements) {
        Heap<E> heap = of(elements);

        List<E> result = new ArrayList<>();

        while (!heap.isEmpty()) {
            result.add(heap.pop());
        }

        return result;
    }

    public static <E extends Comparable<E>> Heap<E> of(E... elements) {
        return of(Arrays.asList(elements));
    }

    public static <E extends Comparable<E>> Heap<E> of(Iterable<E> elements) {
        Heap<E> result = new Heap<>();
        for (E element : elements) {
            result.add(element);
        }
        return result;
    }

    public void add(E e) {
        elements.add(e);
        int elementIndex = elements.size() - 1;
        while (!isRoot(elementIndex) && !isCorrectChild(elementIndex)) {
            int parentIndex = parentIndex(elementIndex);
            swap(elementIndex, parentIndex);
            elementIndex = parentIndex;
        }
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("You cannot pop from an empty heap");
        }

        E result = elementAt(0);

        int lasElementIndex = elements.size() - 1;
        swap(0, lasElementIndex);
        elements.remove(lasElementIndex);

        int elementIndex = 0;
        while (!isLeaf(elementIndex) && !isCorrectParent(elementIndex)) {
            int smallerChildIndex = smallerChildIndex(elementIndex);
            swap(elementIndex, smallerChildIndex);
            elementIndex = smallerChildIndex;
        }

        return result;
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    private boolean isRoot(int index) {
        return index == 0;
    }

    private int smallerChildIndex(int index) {
        int leftChildIndex = leftChildIndex(index);
        int rightChildIndex = rightChildIndex(index);
        
        if (!isValidIndex(rightChildIndex)) {
            return leftChildIndex;
        }

        if (elementAt(leftChildIndex).compareTo(elementAt(rightChildIndex)) < 0) {
            return leftChildIndex;
        }

        return rightChildIndex;
    }

    private boolean isLeaf(int index) {
        return !isValidIndex(leftChildIndex(index));
    }

    private boolean isCorrectParent(int index) {
        return isCorrect(index, leftChildIndex(index)) && isCorrect(index, rightChildIndex(index));
    }

    private boolean isCorrectChild(int index) {
        return isCorrect(parentIndex(index), index);
    }

    private boolean isCorrect(int parentIndex, int childIndex) {
        if (!isValidIndex(parentIndex) || !isValidIndex(childIndex)) {
            return true;
        }

        return elementAt(parentIndex).compareTo(elementAt(childIndex)) < 0;
    }

    private boolean isValidIndex(int index) {
        return index < elements.size();
    }

    private void swap(int index1, int index2) {
        E element1 = elementAt(index1);
        E element2 = elementAt(index2);
        elements.set(index1, element2);
        elements.set(index2, element1);
    }

    private E elementAt(int index) {
        return elements.get(index);
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

}
*/