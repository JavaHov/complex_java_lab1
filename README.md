# complex_java_lab1
Här kommer jag lägga till instruktioner om endpoints och annat...

ENDPOINTS:

Get all students: [GET] localhost:8080/student-management-system/api/v1/students

Get student by id: [GET] localhost:8080/student-management-system/api/v1/students/{id}

Get students by lastname: [GET] localhost:8080/student-management-system/api/v1/students/querylast
QUERY lastname: <the_name>

Get students by firstname: [GET] localhost:8080/student-management-system/api/v1/students/queryfirst
QUERY firstname: <the_name>

Create student: [POST] localhost:8080/student-management-system/api/v1/students
Json example:
{
"firstName": "James",
"lastName": "Dean",
"email": "james@apple.se",
"phoneNumber": "50495868"
}

Update student info: [PATCH] localhost:8080/student-management-system/api/v1/students/{id}
Json example:
{
"firstName": "Newname",
}
(Rest of data will be intact)

Replace student info: [PUT] localhost:8080/student-management-system/api/v1/students/{id}
Json example:
{
"id": "1",
"firstName": "Ingrid",
}
(Rest of data will be null or 0)

Delete student: [DELETE] localhost:8080/student-management-system/api/v1/students/{id}