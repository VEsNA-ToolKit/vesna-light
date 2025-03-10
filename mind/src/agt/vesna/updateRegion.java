package vesna;

import jason.asSemantics.*;
import jason.asSyntax.*;

import org.json.JSONObject;

// updateRegion sends to the body the signal of a new region in which the user is located;
// region management is performed by the body.
public class updateRegion extends DefaultInternalAction {
   
    @Override
    public Object execute( TransitionSystem ts, Unifier un, Term[] args ) throws Exception {
        
        JSONObject region = new JSONObject();
        region.put( "sender", "mind" );
        region.put( "receiver", "body" );
        region.put( "type", "region" );
        JSONObject data = new JSONObject();
        data.put( "region", args[0] );
        region.put( "data", data );

        VesnaAgent ag = ( VesnaAgent ) ts.getAg();
        ag.perform( region.toString() );

        return true;
    }
}
