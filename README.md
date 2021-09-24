# Hifos API Server

    테스트 목적으로 만든 API Server 입니다.
    User 의 할 일을 등록하고 , 조회 및 수정 삭제를 할 수 있습니다.

## REST API

|HTTP METHOD|URL|
|-------------|-------------|
|POST|localhost:9100/hifos-api/v1/user-todo|
|GET|localhost:9100/hifos-api/v1/user-todo|
|GET|localhost:9100/hifos-api/v1/user-todo/user/{userId} |
|PUT|localhost:9100/hifos-api/v1/user-todo/todo/{id} |
|DELETE|localhost:9100/hifos-api/v1/user-todo/todo/{id} |
|DELETE|localhost:9100/hifos-api/v1/user-todo/todo/all |

### 1. POST parameter
    
    ex) ../hifos-api/v1/user-todo/
    
    parameter(JSON TYPE)
    {
	    "userId" : "TEST1" ,
	    "todoContent" : "Server 구성"
    }

### 2. GET parameter & result

    ex) ../hifos-api/v1/user-todo/user/{userId}
    등록된 userId 를 URL 정보에 포함 시킨다.

    result(JSON TYPE)
    [
        {
            "id": 1,
            "userId": "TEST1",
            "todoContent": "Server 구성",
            "isCompleted": "Y",
            "regDate": "2021-09-24 15:03:48"
        },
        {
            "id": 2,
            "userId": "TEST1",
            "todoContent": "Android 구성",
            "isCompleted": "Y",
            "regDate": "2021-09-24 15:03:52"
        }
    ]

### 3. PUT parameter & result

    ex) ../hifos-api/v1/user-todo/todo/{id}
    수정할 id 를 URL 정보에 포함 시키고
    변경할 내용을 Body에 포함

    parameter(JSON TYPE)
    {
        "userId" : "TEST1" ,
        "todoContent" : "Server 구성" , 
        "isCompleted" : "Y"
    }

    result(JSON TYPE)
    변경이 완료 되면 
    {
        "id": 2,
        "userId": "TEST1",
        "todoContent": "Server 구성",
        "isCompleted": "Y"
    }
    변경 실패 또는 해당 id가 없다면
    {
        "id": -1,
        "userId": "TEST1",
        "todoContent": "Server 구성",
        "isCompleted": "Y"
    }

### 4. DELETE parameter

    ex) 1건 삭제 & 전체 삭제
        ../hifos-api/v1/user-todo/todo/{id}  (1건 삭제)
        ../hifos-api/v1/user-todo/todo/all   (전체 삭제)

### 5. DB 스키마 정보 확인

    http://localhost:9100/hifos-api/v1/h2-console