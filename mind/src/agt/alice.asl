{ include( "office_map.asl" ) }
{ include( "academic.asl" ) }

// // +!start
// //     :   error( Reason ) & .my_name( Me )
// //     <-  .print( Reason );
// //         .kill( Me ).

+!start
    :   .my_name( Me )
    <-  +ntpp( Me, senior_office_2 );
        +my_desk( senior_3_desk );
        .wait( 5000 );
        !go_to( open_office );
        // // kqml_s.send( public, bob, tell, achieve( print( paper( exam, 2 ) ) ) );
        .send( public, bob, tell, achieve( print( paper( exam, 2 ) ) ) );
        .wait( 2000 );
        !go_to_work.