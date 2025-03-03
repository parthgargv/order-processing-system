output "alb_dns" {
  value = aws_lb.order_alb.dns_name
}

output "db_endpoint" {
  value = aws_db_instance.order_db.endpoint
}

output "sqs_queue_url" {
  value = aws_sqs_queue.order_queue.url
}

output "ecs_cluster_name" {
  value = aws_ecs_cluster.order_cluster.name
}
