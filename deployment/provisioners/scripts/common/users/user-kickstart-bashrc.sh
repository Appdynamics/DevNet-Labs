# .bashrc
# bash resource configuration for kickstart users.

# source global definitions.
if [ -f /etc/bashrc ]; then
  . /etc/bashrc
fi

# Uncomment the following line if you don't like systemctl's auto-paging feature:
# export SYSTEMD_PAGER=

# user specific aliases and functions.
umask 022

# set java home path.
JAVA_HOME=/usr/local/java/jdk180
#JAVA_HOME=/usr/local/java/jdk11
export JAVA_HOME

# set git home paths.
GIT_HOME=/usr/local/git/git
export GIT_HOME
GIT_FLOW_HOME=/usr/local/git/gitflow
export GIT_FLOW_HOME

# set appd kickstart home path.
kickstart_home=/opt/appd-cloud-kickstart
export kickstart_home

# set kubectl config path.
KUBECONFIG=$KUBECONFIG:$HOME/.kube/config
export KUBECONFIG

# define prompt code and colors.
reset='\[\e]0;\w\a\]'

black='\[\e[30m\]'
red='\[\e[31m\]'
green='\[\e[32m\]'
yellow='\[\e[33m\]'
blue='\[\e[34m\]'
magenta='\[\e[35m\]'
cyan='\[\e[36m\]'
white='\[\e[0m\]'

# define command line prompt.
#PS1="\h[\u] \$ "
#PS1="$(uname -n)[$(whoami)] $ "
#PS1="${reset}${blue}\h${magenta}[${cyan}\u${magenta}]${white}\$ "
PS1="${reset}${cyan}\h${blue}[${green}\u${blue}]${white}\$ "
export PS1

# add local applications to main PATH.
PATH=$JAVA_HOME/bin:$GIT_HOME/bin:$GIT_FLOW_HOME/bin:$HOME/.local/bin:$PATH
export PATH

# set options.
set -o noclobber
set -o ignoreeof
set -o vi

# set environment variables to configure command history.
HISTSIZE=16384
export HISTSIZE
HISTCONTROL=ignoredups
export HISTCONTROL

# define system alias commands.
alias back='cd $OLDPWD; pwd'
alias c=clear
#alias gvim='gvim -u $HOME/.vim/vimrc.vim'
alias here='cd $here; pwd'
alias kickstarthome='cd $kickstart_home; pwd'
alias more='less'
alias there='cd $there; pwd'
alias vi='vim -u $HOME/.vim/vimrc.vim'
alias vim='vim -u $HOME/.vim/vimrc.vim'

# fix issue with bash shell tab completion.
complete -r

# process bash completion files.
bcfiles=( .docker-completion.sh .git-completion.bash )

for bcfile in ${bcfiles[@]}; do
  # source bash completion file.
  if [ -f $HOME/${bcfile} ]; then
    . $HOME/${bcfile}
  fi
done

function lsf {
  echo ""
  pwd
  echo ""
  ls -alF $@
  echo ""
}

function psgrep {
  ps -ef | grep "UID\|$@" | grep -v grep
}

function netstatgrep {
  netstat -an | grep "Active\|Proto\|$@"
}
