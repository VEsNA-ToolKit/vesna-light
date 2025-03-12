{ include( "vesna.asl" ) }
{ include( "playgrounds/office.asl" ) }


+!start
    :   .my_name( Me )
    <-  +ntpp( Me, senior_office_2 );
        +my_desk( senior_3_desk );
        .wait( 5000 );
        !go_to( open_office );
        .send( public, bob, tell, achieve( print( paper( exam, 2 ) ) ) );
        .wait( 2000 );
        !go_to_work.