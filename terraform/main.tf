terraform {
  required_version = ">= 1.0"
  backend "s3" {
    bucket         = "terraform-state-bucket"
    key            = "order-processing/terraform.tfstate"
    region         = "us-east-1"
    encrypt        = true
  }
}

module "vpc" {
  source = "network/vpc.tf"
}

module "ecs" {
  source = "components/ecs.tf"
}

module "rds" {
  source = "components/rds.tf"
}

module "sqs" {
  source = "components/sqs.tf"
}

module "security" {
  source = "network/security_groups.tf"
}

module "autoscaling" {
  source = "network/autoscaling.tf"
}

module "alb" {
  source = "network/alb.tf"
}

module "cloudwatch" {
  source = "components/cloudwatch.tf"
}

module "s3" {
  source = "components/s3.tf"
}
