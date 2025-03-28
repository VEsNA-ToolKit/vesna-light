package vesna.playgrounds.office;

import vesna.SituatedArtifact;
import vesna.WsClient;
import vesna.WsClientMsgHandler;
import java.net.URI;

import org.json.JSONObject;

import cartago.*;

public class CoffeeMachine extends SituatedArtifact {

    private WsClient client;

    public void init( String region, int limit ) {
        super.init( region, limit );
        try {
            client = new WsClient( new URI( "ws://localhost:8090") );
            client.setMsgHandler( new WsClientMsgHandler() {
                @Override
                public void handle_msg( String msg ) {
                    manage_msg( msg );
                }

                @Override
                public void handle_error( Exception ex ) {
                    manage_error( ex );
                }
            });
            client.connect();
        } catch( Exception e ){
            e.printStackTrace();
        }
    }

    private void manage_msg( String msg ) {
        log( msg );
    }

    private void manage_error( Exception ex ) {
        log( ex.getMessage() );
    }
    
    @OPERATION
    public void make_coffee( String cup_name ) throws Exception {
        ArtifactId cup_id = lookupArtifact( cup_name );
        execLinkedOp( cup_id, "set_content", "coffee" );

        JSONObject log = new JSONObject();
        log.put( "sender", get_art_name() );
        log.put( "receiver", "artifact" );
        log.put( "type", "interaction" );
        JSONObject data = new JSONObject();
        data.put( "type", "make_coffee" );
        data.put( "quantity", "short" );
        log.put( "data", data );

        client.send( log.toString() );
    }

}
