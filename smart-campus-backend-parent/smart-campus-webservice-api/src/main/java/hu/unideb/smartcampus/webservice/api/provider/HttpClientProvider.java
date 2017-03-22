package hu.unideb.smartcampus.webservice.api.provider;


import org.apache.http.client.HttpClient;

/**
 * Http client provider.
 */
public interface HttpClientProvider {

  /**
   * Build http client.
   *
   * @return httpclient
   */
  HttpClient buildHttpClient();
}
