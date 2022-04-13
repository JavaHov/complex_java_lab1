# complex_java_lab1
Här kommer jag lägga till instruktioner om endpoints och annat...

ENDPOINTS:

Get all students: [GET] localhost:8080/student-management-system/api/v1/students
Get student by id: [GET] .<same>../students/{id}
Get students by lastname: [GET] .../students/querylast
QUERY lastname: <the_name>

Get students by firstname: [GET] .../students/queryfirst
QUERY firstname: <the_name>

Create student: [POST] .../students
Json example:
{
"firstName": "James",
"lastName": "Dean",
"email": "james@apple.se",
"phoneNumber": "50495868"
}

Update student info: [PATCH] .../students/{id}
Json example:
{
"firstName": "Newname",
}
(Rest of data will be intact)

Replace student info: [PUT] .../students/{id}
Json example:
{
"id": "1",
"firstName": "Ingrid",
}
(Rest of data will be null or 0)

Delete student: [DELETE] .../students/{id}

Add subject to student : [PATCH] .../students/{studentId}/subject/{subjectId}

Get all teachers: [GET] .../teachers
Get teacher by id: [GET] .../teachers/{id}
Get teacher by firstname: [GET] .../teachers/teacherfirst
Query: firstname : <firstname>

Get teacher by lastname: [GET] .../teachers/teacherlast
Query: lastname : <lastname>

Create teacher: [POST] .../teachers
JSON:
{
"firstName": "Glenn",
"lastName": "Hysen"
}

Delete teacher: [DELETE] .../teachers/{id}

Get all subjects: [GET] .../subjects
Get subject by id: [GET] .../subjects/{id}
Find subject by name: [GET] .../subjects/subjectname
Query: name : <the_name(title)>

Create subject: [POST] .../subjects
JSON:
{
"title": "Filosofi"
}

Add student to subject: [POST] .../subjects/{subjectId}/student/{studentId}
Delete subject: [DELETE] .../subjects/{id}
