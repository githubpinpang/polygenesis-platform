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

package io.polygenesis.models.domain;

import io.polygenesis.abstraction.thing.Thing;
import io.polygenesis.abstraction.thing.ThingName;
import java.util.Set;

/**
 * The interface Domain metamodel repository.
 *
 * @param <T> the type parameter
 * @author Christos Tsakostas
 */
public interface DomainMetamodelRepository<T> {

  /**
   * Find entity by thing name base domain entity.
   *
   * @param thingName the thing name
   * @return the base domain entity
   */
  DomainObject findEntityByThingName(ThingName thingName);

  /**
   * Find by things set.
   *
   * @param things the things
   * @return the set
   */
  Set<T> findByThings(Set<Thing> things);
}
