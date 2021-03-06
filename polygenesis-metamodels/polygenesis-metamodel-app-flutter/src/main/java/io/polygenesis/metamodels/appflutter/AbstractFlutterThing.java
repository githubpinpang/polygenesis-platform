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

package io.polygenesis.metamodels.appflutter;

import io.polygenesis.abstraction.data.DataObject;
import io.polygenesis.abstraction.data.DataRepository;
import io.polygenesis.abstraction.thing.Function;
import io.polygenesis.abstraction.thing.Purpose;
import io.polygenesis.abstraction.thing.Thing;
import io.polygenesis.abstraction.thing.ThingName;
import io.polygenesis.abstraction.thing.dsl.FunctionBuilder;
import io.polygenesis.commons.keyvalue.KeyValue;
import io.polygenesis.commons.valueobjects.ContextName;
import io.polygenesis.commons.valueobjects.ObjectName;
import io.polygenesis.commons.valueobjects.PackageName;
import io.polygenesis.commons.valueobjects.VariableName;
import io.polygenesis.core.AbstractionScope;
import java.util.Set;

/**
 * The type Abstract flutter thing.
 *
 * @param <S> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractFlutterThing<S extends Thing> extends Thing {

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract flutter thing.
   *
   * @param abstractionScopes the abstraction scopes
   * @param contextName the context name
   * @param thingName the thing name
   * @param thingProperties the thing properties
   * @param multiTenant the multi tenant
   * @param optionalParent the optional parent
   * @param metadata the metadata
   */
  protected AbstractFlutterThing(
      Set<AbstractionScope> abstractionScopes,
      ContextName contextName,
      ThingName thingName,
      DataRepository thingProperties,
      Boolean multiTenant,
      Thing optionalParent,
      Set<KeyValue> metadata) {
    super(
        abstractionScopes,
        contextName,
        thingName,
        thingProperties,
        multiTenant,
        optionalParent,
        metadata);
  }

  // ===============================================================================================
  // BEHAVIOR
  // ===============================================================================================

  /**
   * Add build function.
   *
   * @param source the source
   */
  protected void addBuildFunction(S source) {
    Function build =
        FunctionBuilder.of(source, "build", Purpose.build())
            .setReturnValue(new DataObject(new ObjectName("Widget"), new PackageName("com.dummy")))
            .addArgument(
                new DataObject(
                    new ObjectName("BuildContext"),
                    new PackageName("com.dummy"),
                    new VariableName("context")))
            .build();

    source.addFunction(build);
  }
}
