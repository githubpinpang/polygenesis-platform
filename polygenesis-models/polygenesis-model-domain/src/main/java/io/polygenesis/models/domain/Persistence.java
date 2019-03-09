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

import io.polygenesis.commons.text.Name;
import io.polygenesis.core.data.PackageName;

/**
 * The type Persistence.
 *
 * @author Christos Tsakostas
 */
public class Persistence {

  private PackageName packageName;
  private Name name;
  private Name aggregateRootName;
  private Name aggregateRootIdName;
  private Boolean multiTenant;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Persistence.
   *
   * @param packageName the package name
   * @param name the name
   * @param aggregateRootName the aggregate root name
   * @param aggregateRootIdName the aggregate root id name
   * @param multiTenant the multi tenant
   */
  public Persistence(
      PackageName packageName,
      Name name,
      Name aggregateRootName,
      Name aggregateRootIdName,
      Boolean multiTenant) {
    setPackageName(packageName);
    setName(name);
    setAggregateRootName(aggregateRootName);
    setAggregateRootIdName(aggregateRootIdName);
    setMultiTenant(multiTenant);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets package name.
   *
   * @return the package name
   */
  public PackageName getPackageName() {
    return packageName;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public Name getName() {
    return name;
  }

  /**
   * Gets aggregate root name.
   *
   * @return the aggregate root name
   */
  public Name getAggregateRootName() {
    return aggregateRootName;
  }

  /**
   * Gets aggregate root id name.
   *
   * @return the aggregate root id name
   */
  public Name getAggregateRootIdName() {
    return aggregateRootIdName;
  }

  /**
   * Gets multi tenant.
   *
   * @return the multi tenant
   */
  public Boolean getMultiTenant() {
    return multiTenant;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets package name.
   *
   * @param packageName the package name
   */
  private void setPackageName(PackageName packageName) {
    this.packageName = packageName;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  private void setName(Name name) {
    this.name = name;
  }

  /**
   * Sets aggregate root name.
   *
   * @param aggregateRootName the aggregate root name
   */
  private void setAggregateRootName(Name aggregateRootName) {
    this.aggregateRootName = aggregateRootName;
  }

  /**
   * Sets aggregate root id name.
   *
   * @param aggregateRootIdName the aggregate root id name
   */
  private void setAggregateRootIdName(Name aggregateRootIdName) {
    this.aggregateRootIdName = aggregateRootIdName;
  }

  /**
   * Sets multi tenant.
   *
   * @param multiTenant the multi tenant
   */
  private void setMultiTenant(Boolean multiTenant) {
    this.multiTenant = multiTenant;
  }
}
