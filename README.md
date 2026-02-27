# van-spring-boot-demo

<p>
  <a href="https://github.com/vanengine/van"><img src="https://img.shields.io/badge/van-template%20engine-steelblue" alt="Van" /></a>
  <a href="LICENSE"><img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="License" /></a>
</p>

A minimal Spring Boot application demonstrating [van-spring-boot-starter](https://github.com/van-java/van-spring-boot-starter) integration.

<p>
  <a href="README.md">English</a> · <a href="docs/i18n/zh-CN/README.md">简体中文</a>
</p>

## Run

```bash
./gradlew bootRun
```

Open [http://localhost:8080](http://localhost:8080).

## Endpoints

| Endpoint | Type | Description |
|---|---|---|
| `GET /` | MVC View | Renders `themes/default/pages/index.van` with Spring Model |
| `GET /api/render` | REST API | Compiles an inline template via `VanEngine.compileLiteral()` |

## Project Structure

```
src/main/
├── java/dev/vanengine/demo/
│   ├── DemoApplication.java      # Spring Boot entry point
│   ├── IndexController.java      # MVC controller → view name "index"
│   └── ApiController.java        # REST controller → inline template
└── resources/
    ├── application.yml            # server.port: 8080
    └── themes/default/pages/
        └── index.van              # Template with {{ title }} and {{ message }}
```

## How it works

### MVC View (IndexController)

```java
@GetMapping("/")
public String index(Model model) {
    model.addAttribute("title", "Van Demo");
    model.addAttribute("message", "Hello from Van + Spring Boot!");
    return "index";  // resolves to themes/default/pages/index.van
}
```

The `VanViewResolver` auto-configured by the starter compiles `index.van` and evaluates `{{ expr }}` placeholders with model data.

### REST API (ApiController)

```java
@GetMapping("/api/render")
public String render() throws IOException {
    return engine.compileLiteral(template,
            Map.of("title", "Van API Demo", "message", "Compiled via VanEngine"));
}
```

Uses `VanEngine` directly — compile an inline template and evaluate in one step.

## Related

- [**Van**](https://github.com/vanengine/van) — Core template engine (Rust / WASM)
- [**van-java-core**](https://github.com/van-java/van-java-core) — Pure Java SDK
- [**van-spring-boot-starter**](https://github.com/van-java/van-spring-boot-starter) — Spring Boot integration

## License

[MIT](LICENSE)
