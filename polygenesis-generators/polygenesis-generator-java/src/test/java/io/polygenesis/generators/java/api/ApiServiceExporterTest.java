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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.polygenesis.commons.freemarker.FreemarkerService;
import io.polygenesis.core.datatype.PackageName;
import io.polygenesis.models.api.Service;
import io.polygenesis.models.api.ServiceName;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

/** @author Christos Tsakostas */
public class ApiServiceExporterTest {

  private Path generationPath;
  private Service service;
  private FreemarkerService freemarkerService;
  private ApiServiceProjectionMaker apiServiceProjectionMaker;
  private ApiServiceExporter apiServiceExporter;

  @Before
  public void setUp() {
    generationPath = Paths.get("tmp");
    service = mock(Service.class);
    freemarkerService = mock(FreemarkerService.class);
    apiServiceProjectionMaker = mock(ApiServiceProjectionMaker.class);
    apiServiceExporter = new ApiServiceExporter(freemarkerService, apiServiceProjectionMaker);
  }

  @Test
  public void shouldExport() {
    given(service.getPackageName()).willReturn(new PackageName("com.oregor"));
    given(service.getServiceName()).willReturn(new ServiceName("someServiceName"));

    apiServiceExporter.export(generationPath, service);

    verify(apiServiceProjectionMaker).make(eq(service));
    verify(freemarkerService)
        .export(
            any(HashMap.class),
            eq("polygenesis-generator-java-api/ApiService.java.ftl"),
            eq(Paths.get("tmp/src/main/java/com/oregor/SomeServiceName.java")));
  }
}