# Handle tests
./gradlew test --daemon

testResult=$?

# Perform checks
if [ $testResult -ne 0 ]
then
    echo "Tests failed to run, fix them to proceed with the commit"
    exit 1
fi


# Handle checks

./gradlew check --daemon

checkResult=$?

if [ $checkResult -ne 0 ]
then 
    exit 1
fi

exit 0