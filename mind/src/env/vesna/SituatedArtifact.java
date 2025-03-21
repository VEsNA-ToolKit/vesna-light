package vesna;

import cartago.Artifact;
import cartago.OPERATION;

import java.util.List;
import java.util.ArrayList;

public class SituatedArtifact extends Artifact{

    private String region;
    private String art_name;
    private int limit;
    private List<String> using;

    void init( String region, int limit ) {
        this.region = region;
        this.limit = limit;
        using = new ArrayList<String>();
        this.art_name = getId().getName();
        log( "Artifact name: " + art_name );
        log( "Init finished!" );
    }

    @OPERATION
    public void use( String art_name, String ag_region ) {

        if ( ! art_name.equals( this.art_name ) )
            return;

        if ( ! ag_region.equals( this.region ) ) {
            failed( "You cannot use this artifact: it is in another region!" );
        }

        if ( using.size() >= limit )
            failed( "You cannot use " + art_name + " because it is already used by other agent(s)" );
        
        String ag_name = getCurrentOpAgentId().getAgentName();
        if ( using.contains( ag_name ) ) {
            log( "Agent " + ag_name + "is already using the artifact!" );
            return;
        }

        using.add( ag_name );
        log( ag_name + " can use " + this.art_name );

    }

    @OPERATION
    public void free( String art_name ) throws Exception {

        if ( ! art_name.equals( this.art_name ) )
            return;

        String ag_name = getCurrentOpAgentId().getAgentName();
        if ( ! using.contains( ag_name ) ) {
            log( "Agent " + ag_name + " was not using the artifact!" );
            return;
        }

        using.remove( ag_name );
    }

    public boolean is_using( String ag_name ) {
        return using.contains( ag_name );
    }
    
}
