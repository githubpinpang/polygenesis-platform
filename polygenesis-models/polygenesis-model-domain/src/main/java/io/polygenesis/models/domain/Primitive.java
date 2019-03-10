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

package io.polygenesis.models.domain;

import io.polygenesis.core.data.Data;
import io.polygenesis.core.data.DataPrimitive;
import io.polygenesis.core.data.VariableName;

/**
 * The type Primitive.
 *
 * @author Christos Tsakostas
 */
public class Primitive extends AbstractProperty {

  private DataPrimitive dataPrimitive;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Primitive.
   *
   * @param dataPrimitive the data primitive
   * @param variableName the variable name
   */
  public Primitive(DataPrimitive dataPrimitive, VariableName variableName) {
    super(PropertyType.PRIMITIVE, variableName);
    setDataPrimitive(dataPrimitive);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets data primitive.
   *
   * @return the data primitive
   */
  public DataPrimitive getDataPrimitive() {
    return dataPrimitive;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets data primitive.
   *
   * @param dataPrimitive the data primitive
   */
  private void setDataPrimitive(DataPrimitive dataPrimitive) {
    this.dataPrimitive = dataPrimitive;
  }

  // ===============================================================================================
  // ABSTRACT IMPLEMENTATIONS
  // ===============================================================================================

  @Override
  public Data getData() {
    return dataPrimitive;
  }

  @Override
  public Data getTypeParameterData() {
    throw new UnsupportedOperationException();
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================
}
