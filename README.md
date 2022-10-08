# spring-file-system

start db and application <br/>
docker-compose up --build

POST /files
```aidl
response 
{
    "id": 1
}
```

GET /files
```aidl
response 
{
    "files": [
        {
            "id": 1,
            "name": "test.pdf",
            "size": 142316
        },
        {
            "id": 2,
            "name": "test2.pdf",
            "size": 120855
        }
    ]
}
```

GET /files/{id} <br/>
response type filestream

DELETE /files/{id} <br/>
response void
