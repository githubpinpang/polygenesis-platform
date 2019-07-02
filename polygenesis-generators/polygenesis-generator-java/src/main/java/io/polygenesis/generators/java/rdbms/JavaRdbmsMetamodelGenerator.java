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

package io.polygenesis.generators.java.rdbms;

import io.polygenesis.commons.valueobjects.ObjectName;
import io.polygenesis.commons.valueobjects.PackageName;
import io.polygenesis.core.AbstractMetamodelGenerator;
import io.polygenesis.core.CoreRegistry;
import io.polygenesis.core.MetamodelRepository;
import io.polygenesis.generators.java.rdbms.domainmessagedata.DomainMessageDataExporter;
import io.polygenesis.generators.java.rdbms.domainmessagedataconverter.DomainMessageDataConverterExporter;
import io.polygenesis.generators.java.rdbms.repositoryimpl.PersistenceImplExporter;
import io.polygenesis.generators.java.rdbms.springdomainmessagedatarepository.DomainMessageDataRepositoryExporter;
import io.polygenesis.generators.java.rdbms.projection.ProjectionRepositoryImplExporter;
import io.polygenesis.generators.java.rdbms.projection.ProjectionSpringDataRepositoryExporter;
import io.polygenesis.generators.java.rdbms.projection.testing.ProjectionRepositoryImplTestExporter;
import io.polygenesis.generators.java.rdbms.testing.PersistenceImplTestExporter;
import io.polygenesis.models.domain.AggregateRootPersistable;
import io.polygenesis.models.domain.DomainMetamodelRepository;
import io.polygenesis.models.domain.ProjectionMetamodelRepository;
import java.nio.file.Path;
import java.util.Set;

/**
 * The type Java domain generator.
 *
 * @author Christos Tsakostas
 */
public class JavaRdbmsMetamodelGenerator extends AbstractMetamodelGenerator {

  private final PackageName rootPackageName;
  private final ObjectName contextName;
  private final DomainMessageDataExporter domainMessageDataExporter;
  private final DomainMessageDataConverterExporter domainMessageDataConverterExporter;
  private final DomainMessageDataRepositoryExporter domainMessageDataRepositoryExporter;
  private final PersistenceImplExporter persistenceImplExporter;
  private final PersistenceImplTestExporter persistenceImplTestExporter;
  private final SpringDataRepositoryExporter springDataRepositoryExporter;
  private final RdbmsTestExporter rdbmsTestExporter;
  private final RdbmsTestConfigExporter rdbmsTestConfigExporter;
  private final ApplicationCiRdbmsYmlExporter applicationCiRdbmsYmlExporter;
  private final ProjectionRepositoryImplExporter projectionRepositoryImplExporter;
  private final ProjectionSpringDataRepositoryExporter projectionSpringDataRepositoryExporter;
  private final ProjectionRepositoryImplTestExporter projectionRepositoryImplTestExporter;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Java rdbms generator.
   *
   * @param generationPath the generation path
   * @param rootPackageName the root package name
   * @param contextName the context name
   * @param domainMessageDataExporter the domain message data exporter
   * @param domainMessageDataConverterExporter the domain message data converter exporter
   * @param domainMessageDataRepositoryExporter the domain message data repository exporter
   * @param persistenceImplExporter the persistence impl exporter
   * @param persistenceImplTestExporter the persistence impl test exporter
   * @param springDataRepositoryExporter the spring data repository exporter
   * @param rdbmsTestExporter the rdbms test exporter
   * @param rdbmsTestConfigExporter the rdbms test config exporter
   * @param applicationCiRdbmsYmlExporter the application ci rdbms yml exporter
   * @param projectionRepositoryImplExporter the projection repository impl exporter
   * @param projectionSpringDataRepositoryExporter the projection spring data repository exporter
   * @param projectionRepositoryImplTestExporter the projection repository impl test exporter
   */
  public JavaRdbmsMetamodelGenerator(
      Path generationPath,
      PackageName rootPackageName,
      ObjectName contextName,
      DomainMessageDataExporter domainMessageDataExporter,
      DomainMessageDataConverterExporter domainMessageDataConverterExporter,
      DomainMessageDataRepositoryExporter domainMessageDataRepositoryExporter,
      PersistenceImplExporter persistenceImplExporter,
      PersistenceImplTestExporter persistenceImplTestExporter,
      SpringDataRepositoryExporter springDataRepositoryExporter,
      RdbmsTestExporter rdbmsTestExporter,
      RdbmsTestConfigExporter rdbmsTestConfigExporter,
      ApplicationCiRdbmsYmlExporter applicationCiRdbmsYmlExporter,
      ProjectionRepositoryImplExporter projectionRepositoryImplExporter,
      ProjectionSpringDataRepositoryExporter projectionSpringDataRepositoryExporter,
      ProjectionRepositoryImplTestExporter projectionRepositoryImplTestExporter) {
    super(generationPath);
    this.rootPackageName = rootPackageName;
    this.contextName = contextName;
    this.domainMessageDataExporter = domainMessageDataExporter;
    this.domainMessageDataConverterExporter = domainMessageDataConverterExporter;
    this.domainMessageDataRepositoryExporter = domainMessageDataRepositoryExporter;
    this.persistenceImplExporter = persistenceImplExporter;
    this.persistenceImplTestExporter = persistenceImplTestExporter;
    this.springDataRepositoryExporter = springDataRepositoryExporter;
    this.rdbmsTestExporter = rdbmsTestExporter;
    this.rdbmsTestConfigExporter = rdbmsTestConfigExporter;
    this.applicationCiRdbmsYmlExporter = applicationCiRdbmsYmlExporter;
    this.projectionRepositoryImplExporter = projectionRepositoryImplExporter;
    this.projectionSpringDataRepositoryExporter = projectionSpringDataRepositoryExporter;
    this.projectionRepositoryImplTestExporter = projectionRepositoryImplTestExporter;
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets root package name.
   *
   * @return the root package name
   */
  public PackageName getRootPackageName() {
    return rootPackageName;
  }

  /**
   * Gets context name.
   *
   * @return the context name
   */
  public ObjectName getContextName() {
    return contextName;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @SuppressWarnings("rawtypes")
  @Override
  public void generate(Set<MetamodelRepository> modelRepositories) {
    domainMessageDataExporter.export(getGenerationPath(), getRootPackageName(), getContextName());
    domainMessageDataConverterExporter.export(
        getGenerationPath(), getRootPackageName(), getContextName());
    domainMessageDataRepositoryExporter.export(
        getGenerationPath(), getRootPackageName(), getContextName());
    rdbmsTestExporter.export(getGenerationPath(), getRootPackageName());
    rdbmsTestConfigExporter.export(getGenerationPath(), getRootPackageName());
    applicationCiRdbmsYmlExporter.export(getGenerationPath());

    aggregateRoots(modelRepositories);
    projections(modelRepositories);
  }

  @SuppressWarnings("rawtypes")
  private void aggregateRoots(Set<MetamodelRepository> modelRepositories) {
    CoreRegistry.getMetamodelRepositoryResolver()
        .resolve(modelRepositories, DomainMetamodelRepository.class)
        .getItems()
        .stream()
        .filter(aggregateRoot -> aggregateRoot instanceof AggregateRootPersistable)
        .map(AggregateRootPersistable.class::cast)
        .forEach(
            aggregateRoot -> {
              persistenceImplExporter.export(
                  getGenerationPath(),
                  aggregateRoot.getPersistence(),
                  getRootPackageName(),
                  getContextName());

              persistenceImplTestExporter.export(
                  getGenerationPath(),
                  aggregateRoot.getPersistence(),
                  getRootPackageName(),
                  getContextName());

              springDataRepositoryExporter.export(
                  getGenerationPath(), aggregateRoot.getPersistence());
            });
  }

  @SuppressWarnings("rawtypes")
  private void projections(Set<MetamodelRepository> modelRepositories) {
    CoreRegistry.getMetamodelRepositoryResolver()
        .resolve(modelRepositories, ProjectionMetamodelRepository.class)
        .getItems()
        .stream()
        .forEach(
            projection -> {
              projectionRepositoryImplExporter.export(
                  getGenerationPath(),
                  projection.getPersistence(),
                  getRootPackageName(),
                  getContextName());

              projectionRepositoryImplTestExporter.export(
                  getGenerationPath(),
                  projection.getPersistence(),
                  getRootPackageName(),
                  getContextName());

              projectionSpringDataRepositoryExporter.export(
                  getGenerationPath(), projection.getPersistence());
            });
  }
}