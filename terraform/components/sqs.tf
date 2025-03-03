resource "aws_sqs_queue" "order_queue" {
  name                      = "order-queue"
  delay_seconds             = 0
  visibility_timeout_seconds = 30
}
