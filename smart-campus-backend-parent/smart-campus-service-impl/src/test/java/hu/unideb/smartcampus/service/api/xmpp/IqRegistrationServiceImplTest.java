package hu.unideb.smartcampus.service.api.xmpp;

import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import hu.unideb.smartcampus.shared.exception.IqRegistrationException;
import hu.unideb.smartcampus.shared.iq.context.IqClassContext;
import hu.unideb.smartcampus.shared.iq.provider.BaseSmartCampusIqProvider;
import hu.unideb.smartcampus.shared.iq.provider.SubjectRequestIqProvider;
import hu.unideb.smartcampus.shared.iq.request.BaseSmartCampusIq;
import hu.unideb.smartcampus.shared.iq.request.SubjectsIqRequest;

/**
 * Testing IQ registration with providers.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(IqClassContext.class)
public class IqRegistrationServiceImplTest {

  private IqRegistrationServiceImpl service = new IqRegistrationServiceImpl();

  @Test
  public void testRegisteringIqsWithProviders()
      throws IqRegistrationException, InstantiationException, IllegalAccessException {
    // given
    Map<Class<? extends BaseSmartCampusIq>, Class<? extends BaseSmartCampusIqProvider>> iqWithProviders =
        new HashMap<>();
    iqWithProviders.put(SubjectsIqRequest.class, SubjectRequestIqProvider.class);
    PowerMockito.mockStatic(IqClassContext.class, IqClassContext.class);

    // when
    PowerMockito.when(IqClassContext.getIqWithProvider()).thenReturn(iqWithProviders);

    // then
    service.registerIqWithProviders();
    IQProvider<IQ> iqProvider =
        ProviderManager.getIQProvider(SubjectsIqRequest.ELEMENT, BaseSmartCampusIq.BASE_NAMESPACE);
    Assert.assertNotNull(iqProvider);
    Assert.assertEquals(SubjectRequestIqProvider.class, iqProvider.getClass());
  }

}
