# HTTP Requests

Endpoints also available at `https://person-web-service.example.wimwenigerkind.com/`.

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

### patchPerson
```shell
xh patch http://localhost:8080/SpringBootCrudService/person/1 firstname=Wim
```

### deletePersonById
```shell
xh delete http://localhost:8080/SpringBootCrudService/person/1
```

### searchPersons
```shell
xh "http://localhost:8080/SpringBootCrudService/person/search?firstname=Wim"
```

```shell
xh "http://localhost:8080/SpringBootCrudService/person/search?lastname=Wenigerkind"
```

```shell
xh "http://localhost:8080/SpringBootCrudService/person/search?firstname=Wim&lastname=Wenigerkind"
```

# Insert Test Data
```shell
xh post http://localhost:8080/SpringBootCrudService/person firstname=Alice lastname=Smith
xh post http://localhost:8080/SpringBootCrudService/person firstname=Bob lastname=Johnson
xh post http://localhost:8080/SpringBootCrudService/person firstname=Charlie lastname=Williams
xh post http://localhost:8080/SpringBootCrudService/person firstname=David lastname=Brown
xh post http://localhost:8080/SpringBootCrudService/person firstname=Eve lastname=Jones
xh post http://localhost:8080/SpringBootCrudService/person firstname=Frank lastname=Miller
xh post http://localhost:8080/SpringBootCrudService/person firstname=Grace lastname=Davis
xh post http://localhost:8080/SpringBootCrudService/person firstname=Helen lastname=Garcia
xh post http://localhost:8080/SpringBootCrudService/person firstname=Ian lastname=Martinez
xh post http://localhost:8080/SpringBootCrudService/person firstname=Jane lastname=Rodriguez
```