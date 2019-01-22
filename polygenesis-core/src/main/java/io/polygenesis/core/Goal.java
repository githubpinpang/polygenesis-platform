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

package io.polygenesis.core;

import io.polygenesis.commons.text.AbstractText;

/**
 * One of the most essential parts of PolyGenesis is the <b>Goal</b>. Based on the Goal of a {@link
 * Function}, deducers are able to instantiate the technology models.
 *
 * <p>It is highly preferable to use one of the constants provided at {@link
 * io.polygenesis.annotations.core.GGoalStandardType}, in order to instantiate a Goal.
 *
 * <p>For the rare cases that available types in {@link
 * io.polygenesis.annotations.core.GGoalStandardType} are not adequate to describe your goal, it is
 * possible to pass any goal type as string.
 *
 * <p>Be aware though, that PolyGenesis will <b>not</b> be able to use your custom goal and it is
 * your responsibility to utilize it in your generators, deducers and instantiation of technology
 * models.
 *
 * @author Christos Tsakostas
 */
public class Goal extends AbstractText {

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Goal.
   *
   * @param goalType the goal type
   */
  public Goal(String goalType) {
    super(goalType);
  }
}
