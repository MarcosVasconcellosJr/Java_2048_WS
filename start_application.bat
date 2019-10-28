cd C:\
mkdir logWS

ssh -R 80:localhost:8080 serveo.net > C:\logWS\ssh_output.txt