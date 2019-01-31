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

import com.oregor.ddd4j.check.assertion.Assertion;
import io.polygenesis.commons.valueobjects.FeatureName;
import io.polygenesis.models.ui.container.AbstractContainer;
import java.util.Set;

/**
 * Encapsulates all of the UI models related to a Feature. A Feature is equivalent to a {@link
 * io.polygenesis.core.Thing}** from the core model.
 *
 * @author Christos Tsakostas
 * @see io.polygenesis.core.Thing
 */
public class Feature {

  private FeatureName featureName;
  private Set<AbstractContainer> containers;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Feature ui.
   *
   * @param featureName the feature name
   * @param containers the containers
   */
  public Feature(FeatureName featureName, Set<AbstractContainer> containers) {
    setFeatureName(featureName);
    setContainers(containers);
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets feature name.
   *
   * @return the feature name
   */
  public FeatureName getFeatureName() {
    return featureName;
  }

  /**
   * Gets containers.
   *
   * @return the containers
   */
  public Set<AbstractContainer> getContainers() {
    return containers;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets feature name.
   *
   * @param featureName the feature name
   */
  private void setFeatureName(FeatureName featureName) {
    Assertion.isNotNull(featureName, "Feature Name is required");
    this.featureName = featureName;
  }

  /**
   * Sets containers.
   *
   * @param containers the containers
   */
  private void setContainers(Set<AbstractContainer> containers) {
    this.containers = containers;
  }
}
