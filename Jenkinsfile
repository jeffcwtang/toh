pipeline {
    agent none
    stages {
        stage('Build in PC_11'){
            agent {label 'ATW_LAB_PC11'}
            stages{
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
                    steps{
                        bat "docker build -t 10.18.30.15:5000/angulartest ."
                    }
                }
                stage('Push Docker Image'){
                    steps{
                        bat "docker push 10.18.30.15:5000/angulartest"
                    }
                }
            }
        }
        
        stage('Angular deploy'){
            agent {label 'ATW_LAB_PC11'}
            steps{
                // Remove below two lines for the first build
                bat "docker stop testsite"
                bat "docker rm testsite"
                bat "docker run -p 8081:80 -d --restart=always --name testsite 10.18.30.15:5000/angulartest"
            }
        }
    }
    post {
        always {
            script{
                // Provide mulitple emails, seperate by ";" 
                def mailTo="jeff.tang@asmpt.com"

                emailext attachLog: true,
                    body: '''${SCRIPT, template="groovy-html.template"}''',
                    mimeType: 'text/html',
                    subject: '$DEFAULT_SUBJECT',
                    to: "${mailTo}",
                    recipientProviders: [requestor()]
            }
        }
    }
}