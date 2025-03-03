resource "aws_cloudwatch_log_group" "order_logs" {
  name = "/ecs/order-processing"
}
