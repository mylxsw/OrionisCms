package name.orionis.helper.reflection.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Remark annotation
 * Explain the method, just like doc, but It can be read at runtime
 * @author code.404
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Remark {
	public String value();
	public String group() default "";
}
