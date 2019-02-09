properties([
  parameters([
    string(name: 'test_run_id', description: "Test Run Id"),
    string(name: 'test_obj_indx', description: 'Test Object Index'),
  ])
])

node {
  stage(params.test_run_id+ ' ' +params.test_obj_indx){
    sh "pre_build_task.sh"
  }
  def remote = [:]
    remote.host = 'cafy-2.cisco.com'
    remote.user = 'prabaniy'
    remote.allowAnyHosts = true

  stage('Connecting to SSH host and script execution'){
    sshScript remote: remote, script: "remote_host.sh"

  }
  stage('Executing groovy script'){
    load("script.groovy")
  }
  post {
    always {
      echo "Executing post build task"
      sh "post_build_task.sh"
   }
    }
}
