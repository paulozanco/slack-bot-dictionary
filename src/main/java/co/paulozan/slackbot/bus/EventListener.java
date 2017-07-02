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

package co.paulozan.slackbot.bus;

import co.paulozan.rest.WebsterImpl;
import co.paulozan.slack.client.ChatClient;
import co.paulozan.slack.domain.Event;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

  @Subscribe
  public void event(Event event) {
    try {
      String token = System.getenv("SLACK_TOKEN");
      String webster_key = System.getenv("WEBSTER_KEY");
      String channel = event.getEvent().getChannel();
      String text = event.getEvent().getText();

      WebsterImpl webster = new WebsterImpl();
      String definition = webster.definition(text, webster_key);

      ChatClient.postMessage(token, channel, definition);

      LOGGER.debug("Message sent - {} - {}", channel, " ", definition);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
  }

}
