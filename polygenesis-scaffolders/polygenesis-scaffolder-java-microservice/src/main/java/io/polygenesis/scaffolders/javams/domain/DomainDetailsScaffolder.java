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

package io.polygenesis.scaffolders.javams.domain;

import io.polygenesis.commons.freemarker.FreemarkerService;
import io.polygenesis.scaffolders.javams.AbstractScaffolder;
import io.polygenesis.scaffolders.javams.Layer;
import io.polygenesis.scaffolders.javams.ProjectDescription;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * The type Domain details scaffolder.
 *
 * @author Christos Tsakostas
 */
public class DomainDetailsScaffolder extends AbstractScaffolder {

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Domain details scaffolder.
   *
   * @param freemarkerService the freemarker service
   */
  public DomainDetailsScaffolder(FreemarkerService freemarkerService) {
    super(freemarkerService);
  }

  // ===============================================================================================
  // BEHAVIOUR
  // ===============================================================================================

  @Override
  public void scaffold(
      Path generationPath, ProjectDescription projectDescription, Map<String, Object> dataModel) {

    // Check if Layer is enabled
    if (!projectDescription.getLayers().contains(Layer.DOMAIN_MODEL)) {
      return;
    }

    Path modulePath =
        Paths.get(
            generationPath.toString(), projectDescription.getModulePrefix() + "-domain-details");

    exportDomainDetailsMavenPomXml(modulePath, dataModel);
  }

  private void exportDomainDetailsMavenPomXml(Path modulePath, Map<String, Object> dataModel) {

    freemarkerService.export(
        dataModel,
        "polygenesis-scaffolder-java-microservice/domain-details/pom.xml.ftl",
        Paths.get(modulePath.toString(), "pom.xml"));
  }
}