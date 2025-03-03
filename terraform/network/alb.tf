resource "aws_lb" "order_alb" {
  name               = "order-alb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.app_sg.id]
  subnets            = [aws_subnet.public_subnet.id]
}

resource "aws_lb_target_group" "order_tg" {
  name     = "order-tg"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = aws_vpc.order_vpc.id
}

resource "aws_lb_listener" "http" {
  load_balancer_arn = aws_lb.order_alb.arn
  port              = 80
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.order_tg.arn
  }
}
