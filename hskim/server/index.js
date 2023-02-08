const express = require('express')
const socketio = require('socket.io')
const http = require('http')

const cors = require('cors')
const router = require('./router')
const { addUser, removeUser, getUser, getUsersInRoom } = require('./users.js')

const PORT = process.env.PORT || 5000

const app = express()
const server = http.createServer(app)
const io = socketio(server, {
    cors:{
        origin:"*",
        methods:["GET","POST"]
    }
})
app.use(cors())
app.use(router)

io.on('connection', (socket) => {
  console.log('새로운 connection이 발생하였습니다.')
  socket.on('join', ({ name, room }, callback) => {
    const { error, user } = addUser({ id: socket.id, name, room })
    if (error) callback({ error: '에러가 발생했어요.' })

    socket.emit('message', {
      user: 'admin',
      text: `${name}, ${room}에 오신것을 환영합니다.`,
    })
    socket.broadcast.to(room).emit('message', {
      user: 'admin',
      text: `${name} 님이 가입하셨습니다.`,
    })
    io.to(room).emit('roomData', {
      room: room,
      users: getUsersInRoom(room),
    })
    socket.join(room)

    callback()
  })
  socket.on('sendMessage', (props) => {
    const user = getUser(socket.id)

    io.to(props.room).emit('message', { user: props.name, text: props.message })
    
  })
  socket.on('disconnect', ({name,room},message) => {
    const user = removeUser(socket.id)

    if (user) {
      io.to(room).emit('message', {
        user: 'Admin',
        text: `${name} 님이 방을 나갔습니다.`,
      })
      io.to(room).emit('roomData', {
        room: room,
        users: getUsersInRoom(room),
      })
    }
    console.log('유저가 떠났어요.')
  })
})
server.listen(PORT, () => console.log(`서버가 ${PORT} 에서 시작되었어요`))