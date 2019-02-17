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

package io.polygenesis.generators.java.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.polygenesis.core.ModelRepository;
import io.polygenesis.models.api.Service;
import io.polygenesis.models.api.ServiceModelRepository;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/** @author Christos Tsakostas */
public class JavaApiGeneratorTest {

  private Path generationPath;
  private ApiServiceExporter apiServiceExporter;
  private DtoExporter dtoExporter;
  private JavaApiGenerator javaApiGenerator;

  @Before
  public void setUp() {
    generationPath = Paths.get("tmp");
    apiServiceExporter = mock(ApiServiceExporter.class);
    dtoExporter = mock(DtoExporter.class);
    javaApiGenerator = new JavaApiGenerator(generationPath, apiServiceExporter, dtoExporter);
  }

  @Test
  public void shouldGenerate() {
    javaApiGenerator.generate(createModelRepositories());

    verify(apiServiceExporter).export(eq(generationPath), any(Service.class));
    verify(dtoExporter).export(eq(generationPath), any(Service.class));
  }

  @Test
  public void shouldFailOnMissingServiceModelRepository() {
    assertThatThrownBy(() -> javaApiGenerator.generate(new LinkedHashSet<>()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(
            "No Model Repository found for Class=io.polygenesis.models.api.ServiceModelRepository "
                + "in provided modelRepositories");
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

  private Set<ModelRepository> createModelRepositories() {
    Set<Service> services = new LinkedHashSet<>();

    Service service = mock(Service.class);
    services.add(service);

    ServiceModelRepository serviceModelRepository = new ServiceModelRepository(services);

    return new LinkedHashSet<>(Arrays.asList(serviceModelRepository));
  }
}