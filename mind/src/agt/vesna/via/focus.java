package vesna;

import jason.asSemantics.*;
import jason.asSyntax.*;
import cartago.*;
import jacamo.infra.JaCaMoRuntimeServices;
import jaca.*;
import jason.architecture.AgArch;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.List;

import static jason.asSyntax.ASSyntax.*;

public class focus extends DefaultInternalAction {

    @Override
    public Object execute( TransitionSystem ts, Unifier un, Term[] args ) throws Exception {

        AgentId my_agent_id = null;

        String art = ( ( StringTerm) args[0] ).getString();
        String art_wp = art.split("\\.")[0];
        String art_name = art.split("\\.")[1];

        Agent ag = ts.getAg();

        cartago.CartagoEnvironment cenv = cartago.CartagoEnvironment.getInstance(); 
        Workspace main = cenv.getRootWSP().getWorkspace();

        AgentId my_ag_id = getAgId( main, ts.getAgArch().getAgName() );

        // Unifier ag_wps = new Unifier();
        // Iterator<Literal> ag_wp_it = ag.getBB().getCandidateBeliefs( parseLiteral( "joinedWsp( WpId, WpName, LogPath )" ), ag_wps );
        // while( ag_wp_it.hasNext() ){
        //     Literal wp = ag_wp_it.next();
        //     String wp_log_path = ( ( StringTerm ) wp.getTerm( 2 ) ).getString();
        //     Workspace curr_wp = cenv.resolveWSP( wp_log_path ).getWorkspace();
        //     if ( curr_wp.isArtifactPresent( art_name) ) {
        //         ArtifactId art_id = curr_wp.lookupArtifact( my_agent_id, art_name );
        //     }
        // }
        Workspace wp = cenv.resolveWSP( "/main/" + art_wp ).getWorkspace();
        if ( !wp.isArtifactPresent( art_name ) )
            return false;

        ArtifactId art_id = wp.lookupArtifact( my_ag_id, art_name );
        ArtifactId ag_body_id = wp.getAgentBodyArtifact( my_ag_id );

        // AgArch agArch = ts.getAgArch();
        
        // // Naviga nell'architettura fino a trovare CentralisedAgArch
        // while (agArch != null && !(agArch instanceof CAgentArch ) ) {
        //     agArch = agArch.getNextAgArch();
        // }
        CAgentArch agArch = CAgentArch.getCartagoAgArch( ts );

        AgentSession session = (AgentSession)((CAgentArch) agArch).getSession();
        List<ArtifactObsProperty> props = wp.focus( my_ag_id, null, (AgentSession)((CAgentArch) agArch).getSession(), art_id );
        wp.notifyFocusCompleted(session, -1, null, null, art_id, props);
        System.out.println( "Artifact focused!" );

        return true;
    }

    private AgentId getAgId( Workspace main, String ag_name ){
        try {
            for ( AgentId ag_id : main.getController().getCurrentAgents() )
                if ( ag_id.getAgentName().equals( ag_name ) )
                    return ag_id;
        } catch( Exception e ){
            e.printStackTrace();
        }

        return null;

    }


}