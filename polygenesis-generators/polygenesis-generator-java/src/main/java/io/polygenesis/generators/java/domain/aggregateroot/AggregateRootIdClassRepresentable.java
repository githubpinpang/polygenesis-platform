/*-
 * ==========================LICENSE_START=================================
 * PolyGenesis Platform
 * ========================================================================
 * Copyright (C) 2015 - 2019 OREGOR LTD
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

package io.polygenesis.generators.java.domain.aggregateroot;

import io.polygenesis.commons.text.TextConverter;
import io.polygenesis.models.domain.AggregateRoot;
import io.polygenesis.representations.java.AbstractClassRepresentable;
import io.polygenesis.representations.java.ConstructorRepresentation;
import io.polygenesis.representations.java.FieldRepresentation;
import io.polygenesis.representations.java.FromDataTypeToJavaConverter;
import io.polygenesis.representations.java.MethodRepresentation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * The type Aggregate root projection converter.
 *
 * @author Christos Tsakostas
 */
public class AggregateRootIdClassRepresentable extends AbstractClassRepresentable<AggregateRoot> {

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Aggregate root id class representable.
   *
   * @param fromDataTypeToJavaConverter the from data type to java converter
   */
  public AggregateRootIdClassRepresentable(
      FromDataTypeToJavaConverter fromDataTypeToJavaConverter) {
    super(fromDataTypeToJavaConverter);
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public Set<FieldRepresentation> fieldRepresentations(AggregateRoot source, Object... args) {
    return new LinkedHashSet<>();
  }

  @Override
  public Set<ConstructorRepresentation> constructorRepresentations(
      AggregateRoot source, Object... args) {
    Set<ConstructorRepresentation> constructorRepresentations = new LinkedHashSet<>();

    // ---------------------------------------------------------------------------------------------
    // Create no-args constructor
    // ---------------------------------------------------------------------------------------------
    constructorRepresentations.add(createNoArgsConstructorForPersistence());

    // ---------------------------------------------------------------------------------------------
    // Create constructor with parameters
    // ---------------------------------------------------------------------------------------------
    Set<FieldRepresentation> fieldRepresentations = fieldRepresentations(source);

    fieldRepresentations.add(new FieldRepresentation("UUID", "rootUuid", true));

    constructorRepresentations.add(
        createConstructorWithSettersFromFieldRepresentations(
            source.getName().getText(), fieldRepresentations));

    return constructorRepresentations;
  }

  @Override
  public Set<MethodRepresentation> methodRepresentations(AggregateRoot source, Object... args) {
    return new LinkedHashSet<>();
  }

  @Override
  public String packageName(AggregateRoot source, Object... args) {
    return source.getPackageName().getText();
  }

  @Override
  public Set<String> imports(AggregateRoot source, Object... args) {
    Set<String> imports = new TreeSet<>();

    imports.add("com.oregor.ddd4j.core.AggregateRootId");
    imports.add("javax.persistence.Embeddable");
    imports.add("java.util.UUID");

    return imports;
  }

  @Override
  public Set<String> annotations(AggregateRoot source, Object... args) {
    return new LinkedHashSet<>(Arrays.asList("@Embeddable"));
  }

  @Override
  public String description(AggregateRoot source, Object... args) {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("The ");

    stringBuilder.append(TextConverter.toUpperCamelSpaces(source.getName().getText()));

    stringBuilder.append(" Aggregate Root Id.");

    return stringBuilder.toString();
  }

  @Override
  public String modifiers(AggregateRoot source, Object... args) {
    return MODIFIER_PUBLIC;
  }

  @Override
  public String simpleObjectName(AggregateRoot source, Object... args) {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(TextConverter.toLowerCamel(source.getName().getText()));
    stringBuilder.append("Id");

    return stringBuilder.toString();
  }

  @Override
  public String fullObjectName(AggregateRoot source, Object... args) {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(TextConverter.toUpperCamel(source.getName().getText()));
    stringBuilder.append("Id");
    stringBuilder.append(" extends ");
    stringBuilder.append("AggregateRootId");

    return stringBuilder.toString();
  }
}
