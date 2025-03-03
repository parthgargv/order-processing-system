pipeline {
    agent any
    environment {
        AWS_ACCOUNT_ID = '<AWS_ACCOUNT_ID>'
        AWS_REGION = 'us-east-1'
        ECR_REPO = 'order-processing'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Docker Build & Push') {
            steps {
                sh 'aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com'
                sh 'docker build -t $ECR_REPO .'
                sh 'docker tag $ECR_REPO:latest $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPO:latest'
                sh 'docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPO:latest'
            }
        }
        stage('Deploy to ECS') {
            steps {
                sh 'aws ecs update-service --cluster main --service order-processing-service --force-new-deployment'
            }
        }
    }
}
