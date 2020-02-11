package pink.catty.core.service;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import pink.catty.core.support.timer.HashedWheelTimer;

/**
 * Setting of the method.
 */
@Target({METHOD})
@Retention(RUNTIME)
@Documented
@Inherited
public @interface Function {

  /**
   * Milli-seconds. After timeout milli-seconds, you will receive a TimeoutException(RuntimeException).
   *
   * NOTICE:
   *
   * There are several timeout setting might be set at meantime such as Service setting, Function
   * setting and Endpoint(client & server) setting. If every timeout options are set, Endpoint
   * setting is the deadline, which means the timeout-event will be trigger when Endpoint's timeout
   * reached even though Service timeout-setting or Function timeout-setting is longer than
   * Endpoint's timeout. So I suggest you to set Endpoint's timeout longer than Service or Function
   * setting.
   *
   * When there are comparable setting of Service and Function, Service timeout-setting will be the
   * deadline.
   *
   * So, if you want the timeout-setting work well, Endpoint > Service > Method (timeout) will be a
   * great setting.
   *
   * And you should also be careful about "retry-strategy", when timeout triggered might cause
   * severe problems.
   *
   * @see HashedWheelTimer
   */
  int timeout() default -1;

  /**
   * Method's name. It need be unique in one Service. If does'nt, {@link
   * DuplicatedMethodNameException} will be thrown. If name equals with "", the signature string of
   * this method will be taken as the name.
   *
   * @see pink.catty.core.utils.ReflectUtils#getMethodSign(Method)
   */
  String name() default "";

  /**
   * Alias, other names of this method. Each alias also need be unique in one Service. If does'nt,
   * {@link DuplicatedMethodNameException} will be thrown.
   */
  String[] alias() default {};

}