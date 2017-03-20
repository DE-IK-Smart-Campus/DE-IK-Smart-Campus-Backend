package hu.unideb.smartcampus.service.api.filter;

import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Stanza;

import hu.unideb.smartcampus.shared.iq.TestIq;

/**
 * Test IQ filter.
 *
 */
public class TestIqFilter implements StanzaFilter {

  @Override
  public boolean accept(Stanza stanza) {
    boolean customIqReceived = false;
    if (stanza instanceof TestIq) {
      customIqReceived = true;
    }
    return customIqReceived;
  }

}
