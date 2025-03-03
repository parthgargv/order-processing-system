resource "aws_db_instance" "order_db" {
  identifier           = "order-db"
  allocated_storage    = 20
  engine              = "mysql"
  instance_class      = "db.t3.micro"
  username           = "admin"
  password           = "password"
  publicly_accessible = false
  skip_final_snapshot = true
}
