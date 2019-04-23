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

package io.polygenesis.deducers.sql;

import io.polygenesis.commons.text.TextConverter;
import io.polygenesis.core.data.Data;
import io.polygenesis.core.data.DataGroup;
import io.polygenesis.core.data.DataPrimitive;
import io.polygenesis.core.data.PrimitiveType;
import io.polygenesis.core.data.VariableName;
import io.polygenesis.models.domain.AggregateEntityCollection;
import io.polygenesis.models.domain.AggregateRoot;
import io.polygenesis.models.domain.BaseDomainObject;
import io.polygenesis.models.domain.DomainObjectProperty;
import io.polygenesis.models.sql.Column;
import io.polygenesis.models.sql.ColumnDataType;
import io.polygenesis.models.sql.RequiredType;
import io.polygenesis.models.sql.Table;
import io.polygenesis.models.sql.TableName;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Table deducer.
 *
 * @author Christos Tsakostas
 */
public class TableDeducer {

  // ===============================================================================================
  // DEPENDENCIES
  // ===============================================================================================

  private final FromDataTypeToSqlColumnConverter fromDataTypeToSqlColumnConverter;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Table deducer.
   *
   * @param fromDataTypeToSqlColumnConverter the from data type to sql column converter
   */
  public TableDeducer(FromDataTypeToSqlColumnConverter fromDataTypeToSqlColumnConverter) {
    this.fromDataTypeToSqlColumnConverter = fromDataTypeToSqlColumnConverter;
  }

  // ===============================================================================================
  // FUNCTIONALITY
  // ===============================================================================================

  /**
   * Deduce set.
   *
   * @param aggregateRoot the aggregate root
   * @return the set
   */
  public Set<Table> deduce(AggregateRoot aggregateRoot) {
    Set<Table> allAggregateRootRelatedTables = new LinkedHashSet<>();

    Set<Column> aggregateRootColumns = new LinkedHashSet<>();

    addAggregateRootIdInColumnSetAsPrimaryKey(aggregateRootColumns, aggregateRoot);

    aggregateRoot
        .getProperties()
        .forEach(
            property -> {
              switch (property.getPropertyType()) {
                case AGGREGATE_ROOT_ID:
                  break;
                case PRIMITIVE:
                  aggregateRootColumns.add(getColumnForPrimitive(property.getData(), ""));
                  break;
                case PRIMITIVE_COLLECTION:
                  allAggregateRootRelatedTables.add(
                      getTableForPrimitiveCollection(aggregateRoot, property));
                  break;
                case VALUE_OBJECT:
                  aggregateRootColumns.addAll(
                      getColumnsForValueObject(property.getData().getAsDataGroup()));
                  break;
                case VALUE_OBJECT_COLLECTION:
                  allAggregateRootRelatedTables.add(
                      getTableForValueObjectCollection(aggregateRoot, property));
                  break;
                case AGGREGATE_ENTITY:
                  aggregateRootColumns.addAll(
                      getColumnsForValueObject(property.getData().getAsDataGroup()));
                  break;
                case AGGREGATE_ENTITY_COLLECTION:
                  AggregateEntityCollection aggregateEntityCollection =
                      (AggregateEntityCollection) property;
                  allAggregateRootRelatedTables.add(
                      getTableForAggregateEntityCollection(
                          aggregateRoot, aggregateEntityCollection.getAggregateEntity(), property));
                  break;
                default:
                  throw new IllegalArgumentException(
                      String.format(
                          "Cannot convert to SQL column property=%s with type=%s",
                          property.getData().getVariableName().getText(),
                          property.getPropertyType().name()));
              }
            });

    // Add version
    aggregateRootColumns.add(
        new Column("version", ColumnDataType.INTEGER, 11, RequiredType.REQUIRED));

    Table mainAggregateRootTable =
        new Table(
            new TableName(aggregateRoot.getObjectName().getText()),
            aggregateRootColumns,
            aggregateRoot.getMultiTenant());

    allAggregateRootRelatedTables.add(mainAggregateRootTable);

    return allAggregateRootRelatedTables;
  }

  /**
   * Gets columns by properties.
   *
   * @param properties the properties
   * @return the columns by properties
   */
  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================
  protected Set<Column> getColumnsByProperties(
      Set<DomainObjectProperty<? extends Data>> properties) {
    Set<Column> columns = new LinkedHashSet<>();

    properties.forEach(
        property -> {
          switch (property.getPropertyType()) {
            case AGGREGATE_ROOT_ID:
            case REFERENCE_TO_AGGREGATE_ROOT:
              break;
            case PRIMITIVE:
              columns.add(getColumnForPrimitive(property.getData(), ""));
              break;
            case REFERENCE:
              columns.add(getColumnForReferenceToThing(""));
              break;
            case PRIMITIVE_COLLECTION:
              break;
            case VALUE_OBJECT:
              columns.addAll(getColumnsForValueObject(property.getData().getAsDataGroup()));
              break;
            case VALUE_OBJECT_COLLECTION:
              break;
            case AGGREGATE_ENTITY:
              columns.addAll(getColumnsForValueObject(property.getData().getAsDataGroup()));
              break;
            case AGGREGATE_ENTITY_COLLECTION:
              break;
            default:
              throw new IllegalArgumentException(
                  String.format(
                      "Cannot convert to SQL column property=%s with type=%s",
                      property.getData().getVariableName().getText(),
                      property.getPropertyType().name()));
          }
        });

    return columns;
  }

  /**
   * Gets tables by properties.
   *
   * @param baseDomainObject the base domain object
   * @return the tables by properties
   */
  protected Set<Table> getTablesByProperties(BaseDomainObject<?> baseDomainObject) {
    Set<Table> tables = new LinkedHashSet<>();

    baseDomainObject
        .getProperties()
        .forEach(
            property -> {
              switch (property.getPropertyType()) {
                case AGGREGATE_ROOT_ID:
                  break;
                case PRIMITIVE:
                  break;
                case PRIMITIVE_COLLECTION:
                  tables.add(getTableForPrimitiveCollection(baseDomainObject, property));
                  break;
                case VALUE_OBJECT:
                  break;
                case VALUE_OBJECT_COLLECTION:
                  tables.add(getTableForValueObjectCollection(baseDomainObject, property));
                  break;
                case AGGREGATE_ENTITY:
                  break;
                case AGGREGATE_ENTITY_COLLECTION:
                  AggregateEntityCollection aggregateEntityCollection =
                      (AggregateEntityCollection) property;
                  tables.add(
                      getTableForAggregateEntityCollection(
                          baseDomainObject,
                          aggregateEntityCollection.getAggregateEntity(),
                          property));
                  break;
                default:
                  throw new IllegalArgumentException(
                      String.format(
                          "Cannot convert to SQL column property=%s with type=%s",
                          property.getData().getVariableName().getText(),
                          property.getPropertyType().name()));
              }
            });

    return tables;
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

  /**
   * Gets column for reference to thing.
   *
   * @param columnPrefix the column prefix
   * @return the column for reference to thing
   */
  private Column getColumnForReferenceToThing(String columnPrefix) {
    // TODO
    DataPrimitive dataPrimitive = DataPrimitive.of(PrimitiveType.STRING, new VariableName("ref"));
    return getColumnForPrimitive(dataPrimitive, columnPrefix);
  }

  /**
   * Gets column for primitive.
   *
   * @param data the data
   * @return the column for primitive
   */
  private Column getColumnForPrimitive(Data data, String columnPrefix) {
    ColumnDataType columnDataType = fromDataTypeToSqlColumnConverter.getColumnDataTypeBy(data);

    int length = 100;

    if (columnDataType.equals(ColumnDataType.BIT)) {
      length = 1;
    }

    return new Column(
        String.format("%s%s", columnPrefix, data.getVariableName().getText()),
        columnDataType,
        length,
        RequiredType.OPTIONAL);
  }

  /**
   * Gets columns for value object.
   *
   * @param dataGroup the data group
   * @return the columns for value object
   */
  private Set<Column> getColumnsForValueObject(DataGroup dataGroup) {
    Set<Column> columns = new LinkedHashSet<>();

    dataGroup
        .getModels()
        .forEach(
            model -> {
              if (model.isDataPrimitive()) {
                columns.add(
                    getColumnForPrimitive(
                        model,
                        String.format(
                            "%s_",
                            TextConverter.toLowerUnderscore(
                                dataGroup.getVariableName().getText()))));
              } else {
                throw new IllegalStateException();
              }
            });

    return columns;
  }

  /**
   * Gets table for primitive collection.
   *
   * @param baseDomainObject the aggregate root
   * @param property the property
   * @return the table for primitive collection
   */
  private Table getTableForPrimitiveCollection(
      BaseDomainObject<?> baseDomainObject, DomainObjectProperty<? extends Data> property) {
    Set<Column> columns = new LinkedHashSet<>();

    addAggregateRootIdInColumnSetWithoutPrimaryKey(columns, baseDomainObject);

    columns.add(getColumnForPrimitive(property.getTypeParameterData(), ""));

    return new Table(
        new TableName(
            String.format(
                "%s_%s",
                TextConverter.toLowerUnderscore(baseDomainObject.getObjectName().getText()),
                TextConverter.toLowerUnderscore(property.getData().getVariableName().getText()))),
        columns,
        baseDomainObject.getMultiTenant());
  }

  /**
   * Gets table for value object collection.
   *
   * @param baseDomainObject the aggregate root
   * @param property the property
   * @return the table for value object collection
   */
  private Table getTableForValueObjectCollection(
      BaseDomainObject<?> baseDomainObject, DomainObjectProperty<? extends Data> property) {
    Set<Column> columns = new LinkedHashSet<>();

    return new Table(
        new TableName(
            String.format(
                "%s_%s",
                TextConverter.toLowerUnderscore(baseDomainObject.getObjectName().getText()),
                TextConverter.toLowerUnderscore(property.getData().getVariableName().getText()))),
        columns,
        baseDomainObject.getMultiTenant());
  }

  /**
   * Gets table for aggregate entity collection.
   *
   * @param baseDomainObjectChild the aggregate root
   * @param property the property
   * @return the table for aggregate entity collection
   */
  private Table getTableForAggregateEntityCollection(
      BaseDomainObject<?> baseDomainObjectParent,
      BaseDomainObject<?> baseDomainObjectChild,
      DomainObjectProperty<? extends Data> property) {
    Set<Column> columns = new LinkedHashSet<>();

    addAggregateRootIdInColumnSetAsPrimaryKey(columns, baseDomainObjectParent);
    addAggregateEntityIdInColumnSet(columns);

    columns.addAll(getColumnsByProperties(baseDomainObjectChild.getProperties()));

    // Add version
    columns.add(new Column("version", ColumnDataType.INTEGER, 11, RequiredType.REQUIRED));

    return new Table(
        new TableName(
            String.format(
                "%s_%s",
                TextConverter.toLowerUnderscore(baseDomainObjectParent.getObjectName().getText()),
                TextConverter.toLowerUnderscore(property.getData().getVariableName().getText()))),
        columns,
        baseDomainObjectChild.getMultiTenant());
  }

  /**
   * Add aggregate root id in column set as primary key.
   *
   * @param columns the columns
   * @param baseDomainObject the aggregate root
   */
  private void addAggregateRootIdInColumnSetAsPrimaryKey(
      Set<Column> columns, BaseDomainObject<?> baseDomainObject) {
    addAggregateRootIdInColumnSet(columns, baseDomainObject, true);
  }

  /**
   * Add aggregate root id in column set without primary key.
   *
   * @param columns the columns
   * @param baseDomainObject the aggregate root
   */
  private void addAggregateRootIdInColumnSetWithoutPrimaryKey(
      Set<Column> columns, BaseDomainObject<?> baseDomainObject) {
    addAggregateRootIdInColumnSet(columns, baseDomainObject, false);
  }

  /**
   * Add aggregate root id in column set.
   *
   * @param columns the columns
   * @param baseDomainObject the aggregate root
   * @param addAsPrimaryKey the add as primary key
   */
  private void addAggregateRootIdInColumnSet(
      Set<Column> columns, BaseDomainObject<?> baseDomainObject, boolean addAsPrimaryKey) {
    // Add Object Id
    columns.add(
        new Column("root_id", ColumnDataType.BINARY, 16, RequiredType.REQUIRED, addAsPrimaryKey));

    if (baseDomainObject.getMultiTenant()) {
      // Add Tenant Id
      columns.add(
          new Column(
              "tenant_id", ColumnDataType.BINARY, 16, RequiredType.REQUIRED, addAsPrimaryKey));
    }
  }

  /**
   * Add aggregate enity id in column set.
   *
   * @param columns the columns
   */
  private void addAggregateEntityIdInColumnSet(Set<Column> columns) {
    // Add Entity Id
    columns.add(new Column("entity_id", ColumnDataType.BINARY, 16, RequiredType.REQUIRED, true));
  }
}