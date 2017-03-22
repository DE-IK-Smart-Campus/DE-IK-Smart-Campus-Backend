package hu.unideb.smartcampus.webservice.api.factory.impl;

import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_EQUAL_TO_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_IS_ERROR_MESSAGE;
import static h.unideb.smartcampus.shared.message.AssertionErrorMessage.ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.junit.Test;

import java.util.Optional;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestFactory;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestType;

/**
 * Created by Gabor Ivanyi-Nagy on 2017. 03. 04..
 */
public class HttpRequestFactoryImplTest {

  /**
   * Url.
   */
  private static final String URL = "url";

  /**
   * Http request factory.
   */
  private final HttpRequestFactory httpRequestFactory = new HttpRequestFactoryImpl();

  /**
   * Test for returning empty optional.
   */
  @Test
  public void createHttpRequestShouldReturnEmptyOptionalWhenParameterHttpRequestTypeIsNull() {
    // Given

    // When
    final Optional<HttpRequestBase> result = this.httpRequestFactory.createHttpRequest(URL, null);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_IS_ERROR_MESSAGE, result.isPresent(), is(false));
  }

  /**
   * Test for returning non-empty optional.
   */
  @Test
  public void createHttpRequestShouldReturnNonEmptyOptionalWhenParameterHttpRequestTypeExists() {
    // Given
    final Header expectedHeader = new BasicHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

    // When
    final Optional<HttpRequestBase> result = this.httpRequestFactory.createHttpRequest(URL, HttpRequestType.HTTP_REQUEST_GET);

    // Then
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_IS_ERROR_MESSAGE, result.isPresent(), is(true));
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result.get(), notNullValue());
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result.get().getAllHeaders(), notNullValue());
    assertThat(ASSERTION_IS_ERROR_MESSAGE, result.get().getAllHeaders().length, is(1));
    assertHeader(result.get().getAllHeaders()[0], expectedHeader);
  }

  private void assertHeader(final Header result, final Header expectedHeader) {
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result, notNullValue());
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result.getName(), notNullValue());
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result.getName(), equalTo(expectedHeader.getName()));
    assertThat(ASSERTION_NOT_NULL_VALUE_ERROR_MESSAGE, result.getValue(), notNullValue());
    assertThat(ASSERTION_EQUAL_TO_ERROR_MESSAGE, result.getValue(), equalTo(expectedHeader.getValue()));
  }
}
