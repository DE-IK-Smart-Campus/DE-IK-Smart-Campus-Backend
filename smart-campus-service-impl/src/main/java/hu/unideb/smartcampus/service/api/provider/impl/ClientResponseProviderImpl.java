package hu.unideb.smartcampus.service.api.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import hu.unideb.smartcampus.service.api.provider.ClientProvider;
import hu.unideb.smartcampus.service.api.provider.ClientResponseProvider;
import hu.unideb.smartcampus.service.ejabberd.sharedroster.request.BaseRequest;

@Service
public class ClientResponseProviderImpl implements ClientResponseProvider {

  private final ClientProvider clientProvider;

  @Autowired
  public ClientResponseProviderImpl(final ClientProvider clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public <T extends BaseRequest> Response sendPostRequest(final String endpoint, final T request) {
    Assert.notNull(endpoint);
    Assert.notNull(request);

    return this.clientProvider
        .createClientByUrl(endpoint)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(request, MediaType.APPLICATION_JSON));
  }
}
