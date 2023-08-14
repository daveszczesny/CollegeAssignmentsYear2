#!/bin/bash
chmod a+x ./add_friend.sh

# we take in $id $friend
# check if $id exists
# check if $friend exists
# check if they are already friends

# assign variables to args
id=$1
friend=$2

# conditionals
if [ ! -d "users/"$id ]; then
	echo "nok: user $id does not  exist"

#if the user $friend exists
elif [ ! -d "users/"$friend ]; then 
	echo "nok: user $friend does not exist"
	echo "Could not add $friend as they do not exist" >> $id.pipe

else
	#if the friends list exists and $friend is not in it already
	#We should find the exact name 
	# i.e., username that starts and ends the line (to avoid finding a friend with a larger username)
	if ! grep "^$friend$" "users/"$id"/friends.txt" > /dev/null; then 
		echo $friend >> "users/"$id/"friends.txt"
		echo "ok: friend added!"
		echo "Friend added" >> $id.pipe
        
	else
		echo "nok: could not add $friend as friend"
	fi	
fi
