#!/bin/bash
chmod u+x ./sender.sh

# id is the first agrument
id=$1


while true; do
    ## waits for the user to enter in a command
    ## and sends it to the server pipe
    read req arg1 arg2
    echo "$req" "$id" "$arg1" "$arg2" > server.pipe

done