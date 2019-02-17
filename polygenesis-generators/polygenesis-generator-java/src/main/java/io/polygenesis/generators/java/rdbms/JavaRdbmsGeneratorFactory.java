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

package io.polygenesis.generators.java.rdbms;

import io.polygenesis.commons.freemarker.FreemarkerConfig;
import io.polygenesis.commons.freemarker.FreemarkerService;
import io.polygenesis.commons.text.Name;
import io.polygenesis.core.converter.FromDataTypeToJavaConverter;
import io.polygenesis.core.datatype.PackageName;
import java.nio.file.Path;

/**
 * The Java Domain Generator Factory creates new instances of {@link JavaRdbmsGenerator}.
 *
 * @author Christos Tsakostas
 */
public final class JavaRdbmsGeneratorFactory {

  // ===============================================================================================
  // DEPENDENCIES
  // ===============================================================================================
  private static final PersistenceImplExporter persistenceImplExporter;
  private static final DomainMessageExporter domainMessageExporter;
  private static final SpringDataRepositoryExporter springDataRepositoryExporter;
  private static final RdbmsTestExporter rdbmsTestExporter;
  private static final RdbmsTestConfigExporter rdbmsTestConfigExporter;

  // ===============================================================================================
  // STATIC INITIALIZATION OF DEPENDENCIES
  // ===============================================================================================

  static {
    FreemarkerService freemarkerService =
        new FreemarkerService(FreemarkerConfig.getInstance().getConfiguration());

    FromDataTypeToJavaConverter fromDataTypeToJavaConverter = new FromDataTypeToJavaConverter();

    PersistenceImplProjectionConverter persistenceImplProjectionConverter =
        new PersistenceImplProjectionConverter(fromDataTypeToJavaConverter);

    domainMessageExporter = new DomainMessageExporter(freemarkerService);

    persistenceImplExporter =
        new PersistenceImplExporter(freemarkerService, persistenceImplProjectionConverter);

    SpringDataRepositoryProjectionConverter springDataRepositoryProjectionConverter =
        new SpringDataRepositoryProjectionConverter(fromDataTypeToJavaConverter);

    springDataRepositoryExporter =
        new SpringDataRepositoryExporter(
            freemarkerService, springDataRepositoryProjectionConverter);

    rdbmsTestExporter = new RdbmsTestExporter(freemarkerService);
    rdbmsTestConfigExporter = new RdbmsTestConfigExporter(freemarkerService);
  }

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  private JavaRdbmsGeneratorFactory() {
    throw new IllegalStateException("Utility class");
  }

  // ===============================================================================================
  // NEW INSTANCE
  // ===============================================================================================

  /**
   * New instance java api generator.
   *
   * @param generationPath the generation path
   * @param rootPackageName the root package name
   * @param contextName the context name
   * @return the java api generator
   */
  public static JavaRdbmsGenerator newInstance(
      Path generationPath, PackageName rootPackageName, Name contextName) {
    return new JavaRdbmsGenerator(
        generationPath,
        rootPackageName,
        contextName,
        domainMessageExporter,
        persistenceImplExporter,
        springDataRepositoryExporter,
        rdbmsTestExporter,
        rdbmsTestConfigExporter);
  }
}