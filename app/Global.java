import akka.actor.ActorRef;
import akka.actor.Props;
import controllers.Application;
import play.GlobalSettings;
import play.Logger;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import play.libs.Akka;
import play.mvc.Result;
import static play.mvc.Results.async;
import play.libs.F.Function;

/**
 * Created with IntelliJ IDEA.
 * User: terence
 * Date: 21/07/13
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class Global extends GlobalSettings {

    public void onStart(Application app) {
        ActorRef myActor = play.libs.Akka.system().actorOf(new Props(GrabActor.class));
        Akka.system().scheduler().schedule(Duration.create(0, TimeUnit.MILLISECONDS),
                Duration.create(1, TimeUnit.MINUTES), myActor, "tick", Akka.system().dispatcher());

        Logger.info("Application has started");
    }

}
