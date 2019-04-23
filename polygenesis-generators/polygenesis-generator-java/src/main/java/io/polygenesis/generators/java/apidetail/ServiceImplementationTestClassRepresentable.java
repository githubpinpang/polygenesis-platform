/*-
 * ==========================LICENSE_START=================================
 * PolyGenesis Platform
 * ========================================================================
 * Copyright (C) 2015 - 2019 Christos Tsakostas, OREGOR LTD
 * ========================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ===========================LICENSE_END==================================
 */

package io.polygenesis.generators.java.apidetail;

import io.polygenesis.commons.text.TextConverter;
import io.polygenesis.models.apiimpl.ServiceImplementation;
import io.polygenesis.representations.java.FromDataTypeToJavaConverter;
import java.util.Set;

/**
 * The type Service test implementation class representable.
 *
 * @author Christos Tsakostas
 */
public class ServiceImplementationTestClassRepresentable
    extends ServiceImplementationClassRepresentable {

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Service test implementation class representable.
   *
   * @param fromDataTypeToJavaConverter the from data type to java converter
   * @param serviceMethodImplementationRepresentable the api impl method projection converter
   */
  public ServiceImplementationTestClassRepresentable(
      FromDataTypeToJavaConverter fromDataTypeToJavaConverter,
      ServiceMethodImplementationRepresentable serviceMethodImplementationRepresentable) {
    super(fromDataTypeToJavaConverter, serviceMethodImplementationRepresentable);
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public Set<String> imports(ServiceImplementation source, Object... args) {
    Set<String> imports = super.imports(source);

    imports.add("static org.assertj.core.api.Assertions.assertThat");
    imports.add("static org.assertj.core.api.Assertions.assertThatThrownBy");
    imports.add("static org.mockito.Mockito.mock");
    imports.add("org.junit.Before");
    imports.add("org.junit.Test");

    return imports;
  }

  @Override
  public String simpleObjectName(ServiceImplementation source, Object... args) {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(
        TextConverter.toUpperCamel(source.getService().getServiceName().getText()));
    stringBuilder.append("ImplTest");

    return stringBuilder.toString();
  }

  @Override
  public String fullObjectName(ServiceImplementation source, Object... args) {
    return simpleObjectName(source);
  }
}