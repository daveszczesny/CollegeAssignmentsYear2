#!/bin/bash
chmod a+x ./creates.sh

# assigns first argument to variable id

if [ $# -eq 0 ]; then
    echo: "nok: no indentifier provided"
fi

id=$1

# checks
# checks if the directory users/$id exists
if [[ ! -d "users/$id" ]]; then # it doesn't
    mkdir "users/$id" # makes directory users/$id
    touch "users/$id/wall.txt" # creates file wall.txt
    touch "users/$id/friends.txt" # creates file friends.txt
    echo ok: user created!
    
    echo "SUCCESS: user created!" >> $id.pipe
    
elif [[ -d "users/"$id ]]; then # it does
    echo "ERROR: user already exists!" > $id.pipe

    echo nok: user already exists
elif [[ -z $id ]]; then # no argument given, $id is empty
    echo nok: no identifier provided
fi
