const { GraphQLServer } = require('graphql-yoga')
const Resolver = require('./resolver')

const typeDefs = './src/schema.graphql'
const resolvers = Resolver


const server = new GraphQLServer({
  typeDefs,
  resolvers,
})
server.start(() => console.log(`Server is running on http://localhost:4000`))

