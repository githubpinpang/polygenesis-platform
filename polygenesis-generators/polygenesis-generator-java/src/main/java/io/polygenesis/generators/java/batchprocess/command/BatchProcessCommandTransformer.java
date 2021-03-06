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

package io.polygenesis.generators.java.batchprocess.command;

import io.polygenesis.commons.text.TextConverter;
import io.polygenesis.core.DataTypeTransformer;
import io.polygenesis.core.TemplateData;
import io.polygenesis.models.api.ServiceMethod;
import io.polygenesis.models.periodicprocess.BatchProcessMetamodel;
import io.polygenesis.representations.code.ConstructorRepresentation;
import io.polygenesis.representations.code.FieldRepresentation;
import io.polygenesis.representations.code.MethodRepresentation;
import io.polygenesis.transformers.java.AbstractClassTransformer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The type Batch process command transformer.
 *
 * @author Christos Tsakostas
 */
public class BatchProcessCommandTransformer
    extends AbstractClassTransformer<BatchProcessMetamodel, ServiceMethod> {

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Batch process command transformer.
   *
   * @param dataTypeTransformer the data type transformer
   * @param methodTransformer the method transformer
   */
  public BatchProcessCommandTransformer(
      DataTypeTransformer dataTypeTransformer,
      BatchProcessCommandMethodTransformer methodTransformer) {
    super(dataTypeTransformer, methodTransformer);
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @SuppressWarnings("CPD-START")
  @Override
  public TemplateData transform(BatchProcessMetamodel source, Object... args) {
    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("representation", create(source, args));

    return new TemplateData(dataModel, "polygenesis-representation-java/Class.java.ftl");
  }

  @Override
  public Set<FieldRepresentation> stateFieldRepresentations(
      BatchProcessMetamodel source, Object... args) {
    Set<FieldRepresentation> fieldRepresentations = new LinkedHashSet<>();

    fieldRepresentations.add(
        FieldRepresentation.withModifiers(
            TextConverter.toUpperCamel(
                source.getCommandServiceMethod().getService().getServiceName().getText()),
            TextConverter.toLowerCamel(
                source.getCommandServiceMethod().getService().getServiceName().getText()),
            dataTypeTransformer.getModifierPrivate()));

    return fieldRepresentations;
  }

  @Override
  public Set<ConstructorRepresentation> constructorRepresentations(
      BatchProcessMetamodel source, Object... args) {
    Set<ConstructorRepresentation> constructorRepresentations = new LinkedHashSet<>();

    constructorRepresentations.add(
        createConstructorWithDirectAssignmentFromFieldRepresentations(
            simpleObjectName(source, args), stateFieldRepresentations(source, args)));

    return constructorRepresentations;
  }

  @Override
  public Set<MethodRepresentation> methodRepresentations(
      BatchProcessMetamodel source, Object... args) {
    Set<MethodRepresentation> methodRepresentations = new LinkedHashSet<>();

    methodRepresentations.add(methodTransformer.create(source.getCommandServiceMethod(), args));

    return methodRepresentations;
  }

  @Override
  public String packageName(BatchProcessMetamodel source, Object... args) {
    return source.getPackageName().getText();
  }

  @Override
  public Set<String> imports(BatchProcessMetamodel source, Object... args) {
    Set<String> imports = new TreeSet<>();

    imports.add("com.oregor.trinity4j.api.clients.batchprocess.BatchProcessCommandService");
    imports.add("org.springframework.stereotype.Service");

    imports.add(
        String.format(
            "%s.%s",
            source.getCommandServiceMethod().getService().getPackageName().getText(),
            TextConverter.toUpperCamel(
                source.getCommandServiceMethod().getService().getServiceName().getText())));

    return imports;
  }

  @Override
  public String description(BatchProcessMetamodel source, Object... args) {
    return String.format(
        "The %s Command.", TextConverter.toUpperCamelSpaces(source.getObjectName().getText()));
  }

  @Override
  public Set<String> annotations(BatchProcessMetamodel source, Object... args) {
    Set<String> annotations = new LinkedHashSet<>();

    annotations.add("@Service");

    return annotations;
  }

  @Override
  public String simpleObjectName(BatchProcessMetamodel source, Object... args) {
    return String.format("%sCommand", super.simpleObjectName(source, args));
  }

  @SuppressWarnings("CPD-END")
  @Override
  public String fullObjectName(BatchProcessMetamodel source, Object... args) {
    return String.format(
        "%s implements BatchProcessCommandService", simpleObjectName(source, args));
  }
}
