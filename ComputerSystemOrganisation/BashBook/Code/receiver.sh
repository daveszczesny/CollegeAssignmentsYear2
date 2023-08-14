#!/bin/bash
chmod u+x ./receiver.sh

# id is the first agrument
id=$1

# receiver loop
while true; do

    ## if user pipe exists
    if [ -e $id.pipe ]; then

        # read from user pipe and print if there is anything on it
        read res < $id.pipe
        echo "$res"
    fi

done