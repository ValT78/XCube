var app = require("express")();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var players = [];
var rooms = [];

server.listen(8080, function(){
    console.log("Server is now running");
});

io.on('connection', function(socket){
    if((players.length)%2 == 0){
    rooms.push([]);
    players.push(new player(socket.id, true, rooms.length-1));
    rooms[rooms.length-1].push(players[players.length-1]);
    sleep(500);
    }
    else{
    players.push(new player(socket.id, false, rooms.length-1));
    rooms[rooms.length-1].push(players[players.length-1]);
    }
    console.log("Player Connected!");
    socket.emit('socketID', {id: socket.id}, {bool : players[players.length -1].color});
    socket.on('coord',function(data){
        for(var k=0; k< players.length; k++){
                data.id = socket.id;
            if(players[k].id == socket.id && rooms[players[k].room].length > 1){
                if(players[k].id != rooms[players[k].room][0].id){
                io.to(rooms[players[k].room][0].id).emit('playerPlayed', data);
                }
                else{
                io.to(rooms[players[k].room][1].id).emit('playerPlayed', data);
                }
            }
        }
    });
    socket.on('disconnect', function(){
            console.log("Player Disconnected");
            for(var i = 0; i < players.length; i++){
                if(players[i].id == socket.id){
                    if(players[i].color == true){
                        rooms.splice(players[i].room,1)
                    }
                    players.splice(i, 1);
                    socket.disconnect(true);
                }
            }
        });

    if(rooms[rooms.length-1].length==2){
            sleep(1000);
            console.log("Game will start");
            io.to(rooms[rooms.length-1][0].id).emit('twoPlayers');
            socket.emit('twoPlayers');
    }

});

function player(id, color, room){
    this.id= id;
    this.color = color;
    this.room = room;
}
function sleep(milliseconds) {
  const date = Date.now();
  let currentDate = null;
  do {
    currentDate = Date.now();
  } while (currentDate - date < milliseconds);
}
