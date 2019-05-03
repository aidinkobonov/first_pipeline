node {
    stage("Pull Repo"){
        git 'git@github.com:aidinkobonov/cool_website.git'
    }
    stage("Webserver Install"){
        sh 'ssh ec2-user@54.171.211.32 sudo yum install httpd -y'
    }
}
