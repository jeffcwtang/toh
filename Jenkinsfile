pipeline {
    agent {label 'ATW_LAB_PC11'}
    stages {
        stage('SCM Checkout') {
            steps {
                git 'https://github.com/jeffcwtang/toh.git'
                
            }
        }
        stage('Angular build'){
            steps{
                bat '''npm install'''
                bat '''ng build --configuration production'''
            }
        }
        stage('Build Docker Image'){
            agent {label 'ATW_LAB_PC11'}
            steps{
                bat "docker build -t 10.18.30.15:5000/angulartest ."
            }
        }
        stage('Push Docker Image'){
            agent {label 'ATW_LAB_PC11'}
            steps{
                bat "docker push 10.18.30.15:5000/angulartest"
            }
        }
        
    }
    agent {label 'ATW_LAB_PC11'}
    stages {
            stage('Angular deploy'){
            steps{
                // bat "'C:\\Program Files\\Docker\\Docker\\DockerCli.exe' -SwitchLinuxEngine"
                // bat "docker build -t angulartest ."
                bat "docker stop testsite"
                bat "docker rm testsite"
                bat "docker run -p 8081:80 -d --restart=always --name testsite 10.18.30.15:5000/angulartest"
            }
        }
    }
}