const MushroomHandler = require('./handler');
const validApiKeys = [process.env.KEY]; 

const routes = [
    {
        method: 'GET',
        path: '/mushrooms',
        handler: async (request, h) => {
            const apiKey = request.headers['api-key'];
            if (!apiKey || !validApiKeys.includes(apiKey)) {
              return h.response({ message: 'Invalid API key' }).code(401);
            }
            try {
              const response = await MushroomHandler.getAllMushroomHandler(request, h);
              return response;
            } catch (error) {
              console.error('Error handling request:', error);
              return h.response({ message: 'Internal server error' }).code(500);
            }
          }
    },
    {
        method: 'GET',
        path: '/mushrooms/{jamurId}',
        // handler: MushroomHandler.getMushroombyIdHandler,
        handler: async (request, h) => {
          const apiKey = request.headers['api-key'];
          if (!apiKey || !validApiKeys.includes(apiKey)) {
            return h.response({ message: 'Invalid API key' }).code(401);
          }
          try {
            const response = await MushroomHandler.getMushroombyIdHandler(request, h);
            return response;
          } catch (error) {
            console.error('Error handling request:', error);
            return h.response({ message: 'Internal server error' }).code(500);
          }
        }
    },
    {
        method: 'GET',
        path: '/recipes',
        // handler: MushroomHandler.getAllRecipeHandler,
        handler: async (request, h) => {
          const apiKey = request.headers['api-key'];
          if (!apiKey || !validApiKeys.includes(apiKey)) {
            return h.response({ message: 'Invalid API key' }).code(401);
          }
          try {
            const response = await MushroomHandler.getAllRecipeHandler(request, h);
            return response;
          } catch (error) {
            console.error('Error handling request:', error);
            return h.response({ message: 'Internal server error' }).code(500);
          }
        }
    },
    {
        method: 'GET',
        path: '/recipes/{resepId}',
        // handler: MushroomHandler.getAllRecipebyIdHandler,
        handler: async (request, h) => {
          const apiKey = request.headers['api-key'];
          if (!apiKey || !validApiKeys.includes(apiKey)) {
            return h.response({ message: 'Invalid API key' }).code(401);
          }
          try {
            const response = await MushroomHandler.getAllRecipebyIdHandler(request, h);
            return response;
          } catch (error) {
            console.error('Error handling request:', error);
            return h.response({ message: 'Internal server error' }).code(500);
          }
        }
    },
    {
        method: 'GET',
        path: '/mushrooms/name',
        handler: MushroomHandler.getMushroombyNameHandler,
    },

];


module.exports = routes;