{ include( "vesna.asl" ) }
{ include( "playgrounds/office.asl" ) }

+!start
    :   .my_name( Me )
    <-  +ntpp( Me, reception );
        +my_desk( junior_11_desk );
        .wait( 1000 );
        !grab( cup2 );
        !go_to( coffee_machine );
        !grab( cup2 );
        !take_coffe;
        .wait( 2000 );
        !go_to_work;
        !release( cup2 ).