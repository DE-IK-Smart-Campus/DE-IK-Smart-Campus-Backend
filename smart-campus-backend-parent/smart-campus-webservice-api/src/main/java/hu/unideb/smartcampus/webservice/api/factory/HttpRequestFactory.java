package hu.unideb.smartcampus.webservice.api.factory;

import org.apache.http.client.methods.HttpRequestBase;

import java.util.Optional;

/**
 * Http request factory.
 */
public interface HttpRequestFactory {

  /**
   * Creates a http request.
   *
   * @param url the url
   * @param httpRequestType the httpRequestType
   * @return the actual http request type.
   */
  Optional<HttpRequestBase> createHttpRequest(String url, HttpRequestType httpRequestType);
}
