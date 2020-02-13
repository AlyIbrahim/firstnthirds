const fetch  = require("node-fetch")

const Query = {
      users: () => { return fetch(`http://0.0.0.0:8181/user`).then(res => res.json())   },
      user: (parent, args) => { return fetch(`http://0.0.0.0:8181/user/${args.id}` ).then(res => res.json()) },
      teams: () => { return fetch(`http://0.0.0.0:8282/team`).then(res => res.json())   },
      team: (parent, args) => { return fetch(`http://0.0.0.0:8282/team/${args.id}` ).then(res => res.json()) },
      userteams: () => { return fetch(`http://0.0.0.0:8282/userteam`).then(res => res.json()).then()   },
      userteam: (parent, args) => { return fetch(`http://0.0.0.0:8282/userteam/${args.id}`).then(res => res.json()).then()}, 
      events: () => { return fetch(`http://0.0.0.0:8383/event`).then(res => res.json())   },
      event: (parent, args) => { return fetch(`http://0.0.0.0:8383/event/${args.id}` ).then(res => res.json()) },
      userevents: () => { return fetch(`http://0.0.0.0:8181/userevent`).then(res => res.json())},
      userevent: (parent, args) => { return fetch(`http://0.0.0.0:8181/userevent/${args.id}`).then(res => res.json())}
    }
  
const UserTeam = {
      user: (parent, args) => { return fetch(`http://0.0.0.0:8181/user/${parent.user}` ).then(res => res.json()) }
    }
  
const UserEvent = {
      event: (parent, args) => { return fetch(`http://0.0.0.0:8383/event/${parent.event}` ).then(res => res.json()) }
    }

const Mutation = {
    }

  module.exports = {Query, Mutation, UserTeam, UserEvent};