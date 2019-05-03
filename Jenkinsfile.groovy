node {
    properties([parameters([string(defaultValue: 'IP', description: 'Where should I build?', name: 'Env', trim: false)]), pipelineTriggers([pollSCM('* * * * *')])])
    stage("Pull Repo"){
        git 'git@github.com:aidinkobonov/cool_website.git'
    }
    stage("Webserver Install"){
        sh 'ssh ec2-user@${Env} sudo yum install httpd -y'
    }
     stage("Index file"){
        sh 'scp index.html ec2-user@${Env}:/tmp'
    }
    stage("Copy index file"){
        sh 'ssh ec2-user@${Env} "sudo mv /tmp/index.html  /var/www/html/index.html"'
    }
    stage("Restart Webserver"){
        sh 'ssh ec2-user@${Env} sudo systemctl restart httpd'

    }
}