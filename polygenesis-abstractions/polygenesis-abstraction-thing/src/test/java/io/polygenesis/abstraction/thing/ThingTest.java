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

package io.polygenesis.abstraction.thing;

import static org.assertj.core.api.Assertions.assertThat;

import io.polygenesis.abstraction.data.DataPrimitive;
import io.polygenesis.abstraction.data.PrimitiveType;
import io.polygenesis.abstraction.thing.dsl.FunctionBuilder;
import io.polygenesis.abstraction.thing.dsl.ThingBuilder;
import io.polygenesis.commons.test.AbstractEqualityTest;
import io.polygenesis.commons.valueobjects.PackageName;
import io.polygenesis.commons.valueobjects.VariableName;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Test;

/** @author Christos Tsakostas */
public class ThingTest extends AbstractEqualityTest<Thing> {

  @Test
  public void shouldCreateThingWithName() {
    Thing thing = ThingBuilder.endToEnd("someThingName").createThing(PackageName.any());

    assertThat(thing).isNotNull();

    Function function1 = createFunction1(thing);
    Function function2 = createFunction2(thing);
    Function function3 = createFunction3(thing);

    Set<Function> functionSet = new LinkedHashSet<>();
    functionSet.add(function2);
    functionSet.add(function3);

    thing.addFunction(function1);
    thing.addFunctions(functionSet);

    assertThat(thing.getThingName().getText()).isEqualTo("someThingName");
    assertThat(thing.getChildren()).isEmpty();
    assertThat(thing.getFunctions().size()).isEqualTo(3);
  }

  private Function createFunction1(Thing thing) {
    return FunctionBuilder.of(thing, "functionName", Purpose.modify())
        .setReturnValue(createDataPrimitive())
        .build();
  }

  private Function createFunction2(Thing thing) {
    return FunctionBuilder.of(thing, "anotherFunctionName", Purpose.modify())
        .setReturnValue(createDataPrimitive())
        .build();
  }

  private Function createFunction3(Thing thing) {
    return FunctionBuilder.of(thing, "someAnotherNewFunctionName", Purpose.modify())
        .setReturnValue(createDataPrimitive())
        .build();
  }

  private DataPrimitive createDataPrimitive() {
    return DataPrimitive.of(PrimitiveType.STRING, new VariableName("variableName"));
  }

  // ===============================================================================================
  // Equality and Hash
  // ===============================================================================================
  @Override
  public Thing createObject1() {
    return ThingBuilder.endToEnd("xxx").createThing(PackageName.any());
  }

  @Override
  public Thing createObject2() {
    return ThingBuilder.endToEnd("yyy").createThing(PackageName.any());
  }
}
