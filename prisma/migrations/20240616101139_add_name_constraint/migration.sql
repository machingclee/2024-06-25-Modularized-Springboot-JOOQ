/*
  Warnings:

  - You are about to alter the column `name` on the `Teacher` table. The data in that column could be lost. The data in that column will be cast from `Text` to `VarChar(30)`.

*/
-- AlterTable
ALTER TABLE "Teacher" ALTER COLUMN "name" SET DATA TYPE VARCHAR(30);
