#!/bin/sh -eux
# provision devnet environment and user on centos linux 7.x.
# NOTE: this should be run with 'root' privilege.

# set default values for input environment variables if not set. -----------------------------------
# [OPTIONAL] devnet environment install parameters [w/ defaults].
aws_hostname="${aws_hostname:-appserver}"                               # [optional] hostname (defaults to 'appserver').
user_name="${user_name:-centos}"                                        # [optional] user name (defaults to 'centos').
set +x  # temporarily turn command display OFF.
user_password="${user_password:-}"                              # [optional] user password (defaults to 'cisco123').
set -x  # turn command display back ON.
user_group="${user_group:-centos}"                                      # [optional] user group (defaults to 'centos').
group_name="${group_name:-centos}"                                      # [optional] group name (defaults to 'centos').
tomcat_username="${tomcat_username:-centos}"                            # [optional] tomcat username (defaults to 'centos').
tomcat_group="${tomcat_group:-centos}"                                  # [optional] tomcat group (defaults to 'centos').
user_supplementary_groups="${user_supplementary_groups:-docker}"        # [optional] user supplementary groups (defaults to 'docker').
user_sudo_privileges="${user_sudo_privileges:-true}"                    # [optional] user sudo privileges (defaults to 'true').
user_docker_profile="${user_docker_profile:-true}"                      # [optional] user docker profile (defaults to 'true').
user_comment="${user_comment:-DevNet Cloud Kickstart Project user.}"    # [optional] user comment (defaults to 'DevNet Cloud Kickstart Project user.').
user_install_env="${user_install_env:-true}"                            # [optional] user install env (defaults to 'true').

# [OPTIONAL] appdynamics cloud kickstart home folder [w/ default].
kickstart_home="${kickstart_home:-/opt/appd-cloud-kickstart}"

# export environment variables. --------------------------------------------------------------------
export aws_hostname
export user_name
export user_password
export user_group
export group_name
export tomcat_username
export tomcat_group
export user_supplementary_groups
export user_sudo_privileges
export user_docker_profile
export user_comment
export user_install_env

export kickstart_home

# check-out appd-cloud-kickstart project from github.
cd /opt
git clone https://github.com/Appdynamics/AppD-Cloud-Kickstart.git appd-cloud-kickstart

# provision the devnet environment.
cd /opt/appd-cloud-kickstart

# install general system tools and applicatons.
./provisioners/scripts/aws/config_al2_system_hostname.sh
./provisioners/scripts/centos/install_centos7_repositories.sh
./provisioners/scripts/centos/install_centos7_kickstart_tools.sh
./provisioners/scripts/common/install_aws_corretto_java_jdk_8.sh
./provisioners/scripts/common/install_aws_corretto_java_jdk_11.sh
./provisioners/scripts/common/install_oracle_java_jdk_14.sh
./provisioners/scripts/common/install_docker_compose.sh
./provisioners/scripts/centos/install_centos7_python3.sh
./provisioners/scripts/centos/install_centos7_python3_scl.sh
./provisioners/scripts/common/install_jq_json_processor.sh
./provisioners/scripts/centos/install_centos7_git.sh
./provisioners/scripts/common/install_git_flow.sh
./provisioners/scripts/centos/install_centos7_vim_8.sh
./provisioners/scripts/common/install_apache_ant.sh
./provisioners/scripts/common/install_apache_maven.sh
./provisioners/scripts/common/install_gradle.sh
./provisioners/scripts/centos/install_centos7_oracle_mysql_community_server_57.sh
./provisioners/scripts/common/initialize_supercar_trader_application_db.sh
./provisioners/scripts/centos/install_centos7_phantomjs_headless_browser.sh

# configure 'root' shell and user tools.
./provisioners/scripts/common/install_root_user_env.sh

# create 'centos' user and configure shell and user tools.
#./provisioners/scripts/centos/create_centos7_group.sh
#./provisioners/scripts/centos/create_centos7_user.sh

# tomcat is installed and by default runs as user 'centos'.
./provisioners/scripts/centos/install_centos7_apache_tomcat_7.sh

# NOTE: after nvm, node.js, and npm are installed--this will fail trying to update npm.
# uncomment last 2 lines after opening npm outbound connection to: https://registry.npmjs.org/npm
./provisioners/scripts/common/install_nodejs_javascript_runtime.sh
#./provisioners/scripts/common/install_serverless_framework_cli.sh
#./provisioners/scripts/common/install_appdynamics_nodejs_serverless_tracer.sh

# unset environment variables. ---------------------------------------------------------------------
unset aws_hostname
unset user_name
unset user_password
unset user_group
unset group_name
unset tomcat_username
unset tomcat_group
unset user_supplementary_groups
unset user_sudo_privileges
unset user_docker_profile
unset user_comment
unset user_install_env

unset kickstart_home
