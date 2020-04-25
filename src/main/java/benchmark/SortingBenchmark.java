package benchmark;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import benchmark.Algorithm.PreSort;

@Fork(warmups = 1, value = 2)
@Warmup(iterations = 1, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
public class SortingBenchmark {

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void quick(final BenchmarkState state) {
        Algorithm.quickSort(state.array);
    }

    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Benchmark
    public void insertion(final BenchmarkState state) {
        Algorithm.insertionSort(state.array);
    }

    @State(Scope.Thread)
    public static class BenchmarkState {

        // Arrays with this sizes are generated
        @Param({ "1000", "10000", "100000" })
        int arraySize;

        // The degree of sorting is set with:
        // 
        // 1 -> FullSorted
        // 2 -> Change first and last element: inv is high, others are low: runs = 2,
        // rem = 1, exc = 1
        // 3 -> First and last element are swapped
        // 4 -> First and second half of elements are swapped
        // 5 -> ReverseSorted

        @Param({"1", "2", "3", "4", "5"})
        int degreeOfSorting;

        int array[];

        // Filling array here with values in the selected presorting
        @Setup(Level.Invocation)
        public void setup() {

            Algorithm.PreSort ps = null;

            switch (degreeOfSorting) {
                
                case 1:
                    ps = PreSort.FULL_SORTED;
                    break;
                case 2:
                    ps = PreSort.SWAP_FIRST_AND_LAST_ELEMENT;
                    break;
                case 3:
                    ps = PreSort.SWAP_FIRST_AND_SECOND_HALF;
                    break;
                case 4:
                    ps = PreSort.SWAP_EVERY_TWO_ELEMENTS;
                    break;
                case 5:
                    ps = PreSort.REVERSE_SORTED;
                    break;

            }

            this.array = Algorithm.createArray(arraySize, ps);

        }

    }

}
