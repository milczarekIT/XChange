package org.knowm.xchange.interceptor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import si.mazi.rescu.Interceptor;

public class InterceptorProviderTest {

  @Test
  public void testResolveInterceptors() {
    final Interceptor[] resolvedInterceptors = InterceptorProvider.provide();

    assertThat(resolvedInterceptors).hasSize(1);
    assertThat(resolvedInterceptors[0]).isInstanceOf(TestInterceptor.class);
  }
}
