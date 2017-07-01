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

package co.paulozan.slackbot.worker;

import co.paulozan.slack.client.ChatClient;
import co.paulozan.slack.domain.Event;
import co.paulozan.slack.event.EventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventMessageWorker implements EventWorker {

  private static final Logger LOGGER = LoggerFactory.getLogger(EventMessageWorker.class);

  @Override
  public EventResponse process(Event event) throws Exception {

    String token = System.getenv("SLACK_TOKEN");
    String channel = event.getEvent().getChannel();
    String text = event.getEvent().getText();

    ChatClient.postMessage(token, channel, text);

    LOGGER.debug("Message sent - {} - {}", channel, " ", text);

    return new EventResponse();
  }
}
