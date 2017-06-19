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

import co.paulozan.slack.domain.ChallengeResponse;
import co.paulozan.slack.event.Challenge;
import co.paulozan.slack.parser.JsonParser;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pzanco on 17/06/17.
 */

@RestController
public class ChallengeController {

  private final Logger logger = LoggerFactory.getLogger(ChallengeController.class);

  @RequestMapping("/challenge")
  public @ResponseBody ChallengeResponse challenge(String challenge) {
    logger.debug("Received {}",challenge);
    ChallengeResponse challengeResponse = new ChallengeResponse();
    Optional<Object> optional = JsonParser.readValue(challenge, Challenge.class);
    if (optional.isPresent()){
      challengeResponse.setChallenge(((Challenge)optional.get()).getChallenge());
    }
    return challengeResponse;
  }

}
