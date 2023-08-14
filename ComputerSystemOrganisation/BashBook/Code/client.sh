#!/bin/bash


# assigns first variable as id
id=$1

# checks if id variable is not empty
# creates a custom pipe for the user
# then runs server command to initiliase user
if [ -n "$id" ]; then
    mkfifo "$id".pipe
    echo "create $id" > server.pipe
fi


## code to clean up after user exits
ctrl_c() {
    # clean up code
    rm $id.pipe
    rm -r users/$id/
    exit 0
}
# traps the ctrl c command to instead of interrupting the program it cleans up and then exists
trap ctrl_c INT

## Main client side while loop.
# runs two seperate files
# 1) receiver.sh reads from the server pipe and displays it to the user.
# 2) sendder.sh waits for the user to enter in a command and then sends it to the server to be executed
while true; do
    source ./receiver.sh $id & source ./sender.sh $id
    
done

