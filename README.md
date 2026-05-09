# Fitness Log

## Team
| Name           |   CWID    |
|:---------------|:---------:|
| Dianella Sy    | 884931890 |
| Saloni Joshi   | 885584714 |
| Siddharth Vasu | 885505578 |
| Vanessa Ragan  | 888419215 |

## Instructions

First build the Docker image

`docker build -t fitnesslog:1.0 .`

Then use it to run a Docker container for the app

`docker run -d --name fitnesslog -p 8085:8085 -e SPRING_DATA_MONGODB_URI=mongodb://host.docker.internal
:27017/fitnesslog fitnesslog:1.0`

While it's running you can check the logs for debugging

`docker logs fitnesslog`

When finished, stop the container

`docker stop fitnesslog`

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
