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

package io.polygenesis.generators.java.apidetail.service.activity.root;

import io.polygenesis.abstraction.data.Data;
import io.polygenesis.generators.java.common.AggregateRootData;
import io.polygenesis.models.api.Dto;
import io.polygenesis.models.domain.DomainObjectProperty;
import io.polygenesis.representations.code.ParameterRepresentation;
import java.util.Set;

/**
 * The type Update aggregate root template data.
 *
 * @author Christos Tsakostas
 */
public class UpdateAggregateRootTemplateData extends AbstractAggregateRootTemplateData {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private String stateMutationMethodName;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Update aggregate root template data.
   *
   * @param parameterRepresentations the parameter representations
   * @param aggregateRootDataType the aggregate root data type
   * @param aggregateRootVariable the aggregate root variable
   * @param properties the properties
   * @param persistenceVariable the persistence variable
   * @param requestDto the request dto
   * @param responseDto the response dto
   * @param converterVariable the converter variable
   * @param multiTenant the multi tenant
   * @param returnValue the return value
   * @param aggregateRootIdDataType the aggregate root id data type
   * @param thingIdentity the thing identity
   * @param stateMutationMethodName the state mutation method name
   */
  public UpdateAggregateRootTemplateData(
      AggregateRootData aggregateRootData,
      Set<ParameterRepresentation> parameterRepresentations,
      String aggregateRootDataType,
      String aggregateRootVariable,
      Set<DomainObjectProperty<?>> properties,
      String persistenceVariable,
      Dto requestDto,
      Dto responseDto,
      String converterVariable,
      Boolean multiTenant,
      String returnValue,
      String aggregateRootIdDataType,
      Data thingIdentity,
      String stateMutationMethodName) {
    super(
        aggregateRootData,
        parameterRepresentations,
        aggregateRootDataType,
        aggregateRootVariable,
        properties,
        persistenceVariable,
        requestDto,
        responseDto,
        converterVariable,
        multiTenant,
        returnValue,
        aggregateRootIdDataType,
        thingIdentity);
    this.stateMutationMethodName = stateMutationMethodName;
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets state mutation method name.
   *
   * @return the state mutation method name
   */
  public String getStateMutationMethodName() {
    return stateMutationMethodName;
  }

  @Override
  public String toString() {
    return "UpdateAggregateRootTemplateData{"
        + "getProperties='"
        + getProperties()
        + '\''
        + "stateMutationMethodName='"
        + stateMutationMethodName
        + '\''
        + '}';
  }
}
