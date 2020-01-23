package io.catty.lbs;

import io.catty.core.Invoker;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomLoadBalance implements LoadBalance {

  @Override
  public <T extends Invoker> T select(List<T> invokers) {
    ThreadLocalRandom random = ThreadLocalRandom.current();
    return invokers.get(random.nextInt(invokers.size()));
  }

}