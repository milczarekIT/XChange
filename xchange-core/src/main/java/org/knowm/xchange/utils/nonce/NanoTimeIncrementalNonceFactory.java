package org.knowm.xchange.utils.nonce;

import si.mazi.rescu.SynchronizedValueFactory;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

/**
 * Class computes a current time based nonce.
 *
 * <p>This class while designed to be thread-safe does not protect against multiple processes where
 * the system clock may be out of sync. It also does not protect a system where a lower granularity
 * time unit like {@link TimeUnit#SECONDS} is used and the same nonce is computed at the same time
 * from competing processes.
 *
 * <p>Compatibility is limited to the time units specified.
 */
public class NanoTimeIncrementalNonceFactory implements SynchronizedValueFactory<Long> {

  private static final long NANOSECONDS_MULTIPLIER = (long) Math.pow(10, 9);

  private final AtomicLong nonce = new AtomicLong(0);

  private final Supplier<Long> timeFn = () -> {
      final Instant now = Instant.now();
      final long epochSecond = now.getEpochSecond() * NANOSECONDS_MULTIPLIER;
      final int nano = now.getNano();
      return epochSecond + nano;
  };



  @Override
  public Long createValue() {
    return nonce.updateAndGet(
        prevNonce -> {
          long newNonce = timeFn.get();

          if (newNonce <= prevNonce) {
            newNonce = prevNonce + 1;
          }
          return newNonce;
        });
  }
}
