{ include( "vesna.asl" ) }
{ include( "playground.asl" ) }

+!start
    :   .my_name( Me )
    <-  +ntpp( Me, reception );
        +my_desk( junior_10_desk );
        .wait( 1000 );
        !take_coffe;
        .wait( 2000 );
        !go_to_work.