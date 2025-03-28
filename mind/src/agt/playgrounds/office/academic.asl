// { include( "vesna.asl" ) }

+!go_to_work
    :   .my_name( Me ) & my_desk( MyDesk )
    <-  !go_to( MyDesk );
        .print( "I am at my desk" ).

+!work
    :   .my_name( Me ) & my_desk( MyDesk ) & at( Me, MyDesk )
    <-  .print( "I am working..." );
        .wait( 10000 ).

+!work
    :   .my_name( Me ) & my_desk( MyDesk )
    <-  .print( "I am not at my work station!" );
        !go_to_work;
        !work.

+!take_coffe
    :   .my_name( Me ) & not at( coffee_machine ) & not ntpp( Me, common )
    <-  .print( "Going to take a coffee" );
        !go_to( coffee_machine );
        !take_coffe.

+!take_coffe
    :   not grab( Cup )
    <-  !grab( Cup ).

+!take_coffe
    :   grab( Cup )
    <-  !use( coffee_machine );
        make_coffee( Cup );
        .wait(1000);
        !free( coffee_machine );
        .wait( 4000 );
        .print( "I drank a coffee!" ).

// +!take_coffe
//     <-  .print( "I'm taking a coffee" );
//         !use( coffee_machine );
//         .wait( 5000 );
//         .print( "Back to work!" );
//         !free( coffee_machine ).

+!speak_with( Privacy, Person, Performative, Msg )
    <-  get_location( Person, Location );
        !go_to( Location );
        .send( Privacy, Person, Performative, Msg ).

+!print( Stuff )
    :   my_desk( Desk ) & at( Desk )
    <-  .print( "I am printing ", Stuff, "...");
        .wait( 4000 );
        .print( "Printed!" ).

+!print( Stuff )
    <-  !go_to_work;
        !print( Stuff ).