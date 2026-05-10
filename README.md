# Fitness Log

## Team
| Name           |   CWID    |
|:---------------|:---------:|
| Dianella Sy    | 884931890 |
| Saloni Joshi   | 885584714 |
| Siddharth Vasu | 885505578 |
| Vanessa Ragan  | 888419215 |

## Instructions

To start the app, use the following command to run the Docker containers

`docker compose up --build`

Then to stop the app, use the following command to stop the containers

`docker compose down`

## Demo Video
[Fitness Log Demo](https://youtu.be/zbFS4d22RK8?si=9kIS6NpvKxfQohlR)

## Endpoints

Registering a new user
![POST /api/auth/register](images/register.png)

Authenticating as an existing user
![POST /api/auth/login](images/login.png)

Viewing all workouts
![GET /api/workouts](images/all-workouts.png)

Creating a new workout
![POST /api/workouts](images/create.png)

Viewing a workout by id
![GET /api/workouts/{id}](images/workout-by-id.png)

Updating a workout
![PUT /api/workouts/{id}](images/update.png)

Deleting a workout
![DELETE /api/workouts/{id}](images/delete.png)
