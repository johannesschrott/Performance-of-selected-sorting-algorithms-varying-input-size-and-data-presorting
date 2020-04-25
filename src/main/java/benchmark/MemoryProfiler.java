package benchmark;


import org.openjdk.jmh.profile.InternalProfiler;
import org.openjdk.jmh.results.AggregationPolicy;
import org.openjdk.jmh.results.IterationResult;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.ScalarResult;

import java.util.ArrayList;
import java.util.Collection;
import java.lang.management.ManagementFactory;
import java.lang.management.GarbageCollectorMXBean;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.infra.IterationParams;


public class MemoryProfiler implements InternalProfiler {
  @Override
  public String getDescription() {
      return "Benchmark measuring time and memory taken by Insertion and Quicksort varying input size and data presorting";
  }

  // The following methods are used for Memory measuring

  @Override
  public void beforeIteration(final BenchmarkParams benchmarkParams, final IterationParams iterationParams) {
    long before = getGcCount();
    System.gc();
    while (getGcCount() == before);
  }

  @Override
  public Collection<? extends Result> afterIteration(final BenchmarkParams benchmarkParams,


      final IterationParams iterationParams, final IterationResult result) {

        final long mb = 1024 * 1024;
      
        final long totalHeap = getCurrentlyUsedMemory(); // is returned in bytes!

        final Collection<ScalarResult> results = new ArrayList<>();
        results.add(new ScalarResult("Max memory heap", totalHeap / mb, "mbs", AggregationPolicy.MAX));

        return results;
  }

  static long getGcCount() {
    long sum = 0;
    for (GarbageCollectorMXBean b : ManagementFactory.getGarbageCollectorMXBeans()) {
      long count = b.getCollectionCount();
      if (count != -1) { sum +=  count; }
    }
    return sum;
  }

  static long getCurrentlyUsedMemory() {
    return
      ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() +
      ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
  }
}