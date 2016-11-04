angular.module('starter.services', [])

.factory('Chats', function() {
  // Might use a resource here that returns a JSON array

  // Some fake testing data
  var chats = [{
    id: 0,
    name: 'Marco',
    lastText: 'oie mira',
    face: 'img/marco.png'
  }, {
    id: 1,
    name: 'Persona',
    lastText: 'Seeeee',
    face: 'img/SI.png'
  }, {
    id: 2,
    name: 'Alersi',
    lastText: 'hice un golazo ayer',
    face: 'img/AlexisFace.PNG'
  }, {
    id: 3,
    name: 'Sanic',
    lastText: 'Gotta go fast',
    face: 'img/sonic.png'
  }, {
    id: 4,
    name: 'Viejo',
    lastText: 'xdxdXDXDXDxdxd',
    face: 'img/logan.png'
  }];

  return {
    all: function() {
      return chats;
    },
    remove: function(chat) {
      chats.splice(chats.indexOf(chat), 1);
    },
    get: function(chatId) {
      for (var i = 0; i < chats.length; i++) {
        if (chats[i].id === parseInt(chatId)) {
          return chats[i];
        }
      }
      return null;
    }
  };
});
