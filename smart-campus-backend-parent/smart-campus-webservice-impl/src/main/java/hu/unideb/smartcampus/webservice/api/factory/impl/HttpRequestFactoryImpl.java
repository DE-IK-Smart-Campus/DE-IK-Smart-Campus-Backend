package hu.unideb.smartcampus.webservice.api.factory.impl;

import static hu.unideb.smartcampus.webservice.api.factory.HttpRequestType.HTTP_REQUEST_GET;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Component;

import java.util.Optional;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestFactory;
import hu.unideb.smartcampus.webservice.api.factory.HttpRequestType;

/**
 * Implementation for {@link HttpRequestFactory}.
 */
@Component
public class HttpRequestFactoryImpl implements HttpRequestFactory {

  @Override
  public Optional<HttpRequestBase> createHttpRequest(final String url, final HttpRequestType httpRequestType) {
    if (HTTP_REQUEST_GET.equals(httpRequestType)) {
      final HttpGet httpGet = new HttpGet(url);
      httpGet.addHeader(new BasicHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType()));
      return Optional.of(httpGet);
    } else {
      return Optional.empty();
    }
  }
}
