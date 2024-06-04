/*
  Warnings:

  - Added the required column `jenis` to the `jamur` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE `jamur` ADD COLUMN `jenis` VARCHAR(191) NOT NULL;
