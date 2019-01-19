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

package io.polygenesis.models.state;

import static org.assertj.core.api.Assertions.assertThat;

import io.polygenesis.core.ThingRepository;
import io.polygenesis.core.ThingRepositoryImpl;
import java.util.LinkedHashSet;
import org.junit.Test;

/** @author Christos Tsakostas */
public class StateDeducerImplTest {

  @Test
  public void deduce() {
    StoreDeducer storeDeducer = new StoreDeducer();
    StateDeducer stateDeducer = new StateDeducerImpl(storeDeducer);
    ThingRepository thingRepository = new ThingRepositoryImpl(new LinkedHashSet<>());

    StateModelRepository stateModelRepository = stateDeducer.deduce(thingRepository);

    assertThat(stateModelRepository).isNotNull();
    assertThat(stateModelRepository.getStores()).isNotNull();
    assertThat(stateModelRepository.getStores().size()).isEqualTo(0);
  }
}