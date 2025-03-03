resource "aws_autoscaling_group" "order_asg" {
  vpc_zone_identifier  = [aws_subnet.public_subnet.id]
  desired_capacity     = 2
  min_size            = 1
  max_size            = 5
  health_check_type    = "EC2"

  launch_template {
    id      = aws_launch_template.order_lt.id
    version = "$Latest"
  }
}

resource "aws_launch_template" "order_lt" {
  name_prefix   = "order-lt"
  image_id      = "ami-1234567890abcdefg" # Replace with valid AMI ID
  instance_type = "t3.micro"
  key_name      = "your-key-pair"

  network_interfaces {
    associate_public_ip_address = true
    security_groups             = [aws_security_group.app_sg.id]
  }

  user_data = base64encode(<<-EOF
              #!/bin/bash
              echo "Starting Order Processing App"
              java -jar /opt/app/order-processing.jar
              EOF
  )
}
