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

package io.polygenesis.models.api;

import io.polygenesis.core.datatype.PackageName;

/**
 * The type Rest deducer factory.
 *
 * @author Christos Tsakostas
 */
public final class RestDeducerFactory {

  // ===============================================================================================
  // DEPENDENCIES
  // ===============================================================================================
  private static final ResourceDeducer resourceDeducer;

  // ===============================================================================================
  // STATIC INITIALIZATION OF DEPENDENCIES
  // ===============================================================================================

  static {
    resourceDeducer = new ResourceDeducer();
  }

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================
  private RestDeducerFactory() {
    throw new IllegalStateException("Utility class");
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * New instance domain deducer.
   *
   * @param packageName the package name
   * @return the api deducer
   */
  public static RestDeducerImpl newInstance(PackageName packageName) {
    return new RestDeducerImpl(packageName, resourceDeducer);
  }
}