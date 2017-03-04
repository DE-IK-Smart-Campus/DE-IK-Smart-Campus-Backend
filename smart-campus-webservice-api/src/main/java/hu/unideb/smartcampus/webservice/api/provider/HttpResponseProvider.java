package hu.unideb.smartcampus.webservice.api.provider;

import org.apache.http.HttpResponse;

import java.io.IOException;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestType;

/**
 * Http response provider.
 */
public interface HttpResponseProvider {

  /**
   * Sends a http request to url.
   *
   * @param url the url
   * @param httpRequestType the httpRequestType
   * @return the httpResponse.
   * @throws IOException in case of a problem or the connection was aborted.
   */
  HttpResponse sendHttpRequest(String url, HttpRequestType httpRequestType) throws IOException;
}
