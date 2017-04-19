package hu.unideb.smartcampus.webservice.api.neptun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MediaType;

/**
 * JSON content response filter.
 */
public class JsonContentTypeResponseFilter implements ClientResponseFilter {

  private static final String CONTENT_TYPE = "Content-Type";

  @Override
  public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext)
      throws IOException {
    List<String> contentType = new ArrayList<>(1);
    contentType.add(MediaType.APPLICATION_JSON);
    responseContext.getHeaders().put(CONTENT_TYPE, contentType);
  }
}
