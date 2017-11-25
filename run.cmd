@echo off
if not exist "build\list.jar" (
    @echo on
    echo "Please build the project first"
    @echo off
) else (
    "%JAVA_HOME%\bin\java" -jar build\list.jar %*
)