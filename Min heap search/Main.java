// Java program to search for an element in a Min Heap
import java.util.Arrays;

class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor to initialize the heap with given capacity
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Function to get the index of the parent node
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Function to get the index of the left child node
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Function to get the index of the right child node
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // Function to swap two elements in the heap array
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Function to insert a new element into the Min Heap
    public void insert(int key) {
        if (size == capacity) {
            System.out.println("Heap is full, cannot insert more elements.");
            return;
        }

        // Insert the new element at the end of the heap
        heap[size] = key;
        size++;

        // Heapify the heap by moving the new element upwards
        int i = size - 1;
        while (i != 0 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Function to search for a key in the Min Heap
    public boolean search(int key) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == key) {
                return true; // Key found
            }
        }
        return false; // Key not found
    }

    // Function to print the heap array
    public void printHeap() {
        System.out.println("Min Heap: " + Arrays.toString(Arrays.copyOf(heap, size)));
    }

    public static void main(String[] args) {
        // Create a Min Heap with capacity 10
        MinHeap minHeap = new MinHeap(10);

        // Insert elements into the heap
        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(15);
        minHeap.insert(30);
        minHeap.insert(40);
        minHeap.insert(5);

        // Print the heap after insertion
        System.out.println("Heap after insertion:");
        minHeap.printHeap();

        // Search for an element in the heap
        int elementToSearch = 15;
        if (minHeap.search(elementToSearch)) {
            System.out.println("Element " + elementToSearch + " found in the heap.");
        } else {
            System.out.println("Element " + elementToSearch + " not found in the heap.");
        }

        // Search for a non-existent element
        elementToSearch = 50;
        if (minHeap.search(elementToSearch)) {
            System.out.println("Element " + elementToSearch + " found in the heap.");
        } else {
            System.out.println("Element " + elementToSearch + " not found in the heap.");
        }
    }
}