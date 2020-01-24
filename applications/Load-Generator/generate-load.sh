DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo $DIR
CLASSPATH=$DIR/grinder-3.11/lib/grinder.jar 
export CLASSPATH
echo $CLASSPATH
cd $DIR/grinder-3.11/supercars/
java net.grinder.Grinder

