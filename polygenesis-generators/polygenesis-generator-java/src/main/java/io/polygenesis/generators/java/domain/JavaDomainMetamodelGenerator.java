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

package io.polygenesis.generators.java.domain;

import io.polygenesis.commons.text.TextConverter;
import io.polygenesis.commons.valueobjects.ContextName;
import io.polygenesis.commons.valueobjects.ObjectName;
import io.polygenesis.commons.valueobjects.PackageName;
import io.polygenesis.core.AbstractMetamodelGenerator;
import io.polygenesis.core.CoreRegistry;
import io.polygenesis.core.ExportInfo;
import io.polygenesis.core.MetamodelRepository;
import io.polygenesis.generators.java.domain.aggregateentity.AggregateEntityExporter;
import io.polygenesis.generators.java.domain.aggregateentity.AggregateEntityIdExporter;
import io.polygenesis.generators.java.domain.aggregateroot.AggregateRootExporter;
import io.polygenesis.generators.java.domain.aggregateroot.AggregateRootIdExporter;
import io.polygenesis.generators.java.domain.domainevent.DomainEventExporter;
import io.polygenesis.generators.java.domain.domainmessage.data.DomainMessageData;
import io.polygenesis.generators.java.domain.domainmessage.data.DomainMessageDataGenerator;
import io.polygenesis.generators.java.domain.domainmessage.datarepository.DomainMessageDataRepository;
import io.polygenesis.generators.java.domain.domainmessage.datarepository.DomainMessageDataRepositoryGenerator;
import io.polygenesis.generators.java.domain.domainmessage.publisheddata.DomainMessagePublishedData;
import io.polygenesis.generators.java.domain.domainmessage.publisheddata.DomainMessagePublishedDataGenerator;
import io.polygenesis.generators.java.domain.domainmessage.publisheddatarepository.DomainMessagePublishedDataRepository;
import io.polygenesis.generators.java.domain.domainmessage.publisheddatarepository.DomainMessagePublishedDataRepositoryGenerator;
import io.polygenesis.generators.java.domain.persistence.PersistenceExporter;
import io.polygenesis.generators.java.domain.projection.exporter.ProjectionExporter;
import io.polygenesis.generators.java.domain.projection.exporter.ProjectionIdExporter;
import io.polygenesis.generators.java.domain.projection.exporter.ProjectionRepositoryExporter;
import io.polygenesis.generators.java.domain.service.DomainServiceGenerator;
import io.polygenesis.generators.java.domain.supportiveentity.SupportiveEntityExporter;
import io.polygenesis.generators.java.domain.valueobject.ValueObjectExporter;
import io.polygenesis.generators.java.shared.FolderFileConstants;
import io.polygenesis.models.domain.AggregateEntity;
import io.polygenesis.models.domain.AggregateEntityCollection;
import io.polygenesis.models.domain.AggregateRootPersistable;
import io.polygenesis.models.domain.BaseDomainEntity;
import io.polygenesis.models.domain.DomainMetamodelRepository;
import io.polygenesis.models.domain.DomainService;
import io.polygenesis.models.domain.DomainServiceRepository;
import io.polygenesis.models.domain.ProjectionMetamodelRepository;
import io.polygenesis.models.domain.PropertyType;
import io.polygenesis.models.domain.SupportiveEntityMetamodelRepository;
import io.polygenesis.models.domain.ValueObject;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

/**
 * The type Java domain generator.
 *
 * @author Christos Tsakostas
 */
public class JavaDomainMetamodelGenerator extends AbstractMetamodelGenerator {

  private final String tablePrefix;

  private final PackageName rootPackageName;
  private final ContextName contextName;

  private final AggregateRootExporter aggregateRootExporter;
  private final AggregateRootIdExporter aggregateRootIdExporter;
  private final AggregateEntityExporter aggregateEntityExporter;
  private final AggregateEntityIdExporter aggregateEntityIdExporter;
  private final ValueObjectExporter valueObjectExporter;
  private final DomainEventExporter domainEventExporter;
  private final PersistenceExporter persistenceExporter;
  private final DomainServiceGenerator domainServiceGenerator;
  private final SupportiveEntityExporter supportiveEntityExporter;
  private final ConstantsExporter constantsExporter;
  private final ProjectionExporter projectionExporter;
  private final ProjectionIdExporter projectionIdExporter;
  private final ProjectionRepositoryExporter projectionRepositoryExporter;

  private final DomainMessageDataGenerator domainMessageDataGenerator;
  private final DomainMessageDataRepositoryGenerator domainMessageDataRepositoryGenerator;
  private final DomainMessagePublishedDataGenerator domainMessagePublishedDataGenerator;
  private final DomainMessagePublishedDataRepositoryGenerator
      domainMessagePublishedDataRepositoryGenerator;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  public JavaDomainMetamodelGenerator(
      Path generationPath,
      String tablePrefix,
      PackageName rootPackageName,
      ContextName contextName,
      AggregateRootExporter aggregateRootExporter,
      AggregateRootIdExporter aggregateRootIdExporter,
      AggregateEntityExporter aggregateEntityExporter,
      AggregateEntityIdExporter aggregateEntityIdExporter,
      ValueObjectExporter valueObjectExporter,
      DomainEventExporter domainEventExporter,
      PersistenceExporter persistenceExporter,
      DomainServiceGenerator domainServiceGenerator,
      SupportiveEntityExporter supportiveEntityExporter,
      ConstantsExporter constantsExporter,
      ProjectionExporter projectionExporter,
      ProjectionIdExporter projectionIdExporter,
      ProjectionRepositoryExporter projectionRepositoryExporter,
      DomainMessageDataGenerator domainMessageDataGenerator,
      DomainMessageDataRepositoryGenerator domainMessageDataRepositoryGenerator,
      DomainMessagePublishedDataGenerator domainMessagePublishedDataGenerator,
      DomainMessagePublishedDataRepositoryGenerator domainMessagePublishedDataRepositoryGenerator) {
    super(generationPath);
    this.tablePrefix = tablePrefix;
    this.rootPackageName = rootPackageName;
    this.contextName = contextName;
    this.aggregateRootExporter = aggregateRootExporter;
    this.aggregateRootIdExporter = aggregateRootIdExporter;
    this.aggregateEntityExporter = aggregateEntityExporter;
    this.aggregateEntityIdExporter = aggregateEntityIdExporter;
    this.valueObjectExporter = valueObjectExporter;
    this.domainEventExporter = domainEventExporter;
    this.persistenceExporter = persistenceExporter;
    this.domainServiceGenerator = domainServiceGenerator;
    this.supportiveEntityExporter = supportiveEntityExporter;
    this.constantsExporter = constantsExporter;
    this.projectionExporter = projectionExporter;
    this.projectionIdExporter = projectionIdExporter;
    this.projectionRepositoryExporter = projectionRepositoryExporter;
    this.domainMessageDataGenerator = domainMessageDataGenerator;
    this.domainMessageDataRepositoryGenerator = domainMessageDataRepositoryGenerator;
    this.domainMessagePublishedDataGenerator = domainMessagePublishedDataGenerator;
    this.domainMessagePublishedDataRepositoryGenerator =
        domainMessagePublishedDataRepositoryGenerator;
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
   * Gets table prefix.
   *
   * @return the table prefix
   */
  public String getTablePrefix() {
    return tablePrefix;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @SuppressWarnings("rawtypes")
  @Override
  public void generate(Set<MetamodelRepository> modelRepositories) {
    CoreRegistry.getMetamodelRepositoryResolver()
        .resolve(modelRepositories, DomainMetamodelRepository.class)
        .getItems()
        .forEach(
            aggregateRoot -> {
              aggregateRootExporter.export(
                  getGenerationPath(), aggregateRoot, getRootPackageName());

              aggregateRootIdExporter.export(getGenerationPath(), aggregateRoot);

              if (aggregateRoot instanceof AggregateRootPersistable) {
                persistenceExporter.export(
                    getGenerationPath(),
                    ((AggregateRootPersistable) aggregateRoot).getPersistence());
              }

              domainEventExporter.export(getGenerationPath(), null);

              // Aggregate Entities
              aggregateRoot
                  .getProperties()
                  .stream()
                  .filter(
                      property -> property.getPropertyType().equals(PropertyType.AGGREGATE_ENTITY))
                  .forEach(
                      property -> {
                        AggregateEntity aggregateEntity = (AggregateEntity) property;
                        aggregateEntityIdExporter.export(getGenerationPath(), aggregateEntity);

                        aggregateEntityExporter.export(
                            getGenerationPath(),
                            (AggregateEntity) property,
                            getRootPackageName(),
                            aggregateRoot);
                      });

              // Aggregate Entities From Collections
              aggregateRoot
                  .getProperties()
                  .stream()
                  .filter(
                      property ->
                          property
                              .getPropertyType()
                              .equals(PropertyType.AGGREGATE_ENTITY_COLLECTION))
                  .forEach(
                      property -> {
                        AggregateEntity aggregateEntity =
                            ((AggregateEntityCollection) property).getAggregateEntity();
                        aggregateEntityIdExporter.export(getGenerationPath(), aggregateEntity);

                        aggregateEntityExporter.export(
                            getGenerationPath(),
                            aggregateEntity,
                            getRootPackageName(),
                            aggregateRoot);
                      });

              // Value Objects
              aggregateRoot
                  .getProperties()
                  .forEach(
                      property -> {
                        if (property.getPropertyType().equals(PropertyType.VALUE_OBJECT)) {
                          valueObjectExporter.export(getGenerationPath(), (ValueObject) property);
                        }
                      });
            });

    // DOMAIN SERVICES
    CoreRegistry.getMetamodelRepositoryResolver()
        .resolve(modelRepositories, DomainServiceRepository.class)
        .getItems()
        .forEach(
            domainService ->
                domainServiceGenerator.generate(
                    domainService, domainServiceExportInfo(getGenerationPath(), domainService)));

    // SUPPORTICE ENTITIES
    CoreRegistry.getMetamodelRepositoryResolver()
        .resolve(modelRepositories, SupportiveEntityMetamodelRepository.class)
        .getItems()
        .forEach(
            helperEntity ->
                supportiveEntityExporter.export(
                    getGenerationPath(), helperEntity, getRootPackageName()));

    constantsExporter.export(getGenerationPath(), getRootPackageName(), getTablePrefix());

    generateProjections(modelRepositories);

    // =============================================================================================
    // DOMAIN MESSAGE

    DomainMessageData domainMessageData = makeDomainMessageData();
    domainMessageDataGenerator.generate(
        domainMessageData,
        domainMessageDataExportInfo(getGenerationPath(), domainMessageData),
        contextName);

    DomainMessageDataRepository domainMessageDataRepository = makeDomainMessageDataRepository();
    domainMessageDataRepositoryGenerator.generate(
        domainMessageDataRepository,
        domainMessageDataRepositoryExportInfo(getGenerationPath(), domainMessageDataRepository),
        contextName);

    DomainMessagePublishedData domainMessagePublishedData = makeDomainMessagePublishedData();
    domainMessagePublishedDataGenerator.generate(
        domainMessagePublishedData,
        domainMessagePublishedDataExportInfo(getGenerationPath(), domainMessagePublishedData),
        contextName);

    DomainMessagePublishedDataRepository domainMessagePublishedDataRepository =
        makeDomainMessagePublishedDataRepository();
    domainMessagePublishedDataRepositoryGenerator.generate(
        domainMessagePublishedDataRepository,
        domainMessagePublishedDataRepositoryExportInfo(
            getGenerationPath(), domainMessagePublishedDataRepository),
        contextName);
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

  @SuppressWarnings("rawtypes")
  private void generateProjections(Set<MetamodelRepository> modelRepositories) {
    CoreRegistry.getMetamodelRepositoryResolver()
        .resolve(modelRepositories, ProjectionMetamodelRepository.class)
        .getItems()
        .forEach(
            projection -> {
              projectionIdExporter.export(getGenerationPath(), projection);
              projectionExporter.export(getGenerationPath(), projection, getRootPackageName());
              if (projection.getPersistence() != null) {
                projectionRepositoryExporter.export(
                    getGenerationPath(), projection.getPersistence());
              }
              exportValueObjects(projection);
            });
  }

  private void exportValueObjects(BaseDomainEntity baseDomainEntity) {
    baseDomainEntity
        .getProperties()
        .forEach(
            property -> {
              if (property.getPropertyType().equals(PropertyType.VALUE_OBJECT)) {
                valueObjectExporter.export(getGenerationPath(), (ValueObject) property);
              }
            });
  }

  private ExportInfo domainServiceExportInfo(Path generationPath, DomainService domainService) {
    return ExportInfo.file(
        Paths.get(
            generationPath.toString(),
            FolderFileConstants.SRC,
            FolderFileConstants.MAIN,
            FolderFileConstants.JAVA,
            domainService.getPackageName().toPath().toString()),
        TextConverter.toUpperCamel(domainService.getObjectName().getText())
            + FolderFileConstants.JAVA_POSTFIX);
  }

  // ===============================================================================================
  // PRIVATE MESSAGE DATA
  // ===============================================================================================

  private DomainMessageData makeDomainMessageData() {
    return new DomainMessageData(
        new ObjectName(
            String.format(
                "%sDomainMessageData", TextConverter.toUpperCamel(contextName.getText()))),
        new PackageName(String.format("%s", rootPackageName.getText())));
  }

  private DomainMessageDataRepository makeDomainMessageDataRepository() {
    return new DomainMessageDataRepository(
        new ObjectName(
            String.format(
                "%sDomainMessageDataRepository",
                TextConverter.toUpperCamel(contextName.getText()))),
        new PackageName(String.format("%s", rootPackageName.getText())));
  }

  private DomainMessagePublishedData makeDomainMessagePublishedData() {
    return new DomainMessagePublishedData(
        new ObjectName(
            String.format(
                "%sDomainMessagePublishedData", TextConverter.toUpperCamel(contextName.getText()))),
        new PackageName(String.format("%s", rootPackageName.getText())));
  }

  private DomainMessagePublishedDataRepository makeDomainMessagePublishedDataRepository() {
    return new DomainMessagePublishedDataRepository(
        new ObjectName(
            String.format(
                "%sDomainMessagePublishedDataRepository",
                TextConverter.toUpperCamel(contextName.getText()))),
        new PackageName(String.format("%s", rootPackageName.getText())));
  }

  private ExportInfo domainMessageDataExportInfo(
      Path generationPath, DomainMessageData domainMessageData) {
    return ExportInfo.file(
        Paths.get(
            generationPath.toString(),
            FolderFileConstants.SRC,
            FolderFileConstants.MAIN,
            FolderFileConstants.JAVA,
            domainMessageData.getPackageName().toPath().toString()),
        String.format(
            "%s%s",
            TextConverter.toUpperCamel(domainMessageData.getObjectName().getText()),
            FolderFileConstants.JAVA_POSTFIX));
  }

  private ExportInfo domainMessageDataRepositoryExportInfo(
      Path generationPath, DomainMessageDataRepository domainMessageDataRepository) {
    return ExportInfo.file(
        Paths.get(
            generationPath.toString(),
            FolderFileConstants.SRC,
            FolderFileConstants.MAIN,
            FolderFileConstants.JAVA,
            domainMessageDataRepository.getPackageName().toPath().toString()),
        String.format(
            "%s%s",
            TextConverter.toUpperCamel(domainMessageDataRepository.getObjectName().getText()),
            FolderFileConstants.JAVA_POSTFIX));
  }

  private ExportInfo domainMessagePublishedDataExportInfo(
      Path generationPath, DomainMessagePublishedData domainMessagePublishedData) {
    return ExportInfo.file(
        Paths.get(
            generationPath.toString(),
            FolderFileConstants.SRC,
            FolderFileConstants.MAIN,
            FolderFileConstants.JAVA,
            domainMessagePublishedData.getPackageName().toPath().toString()),
        String.format(
            "%s%s",
            TextConverter.toUpperCamel(domainMessagePublishedData.getObjectName().getText()),
            FolderFileConstants.JAVA_POSTFIX));
  }

  private ExportInfo domainMessagePublishedDataRepositoryExportInfo(
      Path generationPath,
      DomainMessagePublishedDataRepository domainMessagePublishedDataRepository) {
    return ExportInfo.file(
        Paths.get(
            generationPath.toString(),
            FolderFileConstants.SRC,
            FolderFileConstants.MAIN,
            FolderFileConstants.JAVA,
            domainMessagePublishedDataRepository.getPackageName().toPath().toString()),
        String.format(
            "%s%s",
            TextConverter.toUpperCamel(
                domainMessagePublishedDataRepository.getObjectName().getText()),
            FolderFileConstants.JAVA_POSTFIX));
  }
}
