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

package io.polygenesis.models.domain;

import io.polygenesis.abstraction.data.Data;
import io.polygenesis.abstraction.data.DataObject;
import io.polygenesis.abstraction.data.DataPurpose;
import io.polygenesis.abstraction.data.DataSourceType;
import io.polygenesis.abstraction.data.DataValidator;
import io.polygenesis.commons.valueobjects.ObjectName;
import io.polygenesis.commons.valueobjects.PackageName;
import io.polygenesis.commons.valueobjects.VariableName;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Aggregate root.
 *
 * @author Christos Tsakostas
 */
public class AggregateRoot extends DomainObject implements DomainObjectProperty<DataObject> {

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Aggregate root.
   *
   * @param instantiationType the instantiation type
   * @param objectName the object name
   * @param packageName the package name
   * @param multiTenant the multi tenant
   */
  public AggregateRoot(
      InstantiationType instantiationType,
      ObjectName objectName,
      PackageName packageName,
      Boolean multiTenant) {
    super(DomainObjectType.AGGREGATE_ROOT, instantiationType, objectName, packageName, multiTenant);
  }

  // ===============================================================================================
  // IMPLEMENTATIONS
  // ===============================================================================================

  @SuppressWarnings("CPD-START")
  @Override
  public PropertyType getPropertyType() {
    return PropertyType.REFERENCE_TO_AGGREGATE_ROOT;
  }

  @Override
  public DataObject getData() {
    Set<Data> models = new LinkedHashSet<>();

    return new DataObject(
        new VariableName(getObjectName().getText()),
        DataPurpose.any(),
        DataValidator.empty(),
        getObjectName(),
        getPackageName(),
        models,
        DataSourceType.DEFAULT);
  }

  @Override
  public Data getTypeParameterData() {
    throw new UnsupportedOperationException();
  }
}
