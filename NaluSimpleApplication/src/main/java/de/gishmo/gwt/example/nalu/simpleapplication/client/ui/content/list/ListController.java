/*
 * Copyright (c) 2018 - Frank Hossfeld
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 *
 */

package de.gishmo.gwt.example.nalu.simpleapplication.client.ui.content.list;

import com.github.mvp4g.nalu.client.ui.AbstractComponentController;
import com.github.mvp4g.nalu.client.ui.annotations.Controller;
import de.gishmo.gwt.example.nalu.simpleapplication.client.NaluSimpleApplicationContext;
import de.gishmo.gwt.example.nalu.simpleapplication.client.data.model.dto.Person;
import de.gishmo.gwt.example.nalu.simpleapplication.client.data.model.dto.PersonSearch;
import de.gishmo.gwt.example.nalu.simpleapplication.client.data.service.PersonService;
import de.gishmo.gwt.example.nalu.simpleapplication.client.ui.Selectors;
import elemental2.dom.HTMLElement;

import java.util.List;

import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.EventType.click;

@Controller(route = "/list/:name/:city", selector = Selectors.CONTENT, componentInterface = IListComponent.class, component = ListComponent.class)
public class ListController
  extends AbstractComponentController<NaluSimpleApplicationContext, IListComponent>
  implements IListComponent.Controller{

  private String name;
  private String city;

  public ListController() {
  }

  @Override
  public void start() {
    List<Person> result = PersonService.get()
                                                 .get(new PersonSearch(this.name,
                                                                       this.city));
    this.component.resetTable();
    this.component.setData(result);
//    view.setData(result);
//    eventBus.setContent(view.asElement());
//    if (result.size() == 0) {
//      eventBus.setStatus("No person found");
//    } else if (result.size() == 1) {
//      eventBus.setStatus("Found one person");
//    } else {
//      eventBus.setStatus("Found " + Integer.toString(result.size()) + " persons");
//    }
  }


  @Override
  public void doUpdate(Person object) {
    this.router.route("/detail",
                      Long.toString(object.getId()));
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCity(String city) {
    this.city = city;
  }
}