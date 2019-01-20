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

package io.polygenesis.generators.angular;

import io.polygenesis.commons.assertions.Assertion;
import io.polygenesis.core.AbstractGenerator;
import io.polygenesis.core.CoreRegistry;
import io.polygenesis.core.ModelRepository;
import io.polygenesis.generators.angular.reactivestate.StoreExporter;
import io.polygenesis.models.reactivestate.ReactiveStateModelRepository;
import java.nio.file.Path;
import java.util.Set;

/**
 * PolyGenesis Angular Generator.
 *
 * @author Christos Tsakostas
 */
public class PolyGenesisAngularGenerator extends AbstractGenerator {

  private final StoreExporter storeExporter;

  private ReactiveStateModelRepository reactiveStateModelRepository;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new PolyGenesis Angular Generator.
   *
   * @param generationPath the generation path
   * @param storeExporter the store exporter
   */
  public PolyGenesisAngularGenerator(Path generationPath, StoreExporter storeExporter) {
    super(generationPath);
    Assertion.isNotNull(storeExporter, "StoreExporter is required");
    this.storeExporter = storeExporter;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public void generate(Set<ModelRepository> modelRepositories) {
    initializeModelRepositories(modelRepositories);

    reactiveStateModelRepository
        .getStores()
        .forEach(store -> storeExporter.export(getGenerationPath(), store));
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

  /**
   * Initialize model repositories.
   *
   * @param modelRepositories the model repositories
   */
  private void initializeModelRepositories(Set<ModelRepository> modelRepositories) {
    reactiveStateModelRepository =
        CoreRegistry.getModelRepositoryResolver()
            .resolve(modelRepositories, ReactiveStateModelRepository.class);
  }
}
