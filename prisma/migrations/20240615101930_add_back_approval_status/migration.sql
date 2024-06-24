-- CreateEnum
CREATE TYPE "CourseApprovalStatus" AS ENUM ('PENDING', 'APPROVED');

-- AlterTable
ALTER TABLE "Course" ADD COLUMN     "approvalStatus" "CourseApprovalStatus" NOT NULL DEFAULT 'PENDING';
