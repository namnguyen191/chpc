# Canada Housing Prices Comparator Project

## EECS 3311 - Winter 2023

### Descriptions:

This is an application that helps the user find the most affordable (in relative terms) region in Canada to buy a house

### Team Alpha members:

- Menghua Jiang - 219143676
- Noel Chong - 216931412
- Nam Vu Hoang Nguyen - 217162082
- Xinyi Pang - 218616003

### For developing:

#### Running a Postgres DB on local using Docker:

At the root of the project runs the following command:

```
docker-compose -f docker/docker-compose-dev.yml up
```

Once no longer needed, runs the following command to remove Postgres:

```
docker-compose -f docker/docker-compose-dev.yml down
```
