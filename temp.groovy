pipeline {
    agent any
    stages {
        stage('SCM Checkout') {
            steps {
                sh 'echo "checking..."'             
            }
        }
        
        stage('Angular build'){
            steps{
                sh 'echo "Building..."'
                // sh 'echo "Building..." ; exit 1'  
            }
        }
        stage('Build Docker Image'){
            steps{
                echo 'Dockering...'
            }
        }
        // stage('Send email') {
        //     steps{
        //         script{
        //             def mailRecipients = "your_recipients@company.com"
        //             def jobName = currentBuild.fullDisplayName

        //             emailext attachLog: true,
        //                 body: '''${JELLY_SCRIPT,template="html"}''',
        //                 mimeType: 'text/html',
        //                 subject: "[Jenkins] ${jobName}",
        //                 to: 'jeff.tang@asmpt.com',
        //                 recipientProviders: [[$class: 'CulpritsRecipientProvider']]
        //         }

        //     }
            
        // }
    }
    post {
        always {
            script{
                    def mailTo="jeff.tang@asmpt.com;aeetangcw@asmpt.com"

                    emailext attachLog: true,
                        body: '''${SCRIPT, template="groovy-html.template"}''',
                        mimeType: 'text/html',
                        subject: 'TESTING ' +'$DEFAULT_SUBJECT',
                        to: "${mailTo}",
                        recipientProviders: [requestor()]
                }
            // emailext attachLog: true, body: "CHANGE_AUTHOR = ${env.CHANGE_AUTHOR} JOB_BASE_NAME = ${env.JOB_BASE_NAME}", subject: "${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - Success", to: 'jeff.tang@asmpt.com'
        }
        // failure {
        //     emailext attachLog: true, body: 'Failure', subject: "This is subject", to: 'jeff.tang@asmpt.com'
        // }
    }
}