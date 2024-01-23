/*****************************************************************
 * Vip Service http://www.vipservice.ru
 * Project: webtests
 *
 * $Id: $
 *****************************************************************/
package hello.junit;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * WARNING!!! Environments of type other than DEVELOPMENT can contain production
 * GDSes. Set these types only after confirmation from the customer.
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface EnvironmentType {
    TestEnvironmentType[] value();
}
