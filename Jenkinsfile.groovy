node {
    stage("Pull Repo"){
        git 'git@github.com:aidinkobonov/cool_website.git'
    }
    stage("Webserver Install"){
        sh 'ssh ec2-user@54.171.211.32 sudo yum install httpd -y'
    }
     stage("Index file"){
        sh 'scp index.html ec2-user@54.171.211.32:/tmp'
    }
    stage("Copy index file"){
        sh 'ssh ec2-user@54.171.211.32 "sudo mv /tmp/index.html  /var/www/html/index.html"'
    }
    stage("Restart Webserver"){
        sh 'ssh ec2-user@54.171.211.32 sudo systemctl restart httpd'

    }
}

