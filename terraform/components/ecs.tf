resource "aws_ecs_cluster" "order_cluster" {
  name = "order-processing-cluster"
}

resource "aws_ecs_task_definition" "order_task" {
  family                   = "order-processing-task"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "512"
  memory                   = "1024"
  network_mode             = "awsvpc"

  container_definitions = jsonencode([
    {
      name  = "order-app"
      image = "your-docker-image-url"
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ]
    }
  ])
}

resource "aws_ecs_service" "order_service" {
  name            = "order-service"
  cluster         = aws_ecs_cluster.order_cluster.id
  task_definition = aws_ecs_task_definition.order_task.arn
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = [aws_subnet.public_subnet.id]
    security_groups = [aws_security_group.app_sg.id]
  }
}
