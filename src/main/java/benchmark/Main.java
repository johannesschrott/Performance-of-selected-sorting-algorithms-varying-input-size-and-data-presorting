package benchmark;

import java.io.IOException;

import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;



public class Main {

  public static void main(String[] args) throws IOException {


  //  testing();

    Options opt = new OptionsBuilder()
      .addProfiler(MemoryProfiler.class).build();
    try {
      new Runner(opt).run();
    } catch (RunnerException e){
      System.out.println(e);
    }

    //  org.openjdk.jmh.Main.main(args);
  }

  // This method has been used for checking the sorting algorithms 
  public static void testing(){
    int[] shuffledArray = Algorithm.createArray(10, Algorithm.PreSort.SWAP_FIRST_AND_LAST_ELEMENT);
    
    printArray(shuffledArray);

   // Algorithm.insertionSort(shuffledArray);
    Algorithm.quickSort(shuffledArray);

    printArray(shuffledArray);

  }

  public static void printArray(int[] array){
    for(int val : array){
      System.out.print(val + ", ");
    }
    System.out.println();
  }
}