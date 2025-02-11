// Portals are places that connect one region to another one
// The list of portals available is below
// When a new portal is seen the agent stores it in the beliefs as
// > portal( Type, Id ).
// For example:
// > portal( door, 3 ).
portals( [ door ] ).

+!start
    <-  .wait( 2000 );
        +initialized;
        .print( "Starting Actor" ).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }