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

package io.polygenesis.models.reactivestate;

import io.polygenesis.commons.text.TextConverter;
import io.polygenesis.core.Thing;

/**
 * Deduces a {@link Feature} provided a {@link io.polygenesis.core.Thing}.
 *
 * @author Christos Tsakostas
 */
public class FeatureDeducer {

  /**
   * Feature deduction.
   *
   * @param thing the thing
   * @return the feature
   */
  public Feature from(Thing thing) {
    return new Feature(TextConverter.toLowerCamel(thing.getName().getText()));
  }
}
