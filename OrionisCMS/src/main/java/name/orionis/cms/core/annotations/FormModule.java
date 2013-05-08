package name.orionis.cms.core.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Controller;
/**
 * FormModule
 * Indicates that an annotated class is a "form module".
 * Such classes are considered as an web layer for cms web access.
 * Handle the form submit request.
 * @author orionis
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
public @interface FormModule {
	String value() default "";
}
