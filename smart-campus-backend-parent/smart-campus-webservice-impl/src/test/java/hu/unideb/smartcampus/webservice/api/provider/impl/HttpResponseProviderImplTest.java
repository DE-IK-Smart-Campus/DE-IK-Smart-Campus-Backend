package hu.unideb.smartcampus.webservice.api.provider.impl;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_EQUAL_TO_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Optional;
import hu.unideb.smartcampus.webservice.api.exception.HttpRequestTypeNotFoundException;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestFactory;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestType;
import hu.unideb.smartcampus.webservice.api.provider.HttpClientProvider;
import hu.unideb.smartcampus.webservice.api.provider.HttpResponseProvider;

/**
 * Test for {@link HttpResponseProvider}.
 */
@RunWith(MockitoJUnitRunner.class)
public class HttpResponseProviderImplTest {

  /**
   * Url.
   */
  private static final String URL = "url";

  /**
   * Http client provider.
   */
  private final HttpClientProvider httpClientProvider = mock(HttpClientProvider.class);
  /**
   * Http request factory.
   */
  private final HttpRequestFactory httpRequestFactory = mock(HttpRequestFactory.class);
  /**
   * Http client.
   */
  private final HttpClient httpClient = mock(HttpClient.class);
  /**
   * Http response.
   */
  private final HttpResponse httpResponse = mock(HttpResponse.class);
  /**
   * Http request base.
   */
  private final HttpRequestBase httpRequestBase = mock(HttpRequestBase.class);

  /**
   * Http response provider.
   */
  private final HttpResponseProviderImpl httpResponseProvider = new HttpResponseProviderImpl(this.httpClientProvider, this.httpRequestFactory);


  /**
   * Test for throwing {@link IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void sendHttpRequestShouldThrowIllegalArgumentExceptionWhenParameterUrlIsNull() throws IOException {
    // Given

    // When
    this.httpResponseProvider.sendHttpRequest(null, HttpRequestType.HTTP_REQUEST_GET);

    // Then
  }

  /**
   * Test for throwing {@link IllegalArgumentException}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void sendHttpRequestShouldThrowIllegalArgumentExceptionWhenParameterHttpRequestTypeIsNull() throws IOException {
    // Given

    // When
    this.httpResponseProvider.sendHttpRequest(URL, null);

    // Then
  }

  /**
   * Test for throwing {@link HttpRequestTypeNotFoundException}.
   */
  @Test(expected = HttpRequestTypeNotFoundException.class)
  public void sendHttpRequestShouldThrowHttpRequestTypeNotFoundExceptionWhenHttpRequestTypeDoesNotExist() throws IOException {
    // Given
    given(this.httpClientProvider.buildHttpClient()).willReturn(this.httpClient);
    given(this.httpRequestFactory.createHttpRequest(URL, HttpRequestType.HTTP_REQUEST_GET)).willReturn(Optional.empty());

    // When
    this.httpResponseProvider.sendHttpRequest(URL, HttpRequestType.HTTP_REQUEST_GET);

    // Then
  }

  /**
   * Test for throwing {@link IOException}.
   */
  @Test(expected = IOException.class)
  public void sendHttpRequestShouldThrowInputOutputExceptionWhenTheConnectionWasAborted() throws IOException {
    // Given
    given(this.httpClientProvider.buildHttpClient()).willReturn(this.httpClient);
    given(this.httpRequestFactory.createHttpRequest(URL, HttpRequestType.HTTP_REQUEST_GET)).willReturn(Optional.of(this.httpRequestBase));
    given(this.httpClient.execute(any(HttpGet.class))).willThrow(IOException.class);

    // When
    this.httpResponseProvider.sendHttpRequest(URL, HttpRequestType.HTTP_REQUEST_GET);

    // Then
  }

  /**
   * Test for throwing {@link ClientProtocolException}.
   */
  @Test(expected = ClientProtocolException.class)
  public void sendHttpRequestShouldThrowClientProtocolExceptionWhenHttpProtocolErrorOccurred() throws IOException {
    // Given
    given(this.httpClientProvider.buildHttpClient()).willReturn(this.httpClient);
    given(this.httpRequestFactory.createHttpRequest(URL, HttpRequestType.HTTP_REQUEST_GET)).willReturn(Optional.of(this.httpRequestBase));
    given(this.httpClient.execute(any(HttpGet.class))).willThrow(ClientProtocolException.class);

    // When
    this.httpResponseProvider.sendHttpRequest(URL, HttpRequestType.HTTP_REQUEST_GET);

    // Then
  }

  /**
   * Test for throwing {@link IOException}.
   */
  @Test
  public void sendHttpRequestShouldReturnNonNullHttpRequestWhenThereAreNoExceptions() throws IOException {
    // Given
    given(this.httpClientProvider.buildHttpClient()).willReturn(this.httpClient);
    given(this.httpRequestFactory.createHttpRequest(URL, HttpRequestType.HTTP_REQUEST_GET)).willReturn(Optional.of(httpRequestBase));
    given(this.httpClient.execute(any(HttpRequestBase.class))).willReturn(this.httpResponse);

    // When
    final HttpResponse result = this.httpResponseProvider.sendHttpRequest(URL, HttpRequestType.HTTP_REQUEST_GET);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result, equalTo(this.httpResponse));

    then(this.httpClientProvider).should().buildHttpClient();
    then(this.httpRequestFactory).should().createHttpRequest(URL, HttpRequestType.HTTP_REQUEST_GET);
    then(this.httpClient).should().execute(any(HttpRequestBase.class));
  }
}
