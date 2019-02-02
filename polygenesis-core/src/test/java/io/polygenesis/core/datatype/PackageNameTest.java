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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

/** @author Christos Tsakostas */
public class PackageNameTest {

  @Test
  public void shouldInitialize() {
    assertThat(new PackageName("com.oregor.ddd4j")).isNotNull();
    assertThat(new PackageName("com.oregor")).isNotNull();
    assertThat(new PackageName("com")).isNotNull();
  }

  @Test
  public void shouldFail() {
    assertThatThrownBy(() -> new PackageName("com.oregor.ddd4j."))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> new PackageName("com.oregor.ddd4j.."))
        .isInstanceOf(IllegalArgumentException.class);
    assertThatThrownBy(() -> new PackageName("com.-.ddd4j"))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
