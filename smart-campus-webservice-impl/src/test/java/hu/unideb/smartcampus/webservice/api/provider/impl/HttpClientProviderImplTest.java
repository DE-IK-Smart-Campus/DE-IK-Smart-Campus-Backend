package hu.unideb.smartcampus.webservice.api.provider.impl;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.http.client.HttpClient;
import org.junit.Test;

import hu.unideb.smartcampus.webservice.api.provider.HttpClientProvider;

/**
 * Test for {@link HttpClientProvider}.
 */
public class HttpClientProviderImplTest {

  /**
   * Http client provider.
   */
  private final HttpClientProvider httpClientProvider = new HttpClientProviderImpl();

  /**
   * Test for buildHttpClient() method.
   */
  @Test
  public void buildHttpClientShouldReturnNonNullHttpClient() {
    // Given

    // When
    final HttpClient result = this.httpClientProvider.buildHttpClient();

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
  }
}
