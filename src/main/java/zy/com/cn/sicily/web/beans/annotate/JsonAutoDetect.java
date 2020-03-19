package zy.com.cn.sicily.web.beans.annotate;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/**
 * @Title: JsonAutoDetect.java
 * @Description:
 * @author Alvin  
 * @date 2019年4月23日 下午9:35:29
 * @version V1.0  
 * @Company: didihu.com.cn
 * @Copyright Copyright (c) 2018
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonAutoDetect
{
	/**
	 * Enumeration for possible visibility thresholds (minimum visibility)
	 * that can be used to limit which methods (and fields) are
	 * auto-detected.
	 * <P>Description： </P>
	 *
	 * @author Alvin
	 * @version 1.0
	 */
	public enum Visibility {
		/**
		 * Value that means that all kinds of access modifiers are acceptable,
		 * from private to public.
		 */
		ANY,
		/**
		 * Value that means that any other access modifier other than 'private'
		 * is considered auto-detectable.
		 */
		NON_PRIVATE,
		/**
		 * Value that means access modifiers 'protected' and 'public' are
		 * auto-detectable (and 'private' and "package access" == no modifiers
		 * are not)
		 */
		PROTECTED_AND_PUBLIC,
		/**
		 * Value to indicate that only 'public' access modifier is considered
		 * auto-detectable.
		 */
		PUBLIC_ONLY,
		/**
		 * Value that indicates that no access modifiers are auto-detectable:
		 * this can be used to explicitly disable auto-detection for specified
		 * types.
		 */
		NONE,
		
		/**
		 * Value that indicates that default visibility level (whatever it is,
		 * depends on context) is to be used. This usually means that inherited
		 * value (from parent visibility settings) is to be used.
		 */
		DEFAULT;

		public boolean isVisible(Member m) {
			switch (this) {
			case ANY:
			    return true;
			case NONE:
			    return false;
			case NON_PRIVATE:
			    return !Modifier.isPrivate(m.getModifiers());
			case PROTECTED_AND_PUBLIC:
			    if (Modifier.isProtected(m.getModifiers())) {
			        return true;
			    }
			    // fall through to public case:
			case PUBLIC_ONLY:
			    return Modifier.isPublic(m.getModifiers());
			default:
				return false;
			}
		}
	}
	
    /**
     * Types of property elements (getters, setters, fields, creators) that
     * can be auto-detected.
     * NOTE: as of 1.5, it is recommended that instead of defining this property,
     * distinct visibility properties are used instead. This because levels
     * used with this method are not explicit, but global defaults that differ for different
     * methods. As such, this property can be considered <b>deprecated</b> and
     * only retained for backwards compatibility.
     */
    JsonMethod[] value() default { JsonMethod.ALL };
    
    /**
     * Minimum visibility required for auto-detecting regular getter methods.
     * 
     * @since 1.5
     */
    Visibility getterVisibility() default Visibility.DEFAULT;

    /**
     * Minimum visibility required for auto-detecting is-getter methods.
     * 
     * @since 1.5
     */
    Visibility isGetterVisibility() default Visibility.DEFAULT;
    
    /**
     * Minimum visibility required for auto-detecting setter methods.
     * 
     * @since 1.5
     */    
    Visibility setterVisibility() default Visibility.DEFAULT;

    /**
     * Minimum visibility required for auto-detecting Creator methods,
     * except for no-argument constructors (which are always detected
     * no matter what).
     * 
     * @since 1.5
     */
    Visibility creatorVisibility() default Visibility.DEFAULT;

    /**
     * Minimum visibility required for auto-detecting member fields.
     * 
     * @since 1.5
     */ 
    Visibility fieldVisibility() default Visibility.DEFAULT;
}
