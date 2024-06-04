-- CreateTable
CREATE TABLE `jamur` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `nama` VARCHAR(191) NOT NULL,
    `deskripsi` VARCHAR(191) NOT NULL,
    `media_tanam` VARCHAR(191) NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- CreateTable
CREATE TABLE `gambar` (
    `id` INTEGER NOT NULL AUTO_INCREMENT,
    `gambar1` VARCHAR(191) NOT NULL,
    `gambar2` VARCHAR(191) NOT NULL,
    `gambar3` VARCHAR(191) NOT NULL,

    PRIMARY KEY (`id`)
) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
