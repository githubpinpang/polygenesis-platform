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

package io.polygenesis.commons.valueobjects;

import io.polygenesis.commons.text.AbstractText;

/**
 * Denotes the name of business context. A context can be any broad concept making sense to the
 * business. If you use microservices, you would like to use the name of the service i.e. account,
 * promotion, etc.
 *
 * @author Christos Tsakostas
 */
public class ContextName extends AbstractText {

  private static final long serialVersionUID = 1L;

  // ===============================================================================================
  // STATIC
  // ===============================================================================================

  /**
   * Default context context name.
   *
   * @return the context name
   */
  public static ContextName defaultContext() {
    return new ContextName("defaultContext");
  }

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Feature.
   *
   * @param text the text
   */
  public ContextName(String text) {
    super(text);
  }
}
