package hu.unideb.smartcampus.webservice.api.provider.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import hu.unideb.smartcampus.webservice.api.exception.HttpRequestTypeNotFoundException;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestFactory;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestType;
import hu.unideb.smartcampus.webservice.api.provider.HttpClientProvider;
import hu.unideb.smartcampus.webservice.api.provider.HttpResponseProvider;

/**
 * Implementation for {@link HttpResponseProvider}.
 */
@Component
public class HttpResponseProviderImpl implements HttpResponseProvider {

  private final HttpClientProvider httpClientProvider;
  private final HttpRequestFactory httpRequestFactory;

  @Autowired
  public HttpResponseProviderImpl(final HttpClientProvider httpClientProvider, final HttpRequestFactory httpRequestFactory) {
    this.httpClientProvider = httpClientProvider;
    this.httpRequestFactory = httpRequestFactory;
  }

  @Override
  public HttpResponse sendHttpRequest(final String url, final HttpRequestType httpRequestType) throws IOException {
    Assert.notNull(url);
    Assert.notNull(httpRequestType);

    final HttpRequestBase httpRequestBase = this.httpRequestFactory.createHttpRequest(url, HttpRequestType.HTTP_REQUEST_GET)
        .orElseThrow(() -> new HttpRequestTypeNotFoundException(String.format("Http request type not found: '%s'", httpRequestType)));

    return this.httpClientProvider.buildHttpClient().execute(httpRequestBase);
  }
}
