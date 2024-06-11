const {
    PrismaClient
} = require('@prisma/client');
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
            status: "success",
            data: data,
        });
        response.code(200);
        return response;

    } catch (error) {
        const response = h.response({
            status: 'fail',
            message: error,
        })
        response.code(500);
        return response;
    }
};



const getMushroombyIdHandler = async (request, h) => {
    try {
        const {
            jamurId
        } = request.params;

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
        response.code(200);
        return response;

    } catch (error) {
        const response = h.response({
            status: 'fail',
            message: error,
        })
        response.code(500);
        return response;
    }

}


const getAllRecipeHandler = async (request, h) => {

    try {
        const resepList = await prisma.resep.findMany({
            include: {
                langkah_buat: true
            }
        });

        const response = await Promise.all(resepList.map(async resep => {

            const bahanList = await prisma.bahan.findMany({
                where: {
                    id_resep: resep.id
                }
            });

            return {
                id: resep.id.toString(),
                nama: resep.nama,
                bahan: bahanList.map(b => b.bahan),
                gambar: resep.gambar,

            };
        }));


        return h.response({
            status: "success",
            data: response,
        }).code(200);

    } catch (error) {

        console.error(error);
        return h.response({
            status: 'fail',
            message: 'Terjadi kesalahan pada server'
        }).code(500);
    }
};


const getAllRecipebyIdHandler = async (request, h) => {

    const {
        resepId
    } = request.params;

    try {
        const resep = await prisma.resep.findUnique({
            where: {
                id: parseInt(resepId)
            },
            include: {
                langkah_buat: true
            }
        });

        if (!resep) {
            const response = h.response({
                status: "fail",
                message: `Jamur dengan id ${resepId} tidak ditemukan`,
            });
            response.code(404);
            return response;
        }

        const bahanList = await prisma.bahan.findMany({
            where: {
                id_resep: resep.id
            }
        });

        // Menggunakan split untuk memisahkan langkah-langkah
        const langkahList = resep.langkah_buat.flatMap(l => l.langkah.split('\n').map((line, index) => `${line.trim()}`));

        const response = {
            id: resep.id.toString(),
            nama: resep.nama,
            bahan: bahanList.map(b => b.bahan),
            langkah: langkahList,
            gambar: resep.gambar,
            waktu: resep.waktu,
            porsi: resep.porsi
        };

        return h.response({
            status: "success",
            data: response,
        }).code(200);

    } catch (error) {
        console.error(error);
        return h.response({
            error: 'Terjadi kesalahan pada server'
        }).code(500);
    }

};

const getMushroombyNameHandler = async (request, h) => {
    const { Jamur } = request.query; // Mengambil parameter query 'Jamur'
    const gambar = await prisma.gambar.findMany();

    try {
        if (!Jamur) {
            return h.response({
                status: "fail",
                message: 'Query parameter "Jamur" tidak ditemukan',
            }).code(400);
        }

        const mushrooms = await prisma.jamur.findMany({
            where: {
                nama: {
                    contains: Jamur.toLowerCase(), // Mengubah ke huruf kecil untuk pencarian tidak peka huruf besar/kecil
                },
            },
        });

        const data = mushrooms.map(jamurItem => {
            const relatedGambar = gambar.find(gambarItem => gambarItem.id === jamurItem.id);
            return {
                ...jamurItem,
                gambar1: relatedGambar ? relatedGambar.gambar1 : null,
                gambar2: relatedGambar ? relatedGambar.gambar2 : null,
                gambar3: relatedGambar ? relatedGambar.gambar3 : null
            };
        });

        if (mushrooms.length > 0) {
            return h.response({
                status: "success",
                data: data,
            }).code(200);
        } else {
            return h.response({
                status: "fail",
                message: 'Jamur tidak ditemukan',
            }).code(404);
        }

    } catch (error) {
        console.error(error);
        return h.response({
            status: 'error',
            message: 'Terjadi kesalahan pada server',
        }).code(500);
    }
};


module.exports = {
    getAllMushroomHandler,
    getMushroombyIdHandler,
    getAllRecipeHandler,
    getAllRecipebyIdHandler,
    getMushroombyNameHandler
};