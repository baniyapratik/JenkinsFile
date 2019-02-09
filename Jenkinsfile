// download SSH pipeline plugin
// download Publish over SSH

properties([
  parameters([
    string(name: 'test_run_id', description: "Test Run Id"),
    string(name: 'test_obj_indx', description: 'Test Object Index'),
  ])
])

node {
  def myrepo = checkout scm
  environment {
        test_run_id = params.test_run_id
        test_obj_indx  = params.test_obj_indx
    }

  stage(params.test_run_id+ ' ' +params.test_obj_indx){
    sh "./pre_build_task.sh"
  }
  def remote = [:]
    remote.host = 'cafy-2.cisco.com'
    remote.allowAnyHosts = true

  withCredentials([sshUserPrivateKey(credentialsId: '4852b721-e542-4c11-84c5-308dcc044650', passphraseVariable: 'cisco123', usernameVariable: 'prabaniy')]) {
      remote.user = 'prabaniy'
      stage("SSH Steps Rocks!") {
            sshScript remote: remote, script: "remote_host.sh"
        }
    }

  stage('Executing groovy script'){
    echo "groovy stage"
    load("script.groovy")
  }
  post {
    always {
      echo "Executing post build task"
      sh "post_build_task.sh"
   }
    }
}
