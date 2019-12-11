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

package com.invoiceful.genesis.contexts.trinity4j;

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
 * The type Trinity4J shared.
 *
 * @author Christos Tsakostas
 */
public class Trinity4jShared {

  /**
   * Tenant id data object.
   *
   * @return the data object
   */
  public static DataObject tenantId() {
    Set<Data> data = new LinkedHashSet<>();

    return new DataObject(
        new VariableName("typeId"),
        DataPurpose.tenantIdentity(),
        DataValidator.empty(),
        new ObjectName("tenantId"),
        new PackageName("com.oregor.trinity4j.domain"),
        data,
        DataSourceType.EXTERNALLY_PROVIDED);
  }
}
