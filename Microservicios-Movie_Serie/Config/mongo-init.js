db.createUser(
    {
        user: "user",
        pwd: "dhbackend",
        roles: [
            {
                role: "readWrite",
                db: "backend_specialization"
            }
        ]
    }
);