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

package io.polygenesis.generators.java.shared.transformer;

import io.polygenesis.core.TemplateTransformer;
import io.polygenesis.representations.code.ScssRepresentation;

/**
 * The interface Scss transformer.
 *
 * @param <S> the type parameter
 * @author Christos Tsakostas
 */
public interface ScssTransformer<S> extends TemplateTransformer<S> {

  /**
   * Create scss representation.
   *
   * @param source the source
   * @param args the args
   * @return the scss representation
   */
  ScssRepresentation create(S source, Object... args);
}
