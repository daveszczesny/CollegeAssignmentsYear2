#!/bin/bash
chmod a+x ./post_messages.sh

# we take in $id $friend
# check if $id exists
# check if $friend exists
# check if they are already friends

# assign variables to args
sender=$1
receiver=$2
msg=${@:3}

DATE=`date +%d-%m-%Y`

# if statment
if [[ -d "users/"$sender ]]; then # checks if $id exists    
    if [[ -d "users/"$receiver ]]; then # checks if $friend exists
        # check if they are already friends
        # if grep -q text file
        if grep -q $receiver "users/"$sender"/friends.txt";  # -q makes it quiet :)
        then
            # if they are friends send message to $receiver's walls
            # print msg to the file file
            echo -e $sender"[$DATE]: " $msg >> "users/"$receiver"/wall.txt"
            echo ok: message posted!
        # if the $sender and $receiver are the same, aka posting on your own wall
        elif [[ $sender == $receiver ]];
        then
            # add message to wall
            echo -e $sender"[$DATE]: " $msg >> "users/"$receiver"/wall.txt"
            echo ok: message posted!
        # if $sender and $receiver are not friends or different error occures.
        else
            echo nok: user ’$sender’ is not a friend of ’$receiver’
        fi
    else
        echo nok: user ’$friend’ does not exist
    fi
else
echo nok: user ’$sender’ does not exist
fi