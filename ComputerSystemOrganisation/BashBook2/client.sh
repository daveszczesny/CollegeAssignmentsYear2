#!/bin/bash

id=$1

if [ -n id ]; then
    while true; do
        read req args
        if [ -n req  ]; then
            echo req args > test_pipe
        fi
    done
fi
