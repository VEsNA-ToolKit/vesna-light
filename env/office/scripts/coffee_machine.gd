extends Node3D

const PORT = 8090
var tcp_server := TCPServer.new()
var ws := WebSocketPeer.new()

func _ready() -> void:
	if tcp_server.listen( PORT ) != OK:
		push_error( "Unable to start the srver" )
		set_process( false )
		
func _process(delta: float) -> void:
	while tcp_server.is_connection_available():
		var conn : StreamPeerTCP = tcp_server.take_connection()
		assert( conn != null )
		ws.accept_stream( conn )
		
	ws.poll()
	
	if ws.get_ready_state() == WebSocketPeer.STATE_OPEN:
		while ws.get_available_packet_count():
			var msg : String = ws.get_packet().get_string_from_ascii()
			print( "Received msg ", msg )
			var intention : Dictionary = JSON.parse_string( msg )
			manage( intention )
			
func manage( intention : Dictionary ):
	print( "manage " + str( intention ) )
	if intention[ "type" ] == "interaction":
		var data = intention[ "data" ]
		if data[ "type" ] == "make_coffee":
			get_node( "CPUParticles3D" ).visible = true
			await get_tree().create_timer(2).timeout
			get_node( "CPUParticles3D" ).visible = false

func make_coffee():
	get_node( "CPUParticles3D" ).visible = true
	await get_tree().create_timer(5.0).timeout
	get_node( "CPUParticles3D" ).visible = false
