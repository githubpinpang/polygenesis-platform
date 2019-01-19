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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/** @author Christos Tsakostas */
public class ModelRepositoryResolverTest {

  private ModelRepositoryResolver modelRepositoryResolver;
  private Set<ModelRepository> modelRepositories;

  @Before
  public void setUp() throws Exception {
    modelRepositoryResolver = new ModelRepositoryResolver();
    modelRepositories = new LinkedHashSet<>(Arrays.asList(new TestModelRepository()));
  }

  @Test
  public void shouldSucceedToResolve() {
    TestModelRepository testModelRepository =
        modelRepositoryResolver.resolve(modelRepositories, TestModelRepository.class);

    assertThat(testModelRepository).isNotNull();
  }

  @Test
  public void shouldFailToResolve() {
    assertThatThrownBy(
            () ->
                modelRepositoryResolver.resolve(
                    modelRepositories, AnotherTestModelRepository.class))
        .isInstanceOf(IllegalArgumentException.class);
  }
}