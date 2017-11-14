import java.util.ArrayList;

class MinHeap<T extends Comparable<? super T>> {
  
  private ArrayList<T> data;

  public MinHeap() {data = new ArrayList<>();}

  public String toString() {return data.toString();}

  public int size() {return data.size();}

  public int parentIndex(int i) {return (i - 1) / 2;}

  public int leftIndex(int i) {return 2 * i + 1;}

  public int rightIndex(int i) {return 2 * i + 2;}

  public boolean isLessThan(int i, int j) {
    // Returns whether element at i is less than element at j
    return data.get(i).compareTo(data.get(j)) < 0;
  }

  public boolean validIdx(int i) {
    // Verifies validity of index i
    return i >= 0 && i < data.size();
  }

  public void swap(int i, int j) {
    // Swaps elements at indices i and j
    if (i != j && validIdx(i) && validIdx(j)) {
      T tmp = data.get(i);
      data.set(i, data.get(j));
      data.set(j, tmp);
    }
  }

  public void push(T elem) {
    data.add(elem);

    int curI = data.size() - 1;
    int parentI = parentIndex(curI);

    // Sift up new element until heap is valid
    while (validIdx(parentI) && isLessThan(curI, parentI)) {
      swap(curI, parentI);

      curI = parentI;
      parentI = parentIndex(curI);
    }
  }

  public int siftDown(int curI) {
    // Indices of children
    int leftI = leftIndex(curI);
    int rightI = rightIndex(curI);

    // Figure out index of minimum element among cur and children
    int smallI = curI;
    if (validIdx(leftI) && isLessThan(leftI, smallI))
      smallI = leftI;
    if (validIdx(rightI) && isLessThan(rightI, smallI))
      smallI = rightI;

    // If needed, swap
    if (smallI != curI) {
      swap(curI, smallI);
      // Returns index of where the original element ended up
      return smallI;
    }
    return curI;
  }

  public T pop() {
    if (data.size() == 1) return data.remove(0);

    T min = data.get(0);

    // Remove the root and replace it with the last element
    data.set(0, data.remove(data.size() - 1));

    // Sift the new root down until no more swaps happen
    int curI = 0, newI;
    boolean sifting = true;
    while (sifting) {
      newI = siftDown(curI);
      if (newI == curI) sifting = false;
      else curI = newI;
    }
    return min;
  }

}
