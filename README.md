# Capstone-ShroomScan

# C241-PS132 API
Dokumentasi API yang digunakan pada Capstone Project C241-PS132 shroomscan

## How to Run Application
`npm install`
`npm run start`

### api-key
Untuk mengambil data dari Api harus menggunakan api-key di bagian header,jika tidak ada api-key ma akan mengembalikan json
- ```json
        {
            "message": "Invalid API key"
        }

### Mendapatkan seluruh data jamur
- Method: `GET`
- Path: `/mushrooms`
- Deskripsi: Mendapatkan seluruh data jamur.
- Mengembalikan Data:
  ```json
    {
    "status": "success",
    "data": [
        {
            "id": int,
            "nama": "str",
            "jenis": "strr",
            "deskripsi": "strur",
            "media_tanam": "str",
            "gambar1": "str",
            "gambar2": "str",
            "gambar3": "str"
        },
        {
	    .................
	    .................
	    },
	  ]
    }
    ```
 
###  Mendapatkan detail data jamur
- Method: `GET`
- Path: `/mushrooms/{id}`
- Deskripsi: Mengambil detail data jamur
- Mengembalikan Data:

Jika datanya tidak ada 
 - ```json
  
    {
        "status": "fail",
        "message": "Jamur dengan id {id} tidak ditemukan"
    }

Jika datanya ada 

- ```json
    {
    "status": "success",
    "data": {
        "id": "int",
        "nama": "str",
        "jenis": "str",
        "deskripsi": "str",
        "gambar1": "str",
        "gambar2": "str",
        "gambar3": "str"
        }
    }

###  Mendapatkan seluruh data resep

- Method: `GET`
- Path: `/recipes`
- Deskripsi: Mengambil detail data resep
- Mengembalikan Data:
    ```json
    {
        "status": "success",
        "data": [
            {
                "id": "int",
                "nama": "str",
                "bahan": [
                    "str",
                    "str",
                    "str",
                    "str",
                    "str"
                ],
                "gambar": "str"
            },
        	{
        	...............
        	...............
        	},
        	...............
        	...............
        ]
    }
  ```
  
###  Mendapatkan detail data resep
- Method: `GET`
- Path: `/recipes/{id}`
- Deskripsi: Mengambil detail data jamur
- Mengembalikan Data:

Jika datanya tidak ada
 - ```json
        {
            "status": "fail",
            "message": "Resep dengan id {id} tidak ditemukan"
        }

Jika datanya ada
 - ```json
    {
        "status": "success",
        "data": {
            "id": "int",
            "nama": "str",
            "bahan": [
                "str",
                "str",
                "str",
                "str"
            ],
            "langkah": [
                "text",
                "text",
                "text",
                "text"
            ],
            "gambar": "str",
            "waktu": "str",
            "porsi": "str"
        }
    }

###  Mendapatkan data jamur dengan nama jamur tertentu 
- Method: `GET`
- Path: `/mushrooms?Jamur = {nama jamur}`
- Deskripsi: Mengambil data jamur dengan nama jamur tertentu
- Mengembalikan Data:

Jika datanya ada 
- ```json
        {
            "status": "success",
            "data": [
                {
                    "id": "int,
                    "nama": "str",
                    "jenis": "str",
                    "deskripsi": "str",
                    "media_tanam": "str",
                    "gambar1": "str",
                    "gambar2": "str",
                    "gambar3": "str"
                },
        	{
        	.......................
        	.......................
        	},
        	.......................
        	.......................
        }

Jika datanya tidak ada
- ```json
    {
        "status": "fail",
        "message": "Jamur tidak ditemukan"
    }

