# HTTP Requests

## Welcome Endpoint

```shell
xh http://localhost:8080/SpringBootCrudService/person/welcome
```

## Person Endpoints

### createPerson

```shell
xh post http://localhost:8080/SpringBootCrudService/person firstname=Wmi lastname=Wenigerkind
```

### getPersonById
```shell
xh http://localhost:8080/SpringBootCrudService/person/1
```

### getAllPersons
```shell
xh http://localhost:8080/SpringBootCrudService/person
```

### updatePerson
```shell
xh put http://localhost:8080/SpringBootCrudService/person/1 firstname=Wim lastname=Wenigerkind
```

### deletePersonById
```shell
xh delete http://localhost:8080/SpringBootCrudService/person/1
```