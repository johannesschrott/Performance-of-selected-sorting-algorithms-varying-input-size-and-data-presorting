package benchmark;

//import java.util.concurrent.ThreadLocalRandom;

public class Algorithm {

     

  public static void quickSort(int arr[]){
    quickSort(arr, 0, arr.length-1);
  }

  public static void quickSort(int arr[], int begin, int end) {
    if (begin < end) {
       // System.out.println("Starting Partition with lower bound " + begin + " and with upper bound " + end);
        int partitionIndex = partition(arr, begin, end);
 
        quickSort(arr, begin, partitionIndex);
        quickSort(arr, partitionIndex+1, end);
    }
  }

  /**
   * Partitions the Array and returns the pivot
   * @param arr Array which should be partition
   * @param begin of the partition
   * @param end of the partion
   * @return bound between the two created partitions
   */
  private static int partition(int arr[], int begin, int end) { 

    int lowerIndex = begin;
    int upperIndex = end;

    // Choosing a bound is important! We choose the value of the middle of the array here since this will be best for fullSorted and reverseSorted!

    int bound = ((end-begin) / 2) + begin;

    boolean done = false;

    while (!done) {
      while (lowerIndex < upperIndex && arr[lowerIndex] < bound) {
        lowerIndex++;
      }
      while (upperIndex > lowerIndex && arr[upperIndex] > bound) {
        upperIndex--;
      }
      if (lowerIndex >= upperIndex) {
        done = true;
      } else {
        int temp = arr[lowerIndex];
        arr[lowerIndex] = arr[upperIndex];
        arr[upperIndex] = temp;
      //  System.out.println("Swapped " + arr[lowerIndex] + " with " + arr[upperIndex]);
      }
    }

    return upperIndex;
  }


    // int pivot = arr[end/2];
    // int i = (begin-1);
 
    // for (int j = begin; j < end; j++) {
    //     if (arr[j] <= pivot) {
    //         i++;
 
    //         int swapTemp = arr[i];
    //         arr[i] = arr[j];
    //         arr[j] = swapTemp;
    //     }
    // }
    // int swapTemp = arr[i+1];
    // arr[i+1] = arr[end];
    // arr[end] = swapTemp;
 
    // return i+1;
  //}

  // Vom Buch

  public static int[] insertionSort(int[] a){
    int j, t;
    for(int i = 1; i < a.length; i++){
      j = i;
      t = a[i];
      while(j > 0 && a[j-1] > t){
        a[j] = a[j - 1];
        j = j - 1;
      }
      a[j] = t;
    }
    return a;
  }

  public static int[] createArray(int arraySize, PreSort presort){
   
    int[] array = new int[arraySize]; 

    if (presort == PreSort.REVERSE_SORTED){
      // Fill array with reverse values
      for(int i = 0; i < arraySize; i++) {
        array[i] = arraySize - 1 - i;
      }
    } else {
      // Fill array with sorted values
      for (int i = 0; i < arraySize; i++) {
        array[i] = i;
      }

      if (presort == PreSort.SWAP_FIRST_AND_LAST_ELEMENT) {
        int temp = array[array.length-1];
        array[array.length-1] = array[0];
        array[0] = temp;
      } else if (presort == PreSort.SWAP_FIRST_AND_SECOND_HALF) {
        for (int i = 0; i < arraySize/2; i++) {
          int temp = array[i];
          array[i] = array[i+array.length/2];
          array[i+array.length/2] = temp;
        }
      } else if (presort == PreSort.SWAP_EVERY_TWO_ELEMENTS) {
        for (int i = 0; i < arraySize; i = i+2) {
          int temp = array[i+1];
          array[i+1] = array[i];
          array[i] = temp;
        }
      }
    }
  
  
    
  

   return array;
  }


  public enum PreSort {
    FULL_SORTED,
    REVERSE_SORTED,
    SWAP_FIRST_AND_LAST_ELEMENT, 
    SWAP_FIRST_AND_SECOND_HALF,
    SWAP_EVERY_TWO_ELEMENTS
  }

}