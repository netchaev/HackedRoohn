import akka.actor.UntypedActor;
import controllers.Application;
import play.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: terence
 * Date: 21/07/13
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */
public class GrabActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        Logger.debug("Starting update from y.combinator.");
        Application.update();
        Logger.debug("Finished updating from y.combinator.");
    }
}
