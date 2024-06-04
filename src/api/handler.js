const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

const getAllMushroomHandler = async (request, h) => {
    try {
        const jamur = await prisma.jamur.findMany();
        const gambar = await prisma.gambar.findMany();


        const data = jamur.map(jamurItem => {
            const relatedGambar = gambar.find(gambarItem => gambarItem.id === jamurItem.id);
            return {
              ...jamurItem,
              gambar1: relatedGambar ? relatedGambar.gambar1 : null,
              gambar2: relatedGambar ? relatedGambar.gambar2 : null,
              gambar3: relatedGambar ? relatedGambar.gambar3 : null
            };
          });

        const response = h.response({
            status : "success",
            data : data,
        });
        response.code(200) ;
        return response ;

    } catch (error) {
        const response = h.response({
            status : 'fail',
            message : error,
        })
        response.code(500);
        return response ;
    }
};



const getMushroombyIdHandler = async (request, h) => {
    try {
        const {jamurId} = request.params;

        const jamur = await prisma.jamur.findUnique({
            where: {
                id: parseInt(jamurId),
            },
        });

        const gambar = await prisma.gambar.findUnique({
            where: {
                id: parseInt(jamurId),
            },
        });

        const data = {
            ...jamur,
            gambar1: gambar ? gambar.gambar1 : null,
            gambar2: gambar ? gambar.gambar2 : null,
            gambar3: gambar ? gambar.gambar3 : null
        };

        if (!jamur) {
            // Jika data tidak ditemukan, kembalikan respons dengan status fail
            const response = h.response({
                status: "fail",
                message: `Jamur dengan id ${jamurId} tidak ditemukan`,
            });
            response.code(404);
            return response;
        }

        const response = h.response({
            status: "success",
            data: data
        });
        response.code(200) ;
        return response;
        
    } catch (error) {
        const response = h.response({
            status : 'fail',
            message : error,
        })
        response.code(500);
        return response ;
    }

}

module.exports = {
    getAllMushroomHandler,
    getMushroombyIdHandler
};