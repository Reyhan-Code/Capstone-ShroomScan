const MushroomHandler = require('./handler');

const routes = [
    {
        method: 'GET',
        path: '/mushrooms',
        handler: MushroomHandler.getAllMushroomHandler,
    },
    {
        method: 'GET',
        path: '/mushrooms/{jamurId}',
        handler: MushroomHandler.getMushroombyIdHandler,
    }
];

module.exports = routes;