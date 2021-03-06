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

package com.invoiceful.genesis.contexts.notification;

import io.polygenesis.abstraction.thing.Thing;
import io.polygenesis.abstraction.thing.ThingContext;
import io.polygenesis.abstraction.thing.ThingContextBuilder;
import io.polygenesis.core.ContextGenerator;
import io.polygenesis.core.Deducer;
import java.util.Set;

/** @author Christos Tsakostas */
public class ContextNotification {

  public static ThingContext get(
      String rootPackageName, ContextGenerator contextGenerator, Set<Deducer<?>> deducers) {
    Thing email = Email.create(rootPackageName);

    return ThingContextBuilder.of("notification", contextGenerator)
        .withDeducers(deducers)

        //        // Aggregate Roots
        //        .addThing(email)
        //
        //        // Subscribers
        //        .addThing(OnSignUpCreated.create(email, rootPackageName))

        .build();
  }
}
