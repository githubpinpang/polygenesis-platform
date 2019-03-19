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

package io.polygenesis.models.domain;

import io.polygenesis.commons.valueobjects.ObjectName;
import io.polygenesis.commons.valueobjects.PackageName;
import io.polygenesis.core.Thing;
import io.polygenesis.core.ThingRepository;
import io.polygenesis.core.ThingScopeType;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * The type Aggregate root deducer.
 *
 * @author Christos Tsakostas
 */
public class AggregateRootDeducer {

  // ===============================================================================================
  // DEPENDENCIES
  // ===============================================================================================
  private final DomainObjectConstructorDeducer domainObjectConstructorDeducer;
  private final AggregateRootPropertyDeducer aggregateRootPropertyDeducer;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Aggregate root deducer.
   *
   * @param domainObjectConstructorDeducer the aggregate constructor deducer
   * @param aggregateRootPropertyDeducer the aggregate root property deducer
   */
  public AggregateRootDeducer(
      DomainObjectConstructorDeducer domainObjectConstructorDeducer,
      AggregateRootPropertyDeducer aggregateRootPropertyDeducer) {
    this.domainObjectConstructorDeducer = domainObjectConstructorDeducer;
    this.aggregateRootPropertyDeducer = aggregateRootPropertyDeducer;
  }

  // ===============================================================================================
  // FUNCTIONALITY
  // ===============================================================================================

  /**
   * Deduce from set.
   *
   * @param thingRepository the thing repository
   * @param rootPackageName the root package name
   * @return the set
   */
  public Set<AggregateRoot> deduceFrom(
      ThingRepository thingRepository, PackageName rootPackageName) {
    Set<AggregateRoot> aggregateRoots = new LinkedHashSet<>();

    thingRepository
        .getDomainModelThings()
        .forEach(
            thing -> {
              if (!thing.getOptionalParent().isPresent() || thingRepository.isVirtualChild(thing)) {
                makeAggregateRoot(aggregateRoots, thing, rootPackageName);
              }
            });

    return aggregateRoots;
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

  /**
   * Make aggregate root aggregate root.
   *
   * @param thing the thing
   * @param rootPackageName the root package name
   * @return the aggregate root
   */
  protected void makeAggregateRoot(
      Set<AggregateRoot> aggregateRoots, Thing thing, PackageName rootPackageName) {
    PackageName packageName = thing.makePackageName(rootPackageName, thing);

    ObjectName aggregateRootObjectName = makeAggregateRootName(thing);

    Set<DomainObjectProperty> properties =
        aggregateRootPropertyDeducer.deduceFrom(thing, rootPackageName);

    Set<Constructor> constructors =
        domainObjectConstructorDeducer.deduceFrom(thing, rootPackageName);

    Set<StateMutationMethod> stateMutationMethods = new LinkedHashSet<>();
    Set<StateQueryMethod> stateQueryMethods = new LinkedHashSet<>();
    Set<FactoryMethod> factoryMethods = new LinkedHashSet<>();

    Persistence persistence =
        new Persistence(
            packageName,
            makePersistenceName(thing),
            aggregateRootObjectName,
            makeAggregateRootIdName(thing),
            thing.getMultiTenant());

    aggregateRoots.add(
        new AggregateRootPersistable(
            thing.getThingScopeType().equals(ThingScopeType.DOMAIN_ABSTRACT_AGGREGATE_ROOT)
                ? InstantiationType.ABSTRACT
                : InstantiationType.CONCRETE,
            makeSuperclass(),
            aggregateRootObjectName,
            packageName,
            properties,
            constructors,
            stateMutationMethods,
            stateQueryMethods,
            factoryMethods,
            thing.getMultiTenant(),
            persistence));

    thing
        .getVirtualChildren()
        .forEach(
            thingVirtualChild ->
                makeAggregateRoot(aggregateRoots, thingVirtualChild, rootPackageName));
  }

  /**
   * Make aggregate root name name.
   *
   * @param thing the thing
   * @return the name
   */
  private ObjectName makeAggregateRootName(Thing thing) {
    return new ObjectName(thing.getThingName().getText());
  }

  /**
   * Make aggregate root id name name.
   *
   * @param thing the thing
   * @return the name
   */
  private ObjectName makeAggregateRootIdName(Thing thing) {
    return new ObjectName(thing.getThingName().getText() + "Id");
  }

  /**
   * Make persistence name name.
   *
   * @param thing the thing
   * @return the name
   */
  private ObjectName makePersistenceName(Thing thing) {
    return new ObjectName(thing.getThingName().getText() + "Persistence");
  }

  /**
   * Make superclass aggregate root.
   *
   * @return the aggregate root
   */
  private Optional<AggregateRoot> makeSuperclass() {
    return Optional.of(
        new AggregateRoot(
            InstantiationType.ABSTRACT,
            Optional.empty(),
            new ObjectName("AggregateRoot"),
            new PackageName("com.oregor.ddd4j.core"),
            new LinkedHashSet<>(),
            new LinkedHashSet<>(),
            new LinkedHashSet<>(),
            new LinkedHashSet<>(),
            new LinkedHashSet<>(),
            false));
  }
}
