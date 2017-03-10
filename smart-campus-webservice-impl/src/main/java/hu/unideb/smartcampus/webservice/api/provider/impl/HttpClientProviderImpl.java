package hu.unideb.smartcampus.webservice.api.provider.impl;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.webservice.api.provider.HttpClientProvider;

/**
 * Implementation for {@link HttpClientProvider}.
 */
@Component
public class HttpClientProviderImpl implements HttpClientProvider {

  @Override
  public HttpClient buildHttpClient() {
    return HttpClientBuilder.create().build();
  }
}
