-- CreateTable
CREATE TABLE "Course" (
    "id" UUID NOT NULL,
    "name" TEXT NOT NULL,
    "category" TEXT NOT NULL,

    CONSTRAINT "Course_pkey" PRIMARY KEY ("id")
);
