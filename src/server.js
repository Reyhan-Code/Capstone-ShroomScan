const Hapi = require('@hapi/hapi');
require('dotenv').config();


// routes
const routes = require('./api/routes');



const init = async () => {
  const server = Hapi.server({
    port: process.env.PORT || 3000,
    host: process.env.HOST || '0.0.0.0',
    routes: {
      cors: {
        origin: ['*'],
      },
    },
  });


  server.route(routes);

  await server.start();

 
  console.log(`Server berjalan pada ${server.info.uri}`);
};


process.on('unhandledRejection', (err) => {
  console.log(err);
  process.exit(1);
});

init();