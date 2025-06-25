package demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseEmployee {
     /*
     * [
    // {
    //     "id": 99,
    //     "email": "albertjuntak44@gmail.com",
    //     "password_hash": "2019427744ced02976c1ec9b7eeba833",
    //     "full_name": "Albert Simanjuntak",
    //     "department": "IT",
    //     "title": "QA",
    //     "create_at": "2025-05-20T13:23:56.839Z",
    //     "update_at": "2025-05-20T13:23:56.839Z"
    // }

    {
        "id": "1240",
        "email": "albertsimanjuntak001111@gmail.com",
        "full_name": "Albert Simanjuntak",
        "password": "@dmin123",
        "department": "Technology",
        "phone_number": "082264189134",
        "create_at": "2025-06-25T07:20:13.004Z",
        "update_at": "2025-06-25T07:20:13.004Z"
    }
]
     */

    @JsonProperty("id")
    public int id;

    @JsonProperty("email")
    public String email;
    
    @JsonProperty("full_name")
    public String fullName;

    @JsonProperty("password")
    public String password;

    @JsonProperty("department")
    public String department;

    @JsonProperty("phone_number")
    public String phone_number;

    // @JsonProperty("title")
    // public String title;

    @JsonProperty("create_at")
    public String createAt;

    @JsonProperty("update_at")
    public String updateAt;

    public ResponseEmployee() {};
}
