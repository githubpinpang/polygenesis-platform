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
import io.polygenesis.abstraction.data.DataEnumeration;
import io.polygenesis.commons.valueobjects.ObjectName;
import io.polygenesis.commons.valueobjects.PackageName;
import io.polygenesis.core.Generatable;
import io.polygenesis.core.Nameable;
import io.polygenesis.core.Packageable;

/**
 * The type Enumeration.
 *
 * @author Christos Tsakostas
 */
public class Enumeration extends BaseProperty<DataEnumeration>
    implements Generatable, Nameable, Packageable {

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Enumeration.
   *
   * @param data the data
   */
  public Enumeration(DataEnumeration data) {
    super(PropertyType.ENUMERATION, data);
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public Data getTypeParameterData() {
    throw new UnsupportedOperationException();
  }

  @Override
  public ObjectName getObjectName() {
    return getData().getObjectName();
  }

  @Override
  public PackageName getPackageName() {
    return getData().getPackageName();
  }
}