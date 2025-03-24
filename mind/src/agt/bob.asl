{ include( "vesna.asl" ) }
{ include( "playgrounds/office.asl" ) }

+!start
    :   .my_name( Me )
    <-  +ntpp( Me, reception );
        +my_desk( junior_10_desk );
        .wait( 1000 );
        !grab( cup );
        !go_to( common );
        !grab( cup );
        !take_coffe;
        .wait( 2000 );
        !go_to_work;
        !release( cup ).