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
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to filter tests by functionality in Jenkins builds. When type is
 * annitated, all its methods inherit its annotation. Annotation on methos
 * completely override annotation on type.
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Functionality {
   WebsiteFunctionality[] value();
}
