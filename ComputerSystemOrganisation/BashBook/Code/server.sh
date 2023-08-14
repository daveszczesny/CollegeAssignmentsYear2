#!/bin/bash



ctrl_c() {
    # clean up code

    rm server.pipe
    rm -r users/
    exit 0
}

# traps the ctrl c command to run above function
trap ctrl_c INT

# the program needs the users folder to run
# if it doesn't exist it will create it
if [[ ! -d "users" ]]; then
    mkdir "users"
fi


mkfifo server.pipe  # server pipe

# runs while true
while true; do
    # waiting for user input
    read req arg0 arg1 arg2 < server.pipe

    case $req in
        # if user input "create arg0"
        create)
            source ./creates.sh "$arg0"
            ;;
        # if user input "add arg0 arg1"
        add)
            source ./add_friend.sh "$arg0" "$arg1"
            ;;
        # if user input "post arg0 arg1 arg2"
        post)
            ./post_messages.sh "$arg0" "$arg1" "$arg2"
            ;;
        # if user input "display"
        display)
            ./display_wall.sh "$arg0" "$arg1"
            ;;
        # if user input "exit"
        exit)
            echo "Exiting..."
            exit 1
            ;;
        # anything else
        *)
            echo "Accepted commands : (create|add|post|display)"
            echo "To exit program: (Ctrl+c|exit)"
            ;;
    esac

done
