ntpp( charlie, regione_x ).
ntpp( david, kitchen ).

+!start
    :   .my_name( Me) & ntpp( Me, MyRegion )
    <-  .print( "Hello" );
        .random( X );
        .wait( X * 1000 );
        !use( "coffee_machine" );
        .wait( X * 2000 );
        !free( "coffee_machine" );
        -+ntpp( Me, kitchen );
        .random( Y );
        .wait( Y * 1000 );
        !use( "coffee_machine" );
        !grab( "cup" ).

{ include( "vesna.asl" ) }

// { include("$jacamoJar/templates/common-cartago.asl") }
// { include("$jacamoJar/templates/common-moise.asl") }