package vesna;

import jason.asSemantics.*;
import jason.asSyntax.*;

import java.util.Set;

import org.json.JSONObject;

public class walk extends DefaultInternalAction {

    // // private static Set<String> available_actions = Set.of( "random", "stop", "door", "walk" );
    // walk()               performs a step
    // walk(n)              performs a step of length n
    // walk( target )       goes to target
    // walk( target, it )   goes to target with id

    @Override
    public Object execute( TransitionSystem ts, Unifier un, Term[] args ) throws Exception {

        String type = "none";
        if ( args.length == 0 )
            type = "step";
        else if ( args.length == 1 ){
            if ( args[0].isNumeric() )
                type = "step";
            else if ( args[0].isLiteral() )
                type = "goto";
        } else if ( args.length == 2 && args[0].isLiteral() && args[1].isNumeric() )
            type = "goto";
        else 
            return false;

        // // String target = args[0].toString();
        // // if ( !available_actions.contains( target ) )
        // //     throw new Exception( "Available arguments for functor 'walk' are: " + available_actions.toString() + ", given: " + target );
        JSONObject data = new JSONObject();
        data.put( "type", type );
        if ( type.equals( "step" ) ){
            if ( args.length == 2 ){
                data.put( "length", ( ( NumberTerm ) args[1] ).solve() );
            }
        } else if ( type.equals( "goto" ) ) {
            data.put( "target", args[0].toString() );
            if ( args.length == 2 )
                data.put( "id", ( ( NumberTerm ) args[1] ).solve() );
        }

        // // long id = -1;
        // // switch ( target ) {
        // //     case "random":
        // //         id = 0;
        // //         break;
        // //     case "door":
        // //         id = (long)( (NumberTerm) args[1] ).solve();
        // //         break;
        // //     case "walk":
        // //         id = 0;
        // //         break;
        // //     default:
        // //         System.out.println( "Action " + target + " not implemented yet" );
        // //         break;
        // // }

        JSONObject action = new JSONObject();
        action.put( "sender", ts.getAgArch().getAgName() );
        action.put( "receiver", "body" );
        action.put( "type", "walk" );
        // // JSONObject data = new JSONObject();
        // // data.put( "target", target );
        // // data.put( "id", id );
        action.put( "data", data );

        VesnaAgent ag = ( VesnaAgent ) ts.getAg();
        ag.perform( action.toString() );

        return true;
    }
    
}
