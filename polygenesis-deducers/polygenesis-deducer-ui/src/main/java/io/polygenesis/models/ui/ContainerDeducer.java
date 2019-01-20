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

package io.polygenesis.models.ui;

import io.polygenesis.core.Function;
import io.polygenesis.models.ui.container.AbstractContainer;
import io.polygenesis.models.ui.container.InlineContainer;

/**
 * The type Container deducer.
 *
 * @author Christos Tsakostas
 */
public class ContainerDeducer {

  // ===============================================================================================
  // STATIC
  // ===============================================================================================

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  // ===============================================================================================
  // FUNCTIONALITY
  // ===============================================================================================

  /**
   * Deduce container from thing function.
   *
   * @param feature the feature
   * @param function the function
   * @return the container
   */
  public AbstractContainer deduceContainerFromThingFunction(Feature feature, Function function) {
    InlineContainer container = new InlineContainer();

    return container;
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

}
