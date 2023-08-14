#!/bin/bash
chmod a+x ./display_wall.sh

id=$1
wall=$2

# checks if directory $id exists
if [[ -d "users/"$wall ]]; then
    less 'users/'$wall'/wall.txt' >> $id.pipe
else
    echo nok: user ’$id’ does not exist
fi
