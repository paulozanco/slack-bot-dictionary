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

import co.paulozan.slack.domain.EventResponse;
import co.paulozan.slack.event.Event;
import io.swagger.annotations.ApiModel;
import javax.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pzanco on 17/06/17.
 */

@RestController
@ApiModel()
public class ChallengeController {

  private final Logger logger = LoggerFactory.getLogger(ChallengeController.class);

  @RequestMapping(
      value = "/event",
      method = RequestMethod.POST)
  public EventResponse event(@RequestBody Event event) {
    logger.debug("Received {}", event);
    EventResponse eventResponse = new EventResponse();
    if (event.getType().equals("url_verification")) {
      eventResponse.setChallenge(event.getChallenge());
    }
    return eventResponse;
  }

  @RequestMapping(
      value = "/callback",
      method = RequestMethod.GET)
  public void challenge(@QueryParam("code") String code, @QueryParam("state") String state) {
    logger.debug("OAuth Code {}", code);
  }
}
