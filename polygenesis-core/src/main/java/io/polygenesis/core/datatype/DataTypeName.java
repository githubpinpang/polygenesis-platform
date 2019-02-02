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

package io.polygenesis.core.datatype;

import io.polygenesis.commons.text.AbstractText;

/**
 * The Data type name.
 *
 * @author Christos Tsakostas
 */
public class DataTypeName extends AbstractText {

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Data type name.
   *
   * @param text the text
   */
  public DataTypeName(String text) {
    super(text);
    guardText(text);
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  private void guardText(String text) {
    if (text.indexOf('.') > 0) {
      throw new IllegalArgumentException(String.format("Invalid data type=%s", text));
    }
  }
}
