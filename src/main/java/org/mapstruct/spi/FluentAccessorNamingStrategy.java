package org.mapstruct.spi;

import java.beans.Introspector;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.TypeKind;

import org.mapstruct.ap.spi.AccessorNamingStrategy;
import org.mapstruct.ap.spi.DefaultAccessorNamingStrategy;

/**
 * A custom {@link AccessorNamingStrategy} recognizing fluent accessors.
 *
 */
public class FluentAccessorNamingStrategy extends DefaultAccessorNamingStrategy {

  @Override
  public boolean isGetterMethod(final ExecutableElement method) {
    return method.getParameters().isEmpty()
        && method.getReturnType().getKind() != TypeKind.VOID;
  }

  @Override
  public boolean isSetterMethod(final ExecutableElement method) {
    return method.getParameters().size()==1
        && method.getReturnType().toString().equals( method.getEnclosingElement().asType().toString());
  }

  @Override
  public String getPropertyName(final ExecutableElement getterOrSetterMethod) {
    final String methodName = getterOrSetterMethod.getSimpleName().toString();
    return Introspector.decapitalize(methodName);
  }
}
