package vesna;

import cartago.Artifact;
import cartago.OPERATION;

public class GrabbableArtifact extends Artifact {

    private String owner;
    private String region;
    private String art_name;

    void init( String region ) {
        this.region = region;
        this.owner = null;
        this.art_name = getId().getName();
    }

    @OPERATION
    public void grab( String art_name, String ag_region ) {

        if ( ! art_name.equals( this.art_name ) )
            return;

        if ( ! ag_region.equals( this.region ) )
            failed( "You cannot grab this artifact: it is in another region!" );

        if ( this.owner != null )
            failed( "You cannot grab this artifact: it has already an owner!" );

        String ag_name = getCurrentOpAgentId().getAgentName();
        this.owner = ag_name;

        log( ag_name + " has grabbed " + this.art_name );
    }

    @OPERATION
    public void release( String art_name, String ag_region ) {

        if ( ! art_name.equals( this.art_name ) )
            return;
        
        String ag_name = getCurrentOpAgentId().getAgentName();
        if ( ! owner.equals( ag_name ) )
            failed( "Agent " + ag_name + " was not grabbing the artifact" + art_name );

        owner = null;
    }
    
}
