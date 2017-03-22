package hu.unideb.smartcampus.shared.iq.request;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRegistry;

import org.jivesoftware.smack.packet.AbstractError;
import org.jivesoftware.smack.packet.XMPPError;
import org.jxmpp.jid.Jid;

/**
 * Object factory for serializing/deserializing Smack objects.
 *
 */
@XmlRegistry
@SuppressWarnings({"PMD"})
public class ObjectFactory {

  /**
   * Create JID.
   */
  public Jid createJid() {
    return createInstance(Jid.class);
  }

  /**
   * Create XMPP Error.
   */
  public XMPPError createXmppError() {
    return XMPPError.getBuilder().build();
  }

  /**
   * Create Abstract error.
   */
  public AbstractError createAbstractError() throws InstantiationException, IllegalAccessException {
    return AbstractError.class.newInstance();
  }

  private <T> T createInstance(Class<T> anInterface) {
    return (T) Proxy.newProxyInstance(anInterface.getClassLoader(), new Class[] {anInterface},
        new InterfaceInvocationHandler());
  }

  /**
   * Interface handler.
   */
  private static class InterfaceInvocationHandler implements InvocationHandler {

    /**
     * Getter method prefix.
     */
    private static final String GET = "get";

    /**
     * Values.
     */
    private Map<String, Object> values = new HashMap<String, Object>();

    /**
     * Invoke method.
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      String methodName = method.getName();
      if (methodName.startsWith(GET)) {
        return values.get(methodName.substring(3));
      } else {
        values.put(methodName.substring(3), args[0]);
        return null;
      }
    }
  }
}
