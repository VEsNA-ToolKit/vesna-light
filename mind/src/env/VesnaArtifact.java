package vesna;

import cartago.Artifact;
import cartago.OPERATION;

public class VesnaArtifact extends Artifact {

    @OPERATION
    public void print_hello() {
        System.out.println( "Hello from the artifact!" );
    }

    @OPERATION
    public void tick() {
        return;
    }
}