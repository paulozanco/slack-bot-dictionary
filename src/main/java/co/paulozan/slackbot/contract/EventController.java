/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 * The full license can be found online at http://www.gnu.org/copyleft/gpl.html
 *
 */

package co.paulozan.slackbot.contract;

import co.paulozan.slack.domain.Event;
import co.paulozan.slack.event.EventResponse;
import co.paulozan.slack.parser.JsonParser;
import co.paulozan.slackbot.Application;
import co.paulozan.slackbot.worker.EventWorker;
import co.paulozan.slackbot.worker.EventWorkerFactory;
import feign.Response;
import io.swagger.annotations.ApiModel;
import javax.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("DefaultFileTemplate")
@RestController
@ApiModel()
public class EventController {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

  @RequestMapping(
      value = "/event",
      method = RequestMethod.POST)
  public EventResponse event(@RequestBody Event event) throws Exception {
    LOGGER.debug("Received {}", JsonParser.toJson(event));
    EventWorker worker = EventWorkerFactory.instance(event);
    return worker.process(event);
  }

  @RequestMapping(
      value = "/callback",
      method = RequestMethod.GET)
  public void challenge(@QueryParam("code") String code, @QueryParam("state") String state) {
    LOGGER.debug("OAuth Code {}", code);
  }
}
