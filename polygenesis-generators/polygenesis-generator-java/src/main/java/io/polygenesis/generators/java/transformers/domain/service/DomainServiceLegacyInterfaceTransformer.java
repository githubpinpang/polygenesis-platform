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

package io.polygenesis.generators.java.transformers.domain.service;

import io.polygenesis.commons.text.TextConverter;
import io.polygenesis.generators.java.shared.transformer.AbstractLegacyInterfaceTransformer;
import io.polygenesis.generators.java.shared.transformer.FromDataTypeToJavaConverter;
import io.polygenesis.generators.java.shared.transformer.FunctionToLegacyMethodRepresentationTransformer;
import io.polygenesis.models.domain.DomainService;
import io.polygenesis.representations.code.MethodRepresentation;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Domain service interface representable.
 *
 * @author Christos Tsakostas
 */
public class DomainServiceLegacyInterfaceTransformer
    extends AbstractLegacyInterfaceTransformer<DomainService> {

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Domain service interface representable.
   *
   * @param fromDataTypeToJavaConverter the from data type to java converter
   * @param functionToMethodRepresentationTransformer the function to method representation
   *     converter
   */
  public DomainServiceLegacyInterfaceTransformer(
      FromDataTypeToJavaConverter fromDataTypeToJavaConverter,
      FunctionToLegacyMethodRepresentationTransformer functionToMethodRepresentationTransformer) {
    super(fromDataTypeToJavaConverter, functionToMethodRepresentationTransformer);
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public Set<MethodRepresentation> methodRepresentations(DomainService source, Object... args) {

    return new LinkedHashSet<>();
  }

  @Override
  public String packageName(DomainService source, Object... args) {
    return source.getPackageName().getText();
  }

  @Override
  public Set<String> imports(DomainService source, Object... args) {
    return new LinkedHashSet<>();
  }

  @Override
  public Set<String> annotations(DomainService source, Object... args) {
    return new LinkedHashSet<>();
  }

  @Override
  public String description(DomainService source, Object... args) {
    return String.format(
        "The %s Domain Service.",
        TextConverter.toUpperCamelSpaces(source.getObjectName().getText()));
  }

  @Override
  public String modifiers(DomainService source, Object... args) {
    return MODIFIER_PUBLIC;
  }

  @Override
  public String simpleObjectName(DomainService source, Object... args) {
    return String.format(
        "%sDomainService", TextConverter.toUpperCamel(source.getObjectName().getText()));
  }

  @Override
  public String fullObjectName(DomainService source, Object... args) {
    return simpleObjectName(source);
  }
}